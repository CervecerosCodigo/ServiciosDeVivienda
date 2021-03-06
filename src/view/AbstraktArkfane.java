package view;

import controller.VisMeldingInterface;
import java.awt.BorderLayout;
import javax.swing.*;
import lib.Melding;
import lib.VinduStorrelse;

public abstract class AbstraktArkfane extends AbstractPanel implements VisMeldingInterface{

    private JPanel toppanel;
    private BunnPanel bunnpanel;
    private VenstrePanel venstrepanel;
    private SenterPanel senterpanel;
    

    public AbstraktArkfane(String valgtToppanel) {
        setLayout(new BorderLayout());
        setVisible(true);
        
        bunnpanel = new BunnPanel(VinduStorrelse.BUNNPANEL.getHEIGHT(), 
                VinduStorrelse.BUNNPANEL.getWIDTH());
        venstrepanel = new VenstrePanel("Liste",VinduStorrelse.VENSTREPANEL.getHEIGHT(), 
                VinduStorrelse.VENSTREPANEL.getWIDTH());
        senterpanel = new SenterPanel("Visning",VinduStorrelse.SENTERPANEL.getHEIGHT(), 
                VinduStorrelse.SENTERPANEL.getWIDTH());

        if (valgtToppanel.equals("megler")) {
            toppanel = new TopPanelMegler("Søk",VinduStorrelse.TOPPANEL.getHEIGHT(), VinduStorrelse.TOPPANEL.getWIDTH());
            add(toppanel, BorderLayout.NORTH);
        } 
        
        else{
            toppanel = new TopPanelAnnonse("Søk",VinduStorrelse.TOPPANEL.getHEIGHT(), VinduStorrelse.TOPPANEL.getWIDTH());
            add(toppanel, BorderLayout.NORTH);
        }
        
        add(venstrepanel, BorderLayout.WEST);
        add(senterpanel, BorderLayout.CENTER);
        add(bunnpanel, BorderLayout.SOUTH);
    }

    public TopPanelMegler getToppanelMegler() {
        return (TopPanelMegler) toppanel;
    }
    public TopPanelAnnonse getToppanelAnnonse() {
        return (TopPanelAnnonse) toppanel;
    }

    public BunnPanel getBunnpanel() {
        return bunnpanel;
    }

    public VenstrePanel getVenstrepanel() {
        return venstrepanel;
    }

    public SenterPanel getSenterpanel() {
        return senterpanel;
    }

    @Override
    public void visMelding(String overskrift, String melding) {
        Melding.visMelding(overskrift, melding);
    }
}
