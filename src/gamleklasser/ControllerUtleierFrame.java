package controller.registrer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import lib.Melding;
import lib.RegexTester;
import model.Person;
import model.Utleier;
import view.CustomJTextField;
import view.registrer.UtleierFrame;
import view.registrer.UtleierRegisterPanel;

/**
 *
 * File: ControllerUtleierFrame.java Package: controller.registrer Project:
 * ServiciosDeVivienda Apr 27, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class ControllerUtleierFrame {

    UtleierFrame utleierFrame;
    HashSet<Person> personRegister;

    public ControllerUtleierFrame(UtleierFrame utleierFrame, HashSet<Person> personRegister) {
        this.utleierFrame = utleierFrame;
        this.personRegister = personRegister;

        //Legger til lytter
        utleierFrame.getUtleierPanel().addUtleierPanelListener(new KnappLytter());
    }

    private void registrerUtleier() {
        String fnavn = utleierFrame.getUtleierPanel().getFornavnField().getText();
        String enavn = utleierFrame.getUtleierPanel().getEtternavnField().getText();
        String epost = utleierFrame.getUtleierPanel().getEpostField().getText();
        String telnr = utleierFrame.getUtleierPanel().getTelefonField().getText();
        boolean erRepresentant = utleierFrame.getUtleierPanel().getErRepresentantCheckBox().isSelected();
        String erRepresentantFor = "";
        if (erRepresentant) {
            erRepresentantFor = utleierFrame.getUtleierPanel().getErRepresentatForField().getText();
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

    private class KnappLytter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(utleierFrame.getUtleierPanel().getLagreButton())) {
//                Melding.visMelding("Controller utleier frame", "Lagre knapp trukket");
                registrerUtleier();
            }
        }

    }

}
