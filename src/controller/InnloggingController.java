package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Megler;
import view.LoggInnDialog;
import view.StartGUI;

public class InnloggingController {
	
	private static boolean innlogget;
	private Megler adminMegler;
	private LoggInnDialog loggInnDialog;
	StartGUI startGUI;
	
	public InnloggingController(StartGUI startGUI) {
		innlogget = false;
		adminMegler = new Megler("Ola", "Nordmann", "olanordmann@norge.no", "99999999", "admin", "admin", "Oslo");
		this.startGUI = startGUI;
		loggInnDialog = new LoggInnDialog();
		
		startGUI.getMainPanel().addTabListener(new TabLytter());
		loggInnDialog.addKnappeListener(new LoggInnDialogLytter());
	}
	
	private class TabLytter implements ChangeListener {

		public void stateChanged(ChangeEvent e) {
			if(startGUI.getMainPanel().getTabbedPane().getSelectedIndex() == 0 && innlogget == false) {
				loggInnDialog.setVisible(true);
				startGUI.setVisible(false);
			}
		}
	}
	
	private class LoggInnDialogLytter implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == loggInnDialog.getAvbrytKnapp()) {
				startGUI.getMainPanel().getTabbedPane().setSelectedIndex(1);
				loggInnDialog.dispose();
				startGUI.setVisible(true);
			}
			
			else if(e.getSource() == loggInnDialog.getLoggInnKnapp()) {
				
				if(loggInnDialog.getBrukernavnFelt().getText().equals(adminMegler.getBrukernavn()) 
					& (String.valueOf(loggInnDialog.getPassordFelt().getPassword())).equals(adminMegler.getPassord())) {
					
					startGUI.getMainPanel().getTabbedPane().setSelectedIndex(0);
					loggInnDialog.dispose();
					startGUI.setVisible(true);
					innlogget = true;
				}
				
				else JOptionPane.showMessageDialog(null, "Feil brukernavn eller passord", "Prøv igjen", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
