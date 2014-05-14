package view.registrer;

import java.awt.GridLayout;
import javax.swing.JFrame;


/**
 * Denne klassen utgjør rammen for alle registreringsvinduene i pakken view\register.
 */
public class AbstractRegistreringsVindu extends JFrame{

    private int bredde, hoyde;
    public AbstractRegistreringsVindu(int bredde, int hoyde, String tittel){
        super(tittel);
        this. bredde = bredde;
        this.hoyde = hoyde;
        setSize(bredde, hoyde);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        setLayout(new GridLayout(1, 1));
    }
}
