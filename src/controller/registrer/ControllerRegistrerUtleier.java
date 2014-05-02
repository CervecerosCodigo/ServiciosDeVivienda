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
public class ControllerRegistrerUtleier {

    UtleierRegVindu vindu;
    HashSet<Person> personRegister;

    public ControllerRegistrerUtleier(UtleierRegVindu vindu, HashSet<Person> personRegister) {
        this.vindu = vindu;
        this.personRegister = personRegister;

        //Legger til lytter
        vindu.addUtleierPanelListener(new KnappLytter());
    }

    private void registrerUtleier() {
        String fnavn = vindu.getFornavnField().getText();
        String enavn = vindu.getEtternavnField().getText();
        String epost = vindu.getEpostField().getText();
        String telnr = vindu.getTelefonField().getText();
        boolean erRepresentant = vindu.getErRepresentantCheckBox().isSelected();
        String erRepresentantFor = "";
        if (erRepresentant) {
            erRepresentantFor = vindu.getErRepresentatForField().getText();
        }

        boolean fnavnOK = RegexTester.testNavn(fnavn);
        boolean enavnOK = RegexTester.testNavn(enavn);
        boolean epostOK = RegexTester.testEpost(epost);
        boolean telnrOK = RegexTester.testTelNummerNorsk(telnr);
        boolean erRepresentantForOK = true;
        if (erRepresentant) {
            erRepresentantForOK = RegexTester.testNavn(erRepresentantFor);
        }

        if (fnavnOK && enavnOK && epostOK && telnrOK && erRepresentantForOK) {
            Person utleier = new Utleier(fnavn, enavn, epost, telnr, erRepresentant, erRepresentantFor);
            personRegister.add(utleier);
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
            if (e.getSource().equals(vindu.getLagreButton())) {
//                Melding.visMelding("Controller utleier frame", "Lagre knapp trukket");
                registrerUtleier();
            }
        }

    }

}
