package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Megler;
import model.Person;
import view.LoggInnDialog;
import view.StartGUI;

/**
 * Kontroller klasse for innloggingsvindu, og brukerkontroll for innlogging til
 * megler vinduet.
 */
public class InnloggingController {

    private static boolean innlogget;
    private Megler adminMegler;
    private LoggInnDialog loggInnDialog;
    StartGUI startGUI;
    private HashSet<Person> personliste;

    public InnloggingController(StartGUI startGUI, HashSet<Person> personliste) {
        innlogget = false;
        adminMegler = new Megler("Ola", "Nordmann", "olanordmann@norge.no", "99999999", "admin", "admin", "Oslo");
        this.startGUI = startGUI;
        this.personliste = personliste;
        loggInnDialog = new LoggInnDialog();

        startGUI.getMainPanel().addTabListener(new TabLytter());
        loggInnDialog.addKnappeListener(new LoggInnDialogLytter());
    }

    private boolean sjekkInformasjon() {

        for (Person personIterator : personliste) {
            if (personIterator instanceof Megler) {
                if (((Megler) personIterator).getBrukernavn().equals(loggInnDialog.getBrukernavnFelt().getText())) {
                    if (String.valueOf(loggInnDialog.getPassordFelt().getPassword()).equals(loggInnDialog.getPassordFelt())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean sjekkAdmin() {
        if (loggInnDialog.getBrukernavnFelt().getText().equals(adminMegler.getBrukernavn())
                & (String.valueOf(loggInnDialog.getPassordFelt().getPassword())).equals(adminMegler.getPassord())) {
            return true;
        } else {
            return false;
        }
    }

    private class TabLytter implements ChangeListener {

        public void stateChanged(ChangeEvent e) {
            if (startGUI.getMainPanel().getTabbedPane().getSelectedIndex() == 0 && innlogget == false) {
                loggInnDialog.setVisible(true);
                startGUI.setVisible(false);
            }
        }
    }

    private class LoggInnDialogLytter implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == loggInnDialog.getAvbrytKnapp()) {
                startGUI.getMainPanel().getTabbedPane().setSelectedIndex(1);
                loggInnDialog.dispose();
                startGUI.setVisible(true);
            } else if (e.getSource() == loggInnDialog.getLoggInnKnapp()) {

                if (sjekkInformasjon()) {

                    startGUI.getMainPanel().getTabbedPane().setSelectedIndex(0);
                    loggInnDialog.dispose();
                    startGUI.setVisible(true);
                    innlogget = true;
                } else if (sjekkAdmin()) {
                    startGUI.getMainPanel().getTabbedPane().setSelectedIndex(0);
                    loggInnDialog.dispose();
                    startGUI.setVisible(true);
                    innlogget = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Feil brukernavn eller passord", "Prøv igjen", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
