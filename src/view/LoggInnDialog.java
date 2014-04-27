package view;


import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoggInnDialog extends JDialog {
	
	private JTextField brukernavnFelt;
	private JPasswordField passordFelt;
	private JLabel brukernavnLabel, passordLabel;
	private JButton loggInnKnapp, avbrytKnapp;
	
	public LoggInnDialog() {
		brukernavnFelt = new JTextField(13);
		passordFelt = new JPasswordField(13);
		brukernavnLabel = new JLabel("Brukernavn:");
		passordLabel = new JLabel("Passord:");
		loggInnKnapp = new JButton("Ok");
		avbrytKnapp = new JButton("Avbryt");
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setSize(180, 180);
		
		add(brukernavnLabel);
		add(brukernavnFelt);
		add(passordLabel);
		add(passordFelt);
		add(loggInnKnapp);
		add(avbrytKnapp);
	}

	public JButton getLoggInnKnapp() {
		return loggInnKnapp;
	}

	public JButton getAvbrytKnapp() {
		return avbrytKnapp;
	}
	
}

