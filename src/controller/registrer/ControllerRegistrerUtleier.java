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
    private UtleierRegVindu uRegVindu;
    private HashSet<Person> personRegister;
    private boolean erNyregistrering;

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
        erNyregistrering = true;
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
        erNyregistrering = false;
        this.uRegVindu = new UtleierRegVindu("Endre opplysninger for utleier");
        visDataFraRegister(person);
        //Legger til en lytter
        uRegVindu.addUtleierPanelListener(new KnappLytter());
    }

    /**
     * Fylder opp alle texfields i gui med opplysninger i objektet.
     *
     * @param person
     */
    private void visDataFraRegister(Person person) {
        uRegVindu.getFornavnField().setText(person.getFornavn());
        uRegVindu.getEtternavnField().setText(person.getEtternavn());
        uRegVindu.getEpostField().setText(person.getEpost());
        uRegVindu.getTelefonField().setText(person.getTelefon());
        if (person instanceof Utleier) {
            if (((Utleier) person).isErRepresentant()) {
                uRegVindu.getErRepresentantCheckBox().setSelected(((Utleier) person).isErRepresentant());
                uRegVindu.getErRepresentatForField().setText(((Utleier) person).getErRepresentantFor());
            }
        }
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
     *
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
     * Brukes da man skal endre opplysninger til en person som alerede er
     * registrert i registeret. Før vi sletter personen foretar vi en kontroll
     * på data fra gui oppført av bruker. Dersom den er ok så sletter vi
     * personen i registeret.
     *
     * @param person
     * @return
     */
    private boolean kontrollerDataOgOppdater(Person person) {
        getDataGUI();
        if (kontrollerHentetData()) {
            //Sletter objektet fra registeret
            if (super.slettObjekt(person)) {

                //Oppdaterer datafelt i objektet
                person.setFornavn(fnavn);
                person.setEtternavn(enavn);
                person.setEpost(epost);
                person.setTelefon(telnr);
                if (uRegVindu.getErRepresentantCheckBox().isSelected()) {
                    if (person instanceof Utleier) {
                        ((Utleier) person).setErRepresentant(true);
                        ((Utleier) person).setErRepresentantFor(erRepresentantFor);
                    }
                }
            }
            //Skriver objektet tilbake til set
            if (super.registrerObjekt(person)) {
                Melding.visMelding("Oppdatering av utleier", "Opplysninger er oppdatert");
                return true;
            }
        }
        Melding.visMelding("Oppdatering av utleier", "Har ikke oppdatert");
        return false;
    }

    /**
     * Registrere en ny utleier
     */
    private void registrerNyUtleier() {

        getDataGUI();

        if (kontrollerHentetData()) {
            Person utleier = new Utleier(fnavn, enavn, epost, telnr, erRepresentant, erRepresentantFor);
//            super.set.add(utleier);
            super.registrerObjekt((Utleier) utleier);
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
                if (erNyregistrering) {
                    registrerNyUtleier();
                } else {
                    //Her skal vi lagre en oppdatering
                    kontrollerDataOgOppdater((Utleier) obj);
                }
            }
        }

    }

}
