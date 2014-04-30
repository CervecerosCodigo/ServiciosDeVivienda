package view.registrer;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;

/**
 * Abstract JFrame som brukes for å setter opp en JFrame som brukes mestparten i
 * registreringsvinduer. File: AbstractRegistreringsVindu.java Package: view
 * Project: ServiciosDeVivienda Apr 29, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public abstract class AbstractRegistreringsVindu extends JFrame {

    private Dimension dim;

    public AbstractRegistreringsVindu(String tittel, int width, int height) {
        super(tittel);

        setSize(width, height);

        //Sentrerer vinduet midt på skjermen.
        setLocationRelativeTo(null);
        setResizable(true);

        
    }
}
