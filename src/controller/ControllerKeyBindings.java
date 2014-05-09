package controller;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import view.ArkfaneAnnonse;
import view.ArkfaneMegler;
import view.StartGUI;

public class ControllerKeyBindings {
	
    private ArkfaneMegler meglerVindu;
    private ArkfaneAnnonse annonseVindu;
    private StartGUI startGUI;
	
	public ControllerKeyBindings(ArkfaneMegler meglerVindu, ArkfaneAnnonse annonseVindu, StartGUI startGUI) {
		
		this.meglerVindu = meglerVindu;
		this.annonseVindu = annonseVindu;
		this.startGUI = startGUI;
		
		////// Enter for toppPanelAnnonse //////
		annonseVindu.getToppanelAnnonse().getSokeKnapp().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "enterPressed");
		annonseVindu.getToppanelAnnonse().getSokeKnapp().getActionMap().put("enterPressed", new EnterActionAnnonse());
		
		////// Enter for toppPanelMegler ///////
		meglerVindu.getToppanelMegler().getSokeKnapp().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "enterPressed");
		meglerVindu.getToppanelMegler().getSokeKnapp().getActionMap().put("enterPressed", new EnterActionMegler());
		
		///// CTRL-F for toppPanelAnnonse //////
		annonseVindu.getBunnpanel().getMultiKnapp().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK), "CTRL-F pressed");
		annonseVindu.getBunnpanel().getMultiKnapp().getActionMap().put("CTRL-F pressed", new CTRLFPressed());
		
		///// Opp og ned for navigering i venstrepanel //////
		annonseVindu.getBunnpanel().getFremKnapp().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "downPressed");
		annonseVindu.getBunnpanel().getFremKnapp().getActionMap().put("downPressed", new DownPressed());
	}
	
    private class EnterActionAnnonse extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			annonseVindu.getToppanelAnnonse().getSokeKnapp().doClick();
		}
    }
    
    private class EnterActionMegler extends AbstractAction {
    	
		@Override
		public void actionPerformed(ActionEvent e) {
			meglerVindu.getToppanelMegler().getSokeKnapp().doClick();
		}
    }
    
    private class CTRLFPressed extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			annonseVindu.getBunnpanel().getMultiKnapp().doClick();
		}
    }
    
    private class DownPressed extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			annonseVindu.getBunnpanel().getFremKnapp().doClick();
		}
    }
}
