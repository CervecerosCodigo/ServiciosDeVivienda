package view;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.GridLayout;
import javax.swing.*;


public class MainPanel extends JPanel{

    private JPanel megler;
    private JPanel annonse;
    private JTabbedPane arkfaner;
    
    public MainPanel(){
        super( new GridLayout( 1, 1));
        
        arkfaner = new JTabbedPane(JTabbedPane.TOP);
        
        //Lager Panelet til tabben
        megler = makePanel( "Megler" );
        annonse = makePanel( "Annonser" );
        
        //Legger til tab og kobler med panelet.
        arkfaner.addTab("Megler", megler);
        arkfaner.addTab("Annonser", annonse);
        
       
        add(arkfaner);
        System.out.println("Kjører");
    }

    protected JPanel makePanel(String text) {
        JPanel panel = new JPanel();
        
        return panel;
    }
}
