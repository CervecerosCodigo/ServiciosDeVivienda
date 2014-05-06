package view;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import view.CustomJButton;
import lib.Konstanter;

public class BunnPanel extends AbstractPanel {

    private CustomJButton multiKnapp, frem, tilbake;

    public BunnPanel(String borderTitle, int dimHeight, int dimWidth) {
        super(borderTitle, dimHeight, dimWidth);

        setLayout(new GridLayout(1, 1));
        multiKnapp = new CustomJButton("Endre");
        frem = new CustomJButton("Frem");
        tilbake = new CustomJButton("Tilbake");

        add(multiKnapp);
        add(tilbake);
        add(frem);
    }

    public CustomJButton getMultiKnapp() {
        return multiKnapp;
    }

    public CustomJButton getTilbakeKnapp() {
        return tilbake;
    }

    public CustomJButton getFremKnapp() {
        return frem;
    }

    public void addKnappeLytter(ActionListener lytter) {
        frem.addActionListener(lytter);
        tilbake.addActionListener(lytter);        
        multiKnapp.addActionListener(lytter);        
    }

}
