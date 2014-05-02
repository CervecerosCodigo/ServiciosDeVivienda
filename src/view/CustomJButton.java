package view;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import lib.Konstanter;



public class CustomJButton extends JButton{

    private String navn;
    
    public CustomJButton(String navn){
        this.navn = navn;
        
        //setBackground(Konstanter.BAKGRUNNSFARGEPANEL);
        setText(this.navn);
    }
    public CustomJButton(String navn, int bredde, int hoyde){
        this.navn = navn;
        
        //setBackground(Konstanter.BAKGRUNNSFARGEPANEL);
        setText(this.navn);
        setPreferredSize(new Dimension(bredde, hoyde));
    }

  
    
}
