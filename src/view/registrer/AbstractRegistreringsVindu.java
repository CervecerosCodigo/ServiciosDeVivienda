package view.registrer;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import view.registrer.CustomRegPanel;

/**
 * Abstract JFrame som brukes for å setter opp en JFrame som brukes mestparten i
 * registreringsvinduer. File: AbstractRegistreringsVindu.java Package: view Project:
 ServiciosDeVivienda Apr 29, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public abstract class AbstractRegistreringsVindu extends JFrame {

    private Dimension dim;
    private CustomRegPanel panel;

    public AbstractRegistreringsVindu(String tittel, int width, int height, int rader, int kolonner) {
        super(tittel);
        
        dim = new Dimension();
        dim.setSize(width, height);
        setPreferredSize(dim);

        //Sentrerer vinduet midt på skjermen.
        setLocationRelativeTo(null);
        setResizable(false);

        //Panel
        panel = new CustomRegPanel(rader, kolonner);
    }

    public CustomRegPanel getPanel() {
        return panel;
    }

    
}
