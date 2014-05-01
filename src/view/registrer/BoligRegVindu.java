package view.registrer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createLineBorder;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

    private CustomSubPanel toppPanel, venstrePanel, senterPanel, hoyrePanel, bunnPanel;
    private BorderLayout borderLayout;

    ///BOLIG///
    private JLabel boligTypeLabel, eierLabel, meglerLabel, adresseLabel, postNrLabel, postStedLabel, boArealLabel, byggeArLabel, erUtleidLabel, beskrivelseLabel;
    private CustomJRadioButton leilighetRButton, eneboligRButton;
    private ButtonGroup radioButtons;
    private CustomJTextField eierField, meglerField, adresseField, postNrField, postStedField, boArealField, byggeArField;
    private JCheckBox erUtleidCheckBox;
    private JTextArea beskrivelseTextArea;
    //TODO: Legg til kalenderfelt for registrering av tilgjenglighet for utleie
    ///BOLIG SLUTT///
    //
    ///LEILIGHET///
    private JLabel etasjeNrLabel, balkongArealLabel, bodArealLabel, harHeisLabel, harGarasjeLabel, harFellesVaskeri;
    private CustomJTextField etasjeNrField, balkongArealField, bodArealField;
    private CustomJCheckBox harHeisCheckBox, harGarasjeCheckBox, harFellesVaskeriCheckbox;
    ///LEILIGHET SLUTT///
    //
    ///ENEBOLIG///
    private JLabel antallEtasjerLabel, harKjellerLabel, tomtArealLabel;
    private CustomJTextField antallEtasjerField, tomtArealField;
    private CustomJCheckBox harKjellerCheckBox;
    ///ENEBOLIG SLUTT///

    private CustomJButton avbrytButton, lagreButton;

    public BoligRegVindu(String tittel) {
        super(tittel);
        setSize(1000, 700);

        setLayout(new BorderLayout());

        ///PANELER///
        toppPanel = new CustomSubPanel("Topp", 50, 0);
        venstrePanel = new CustomSubPanel("Venstre", 0, 300);
        senterPanel = new CustomSubPanel("Senter", 0, 0);
        hoyrePanel = new CustomSubPanel("Høyre", 0, 150);
        bunnPanel = new CustomSubPanel("Bunn", 50, 0);

        add(toppPanel, BorderLayout.NORTH);
        add(venstrePanel, BorderLayout.WEST);
        add(senterPanel, BorderLayout.CENTER);
        add(hoyrePanel, BorderLayout.EAST);
        add(bunnPanel, BorderLayout.SOUTH);
        ///PANELER///
        //
        ///START PÅ RADIOKANPPER FOR BOLIGVALG///
        leilighetRButton = new CustomJRadioButton("Leilighet");
        eneboligRButton = new CustomJRadioButton("Enebolig");
        radioButtons = new ButtonGroup();
        radioButtons.add(leilighetRButton);
        radioButtons.add(eneboligRButton);
        CustomSubPanel boligTypePanel = new CustomSubPanel(new FlowLayout());
        boligTypePanel.add(leilighetRButton);
        boligTypePanel.add(eneboligRButton);
        toppPanel.add(boligTypePanel);
        ///START PÅ RADIOKANPPER FOR BOLIGVALG///
        //
        ///START PÅ DATAFELT FOR BOLIG///
        boligTypeLabel = new JLabel("Boligtype: ");
        eierLabel = new JLabel("Eier ID: ");
        meglerLabel = new JLabel("Megler ID: ");
        adresseLabel = new JLabel("Adresse: ");
        postNrLabel = new JLabel("Postnummer: ");
        postStedLabel = new JLabel("Poststed: ");
        boArealLabel = new JLabel("Boareal: ");
        byggeArLabel = new JLabel("Byggeår: ");
        erUtleidLabel = new JLabel("Utleid: ");
        beskrivelseLabel = new JLabel("Beskrivelse: ");

        eierField = new CustomJTextField("XXXX", RegexTester.PRIS, 10);
        meglerField = new CustomJTextField("XXXX", RegexTester.PRIS, 10);
        adresseField = new CustomJTextField("Gate", RegexTester.GATE_NAVN_PATTERN, 10);//TODO: Her må det endres til gateadresse + husnummer regex
        postNrField = new CustomJTextField("XXXX", RegexTester.POST_NUMMER_PATTERN, 10);
        postStedField = new CustomJTextField("Oslo", RegexTester.POSTORT_NAVN, 10);
        boArealField = new CustomJTextField("XX(X)", RegexTester.KVM_BOLIG, 10);
        byggeArField = new CustomJTextField("XXXX", RegexTester.YEAR, 10);
        erUtleidCheckBox = new CustomJCheckBox();
        beskrivelseTextArea = new JTextArea(5, 11);
        beskrivelseTextArea.setBorder(BorderFactory.createLineBorder(Color.gray));
        ///SLUTT PÅ DATAFELT FOR BOLIG///
        //
        ///START PÅ DATAFELT FOR LEILIGHET///
        etasjeNrLabel = new JLabel("Etasje nr: ");
        balkongArealLabel = new JLabel("Balkong areal: ");
        bodArealLabel = new JLabel("Areal bod: ");
        harHeisLabel = new JLabel("Heis: ");
        harGarasjeLabel = new JLabel("Garasje: ");
        harFellesVaskeri = new JLabel("Fellesvaskeri: ");
        
        etasjeNrField = new CustomJTextField("XX", RegexTester.ETASJE, 10);
        balkongArealField = new CustomJTextField("XX", RegexTester.KVM_BOLIG, 10);
        bodArealField = new CustomJTextField("XX", RegexTester.KVM_BOLIG, 10);
        
        harHeisCheckBox = new CustomJCheckBox();
        harGarasjeCheckBox = new CustomJCheckBox();
        harFellesVaskeriCheckbox = new CustomJCheckBox();
        ///SLUTT PÅ DATAFELT FOR LEILIGHET///
        //
        ///START PÅ DATAFELT FOR ENEBOLIG///
        antallEtasjerLabel = new JLabel("Antall etasjer");
        tomtArealLabel = new JLabel("Tomt areal");
        harKjellerLabel = new JLabel("Kjeller");
        
        antallEtasjerField = new CustomJTextField("XX", RegexTester.PRIS, 10);
        tomtArealField = new CustomJTextField("XX", RegexTester.KVM_TOMT, 10);
        
        harKjellerCheckBox = new CustomJCheckBox();
        ///SLUTT PÅ DATAFELT FOR ENEBOLIG///
        //
        //--//--//--//--//
        //    LAYOUT    //
        //--//--//--//--//
        ////////START PÅ GENERELL REGISTRERING AV BOLIG////////
        GridBagLayout BoligLayout = new GridBagLayout();
        CustomSubPanel boligPanel = new CustomSubPanel(BoligLayout);
        GridBagConstraints gcBolig = new GridBagConstraints();

        //Rad 1
        gcBolig.gridx = 0;
        gcBolig.gridy = 0;
        gcBolig.anchor = GridBagConstraints.LINE_START;
        boligPanel.add(eierLabel, gcBolig);
        gcBolig.gridx++;
        gcBolig.anchor = GridBagConstraints.LINE_START;
        boligPanel.add(eierField, gcBolig);

        //Rad 2
        gcBolig.gridx = 0;
        gcBolig.gridy++;
        boligPanel.add(meglerLabel, gcBolig);
        gcBolig.gridx++;
        boligPanel.add(meglerField, gcBolig);

        //Rad 3
        gcBolig.gridx = 0;
        gcBolig.gridy++;
        boligPanel.add(adresseLabel, gcBolig);
        gcBolig.gridx++;
        boligPanel.add(adresseField, gcBolig);

        //Rad 4
        gcBolig.gridx = 0;
        gcBolig.gridy++;
        boligPanel.add(postNrLabel, gcBolig);
        gcBolig.gridx++;
        boligPanel.add(postNrField, gcBolig);

        //Rad 5
        gcBolig.gridx = 0;
        gcBolig.gridy++;
        boligPanel.add(postStedLabel, gcBolig);
        gcBolig.gridx++;
        boligPanel.add(postStedField, gcBolig);

        //Rad 6
        gcBolig.gridx = 0;
        gcBolig.gridy++;
        boligPanel.add(boArealLabel, gcBolig);
        gcBolig.gridx++;
        boligPanel.add(boArealField, gcBolig);

        //Rad 7
        gcBolig.gridx = 0;
        gcBolig.gridy++;
        boligPanel.add(byggeArLabel, gcBolig);
        gcBolig.gridx++;
        boligPanel.add(byggeArField, gcBolig);

        //Rad 8
        gcBolig.gridx = 0;
        gcBolig.gridy++;
        boligPanel.add(erUtleidLabel, gcBolig);
        gcBolig.gridx++;
        boligPanel.add(erUtleidCheckBox, gcBolig);

        //Rad 9
        gcBolig.gridx = 0;
        gcBolig.gridy++;
        gcBolig.anchor = GridBagConstraints.FIRST_LINE_START;
        boligPanel.add(beskrivelseLabel, gcBolig);
        gcBolig.gridx++;
        boligPanel.add(beskrivelseTextArea, gcBolig);

        venstrePanel.add(boligPanel);
        ////////SLUTT PÅ GENERELL REGISTRERING AV BOLIG////////

        ////////START PÅ REGISTRERING AV LEILIGHET////////
        GridBagLayout leilighetLayout = new GridBagLayout();
        CustomSubPanel leilighetPanel = new CustomSubPanel(leilighetLayout);
        GridBagConstraints gcLeilighet = new GridBagConstraints();
        //sluttet her
        senterPanel.add(leilighetPanel);
        ////////SLUTT PÅ REGISTRERING AV LEILIGHET////////

        ////////START PÅ REGISTRERING AV ENEBOLIG////////
        ////////SLUTT PÅ REGISTRERING AV ENEBOLIG////////
        ///START PÅ AVBRYT OG LAGREKNAPPER///
        avbrytButton = new CustomJButton("Avbryt");
        lagreButton = new CustomJButton("Lagre");
        CustomSubPanel knappContainer = new CustomSubPanel(new BorderLayout());
        CustomSubPanel knappPanel = new CustomSubPanel(new FlowLayout());
        knappPanel.add(avbrytButton);
        knappPanel.add(lagreButton);
        knappContainer.add(knappPanel, BorderLayout.EAST);
        bunnPanel.add(knappContainer);
        ///SLUTT PÅ AVBRYT OG LAGRE KNAPPER///

        setVisible(true);

    }

}
