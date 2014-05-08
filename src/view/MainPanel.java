package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;


public class MainPanel extends AbstractPanel{

    private JPanel megler;
    private JPanel annonse;
    private static JTabbedPane arkfaner;
    
    
    public MainPanel(ArkfaneTemplate megler, ArkfaneTemplate annonse){
        setLayout( new GridLayout( 1, 1)) ;
        this.megler = (JPanel) megler;
        this.annonse = (JPanel) annonse;
        
        arkfaner = new JTabbedPane(JTabbedPane.TOP);
        
        //Legger til tab og kobler med panelet.
        
        arkfaner.addTab("Megler", this.megler);
        arkfaner.addTab("Annonser", this.annonse);
        
        arkfaner.setSelectedIndex(1);
        arkfaner.setToolTipTextAt(0, "Administrering av boliger, søknader mm.");
        arkfaner.setToolTipTextAt(1, "Finn tilgjengelige boliger, send inn søknader mm.");
        
        add(arkfaner);
    }
    
    
    public JTabbedPane getTabbedPane() {
    	return arkfaner;
    }
    
    public void addTabListener(ChangeListener lytter) {
    	this.arkfaner.addChangeListener(lytter);
    }
    
    public static int returnervalgtArkfane(){
        return arkfaner.getSelectedIndex();
    }
    
}
