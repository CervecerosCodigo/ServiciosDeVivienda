package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	}
	
	private class TabLytter implements ChangeListener {

		public void stateChanged(ChangeEvent e) {
			if(startGUI.getMainPanel().getTabbedPane().getSelectedIndex() == 0 && innlogget == false) {
				loggInnDialog.setVisible(true);
			}
		}
	}
	
	private class LoggInnDialogLytter implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
		}
	}
}
