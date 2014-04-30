
package view.registrer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 * TODO: Denne klassen skal endres slik at den motsvarer samme desing som det er nå tatt frem for alle registreringsvinduer.
 * File: UtleierFrame.java
 Package: view.registrer
 Project: ServiciosDeVivienda
 Apr 27, 2014
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class UtleierFrame extends JFrame{

    private UtleierRegisterPanel utleierPanel;
    
    public UtleierFrame() {
        super("Registrer utleier");
        
        utleierPanel = new UtleierRegisterPanel();
        setLayout(new BorderLayout());
        add(utleierPanel, BorderLayout.WEST);
        
        setSize(600, 500);
        setVisible(true);
        
        /**
         * Lukker vinduet da avbryt er klikket.
         */
        utleierPanel.addUtleierPanelListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource().equals(utleierPanel.getAvbrytButton())){
                    dispose();
                }
            }
        });
    }

    public UtleierRegisterPanel getUtleierPanel() {
        return utleierPanel;
    }

    
}
