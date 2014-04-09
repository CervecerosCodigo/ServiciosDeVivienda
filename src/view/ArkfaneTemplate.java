package view;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.BorderLayout;
import javax.swing.*;
import lib.VinduStorrelse;

public class ArkfaneTemplate extends JPanel {

    private JPanel toppanel;
    private JPanel bunnpanel;
    private JPanel venstrepanel;
    private JPanel senterpanel;

    public ArkfaneTemplate(String valgtToppanel) {
        setLayout(new BorderLayout());
        setVisible(true);

        
        bunnpanel = new BunnPanel("", VinduStorrelse.BUNNPANEL.getHEIGHT(), 
                VinduStorrelse.BUNNPANEL.getWIDTH());
        venstrepanel = new VenstrePanel("Liste",VinduStorrelse.VENSTREPANEL.getHEIGHT(), 
                VinduStorrelse.VENSTREPANEL.getWIDTH());
        senterpanel = new SenterPanel("Visning",VinduStorrelse.SENTERPANEL.getHEIGHT(), 
                VinduStorrelse.SENTERPANEL.getWIDTH());

        if (valgtToppanel.equals("megler")) {
            toppanel = new TopPanelMegler("Søk",VinduStorrelse.TOPPANEL.getHEIGHT(), 
                    VinduStorrelse.TOPPANEL.getWIDTH());
            add(toppanel, BorderLayout.NORTH);
        } else{
            toppanel = new TopPanelAnnonse("Søk",VinduStorrelse.TOPPANEL.getHEIGHT(), 
                    VinduStorrelse.TOPPANEL.getWIDTH());
            add(toppanel, BorderLayout.NORTH);
        }
        add(venstrepanel, BorderLayout.WEST);
        add(senterpanel, BorderLayout.CENTER);
        add(bunnpanel, BorderLayout.SOUTH);


    }

}
