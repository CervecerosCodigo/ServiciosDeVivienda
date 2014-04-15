package view;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class BunnPanel extends AbstractPanel {

    private JButton endre, frem, tilbake;

    public BunnPanel(String borderTitle, int dimHeight, int dimWidth) {
        super(borderTitle, dimHeight, dimWidth);

        setLayout(new GridLayout(1, 1));
        endre = new JButton("Endre");
        frem = new JButton("Frem");
        tilbake = new JButton("Tilbake");

        add(endre);
        add(tilbake);
        add(frem);
    }

    public JButton getEndreKnapp() {
        return endre;
    }

    public JButton getTilbakeKnapp() {
        return tilbake;
    }

    public JButton getFremKnapp() {
        return frem;
    }

    public void addKnappeLytter(ActionListener lytter) {
        frem.addActionListener(lytter);
        tilbake.addActionListener(lytter);        
        endre.addActionListener(lytter);        
    }

}
