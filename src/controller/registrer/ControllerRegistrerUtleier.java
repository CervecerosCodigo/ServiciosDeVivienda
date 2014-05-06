package controller.registrer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import lib.Melding;
import lib.RegexTester;
import model.Person;
import model.Utleier;
import view.registrer.PersonRegVindu;

/**
 * Klassen brukes kun til registrering og redigering av utleiere.
 * File: ControllerRegistrerUtleier.java Package: controller.registrer Project:
 * ServiciosDeVivienda May 6, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class ControllerRegistrerUtleier extends AbstractControllerRegister {

    private String fnavn, enavn, epost, telnr;
    private boolean erRepresentant;
    private String representantNavn;

    private final boolean erNyregistrering;

    private PersonRegVindu vindu;

    /**
     * Registrer en NY utleier.
     *
     * @param personRegister
     */
    public ControllerRegistrerUtleier(HashSet<Person> personRegister) {
        super(personRegister);
        vindu = new PersonRegVindu(200, 200, "Ny utleier");
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

        boolean fnavnOK = RegexTester.testNavn(fnavn);
        boolean enavnOK = RegexTester.testNavn(enavn);
        boolean epostOK = RegexTester.testEpost(epost);
        boolean telnrOK = RegexTester.testTelNummerNorsk(telnr);
        boolean representantNavnOK = true;
        if (erRepresentant) {
            representantNavnOK = RegexTester.testKunBokstaverEllerTall(representantNavn);
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
                Melding.visMelding("Ny utleier", "Utleier:\n" + fnavn + " " + enavn + "\nEr registrert");
                vindu.dispose();
            } else {
                Melding.visMelding("Ny utleier", "Feil ved registrering");
            }
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
                    Melding.visMelding("Oppdater utleier", "Oppdatering vellykket.");
                    vindu.dispose();
                } else {
                    Melding.visMelding("Oppdater utleier", "Oppdatering misslykkedes ved registrering.");
                }
            } else {
                Melding.visMelding("Oppdater utleier", "Oppdatering misslykkedes ved sletting.");
            }
        }
    }

    class KnappeLytter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(vindu.getLagreButton())) {
                if (erNyregistrering) {
                    registrerNyUtleier();
                }else{
                    registrerOppdatering();
                }
            } else if (e.getSource().equals(vindu.getAvbrytButton())) {
                vindu.dispose();
            }
        }

    }

}
