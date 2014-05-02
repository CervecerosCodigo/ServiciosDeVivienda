package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;

import view.registrer.CustomSubPanel;

public class TopPanelMegler extends AbstractPanel {

    private CustomSubPanel sokePanel, knappePanel;
    private MeglerRadioKnapper meglerRadioKnapper;
    private CustomJTextField sokeFelt;
    private CustomJButton sokeKnapp, nyKontraktItem, nyUtleierItem, nyBoligItem, nyAnnonseItem;
    
    private final static int BREDDE = 128, HOYDE = 30;

    TopPanelMegler(String borderTitle, int dimHeight, int dimWidth) {

        super(borderTitle, dimHeight, dimWidth);
        
        meglerRadioKnapper = new MeglerRadioKnapper();
        sokePanel = new CustomSubPanel(new FlowLayout());
        knappePanel = new CustomSubPanel(new FlowLayout());
        sokeFelt = new CustomJTextField("Søk", "", 17);
        sokeKnapp = new CustomJButton("Søk");
        nyKontraktItem = new CustomJButton("Ny kontrakt", BREDDE, HOYDE);
        nyUtleierItem = new CustomJButton("Ny utleier", BREDDE, HOYDE);
        nyBoligItem = new CustomJButton("Ny bolig", BREDDE, HOYDE);
        nyAnnonseItem = new CustomJButton("Ny annonse", BREDDE, HOYDE);
        
        add(meglerRadioKnapper);
        add(sokePanel);
        add(knappePanel);
        
        sokePanel.add(sokeFelt);
        sokePanel.add(sokeKnapp);
        knappePanel.add(nyUtleierItem);
        knappePanel.add(nyBoligItem);
        knappePanel.add(nyAnnonseItem);
        knappePanel.add(nyKontraktItem);

        setLayout(new FlowLayout(FlowLayout.LEADING, 40, 0));
        setVisible(true);
        
        sokePanel.setPreferredSize(new Dimension(350,35));
        knappePanel.setPreferredSize(new Dimension(270,70));
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

    public CustomJButton getNyKontraktItem() {
        return nyKontraktItem;
    }

    public CustomJButton getNyUtleierItem() {
        return nyUtleierItem;
    }

    public CustomJButton getNyBoligItem() {
        return nyBoligItem;
    }

    public CustomJButton getNyAnnonseItem() {
        return nyAnnonseItem;
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
        nyKontraktItem.addActionListener(lytter);
        sokeKnapp.addActionListener(lytter);
        nyAnnonseItem.addActionListener(lytter);
        nyBoligItem.addActionListener(lytter);
        nyUtleierItem.addActionListener(lytter);
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
        setPreferredSize(new Dimension(90, 115));
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
