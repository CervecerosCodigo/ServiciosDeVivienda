package view;

import java.awt.event.ActionListener;
import javax.swing.JRadioButton;
import lib.Konstanter;


/**
 * Dette er en egendefinert JRadioButton som spesifiserer formatering av alle RadioButtons
 * vi har tatt i bruk. 
 */
public class CustomJRadioButton extends JRadioButton{

    private String navn;
    
    public CustomJRadioButton( String navn ){
        
        this.navn = navn;
        
        setBackground(Konstanter.BAKGRUNNSFARGEPANEL);
        setText(this.navn);
    }
}
