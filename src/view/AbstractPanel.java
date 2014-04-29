package view;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import lib.Konstanter;

public abstract class AbstractPanel extends JPanel{

    private String borderTitle;
    private int dimHeight;
    private int dimWidth;
    
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
    
    public AbstractPanel(){
        setBackground(Konstanter.BAKGRUNNSFARGEPANEL);
    }
    

}
