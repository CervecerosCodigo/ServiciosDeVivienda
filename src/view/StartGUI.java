package view;

import java.awt.Image;
import javax.swing.*;
import lib.Ikoner;
import lib.Konstanter;
import lib.VinduStorrelse;

public class StartGUI extends JFrame {

    private final static int HEIGHT = VinduStorrelse.STOR.getHEIGHT();
    private final static int WIDTH = VinduStorrelse.STOR.getWIDTH();
    private MainPanel innhold;

    public StartGUI( AbstraktArkfane megler, AbstraktArkfane annonse) {
        super("Boligformidling");
        setSize(WIDTH, HEIGHT);
        setIconImage(Ikoner.APP_ICON.getImage());
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setBackground(Konstanter.BAKGRUNNSFARGEPANEL);
        innhold = new MainPanel( megler, annonse );
        this.setLocationRelativeTo(null);
        add( innhold );
    }
    
    public MainPanel getMainPanel() {
    	return innhold;
    }

}
