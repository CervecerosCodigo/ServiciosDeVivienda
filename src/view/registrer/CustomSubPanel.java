package view.registrer;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import view.AbstractPanel;

public class CustomSubPanel extends AbstractPanel {

    private LayoutManager layout;
    private Border innerBorder;

    /**
     * Border og størrelse.
     * @param borderNavn
     * @param hoyde
     * @param bredde 
     */
    public CustomSubPanel(String borderNavn, int hoyde, int bredde) {
        super(borderNavn, hoyde, bredde);
        setLayout(new GridLayout(1, 1));
        setVisible(true);
        
        innerBorder = BorderFactory.createTitledBorder(borderNavn);
        setBorder(innerBorder);   

             
    }

    /**
     * Med layout manager.
     * @param layout 
     */
    public CustomSubPanel(LayoutManager layout) {
        setLayout(layout);
        setVisible(true);
    }
    
    public CustomSubPanel(LayoutManager layout, int bredde, int hoyde) {
    	super(null, bredde, hoyde);
    	setLayout(layout);
    	setVisible(true);
    }
    
    /**
     * Border med navn, størrelse og layoutmanager.
     * @param borderNavn
     * @param hoyde
     * @param bredde
     * @param layout 
     */
    public CustomSubPanel(String borderNavn, int hoyde, int bredde, LayoutManager layout) {
        super(borderNavn, hoyde, bredde);
        setLayout(layout);
        setVisible(true);
        innerBorder = BorderFactory.createTitledBorder(borderNavn);
        setBorder(innerBorder);           
    }
    
    /**
     * Størrelse og layoutmanager.
     * @param hoyde
     * @param bredde
     * @param layout 
     */
    public CustomSubPanel(int hoyde, int bredde, LayoutManager layout) {
        super(hoyde, bredde);
        setLayout(layout);
        setVisible(true);
    }

    /**
     * Størrelse, uten layout og borders.
     * @param hoyde
     * @param bredde 
     */
    public CustomSubPanel(int hoyde, int bredde) {
        super(hoyde, bredde);
        setVisible(true);
    }

    void addUtleierPanelListener(ActionListener actionListener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
