package controller;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Megler;
import view.StartGUI;

public class InnloggingController {
	
	private static boolean innlogget;
	private Megler adminMegler;
	StartGUI startGUI;
	
	public InnloggingController(StartGUI startGUI) {
		innlogget = false;
		adminMegler = new Megler("Ola", "Nordmann", "olanordmann@norge.no", "99999999", "admin", "admin", "Oslo");
		this.startGUI = startGUI;
		startGUI.getMainPanel().addTabListener(new TabLytter());
	}
	
	class TabLytter implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
		}
	}
}
