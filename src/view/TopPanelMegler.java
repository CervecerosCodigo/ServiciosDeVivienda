package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class TopPanelMegler extends AbstractPanel {

    private MeglerRadioKnapper meglerRadioKnapper;
    private CustomJTextField sokeFelt;
    private JButton sokeKnapp, lagNyKnapp;

    TopPanelMegler(String borderTitle, int dimHeight, int dimWidth) {

        super(borderTitle, dimHeight, dimWidth);
        meglerRadioKnapper = new MeglerRadioKnapper();
        sokeFelt = new CustomJTextField("Søk", "", 22);
        sokeKnapp = new JButton("Søk");
        lagNyKnapp = new JButton("Lag ny");

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

    public JButton getSokeKnapp() {
        return sokeKnapp;
    }

    public JButton getLagNyKnapp() {
        return lagNyKnapp;
    }

    public JRadioButton getSoknaderRadio() {
        return meglerRadioKnapper.getSoknaderRadio();
    }

    public JRadioButton getAnnonserRadio() {
        return meglerRadioKnapper.getAnnonserRadio();
    }

    public JRadioButton getBoligerRadio() {
        return meglerRadioKnapper.getBoligerRadio();
    }

    public JRadioButton getUtleiereRadio() {
        return meglerRadioKnapper.getUtleiereRadio();
    }

    public JRadioButton getLeietakereRadio() {
        return meglerRadioKnapper.getLeietakereRadio();
    }

    public void leggTilRadioLytter(ActionListener lytter) {
        meglerRadioKnapper.addRadioListener(lytter);
    }

    public void leggTilKnappeLytter(ActionListener lytter) {
        lagNyKnapp.addActionListener(lytter);
        sokeKnapp.addActionListener(lytter);
    }

}

class MeglerRadioKnapper extends JPanel {

    private JRadioButton soknaderRadio, annonserRadio, boligerRadio, utleiereRadio, leietakereRadio;
    private ButtonGroup radioGroup;

    public MeglerRadioKnapper() {
        soknaderRadio = new JRadioButton("Søknader");
        annonserRadio = new JRadioButton("Annonser");
        boligerRadio = new JRadioButton("Boliger");
        utleiereRadio = new JRadioButton("Utleiere");
        leietakereRadio = new JRadioButton("Leietakere");
        radioGroup = new ButtonGroup();

        radioGroup.add(soknaderRadio);
        radioGroup.add(annonserRadio);
        radioGroup.add(boligerRadio);
        radioGroup.add(utleiereRadio);
        radioGroup.add(leietakereRadio);

        setVisible(true);
        setPreferredSize(new Dimension(90, 110));
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, -1));

        add(soknaderRadio);
        add(annonserRadio);
        add(boligerRadio);
        add(utleiereRadio);
        add(leietakereRadio);

        soknaderRadio.setSelected(true);
    }

    public JRadioButton getSoknaderRadio() {
        return soknaderRadio;
    }

    public JRadioButton getAnnonserRadio() {
        return annonserRadio;
    }

    public JRadioButton getBoligerRadio() {
        return boligerRadio;
    }

    public JRadioButton getUtleiereRadio() {
        return utleiereRadio;
    }

    public JRadioButton getLeietakereRadio() {
        return leietakereRadio;
    }

    public void addRadioListener(ActionListener lytter) {
        boligerRadio.addActionListener(lytter);
        utleiereRadio.addActionListener(lytter);
        leietakereRadio.addActionListener(lytter);
        soknaderRadio.addActionListener(lytter);
        annonserRadio.addActionListener(lytter);

    }
}
