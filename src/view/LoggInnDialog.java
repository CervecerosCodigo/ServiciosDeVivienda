package view;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import lib.Ikoner;

import lib.Konstanter;

public class LoggInnDialog extends JDialog {
	
	private JTextField brukernavnFelt;
	private JPasswordField passordFelt;
	private JLabel brukernavnLabel, passordLabel;
	private JButton loggInnKnapp, avbrytKnapp;
	
	public LoggInnDialog() {
		brukernavnFelt = new JTextField("admin", 13);
		passordFelt = new JPasswordField("admin", 13);
		brukernavnLabel = new JLabel("Brukernavn:");
		passordLabel = new JLabel("Passord:");
		loggInnKnapp = new JButton("Ok");
		avbrytKnapp = new JButton("Avbryt");
		
		setLayout(new FlowLayout());
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(getParent());
		setBackground(Konstanter.BAKGRUNNSFARGEPANEL);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		this.getRootPane().setDefaultButton(loggInnKnapp);
		
		add(brukernavnLabel);
		add(brukernavnFelt);
		add(passordLabel);
		add(passordFelt);
		add(loggInnKnapp);
		add(avbrytKnapp);
                
                setIconImage(Ikoner.PASSORD.getImage());
		
		pack();
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(200,180);
	}

	public void addKnappeListener(ActionListener lytter) {
		this.loggInnKnapp.addActionListener(lytter);
		this.avbrytKnapp.addActionListener(lytter);
	}
	
	public JButton getLoggInnKnapp() {
		return loggInnKnapp;
	}

	public JButton getAvbrytKnapp() {
		return avbrytKnapp;
	}
	
	public JTextField getBrukernavnFelt() {
		return brukernavnFelt;
	}
	
	public JPasswordField getPassordFelt() {
		return passordFelt;
	}
}

