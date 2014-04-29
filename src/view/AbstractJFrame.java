package view;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * File: AbstractJFrame.java Package: view Project: ServiciosDeVivienda Apr 29,
 * 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public abstract class AbstractJFrame extends JFrame {

    private Dimension dim;
    private final JPanel panel;

    public AbstractJFrame(String tittel, int width, int height, AbstractPanel panel) {
        super(tittel);
        
        dim.setSize(width, height);
        setPreferredSize(dim);
        
        //Sentrerer vinduet midt på skjermen.
        setLocationRelativeTo(null);
        setResizable(false);
        
        this.panel = panel;
    }
    
    
    
    
}
