package view;


import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;
import lib.Ikoner;


public class MainPanel extends AbstractPanel{

    private JPanel megler;
    private JPanel annonse;
    private static JTabbedPane arkfaner;
    
    public MainPanel(AbstraktArkfane megler, AbstraktArkfane annonse){
        setLayout( new GridLayout( 1, 1)) ;
        this.megler = (JPanel) megler;
        this.annonse = (JPanel) annonse;
        
        arkfaner = new JTabbedPane(JTabbedPane.TOP);
        
        //Legger til tab og kobler med panelet.
        
        arkfaner.addTab("Megler", Ikoner.MEGLER, this.megler);
        arkfaner.addTab("Annonser", Ikoner.ANNONSER, this.annonse);
        
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
