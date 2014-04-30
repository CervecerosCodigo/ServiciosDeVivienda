package view.registrer;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.GridLayout;
import view.AbstractPanel;



public class CustomSubPanel extends AbstractPanel{

    public CustomSubPanel(String borderNavn, int hoyde, int bredde){
        super(borderNavn, hoyde, bredde);
        setLayout( new GridLayout(1, 1));
        setVisible(true);
        
    }
}
