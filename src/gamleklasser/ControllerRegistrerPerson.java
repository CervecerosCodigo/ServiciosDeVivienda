package controller.registrer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import lib.Melding;
import lib.RegexTester;
import model.Annonse;
import model.Leietaker;
import model.Person;
import model.Soknad;
import model.Utleier;
import view.registrer.PersonRegVindu;

/**
 *
 * File: ControllerUtleierFrame.java Package: controller.registrer Project:
 * ServiciosDeVivienda Apr 27, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class ControllerRegistrerPerson extends AbstractControllerRegister {

    //Generelle datafelt
    private PersonRegVindu vindu;
    private Person person;
    private Annonse annonse;
    private HashSet<Person> personRegister;
    private HashSet<Annonse> annonseRegister;
    private HashSet<Soknad> soknadRegister;
    private boolean erNyregistrering;

    //Datafelt for generelle opplysninger
    private String fnavn, enavn, epost, telnr;

    //Datafelt for Utleier
    private String erRepresentantFor;
    private boolean erRepresentant;
    private boolean erLeietaker;

    //Datafelt for Leietaker
    private int fodelsAr, antPersHushold;
    private String sivilStatus, arbeidsForhold, yrke, soknadsTekst;

    /**
     * Konstruktøren blir brukt i samband med registrering av en ny bolig
     * og/eller en ny utleier. Eventuelle felt for en leietaker bil skjult.
     *
     * @param personRegister
     */
    public ControllerRegistrerPerson(HashSet<Person> personRegister) {
        super(personRegister);
        erNyregistrering = true;
        this.vindu = new PersonRegVindu(150, 200, "Registrer utleier");
//        this.personRegister = personRegister;//Dette var gamle måten å gjøre det på før vi hadde en superklasse.

        //Legger til lytter
        vindu.addPersonPanelListener(new KnappeLytter());

        skjulLeietakerFelter();
    }

    /**
     * Konstruktøren blir brukt ved endring av en eksisterende Person.
     *
     * @param personRegister
     * @param person
     */
    public ControllerRegistrerPerson(HashSet<Person> personRegister, Person person) {
        super(personRegister, person);
        this.person = person;
        erNyregistrering = false;
        if (erLeietaker()) {
            this.vindu = new PersonRegVindu(450, 400, "Endre opplysninger for Leietaker");
            skjulRepresentantFelter();
        } else {
            this.vindu = new PersonRegVindu(150, 200, "Endre opplysninger for utleier");
            skjulLeietakerFelter();
        }
        visDataFraRegister(person);
        //Legger til en lytter
        vindu.addPersonPanelListener(new KnappeLytter());
    }

    /**
     * Konstruktør for registrering av ny leietaker.
     *
     * @param personRegister
     * @param annonseRegister
     * @param annonse
     */
    public ControllerRegistrerPerson(HashSet<Person> personRegister, HashSet<Annonse> annonseRegister, HashSet<Soknad> soknadRegister, Annonse annonse) {
        super(personRegister);
        this.personRegister = personRegister;
        this.annonseRegister = annonseRegister;
        this.soknadRegister = soknadRegister;
        this.annonse = annonse;
        this.vindu = new PersonRegVindu(450, 400, "Registrer ny person");
        erNyregistrering = true;
        erLeietaker = true;
        vindu.addPersonPanelListener(new KnappeLytter());
        skjulRepresentantFelter();
    }

    /**
     * Kalles opp i de tilfellene der personobjektet ikke er Utleier.
     */
    private void skjulRepresentantFelter() {
        vindu.getErRepresentantForLabel().setVisible(false);
        vindu.getErRepresentantLabel().setVisible(false);
        vindu.getErRepresentantCheckBox().setVisible(false);
        vindu.getErRepresentantForField().setVisible(false);
    }

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
     * Returnerer true om personen er Leietaker.
     *
     * @return
     */
    private boolean erLeietaker() {
        return erLeietaker || person instanceof Leietaker;
    }

    /**
     * Fylder opp alle texfields i gui med opplysninger i objektet.
     *
     * @param person
     */
    private void visDataFraRegister(Person person) {
        vindu.getFornavnField().setText(person.getFornavn());
        vindu.getEtternavnField().setText(person.getEtternavn());
        vindu.getEpostField().setText(person.getEpost());
        vindu.getTelefonField().setText(person.getTelefon());
        if (person instanceof Utleier) {
            if (((Utleier) person).isErRepresentant()) {
                vindu.getErRepresentantCheckBox().setSelected(((Utleier) person).isErRepresentant());
                vindu.getErRepresentantForField().setEnabled(true);
                vindu.getErRepresentantForField().setText(((Utleier) person).getErRepresentantFor());
            }
        }
        if (person instanceof Leietaker) {
            vindu.getErRepresentantCheckBox().setEnabled(false);
            vindu.getErRepresentantForField().setEnabled(false);
        }
    }

    /**
     * Setter alle datafelt i klassen med data fra GUI.
     */
    private void getDataGUI() {
        fnavn = vindu.getFornavnField().getText();
        enavn = vindu.getEtternavnField().getText();
        epost = vindu.getEpostField().getText();
        telnr = vindu.getTelefonField().getText();
        if (person instanceof Utleier) {
            erRepresentant = vindu.getErRepresentantCheckBox().isSelected();
            erRepresentantFor = "";
            if (erRepresentant) {
                erRepresentantFor = vindu.getErRepresentantForField().getText();
            }
        } else if (person instanceof Leietaker) {
            fodelsAr = (int) vindu.getFodselsArCombo().getSelectedItem();
            antPersHushold = (int) vindu.getAntPersonerHusholdCombo().getSelectedItem();
            sivilStatus = (String) vindu.getSivilStatusCombo().getSelectedItem();
            arbeidsForhold = (String) vindu.getArbeidsForholdCombo().getSelectedItem();
            yrke = vindu.getYrkeField().getText();
            soknadsTekst = vindu.getSoknadsTextArea().getText();
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

        boolean erRepresentantForOK = true;//Denne må være true som default for de tilfelden da utleieren ikke er representant. Dersom utleieren er representat blir letet testet med regex.

        if (erLeietaker()) {
            boolean yrkeOK = RegexTester.testKunBokstaverEllerTall(yrke);
            return fnavnOK && enavnOK && epostOK && telnrOK && yrkeOK;
        }
        if (erRepresentant) {
            erRepresentantForOK = RegexTester.testKunBokstaverEllerTall(erRepresentantFor);
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
                if (person instanceof Utleier) {
                    if (vindu.getErRepresentantCheckBox().isSelected()) {
                        ((Utleier) person).setErRepresentant(true);
                        ((Utleier) person).setErRepresentantFor(erRepresentantFor);
                    }
                }
            }
            //Skriver objektet tilbake til set
            if (super.registrerObjekt(person)) {
                Melding.visMelding("Oppdatering av person", "Opplysninger er oppdatert");
                return true;
            }
        }
        Melding.visMelding("Oppdatering av person", "Har ikke oppdatert");
        return false;
    }

    /**
     * Registrere en ny utleier
     */
    private void registrerNyPerson() {

        getDataGUI();
        StringBuilder melding = new StringBuilder();

        if (kontrollerHentetData()) {
            Person person = null;
            if (erLeietaker()) {
                person = new Leietaker(fnavn, enavn, epost, telnr);
                Soknad soknad = new Soknad(annonse, person);
                //super.registrerObjekt((Leietaker) person);
                if (soknadRegister.add(soknad)) {
                    melding.append("Din søknad er nå registrert");
                }
            } else {
                person = new Utleier(fnavn, enavn, epost, telnr, erRepresentant, erRepresentantFor);
                if (super.registrerObjekt((Utleier) person)) {
                    melding.append("En ny person er registrert:\n").append(fnavn).append(" ").append(enavn).append("\n").append(epost);
                }
            }

            if (erLeietaker()) {
                Melding.visMelding("Ny leietaker", melding.toString());
            } else {
                Melding.visMelding("Ny utleier", melding.toString());
            }
        } else {
            Melding.visMelding("Feil", "Feil format i et eller flere felt.");
        }
    }

    class KnappeLytter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(vindu.getLagreButton())) {
                if (erNyregistrering) {
                    registrerNyPerson();
                    vindu.dispose();
                } else {
                    //Her skal vi lagre en oppdatering
                    if (erLeietaker()) {
                        kontrollerDataOgOppdater((Leietaker) obj);
                        vindu.dispose();
                    } else {
                        kontrollerDataOgOppdater((Utleier) obj);
                        vindu.dispose();
                    }
                }//End else
            }//End if
        }//End actionPerformed

    }

}