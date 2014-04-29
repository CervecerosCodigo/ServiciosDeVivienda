
package view.registrer;

import javax.swing.JButton;

/**
 * 
 * File: BoligRegVindu.java
 * Package: view.registrer
 * Project: ServiciosDeVivienda
 * Apr 29, 2014
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class BoligRegVindu extends AbstractRegistreringsVindu{

    private CustomRegPanel panel;
    
    public BoligRegVindu(String tittel, int width, int height, int rader, int kolonner) {
        super(tittel, width, height, rader, kolonner);
        panel = super.getPanel();

        
//        panel.add(new JButton("Test"));
    }

}
