package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import lib.Ikoner;
import model.Bolig;
import view.registrer.AbstractRegistreringsPanel;
import view.registrer.CustomSubPanel;

/**
 * Klassen er GUI for fremvisning av bilder for en bolig.
 */
public class BildeViser extends AbstractRegistreringsPanel {

    private CustomSubPanel panelVisning, panelTeller, panelButtons;
    private JLabel bildeLabel, tellerLabel;
    private CustomJButton tilbakeButton, fremButton, slettButton, lukkButton;
    private boolean erMegler;

    public BildeViser(Bolig bolig, boolean erMegler) {
        super(410, 370, bolig.getAdresse());
        this.erMegler = erMegler;
        
        panelVisning = new CustomSubPanel(new GridLayout(1, 1));
        panelTeller = new CustomSubPanel(new GridBagLayout());
        panelButtons = new CustomSubPanel(new GridLayout(1, 4));
        
        
        bildeLabel = new JLabel();
        bildeLabel.setPreferredSize(new Dimension(410, 270));
        tellerLabel = new JLabel();
        
        //Buttons
        tilbakeButton = new CustomJButton(Ikoner.PIL_VENSTRE_LITEN);
        fremButton = new CustomJButton(Ikoner.PIL_HOYRE_LITEN);
        slettButton = new CustomJButton("Slett");
        lukkButton = new CustomJButton("Lukk");

        //Legger til komponenter
        panelVisning.add(bildeLabel);
        
        panelTeller.add(tellerLabel);
        panelTeller.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panelButtons.add(tilbakeButton);
        panelButtons.add(fremButton);
        panelButtons.add(slettButton);
        panelButtons.add(lukkButton);
        
        add(panelVisning, BorderLayout.NORTH);
        add(panelTeller, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);
        
        if(!erMegler)
            slettButton.setEnabled(false);
    }

    public JLabel getBildeLabel() {
        return bildeLabel;
    }

    public JLabel getTellerLabel() {
        return tellerLabel;
    }

    public CustomJButton getTilbakeButton() {
        return tilbakeButton;
    }

    public CustomJButton getFremButton() {
        return fremButton;
    }

    public CustomJButton getSlettButton() {
        return slettButton;
    }

    public CustomJButton getLukkButton() {
        return lukkButton;
    }

    /**
     * Lyttermetode for knapper i bildeviser.
     * @param lytter 
     */
    public void addButtonPanelListener(ActionListener lytter){
        tilbakeButton.addActionListener(lytter);
        fremButton.addActionListener(lytter);
        slettButton.addActionListener(lytter);
        lukkButton.addActionListener(lytter);
    }

}
