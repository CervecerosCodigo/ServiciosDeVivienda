package view.registrer;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.BorderLayout;
import lib.Konstanter;



/**
 * Denne klassen er rammeverket for alle panelene som skal vise komponentene i 
 * registreringsvinduene.
 * @author espen
 */
public class AbstractRegistreringsPanel extends AbstractRegistreringsVindu{

    
    protected CustomSubPanel toppPanel, venstrePanel, senterPanel, hoyrePanel, bunnPanel;
    
    /**
     * 
     * @param bredde Bredden på JFrame
     * @param hoyde  Høyde på JFrame
     * @param tittel Tittel på JFrame
     */
    public AbstractRegistreringsPanel(int bredde, int hoyde, String tittel) {
        super(bredde, hoyde, tittel);
        setLayout(new BorderLayout());
        setBackground(Konstanter.BAKGRUNNSFARGEPANEL);
      
    }
    
    
    

}