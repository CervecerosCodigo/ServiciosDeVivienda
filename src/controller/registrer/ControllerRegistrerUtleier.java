package controller.registrer;

import controller.TabellFireDataChangedInterface;
import controller.VisMeldingInterface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import lib.Ikoner;
import lib.Melding;
import lib.RegexTester;
import model.Person;
import model.Utleier;
import view.registrer.PersonRegVindu;

/**
 * Klassen brukes kun til registrering og redigering av utleiere.
 */
public class ControllerRegistrerUtleier extends AbstractControllerRegister implements VisMeldingInterface {

    private String fnavn, enavn, epost, telnr;
    private boolean erRepresentant;
    private String representantNavn;

    private final boolean erNyregistrering;

    private PersonRegVindu vindu;
    TabellFireDataChangedInterface tabellOppdateringLytter;

    /**
     * Registrer en NY utleier.
     *
     * @param personRegister
     */
    public ControllerRegistrerUtleier(HashSet<Person> personRegister) {
        super(personRegister);
        vindu = new PersonRegVindu(200, 200, "Ny utleier");

        vindu.setIconImage(Ikoner.NY_UTLEIER.getImage());

        erNyregistrering = true;
        skjulLeietakerFelter();

        vindu.addPersonPanelListener(new KnappeLytter());
    }

    /**
     * REDIGERING av eksisterende utleier.
     *
     * @param personRegister
     * @param utleier
     */
    public ControllerRegistrerUtleier(HashSet<Person> personRegister, Utleier utleier) {
        super(personRegister, utleier);
        vindu = new PersonRegVindu(200, 200, "Rediger utleier");

        vindu.setIconImage(Ikoner.EDIT.getImage());

        erNyregistrering = false;
        skjulLeietakerFelter();

        vindu.addPersonPanelListener(new KnappeLytter());
        visDataiGUI(utleier);
    }

    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
    //--//                                                              //--//
    //--//                  GENERELLE METODER                           //--//
    //--//                                                              //--//
    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
    /**
     * Skjuler alle felt for leietaker da de ikke brukes.
     */
    private void skjulLeietakerFelter() {
        vindu.getFodselsArLabel().setVisible(false);
        vindu.getAntPersonerHusholdLabel().setVisible(false);
        vindu.getSivilStatusLabel().setVisible(false);
        vindu.getArbeidsForholdLabel().setVisible(false);
        vindu.getYrkeLabel().setVisible(false);
        vindu.getSoknadsTekstLabel().setVisible(false);

        vindu.getFodselsArCombo().setVisible(false);
        vindu.getAntPersonerHusholdCombo().setVisible(false);
        vindu.getSivilStatusCombo().setVisible(false);
        vindu.getArbeidsForholdCombo().setVisible(false);
        vindu.getYrkeField().setVisible(false);
        vindu.getSoknadsScroll().setVisible(false);
    }

    /**
     * Henter inn data for utleier fra gui.
     */
    private void getDataFraGUI() {
        fnavn = vindu.getFornavnField().getText();
        enavn = vindu.getEtternavnField().getText();
        epost = vindu.getEpostField().getText();
        telnr = vindu.getTelefonField().getText();
        erRepresentant = vindu.getErRepresentantCheckBox().isSelected();

        if (erRepresentant) {
            representantNavn = vindu.getErRepresentantForField().getText();
        }
    }

    /**
     * Kontrollerer hentet data fra GUi mot regex.
     *
     * @return
     */
    private boolean kontrollerData() {
        getDataFraGUI();

        boolean fnavnOK = RegexTester.testKunBokstaverBindestrekFStorbokstav(fnavn);
        boolean enavnOK = RegexTester.testKunBokstaverBindestrekFStorbokstav(enavn);
        boolean epostOK = RegexTester.testEpost(epost);
        boolean telnrOK = RegexTester.testTelNummerNorsk(telnr);
        boolean representantNavnOK = true;

        if (erRepresentant) {
            representantNavnOK = RegexTester.testGateadresseEnkel(representantNavn);
        }

        return fnavnOK && enavnOK && epostOK && telnrOK && representantNavnOK;
    }

    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
    //--//                                                              //--//
    //--//                  REGISTRERING                                //--//
    //--//                                                              //--//
    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
    private void registrerNyUtleier() {
        if (kontrollerData()) {
            Person utleier = new Utleier(fnavn, enavn, epost, telnr, erRepresentant, representantNavn);

            if (super.registrerObjekt((Utleier) utleier)) {
                visMelding("Ny utleier", "Utleier:\n" + fnavn + " " + enavn + "\nEr registrert");
                vindu.dispose();
            } else {
                visMelding("Ny utleier", "Feil ved registrering");
            }
        } else {
            visMelding("Feil", "Feil i skjema");
        }
    }

    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
    //--//                                                              //--//
    //--//                          ENDRING                             //--//
    //--//                                                              //--//
    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
    private void visDataiGUI(Utleier utleier) {
        vindu.getFornavnField().setText(utleier.getFornavn());
        vindu.getEtternavnField().setText(utleier.getEtternavn());
        vindu.getEpostField().setText(utleier.getEpost());
        vindu.getTelefonField().setText(utleier.getTelefon());

        if (utleier.isErRepresentant()) {
            vindu.getErRepresentantCheckBox().setSelected(true);
            vindu.getErRepresentantForField().setEnabled(true);
            vindu.getErRepresentantForField().setText(utleier.getErRepresentantFor());
        }
    }

    private void registrerOppdatering() {
        if (kontrollerData()) {
            Utleier utleier = (Utleier) super.obj;
            //Data fra GUI er ok, slett fra register hvis mulig
            if (super.slettObjekt(utleier)) {

                //Oppdater datafelt i objektet
                utleier.setFornavn(fnavn);
                utleier.setEtternavn(enavn);
                utleier.setEpost(epost);
                utleier.setTelefon(telnr);
                if (erRepresentant) {
                    utleier.setErRepresentant(true);
                    utleier.setErRepresentantFor(representantNavn);
                }

                if (super.registrerObjekt((Utleier) utleier)) {
                    visMelding("Oppdater utleier", "Oppdatering vellykket.");
                    vindu.dispose();
                } else {
                    visMelding("Oppdater utleier", "Oppdatering misslykkedes ved registrering.");
                }
            } else {
                visMelding("Oppdater utleier", "Oppdatering misslykkedes ved sletting.");
            }
        } else {
            visMelding("Feil", "Feil i skjema");
        }
    }

    public void settTabellOppdateringsLytter(TabellFireDataChangedInterface lytter) {
        tabellOppdateringLytter = lytter;
    }

    @Override
    public void visMelding(String overskrift, String melding) {
        vindu.visMelding(overskrift, melding);
    }

    class KnappeLytter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(vindu.getLagreButton())) {
                if (erNyregistrering) {
                    registrerNyUtleier();
                    if (tabellOppdateringLytter != null) {
                        tabellOppdateringLytter.oppdaterTabellEtterEndring();
                    }
                } else {
                    registrerOppdatering();
                    if (tabellOppdateringLytter != null) {
                        tabellOppdateringLytter.oppdaterTabellEtterEndring();
                    }
                }
            } else if (e.getSource().equals(vindu.getAvbrytButton())) {
                vindu.dispose();
            }
        }
    }
}
