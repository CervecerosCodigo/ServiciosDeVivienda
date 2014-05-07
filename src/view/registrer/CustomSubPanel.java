package view.registrer;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import view.AbstractPanel;

public class CustomSubPanel extends AbstractPanel {

    private LayoutManager layout;

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
    }

    /**
     * Med layout manager.
     * @param layout 
     */
    public CustomSubPanel(LayoutManager layout) {
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
