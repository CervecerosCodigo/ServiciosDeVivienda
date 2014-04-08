package view;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.GridLayout;
import javax.swing.*;

public class VelkomstFrame extends JFrame {

    private final static int HEIGHT = 700;
    private final static int WIDTH = 1200;

    private MainPanel innhold;

    public VelkomstFrame() {
        super("Boligformidling");
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        innhold = new MainPanel();

        
        add( innhold );
    }

}
