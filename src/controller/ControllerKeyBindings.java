package controller;

import java.awt.AWTKeyStroke;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import view.ArkfaneAnnonse;
import view.ArkfaneMegler;
import view.StartGUI;

/**
 * Denne klassen er controller for keybindings / hurtigtaster for alle vinduer
 */

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
		annonseVindu.getBunnpanel().getMultiKnapp().getActionMap().put("CTRL-F pressed", new CTRL_FPressed());
		
		///// Opp og ned for navigering i venstrepanel //////
		annonseVindu.getBunnpanel().getFremKnapp().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "downPressed");
		annonseVindu.getBunnpanel().getFremKnapp().getActionMap().put("downPressed", new DownPressed());
		annonseVindu.getBunnpanel().getFremKnapp().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "UpPressed");
		annonseVindu.getBunnpanel().getFremKnapp().getActionMap().put("UpPressed", new UpPressed());
		meglerVindu.getBunnpanel().getFremKnapp().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "downPressed");
		meglerVindu.getBunnpanel().getFremKnapp().getActionMap().put("downPressed", new DownPressed());
		meglerVindu.getBunnpanel().getFremKnapp().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "UpPressed");
		meglerVindu.getBunnpanel().getFremKnapp().getActionMap().put("UpPressed", new UpPressed());
		
		///// CTRL-E for endre knapp i toppPanelMegler //////
		meglerVindu.getBunnpanel().getMultiKnapp().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK), "CTRL-E pressed");
		meglerVindu.getBunnpanel().getMultiKnapp().getActionMap().put("CTRL-E pressed", new CTRL_EPressed());
		
		///// CTRL-U for ny utleier knapp i toppPanelMegler //////
		meglerVindu.getToppanelMegler().getNyUtleierItem().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK), "CTRL-U pressed");
		meglerVindu.getToppanelMegler().getNyUtleierItem().getActionMap().put("CTRL-U pressed", new CTRL_UPressed());
		
		//// CTRL-B for ny bolig knapp i toppPanelMegler //////
		meglerVindu.getToppanelMegler().getNyBoligItem().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK), "CTRL-B pressed");
		meglerVindu.getToppanelMegler().getNyBoligItem().getActionMap().put("CTRL-B pressed", new CTRL_BPressed());
		
		//// CTRL-A for ny annonse knapp i toppPanelMegler //////
		meglerVindu.getToppanelMegler().getNyAnnonseItem().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK), "CTRL-A pressed");
		meglerVindu.getToppanelMegler().getNyAnnonseItem().getActionMap().put("CTRL-A pressed", new CTRL_APressed());
		
		///// CTRL-K for ny kontrakt knapp i toppPanelMegler /////
		meglerVindu.getToppanelMegler().getNyKontraktItem().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_MASK), "CTRL-K pressed");
		meglerVindu.getToppanelMegler().getNyKontraktItem().getActionMap().put("CTRL-K pressed", new CTRL_KPressed());
		
		//// CTRL-Tab for å skifte mellom tabs /////
		Set<AWTKeyStroke> forwardKeys = new HashSet<AWTKeyStroke>(startGUI.getMainPanel().getTabbedPane().getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
	    forwardKeys.remove(KeyStroke.getKeyStroke("ctrl TAB"));
	    startGUI.getMainPanel().getTabbedPane().setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, forwardKeys);
		startGUI.getMainPanel().getTabbedPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.CTRL_DOWN_MASK), "CTRL-Tab pressed");
		startGUI.getMainPanel().getTabbedPane().getActionMap().put("CTRL-Tab pressed", new CTRL_TabPressed());
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
    
    private class CTRL_FPressed extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			annonseVindu.getBunnpanel().getMultiKnapp().doClick();
		}
    }
    
    private class DownPressed extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			annonseVindu.getBunnpanel().getFremKnapp().doClick();
			meglerVindu.getBunnpanel().getFremKnapp().doClick();
		}
    }
    
    private class UpPressed extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			annonseVindu.getBunnpanel().getTilbakeKnapp().doClick();
			meglerVindu.getBunnpanel().getTilbakeKnapp().doClick();
		}
    }
    
    private class CTRL_EPressed extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			meglerVindu.getBunnpanel().getMultiKnapp().doClick();
		}
    }
    
    private class CTRL_UPressed extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			meglerVindu.getToppanelMegler().getNyUtleierItem().doClick();
		}
    }
    
    private class CTRL_BPressed extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			meglerVindu.getToppanelMegler().getNyBoligItem().doClick();
		}
    }
    
    private class CTRL_APressed extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			meglerVindu.getToppanelMegler().getNyAnnonseItem().doClick();
		}
    }
    
    private class CTRL_KPressed extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			meglerVindu.getToppanelMegler().getNyKontraktItem().doClick();
		}
    }
    
    private class CTRL_TabPressed extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(startGUI.getMainPanel().getTabbedPane().getSelectedIndex() == 0)
				startGUI.getMainPanel().getTabbedPane().setSelectedIndex(1);
			
			else if(startGUI.getMainPanel().getTabbedPane().getSelectedIndex() == 1)
				startGUI.getMainPanel().getTabbedPane().setSelectedIndex(0);
			
			System.out.println("Test");
		}
    }
    
}
