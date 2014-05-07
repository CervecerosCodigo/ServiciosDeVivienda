package view;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import lib.Konstanter;

public abstract class AbstractPanel extends JPanel{

    private String borderTitle;
    private int dimHeight;
    private int dimWidth;
    
    /**
     * Abstract panel med en tittelborder.
     * @param borderTitle
     * @param dimHeight
     * @param dimWidth 
     */
    public AbstractPanel( String borderTitle, int dimHeight, int dimWidth ) {

        this.borderTitle = borderTitle;
        this.dimHeight = dimHeight;
        this.dimWidth = dimWidth;
        
        Dimension dim = getPreferredSize();
        dim.height = this.dimHeight;
        dim.width = this.dimWidth;
        setPreferredSize(dim);
        
        setBackground(Konstanter.BAKGRUNNSFARGEPANEL);

        Border innerBorder = BorderFactory.createTitledBorder(this.borderTitle);
        Border outerBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }
    
    /**
     * Abstract panel som blir opprettet uten border. 
     * @param dimHeight
     * @param dimWidth 
     */
    public AbstractPanel(int dimHeight, int dimWidth ) {

        this.dimHeight = dimHeight;
        this.dimWidth = dimWidth;
        
        Dimension dim = getPreferredSize();
        dim.height = this.dimHeight;
        dim.width = this.dimWidth;
        setPreferredSize(dim);
        
        setBackground(Konstanter.BAKGRUNNSFARGEPANEL);
    }
    
    public AbstractPanel(){
        setBackground(Konstanter.BAKGRUNNSFARGEPANEL);
    }
    
   
}
