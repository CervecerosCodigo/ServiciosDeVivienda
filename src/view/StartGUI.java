package view;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import javax.swing.*;
import lib.Konstanter;
import lib.VinduStorrelse;

public class StartGUI extends JFrame {

    private final static int HEIGHT = VinduStorrelse.STOR.getHEIGHT();
    private final static int WIDTH = VinduStorrelse.STOR.getWIDTH();
    

    private MainPanel innhold;

    public StartGUI( ArkfaneTemplate megler, ArkfaneTemplate annonse) {
        super("Boligformidling");
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setBackground(Konstanter.BAKGRUNNSFARGEPANEL);
        innhold = new MainPanel( megler, annonse );
        add( innhold );
    }
    
    public MainPanel getMainPanel() {
    	return innhold;
    }

}
