package view;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;


public class MainPanel extends JPanel{

    private JPanel megler;
    private JPanel annonse;
    private JTabbedPane arkfaner;
    
    public MainPanel(ArkfaneTemplate megler, ArkfaneTemplate annonse){
        super( new GridLayout( 1, 1));
        this.megler = (JPanel) megler;
        this.annonse = (JPanel) annonse;
        arkfaner = new JTabbedPane(JTabbedPane.TOP);
        
        //Legger til tab og kobler med panelet.
        arkfaner.addTab("Megler", this.megler);
        arkfaner.addTab("Annonser", this.annonse);
        arkfaner.setSelectedIndex(1);
        
        add(arkfaner);
    }


}
