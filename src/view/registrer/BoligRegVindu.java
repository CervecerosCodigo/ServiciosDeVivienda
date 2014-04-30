package view.registrer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import lib.RegexTester;
import view.CustomJButton;
import view.CustomJCheckBox;
import view.CustomJRadioButton;
import view.CustomJTextField;

/**
 *
 * File: BoligRegVindu.java Package: view.registrer Project: ServiciosDeVivienda
 * Apr 29, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class BoligRegVindu extends JFrame {

    //private CustomRegPanel panelLabels, panelFields;
    private CustomSubPanel toppPanel, venstrePanel, senterPanel, hoyrePanel, bunnPanel;
    private BorderLayout borderLayout;
    private JLabel boligTypeLabel, eierLabel, meglerLabel, adresseLabel, postNrLabel, postStedLabel, boArealLabel, byggeArLabel, erUtleidLabel, beskrivelseLabel;
    private CustomJRadioButton leilighetRButton, eneboligRButton;
    private ButtonGroup radioButtons;
    private CustomJTextField eierField, meglerField, adresseField, postNrField, postStedField, boArealField, byggeArField;
    private JCheckBox erUtleidCheckBox;
    private JTextArea beskrivelseTextArea;
    private CustomJButton avbrytButton, lagreButton;

    public BoligRegVindu(String tittel) {
        super(tittel);
        setSize(1000, 700);
        
        //borderLayout = new BorderLayout();
        setLayout(new BorderLayout());
        
        toppPanel = new CustomSubPanel("Topp", 50, 0);
        venstrePanel = new CustomSubPanel("Venstre", 0, 150);
        senterPanel = new CustomSubPanel("Senter", 0, 0);
        hoyrePanel = new CustomSubPanel("Høyre", 0, 150);
        bunnPanel = new CustomSubPanel("Bunn", 50, 0);
        
        add(toppPanel, BorderLayout.NORTH );
        add(venstrePanel, BorderLayout.WEST );
        add(senterPanel, BorderLayout.CENTER );
        //add(hoyrePanel, BorderLayout.EAST );
        add(bunnPanel, BorderLayout.SOUTH );
       // panelLabels = new CustomRegPanel(tittel, 12, 1);
        //panelFields = new CustomRegPanel(tittel, 12, 1);
        

        boligTypeLabel = new JLabel("Boligtype: ");
        eierLabel = new JLabel("Eier ID: ");
        meglerLabel = new JLabel("Megler ID: ");
        adresseLabel = new JLabel("Adresse: ");
        postNrLabel = new JLabel("Postnummer: ");
        postStedLabel = new JLabel("Poststed: ");
        boArealLabel = new JLabel("Boareal: ");
        byggeArLabel = new JLabel("Byggeår: ");
        erUtleidLabel = new JLabel("Er utleid: ");
        beskrivelseLabel = new JLabel("Beskrivelse: ");

        leilighetRButton = new CustomJRadioButton("Leilighet");
        eneboligRButton = new CustomJRadioButton("Enebolig");
        radioButtons = new ButtonGroup();

        eierField = new CustomJTextField("XXXX", RegexTester.PRIS, 10);
        meglerField = new CustomJTextField("XXXX", RegexTester.PRIS, 10);
        adresseField = new CustomJTextField("Gate", RegexTester.GATE_NAVN_PATTERN, 10);//TODO: Her må det endres til gateadresse + husnummer regex
        postNrField = new CustomJTextField("XXXX", RegexTester.POST_NUMMER_PATTERN, 10);
        postStedField = new CustomJTextField("Oslo", RegexTester.POSTORT_NAVN, 10);
        boArealField = new CustomJTextField("XX(X)", RegexTester.KVM_BOLIG, 10);
        byggeArField = new CustomJTextField("XXXX", RegexTester.YEAR, 10);
        erUtleidCheckBox = new CustomJCheckBox();
        beskrivelseTextArea = new JTextArea(5, 1);

        avbrytButton = new CustomJButton("Avbryt");
        lagreButton = new CustomJButton("Lagre");

        radioButtons.add(leilighetRButton);
        radioButtons.add(eneboligRButton);

        toppPanel.add(adresseField);
        venstrePanel.add(boligTypeLabel);
//        panelLabels.add(boligTypeLabel);
//        panelLabels.add(eierLabel);
//        panelLabels.add(meglerLabel);
//        panelLabels.add(adresseLabel);
//        panelLabels.add(postNrLabel);
//        panelLabels.add(postStedLabel);
//        panelLabels.add(boArealLabel);
//        panelLabels.add(byggeArLabel);
//        panelLabels.add(erUtleidLabel);
//        panelLabels.add(beskrivelseLabel);

//        JPanel radioKnapperPanel = new JPanel(new FlowLayout());
//        radioKnapperPanel.add(leilighetRButton);
//        radioKnapperPanel.add(eneboligRButton);
//        Border innerBorderBoligType = BorderFactory.createTitledBorder("Boligtype");
//        Border outerBorderBoligType = BorderFactory.createEmptyBorder(0,0,0,0);
//        radioKnapperPanel.setBorder(BorderFactory.createCompoundBorder(outerBorderBoligType, innerBorderBoligType));
//        
//        
//        panelFields.add(radioKnapperPanel);
//        panelFields.add(eierField);
//        panelFields.add(meglerField);
//        panelFields.add(adresseField);
//        panelFields.add(postNrField);
//        panelFields.add(postStedField);
//        panelFields.add(boArealField);
//        panelFields.add(byggeArField);
//        panelFields.add(erUtleidCheckBox);
//        panelFields.add(beskrivelseTextArea);
//        Border innerBorderGen = BorderFactory.createTitledBorder("Generelt");
//        Border outerBorderGen = BorderFactory.createEmptyBorder(0,0,0,0);
//        panelFields.setBorder(BorderFactory.createCompoundBorder(outerBorderGen, innerBorderGen));
        

        
//        JPanel knapperPanel = new JPanel(new FlowLayout());
//        knapperPanel.add(avbrytButton);
//        knapperPanel.add(lagreButton);
//        panelFields.add(knapperPanel);
//
//        add(panelLabels, BorderLayout.WEST);
//        add(panelFields, BorderLayout.CENTER);
//        this.pack();
        setVisible(true);

    }

}
