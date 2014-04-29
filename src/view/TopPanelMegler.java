package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import view.CustomJButton;
import javax.swing.JPanel;
import view.CustomJRadioButton;
import lib.Konstanter;

public class TopPanelMegler extends AbstractPanel {

    private MeglerRadioKnapper meglerRadioKnapper;
    private CustomJTextField sokeFelt;
    private CustomJButton sokeKnapp, lagNyKnapp;

    TopPanelMegler(String borderTitle, int dimHeight, int dimWidth) {

        super(borderTitle, dimHeight, dimWidth);
        meglerRadioKnapper = new MeglerRadioKnapper();
        sokeFelt = new CustomJTextField("Søk", "", 17);
        sokeKnapp = new CustomJButton("Søk");
        lagNyKnapp = new CustomJButton("Lag ny");

        setLayout(new FlowLayout(FlowLayout.LEADING, 40, 0));
        setVisible(true);
        add(meglerRadioKnapper);
        add(sokeFelt);
        add(sokeKnapp);
        add(lagNyKnapp);
    }

    public MeglerRadioKnapper getMeglerRadioKnapper() {
        return meglerRadioKnapper;
    }

    public CustomJTextField getSokeFelt() {
        return sokeFelt;
    }

    public CustomJButton getSokeKnapp() {
        return sokeKnapp;
    }

    public CustomJButton getLagNyKnapp() {
        return lagNyKnapp;
    }

    public CustomJRadioButton getSoknaderRadio() {
        return meglerRadioKnapper.getSoknaderRadio();
    }

    public CustomJRadioButton getAnnonserRadio() {
        return meglerRadioKnapper.getAnnonserRadio();
    }

    public CustomJRadioButton getBoligerRadio() {
        return meglerRadioKnapper.getBoligerRadio();
    }

    public CustomJRadioButton getUtleiereRadio() {
        return meglerRadioKnapper.getUtleiereRadio();
    }

    public CustomJRadioButton getLeietakereRadio() {
        return meglerRadioKnapper.getLeietakereRadio();
    }
    
    public CustomJRadioButton getKontraktRadio() {
        return meglerRadioKnapper.getKontraktRadio();
    }

    public void leggTilRadioLytter(ActionListener lytter) {
        meglerRadioKnapper.addRadioListener(lytter);
    }

    public void leggTilKnappeLytter(ActionListener lytter) {
        lagNyKnapp.addActionListener(lytter);
        sokeKnapp.addActionListener(lytter);
    }

}

class MeglerRadioKnapper extends AbstractPanel {

    private CustomJRadioButton soknaderRadio, annonserRadio, boligerRadio, utleiereRadio, leietakereRadio, kontraktRadio;
    private ButtonGroup radioGroup;

    public MeglerRadioKnapper() {
        soknaderRadio = new CustomJRadioButton("Søknader");
        annonserRadio = new CustomJRadioButton("Annonser");
        boligerRadio = new CustomJRadioButton("Boliger");
        utleiereRadio = new CustomJRadioButton("Utleiere");
        leietakereRadio = new CustomJRadioButton("Leietakere");
        kontraktRadio = new CustomJRadioButton("Kontrakt");
        radioGroup = new ButtonGroup();

        radioGroup.add(soknaderRadio);
        radioGroup.add(annonserRadio);
        radioGroup.add(boligerRadio);
        radioGroup.add(utleiereRadio);
        radioGroup.add(leietakereRadio);
        radioGroup.add(kontraktRadio);

        setVisible(true);
        setPreferredSize(new Dimension(90, 115));//Endret til 130 fra 110 for å få plass med den nye radio button for kontrakter
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, -4));
        
        add(soknaderRadio);
        add(annonserRadio);
        add(boligerRadio);
        add(utleiereRadio);
        add(leietakereRadio);
        add(kontraktRadio);

        soknaderRadio.setSelected(true);
    }

    public CustomJRadioButton getSoknaderRadio() {
        return soknaderRadio;
    }

    public CustomJRadioButton getAnnonserRadio() {
        return annonserRadio;
    }

    public CustomJRadioButton getBoligerRadio() {
        return boligerRadio;
    }

    public CustomJRadioButton getUtleiereRadio() {
        return utleiereRadio;
    }

    public CustomJRadioButton getLeietakereRadio() {
        return leietakereRadio;
    }
    
    public CustomJRadioButton getKontraktRadio() {
        return kontraktRadio;
    }

    public void addRadioListener(ActionListener lytter) {
        boligerRadio.addActionListener(lytter);
        utleiereRadio.addActionListener(lytter);
        leietakereRadio.addActionListener(lytter);
        soknaderRadio.addActionListener(lytter);
        annonserRadio.addActionListener(lytter);
        kontraktRadio.addActionListener(lytter);

    }
}
