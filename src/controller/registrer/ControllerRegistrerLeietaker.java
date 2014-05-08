package controller.registrer;

import controller.VisMeldingInterface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import lib.Arbeidsforhold;
import lib.Melding;
import lib.RegexTester;
import lib.Sivilstatus;
import model.Annonse;
import model.Leietaker;
import model.Person;
import model.Soknad;
import view.registrer.PersonRegVindu;

/**
 * Klassen brukes til registrering og redigering av leietakere. Forskjellen fra
 * registrering av utleiere er at denne klassen registrer og sender inn en
 * forespørsel i tilleg. File: ControllerRegistrerLeietaker.java Package:
 * controller.registrer Project: ServiciosDeVivienda May 6, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class ControllerRegistrerLeietaker extends AbstractControllerRegister implements VisMeldingInterface{

    //Genenerelle datafelt
    private String fnavn, enavn, epost, telnr;
    //Leietaker spesifikke datafelt
    private int fodelsAr, antPersHushold;
    private Sivilstatus sivilStatus;
    private Arbeidsforhold arbeidsForhold;
    private String yrke, soknadsTekst;

    private final boolean erNyregistrering;

    private PersonRegVindu vindu;

    //Register og felt for innsedning av annonse
    private HashSet<Annonse> annonseRegister;
    private HashSet<Soknad> soknadsRegister;
    private Annonse annonse;

    /**
     * Registrer en ny leietaker. Vindu sender inn en ny forespørsel til
     * megleren.
     *
     * @param personRegister
     */
    public ControllerRegistrerLeietaker(HashSet<Person> personRegister, HashSet<Annonse> annonseRegister, HashSet<Soknad> soknadsRegister, Annonse annonse) {
        super(personRegister);
        this.annonseRegister = annonseRegister;
        this.soknadsRegister = soknadsRegister;
        this.annonse = annonse;

        vindu = new PersonRegVindu(450, 400, "Send forespørsel");
        erNyregistrering = true;
        skjulRepresentantFelter();

        vindu.addPersonPanelListener(new KnappeLytter());
    }

    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
    //--//                                                              //--//
    //--//                  GENERELLE METODER                           //--//
    //--//                                                              //--//
    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
    private void skjulRepresentantFelter() {
        vindu.getErRepresentantForLabel().setVisible(false);
        vindu.getErRepresentantLabel().setVisible(false);
        vindu.getErRepresentantCheckBox().setVisible(false);
        vindu.getErRepresentantForField().setVisible(false);
    }

    private void getDataFraGUI() {
        fnavn = vindu.getFornavnField().getText();
        enavn = vindu.getEtternavnField().getText();
        epost = vindu.getEpostField().getText();
        telnr = vindu.getTelefonField().getText();
        fodelsAr = (int) vindu.getFodselsArCombo().getSelectedItem();
        antPersHushold = (int) vindu.getAntPersonerHusholdCombo().getSelectedItem();
        sivilStatus = (Sivilstatus) vindu.getSivilStatusCombo().getSelectedItem();
        arbeidsForhold = (Arbeidsforhold) vindu.getArbeidsForholdCombo().getSelectedItem();
        yrke = vindu.getYrkeField().getText();
        soknadsTekst = vindu.getSoknadsTextArea().getText();
    }

    /**
     * Kontrollerer data fra GUI mot regex.
     *
     * @return
     */
    private boolean kontrollerData() {
        getDataFraGUI();

        boolean fnavnOK = RegexTester.testNavn(fnavn);
        boolean enavnOK = RegexTester.testNavn(enavn);
        boolean epostOK = RegexTester.testEpost(epost);
        boolean telnrOK = RegexTester.testTelNummerNorsk(telnr);
        boolean yrkeOK = RegexTester.testKunBokstaver(yrke);

        return fnavnOK && enavnOK && epostOK && telnrOK && yrkeOK;
    }

    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
    //--//                                                              //--//
    //--//                  REGISTRERING                                //--//
    //--//                                                              //--//
    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
    private void registrerNyLeietaker() {
        if (kontrollerData()) {
            
            Person leietaker = new Leietaker(fnavn, enavn, epost, telnr, fodelsAr, antPersHushold, sivilStatus, yrke, arbeidsForhold, soknadsTekst, fnavn, enavn, epost, telnr);
            
            Soknad soknad = new Soknad(annonse, leietaker);
            
            if (super.registrerObjekt((Leietaker) leietaker) && soknadsRegister.add(soknad)) {
                visMelding("Søknad", "" + fnavn + "!\nDin søknad er sendt.");
                vindu.dispose();
            } else {
                visMelding("Søknad", "Feil ved sendning av søknad.");
            }
        }
    }

    @Override
    public void visMelding(String overskrift, String melding) {
        vindu.visMelding(overskrift, melding);
    }

    class KnappeLytter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(vindu.getLagreButton())) {
                if(erNyregistrering){
                    registrerNyLeietaker();
                }
            } else if (e.getSource().equals(vindu.getAvbrytButton())) {
                vindu.dispose();
            }
        }

    }
}
