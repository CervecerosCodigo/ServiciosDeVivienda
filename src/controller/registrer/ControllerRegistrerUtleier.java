package controller.registrer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import lib.Melding;
import lib.RegexTester;
import model.Person;
import model.Utleier;
import view.registrer.UtleierRegVindu;

/**
 *
 * File: ControllerUtleierFrame.java Package: controller.registrer Project:
 * ServiciosDeVivienda Apr 27, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class ControllerRegistrerUtleier extends AbstractControllerRegister {

    //Generelle datafelt
    UtleierRegVindu uRegVindu;
    HashSet<Person> personRegister;

    //Datafelt for personopplysninger
    private String fnavn, enavn, epost, telnr, erRepresentantFor;
    private boolean erRepresentant;

    /**
     * Konstruktøren blir brukt i samband med registrering av en ny bolig.
     *
     * @param personRegister
     */
    public ControllerRegistrerUtleier(HashSet<Person> personRegister) {
        super(personRegister);
        this.uRegVindu = new UtleierRegVindu("Registrer utleier");
//        this.personRegister = personRegister;//Dette var gamle måten å gjøre det på før vi hadde en superklasse.

        //Legger til lytter
        uRegVindu.addUtleierPanelListener(new KnappLytter());
    }

    /**
     * Konstruktøren blir brukt ved endring av en eksisterende utleier.
     *
     * @param personRegister
     * @param person
     */
    public ControllerRegistrerUtleier(HashSet<Person> personRegister, Person person) {
        super(personRegister, person);
        this.uRegVindu = new UtleierRegVindu("Endre opplysninger for utleier");
        //Legger til en lytter
        uRegVindu.addUtleierPanelListener(new KnappLytter());
    }

    /**
     * Setter alle datafelt i klassen med data fra GUI.
     */
    private void getDataGUI() {
        fnavn = uRegVindu.getFornavnField().getText();
        enavn = uRegVindu.getEtternavnField().getText();
        epost = uRegVindu.getEpostField().getText();
        telnr = uRegVindu.getTelefonField().getText();
        erRepresentant = uRegVindu.getErRepresentantCheckBox().isSelected();
        erRepresentantFor = "";
        if (erRepresentant) {
            erRepresentantFor = uRegVindu.getErRepresentatForField().getText();
        }
    }

    /**
     * Kontrollerer hentet data fra bruker med regex 
     * @return 
     */
    private boolean kontrollerHentetData() {
        boolean fnavnOK = RegexTester.testNavn(fnavn);
        boolean enavnOK = RegexTester.testNavn(enavn);
        boolean epostOK = RegexTester.testEpost(epost);
        boolean telnrOK = RegexTester.testTelNummerNorsk(telnr);
        boolean erRepresentantForOK = true;
        if (erRepresentant) {
            erRepresentantForOK = RegexTester.testNavn(erRepresentantFor);
        }

        return fnavnOK && enavnOK && epostOK && telnrOK && erRepresentantForOK;
    }

    /**
     * Registrere en ny utleier
     */
    private void registrerUtleier() {

        getDataGUI();

//        boolean fnavnOK = RegexTester.testNavn(fnavn);
//        boolean enavnOK = RegexTester.testNavn(enavn);
//        boolean epostOK = RegexTester.testEpost(epost);
//        boolean telnrOK = RegexTester.testTelNummerNorsk(telnr);
//        boolean erRepresentantForOK = true;
//        if (erRepresentant) {
//            erRepresentantForOK = RegexTester.testNavn(erRepresentantFor);
//        }

        if (kontrollerHentetData()) {//Sluttet her
            Person utleier = new Utleier(fnavn, enavn, epost, telnr, erRepresentant, erRepresentantFor);
            super.set.add(utleier);
            StringBuilder melding = new StringBuilder();
            melding.append("En ny person er registrert:\n").append(fnavn).append(" ").append(enavn).append("\n").append(epost);
            Melding.visMelding("Ny utleier", melding.toString());
        } else {
            Melding.visMelding("Feil", "Feil format i et eller flere felt.");
        }
    }

    class KnappLytter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(uRegVindu.getLagreButton())) {
//                Melding.visMelding("Controller utleier frame", "Lagre knapp trukket");
                registrerUtleier();
            }
        }

    }

}
