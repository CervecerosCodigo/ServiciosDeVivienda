package view;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import javax.swing.JCheckBox;
import lib.Konstanter;



public class CustomJCheckBox extends JCheckBox{

    private String navn;
    
    public CustomJCheckBox(String navn){
        this.navn = navn;
        
        setBackground(Konstanter.BAKGRUNNSFARGEPANEL);
        setText(this.navn);
    }
    
    public CustomJCheckBox(){
        setBackground(Konstanter.BAKGRUNNSFARGEPANEL);
    }
}
