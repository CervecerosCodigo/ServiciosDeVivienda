package view;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class ArkfaneTemplate extends JPanel{

    private JLabel test;
    
    public ArkfaneTemplate(){
        setLayout( new BorderLayout() );
        setVisible(true);
        test = new JLabel("Test");
        add( test );
    }
    
    public String getTest(){
        return test.getText();
    }
}
