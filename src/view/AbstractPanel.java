package view;

import java.awt.Dimension;
import javax.swing.*;
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

        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(outerBorder);
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
