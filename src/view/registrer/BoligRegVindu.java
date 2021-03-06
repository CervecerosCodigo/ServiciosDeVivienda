package view.registrer;

import controller.VisMeldingInterface;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import lib.GuiSizes;
import lib.Ikoner;
import lib.Melding;
import lib.RegexTester;
import view.ComboDatoVelger;
import view.CustomJButton;
import view.CustomJCheckBox;
import view.CustomJRadioButton;
import view.CustomJTextField;


public class BoligRegVindu extends AbstractRegistreringsPanel implements VisMeldingInterface {

    ///PANELER///
    private CustomSubPanel boligPanel, leilighetPanel, eneboligPanel, bildePanel;
    private CustomSubPanel boligTypePanel, knappContainer, knappPanel, bildeKnapperPanel;
    ///SLUTT PÅ PANELER///
    //
    ///BOLIG///
    private final JLabel boligTypeLabel, eierLabel, meglerLabel, adresseLabel, postNrLabel, postStedLabel, boArealLabel, byggeArLabel, erUtleidLabel, beskrivelseLabel, tilgjengeligForUtleieLabel, bildeLabel, bildeKnappLabel;
    private JLabel bildeResultatLabel;
    private CustomJRadioButton leilighetRButton, eneboligRButton;
    private ComboDatoVelger datoVelger;
    private ButtonGroup radioButtons;
    private CustomJTextField eierField, meglerField, adresseField, postNrField, postStedField, boArealField, byggeArField;
    private JCheckBox erUtleidCheckBox;
    private JTextArea beskrivelseTextArea;
    private CustomJButton bildeButton, visFlereBilderButton;
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
        super(800, 700, tittel);

        ///PANELER///
        toppPanel = new CustomSubPanel(30, 0);
        venstrePanel = new CustomSubPanel("Felles", 0, 450);
        senterPanel = new CustomSubPanel(0, 0, new GridLayout(2, 1));
        bunnPanel = new CustomSubPanel(50, 0);

        boligTypePanel = new CustomSubPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        boligPanel = new CustomSubPanel(new GridBagLayout());
        leilighetPanel = new CustomSubPanel("Leilighet", 250, 200, new GridBagLayout());
        eneboligPanel = new CustomSubPanel("Enebolig", 250, 200, new GridBagLayout());
        knappContainer = new CustomSubPanel(new BorderLayout());
        knappPanel = new CustomSubPanel(new FlowLayout());
        bildeKnapperPanel = new CustomSubPanel(new GridLayout(1, 2));
        
        add(toppPanel, BorderLayout.NORTH);
        add(venstrePanel, BorderLayout.WEST);
        add(senterPanel, BorderLayout.CENTER);
        add(bunnPanel, BorderLayout.SOUTH);
        ///PANELER///
        //
        ///START PÅ RADIOKANPPER FOR BOLIGVALG///
        leilighetRButton = new CustomJRadioButton("Leilighet");
        eneboligRButton = new CustomJRadioButton("Enebolig");
        radioButtons = new ButtonGroup();
        radioButtons.add(leilighetRButton);
        radioButtons.add(eneboligRButton);

        boligTypePanel.add(leilighetRButton);
        boligTypePanel.add(eneboligRButton);
        toppPanel.add(boligTypePanel);
        leilighetRButton.setBorder(BorderFactory.createEtchedBorder()); // Midlertidig bugfiks for lengde på radioknapp tekst
        
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
        tilgjengeligForUtleieLabel = new JLabel("Kan leies fra: ");
        erUtleidLabel = new JLabel("Utleid: ");
        beskrivelseLabel = new JLabel("Beskrivelse: ");

        eierField = new CustomJTextField("#####", RegexTester.PRIS, GuiSizes.FIELD_MEDIUM);
        meglerField = new CustomJTextField("10001", RegexTester.PRIS, GuiSizes.FIELD_MEDIUM);
        adresseField = new CustomJTextField("Gate vei 10 B", RegexTester.GATE_ADRESSE_ENKEL, GuiSizes.FIELD_MEDIUM);
        postNrField = new CustomJTextField("XXXX", RegexTester.POST_NUMMER_PATTERN, GuiSizes.FIELD_MEDIUM);
        postStedField = new CustomJTextField("Oslo", RegexTester.POSTORT_NAVN, GuiSizes.FIELD_MEDIUM);
        boArealField = new CustomJTextField("XX(X)", RegexTester.KVM_BOLIG, GuiSizes.FIELD_MEDIUM);
        byggeArField = new CustomJTextField("XXXX", RegexTester.YEAR, GuiSizes.FIELD_MEDIUM);

        datoVelger = new ComboDatoVelger();
        erUtleidCheckBox = new CustomJCheckBox();
        beskrivelseTextArea = new JTextArea(GuiSizes.TEXTAREA_ROW_MEDIUM, GuiSizes.TEXTAREA_COL_MEDIUM);
        beskrivelseTextArea.setLineWrap(true);
        beskrivelseTextArea.setWrapStyleWord(true);
        beskrivelseTextArea.setMargin(new Insets(3, 3, 3, 3));
        JScrollPane beskrivelseScroll = new JScrollPane(beskrivelseTextArea);

        bildeLabel = new JLabel("Antall bilder: ");
        bildeResultatLabel = new JLabel("");
        bildeKnappLabel = new JLabel("Bilder: ");
        bildeButton = new CustomJButton(Ikoner.ATTACH_IMAGE);
        visFlereBilderButton = new CustomJButton(Ikoner.IMAGES);
        ///SLUTT PÅ DATAFELT FOR BOLIG///
        //
        ///START PÅ DATAFELT FOR LEILIGHET///
        etasjeNrLabel = new JLabel("Etasje nr: ");
        balkongArealLabel = new JLabel("Balkong areal: ");
        bodArealLabel = new JLabel("Areal bod: ");
        harHeisLabel = new JLabel("Heis: ");
        harGarasjeLabel = new JLabel("Garasje: ");
        harFellesVaskeri = new JLabel("Fellesvaskeri: ");

        etasjeNrField = new CustomJTextField("0", RegexTester.ETASJE, GuiSizes.FIELD_MEDIUM);
        balkongArealField = new CustomJTextField("0", RegexTester.KVM_BALKONG_BOD, GuiSizes.FIELD_MEDIUM);
        bodArealField = new CustomJTextField("0", RegexTester.KVM_BALKONG_BOD, GuiSizes.FIELD_MEDIUM);

        harHeisCheckBox = new CustomJCheckBox();
        harGarasjeCheckBox = new CustomJCheckBox();
        harFellesVaskeriCheckbox = new CustomJCheckBox();
        ///SLUTT PÅ DATAFELT FOR LEILIGHET///
        //
        ///START PÅ DATAFELT FOR ENEBOLIG///
        antallEtasjerLabel = new JLabel("Antall etasjer");
        tomtArealLabel = new JLabel("Tomt areal");
        harKjellerLabel = new JLabel("Kjeller");

        antallEtasjerField = new CustomJTextField("XX", RegexTester.PRIS, GuiSizes.FIELD_MEDIUM);
        tomtArealField = new CustomJTextField("XX", RegexTester.KVM_TOMT, GuiSizes.FIELD_MEDIUM);

        harKjellerCheckBox = new CustomJCheckBox();
        ///SLUTT PÅ DATAFELT FOR ENEBOLIG///
        //
        //--//--//--//--//
        //    LAYOUT    //
        //--//--//--//--//
        ////////START PÅ GENERELL REGISTRERING AV BOLIG////////

        GridBagConstraints gcBolig = new GridBagConstraints();


        bildeKnapperPanel.add(bildeButton);
        bildeKnapperPanel.add(visFlereBilderButton);

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
        boligPanel.add(tilgjengeligForUtleieLabel, gcBolig);
        gcBolig.gridx++;
        boligPanel.add(datoVelger, gcBolig);

        //Rad 9
        gcBolig.gridx = 0;
        gcBolig.gridy++;
        boligPanel.add(erUtleidLabel, gcBolig);
        gcBolig.gridx++;
        boligPanel.add(erUtleidCheckBox, gcBolig);

        //Rad 10
        gcBolig.gridx = 0;
        gcBolig.gridy++;
        gcBolig.anchor = GridBagConstraints.FIRST_LINE_START;
        boligPanel.add(beskrivelseLabel, gcBolig);
        gcBolig.gridx++;
        boligPanel.add(beskrivelseScroll, gcBolig);

        //Rad 11 BILDER
        gcBolig.gridx = 0;
        gcBolig.gridy++;
        boligPanel.add(bildeLabel, gcBolig);
        gcBolig.gridx++;
        boligPanel.add(bildeResultatLabel, gcBolig);

        //Rad 12 Knapper for å laste opp og vise bilder
        gcBolig.gridx = 0;
        gcBolig.gridy++;
        boligPanel.add(bildeKnappLabel, gcBolig);
        gcBolig.gridx++;
        boligPanel.add(bildeKnapperPanel, gcBolig);

        venstrePanel.add(boligPanel);
        ////////SLUTT PÅ GENERELL REGISTRERING AV BOLIG////////

        ////////START PÅ REGISTRERING AV LEILIGHET////////
        GridBagConstraints gcLeilighet = new GridBagConstraints();

        //Rad 1
        gcLeilighet.gridx = 0;
        gcLeilighet.gridy = 0;
        gcLeilighet.anchor = GridBagConstraints.LINE_START;
        leilighetPanel.add(etasjeNrLabel, gcLeilighet);

        gcLeilighet.gridx++;
        gcLeilighet.anchor = GridBagConstraints.LINE_START;
        leilighetPanel.add(etasjeNrField, gcLeilighet);

        //Rad 2
        gcLeilighet.gridx = 0;
        gcLeilighet.gridy++;
        leilighetPanel.add(balkongArealLabel, gcLeilighet);
        gcLeilighet.gridx++;
        leilighetPanel.add(balkongArealField, gcLeilighet);

        //Rad 3
        gcLeilighet.gridx = 0;
        gcLeilighet.gridy++;
        leilighetPanel.add(bodArealLabel, gcLeilighet);
        gcLeilighet.gridx++;
        leilighetPanel.add(bodArealField, gcLeilighet);

        //Rad 4
        gcLeilighet.gridx = 0;
        gcLeilighet.gridy++;
        leilighetPanel.add(harHeisLabel, gcLeilighet);
        gcLeilighet.gridx++;
        leilighetPanel.add(harHeisCheckBox, gcLeilighet);

        //Rad 5
        gcLeilighet.gridx = 0;
        gcLeilighet.gridy++;
        leilighetPanel.add(harGarasjeLabel, gcLeilighet);
        gcLeilighet.gridx++;
        leilighetPanel.add(harGarasjeCheckBox, gcLeilighet);

        //Rad 6
        gcLeilighet.gridx = 0;
        gcLeilighet.gridy++;
        leilighetPanel.add(harFellesVaskeri, gcLeilighet);
        gcLeilighet.gridx++;
        leilighetPanel.add(harFellesVaskeriCheckbox, gcLeilighet);

        senterPanel.add(leilighetPanel);
        ////////SLUTT PÅ REGISTRERING AV LEILIGHET////////
        //
        ////////START PÅ REGISTRERING AV ENEBOLIG////////

        GridBagConstraints gcEnebolig = new GridBagConstraints();

        //Rad 1
        gcEnebolig.gridx = 0;
        gcEnebolig.gridy = 0;
        gcEnebolig.anchor = GridBagConstraints.LINE_START;
        eneboligPanel.add(antallEtasjerLabel, gcEnebolig);

        gcEnebolig.gridx++;
        gcEnebolig.anchor = GridBagConstraints.LINE_START;
        eneboligPanel.add(antallEtasjerField, gcEnebolig);

        //Rad 2
        gcEnebolig.gridx = 0;
        gcEnebolig.gridy++;
        eneboligPanel.add(tomtArealLabel, gcEnebolig);
        gcEnebolig.gridx++;
        eneboligPanel.add(tomtArealField, gcEnebolig);
        //Rad 2
        gcEnebolig.gridx = 0;
        gcEnebolig.gridy++;
        eneboligPanel.add(harKjellerLabel, gcEnebolig);
        gcEnebolig.gridx++;
        eneboligPanel.add(harKjellerCheckBox, gcEnebolig);

        senterPanel.add(eneboligPanel);
        ////////SLUTT PÅ REGISTRERING AV ENEBOLIG////////
        //
        ///START PÅ AVBRYT OG LAGREKNAPPER///
        avbrytButton = new CustomJButton("Avbryt");
        lagreButton = new CustomJButton("Lagre");

        knappPanel.add(avbrytButton);
        knappPanel.add(lagreButton);
        knappContainer.add(knappPanel, BorderLayout.EAST);
        bunnPanel.add(knappContainer);
        ///SLUTT PÅ AVBRYT OG LAGRE KNAPPER///
        //
        ///START PÅ AKTIVERING OG DEAKTIVERING AV KOMPONENTER///
        leilighetRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deaktiverAlleKomponenter();
                aktiverBoligKomponenter();
                aktiverLeilighetKomponenter();
                deaktiverBildeKnapper();
            }
        });

        eneboligRButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                deaktiverAlleKomponenter();
                aktiverBoligKomponenter();
                aktiverEneboligKomponenter();
            }
        });
        ///SLUTT PÅ AKTIVERING OG DEAKTIVERING AV KOMPONENTER///
        //
        ///LUKKE VINDU///
        avbrytButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        ///SLUTT PÅ LUKKE VINDU///

        deaktiverAlleKomponenter();
        setVisible(true);

        //--//--//--//--//--//--//--//--//--//--//--//--//--//--//
        //                SLUTT PÅ KONSTRUKTØR                  //
        //--//--//--//--//--//--//--//--//--//--//--//--//--//--//
    }

    private void deaktiverAlleKomponenter() {
        Component[] compBolig = boligPanel.getComponents();
        Component[] compLeilighet = leilighetPanel.getComponents();
        Component[] compEnebolig = eneboligPanel.getComponents();

        for (Component comp1 : compBolig) {
            comp1.setEnabled(false);
        }
        
        for (Component comp1 : compLeilighet) {
            comp1.setEnabled(false);
        }
        
        for (Component comp1 : compEnebolig) {
            comp1.setEnabled(false);
        }
        
        beskrivelseTextArea.setEditable(false);
        lagreButton.setEnabled(false);
    }

    public void aktiverBoligKomponenter() {
        Component[] compBolig = boligPanel.getComponents();
        for (Component comp1 : compBolig) {
            comp1.setEnabled(true);
        }
        
        eierField.setEnabled(false);
        meglerField.setEnabled(false);
        beskrivelseTextArea.setEditable(true);
        lagreButton.setEnabled(true);
    }

    public void aktiverLeilighetKomponenter() {
        Component[] compLeilighet = leilighetPanel.getComponents();
        for (Component comp1 : compLeilighet) {
            comp1.setEnabled(true);
        }
    }

    public void aktiverEneboligKomponenter() {
        Component[] compEnebolig = eneboligPanel.getComponents();
        for (Component comp1 : compEnebolig) {
            comp1.setEnabled(true);
        }
    }
    
    public void deaktiverBildeKnapper(){
        bildeButton.setEnabled(false);
        visFlereBilderButton.setEnabled(false);
    }
    
    public void aktiverBildeKnapper(){
        bildeButton.setEnabled(true);
        visFlereBilderButton.setEnabled(true);
    }

    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
    //||//                                                              //||//
    //||//                                                              //||//
    //||//                                                              //||//
    //||//                                                              //||//
    //||//                                                              //||//
    //||//                                                              //||//
    //||//                                                              //||//
    //||//                      GETTERS OG SETTERS                      //||//
    //||//                                                              //||//
    //||//                                                              //||//
    //||//                                                              //||//
    //||//                                                              //||//
    //||//                                                              //||//
    //||//                                                              //||//
    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
    public CustomSubPanel getToppPanel() {
        return toppPanel;
    }

    public void setToppPanel(CustomSubPanel toppPanel) {
        this.toppPanel = toppPanel;
    }

    public CustomSubPanel getVenstrePanel() {
        return venstrePanel;
    }

    public void setVenstrePanel(CustomSubPanel venstrePanel) {
        this.venstrePanel = venstrePanel;
    }

    public CustomSubPanel getHoyrePanel() {
        return hoyrePanel;
    }

    public void setHoyrePanel(CustomSubPanel hoyrePanel) {
        this.hoyrePanel = hoyrePanel;
    }

    public CustomSubPanel getBoligPanel() {
        return boligPanel;
    }

    public void setBoligPanel(CustomSubPanel boligPanel) {
        this.boligPanel = boligPanel;
    }

    public CustomSubPanel getLeilighetPanel() {
        return leilighetPanel;
    }

    public void setLeilighetPanel(CustomSubPanel leilighetPanel) {
        this.leilighetPanel = leilighetPanel;
    }

    public CustomSubPanel getEneboligPanel() {
        return eneboligPanel;
    }

    public void setEneboligPanel(CustomSubPanel eneboligPanel) {
        this.eneboligPanel = eneboligPanel;
    }

    public CustomJRadioButton getLeilighetRButton() {
        return leilighetRButton;
    }

    public void setLeilighetRButton(CustomJRadioButton leilighetRButton) {
        this.leilighetRButton = leilighetRButton;
    }

    public CustomJRadioButton getEneboligRButton() {
        return eneboligRButton;
    }

    public void setEneboligRButton(CustomJRadioButton eneboligRButton) {
        this.eneboligRButton = eneboligRButton;
    }

    public ButtonGroup getRadioButtons() {
        return radioButtons;
    }

    public void setRadioButtons(ButtonGroup radioButtons) {
        this.radioButtons = radioButtons;
    }

    public CustomJTextField getEierField() {
        return eierField;
    }

    public void setEierField(CustomJTextField eierField) {
        this.eierField = eierField;
    }

    public CustomJTextField getMeglerField() {
        return meglerField;
    }

    public void setMeglerField(CustomJTextField meglerField) {
        this.meglerField = meglerField;
    }

    public CustomJTextField getAdresseField() {
        return adresseField;
    }

    public void setAdresseField(CustomJTextField adresseField) {
        this.adresseField = adresseField;
    }

    public CustomJTextField getPostNrField() {
        return postNrField;
    }

    public void setPostNrField(CustomJTextField postNrField) {
        this.postNrField = postNrField;
    }

    public CustomJTextField getPostStedField() {
        return postStedField;
    }

    public void setPostStedField(CustomJTextField postStedField) {
        this.postStedField = postStedField;
    }

    public CustomJTextField getBoArealField() {
        return boArealField;
    }

    public void setBoArealField(CustomJTextField boArealField) {
        this.boArealField = boArealField;
    }

    public CustomJTextField getByggeArField() {
        return byggeArField;
    }

    public void setByggeArField(CustomJTextField byggeArField) {
        this.byggeArField = byggeArField;
    }

    public JCheckBox getErUtleidCheckBox() {
        return erUtleidCheckBox;
    }

    public void setErUtleidCheckBox(JCheckBox erUtleidCheckBox) {
        this.erUtleidCheckBox = erUtleidCheckBox;
    }

    public JTextArea getBeskrivelseTextArea() {
        return beskrivelseTextArea;
    }

    public void setBeskrivelseTextArea(JTextArea beskrivelseTextArea) {
        this.beskrivelseTextArea = beskrivelseTextArea;
    }

    public CustomJTextField getEtasjeNrField() {
        return etasjeNrField;
    }

    public void setEtasjeNrField(CustomJTextField etasjeNrField) {
        this.etasjeNrField = etasjeNrField;
    }

    public CustomJTextField getBalkongArealField() {
        return balkongArealField;
    }

    public void setBalkongArealField(CustomJTextField balkongArealField) {
        this.balkongArealField = balkongArealField;
    }

    public CustomJTextField getBodArealField() {
        return bodArealField;
    }

    public void setBodArealField(CustomJTextField bodArealField) {
        this.bodArealField = bodArealField;
    }

    public CustomJCheckBox getHarHeisCheckBox() {
        return harHeisCheckBox;
    }

    public void setHarHeisCheckBox(CustomJCheckBox harHeisCheckBox) {
        this.harHeisCheckBox = harHeisCheckBox;
    }

    public CustomJCheckBox getHarGarasjeCheckBox() {
        return harGarasjeCheckBox;
    }

    public void setHarGarasjeCheckBox(CustomJCheckBox harGarasjeCheckBox) {
        this.harGarasjeCheckBox = harGarasjeCheckBox;
    }

    public CustomJCheckBox getHarFellesVaskeriCheckbox() {
        return harFellesVaskeriCheckbox;
    }

    public void setHarFellesVaskeriCheckbox(CustomJCheckBox harFellesVaskeriCheckbox) {
        this.harFellesVaskeriCheckbox = harFellesVaskeriCheckbox;
    }

    public CustomJTextField getAntallEtasjerField() {
        return antallEtasjerField;
    }

    public void setAntallEtasjerField(CustomJTextField antallEtasjerField) {
        this.antallEtasjerField = antallEtasjerField;
    }

    public CustomJTextField getTomtArealField() {
        return tomtArealField;
    }

    public void setTomtArealField(CustomJTextField tomtArealField) {
        this.tomtArealField = tomtArealField;
    }

    public CustomJCheckBox getHarKjellerCheckBox() {
        return harKjellerCheckBox;
    }

    public void setHarKjellerCheckBox(CustomJCheckBox harKjellerCheckBox) {
        this.harKjellerCheckBox = harKjellerCheckBox;
    }

    public CustomJButton getLagreButton() {
        return lagreButton;
    }

    public void setLagreButton(CustomJButton lagreButton) {
        this.lagreButton = lagreButton;
    }

    public JLabel getBildeResultatLabel() {
        return bildeResultatLabel;
    }

    public void setBildeResultatLabel(JLabel bildeResultatLabel) {
        this.bildeResultatLabel = bildeResultatLabel;
    }

    public CustomJButton getBildeButton() {
        return bildeButton;
    }

    public void setBildeButton(CustomJButton bildeButton) {
        this.bildeButton = bildeButton;
    }

    public CustomJButton getVisFlereBilderButton() {
        return visFlereBilderButton;
    }

    public void setVisFlereBilderButton(CustomJButton visFlereBilderButton) {
        this.visFlereBilderButton = visFlereBilderButton;
    }

    public ComboDatoVelger getDatoVelger() {
        return datoVelger;
    }

    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
    //||//                                                              //||//
    //||//                                                              //||//
    //||//                          LYTTER                              //||//
    //||//                                                              //||//
    //||//                                                              //||//
    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
    /**
     * Legger til en ekstern lytter slik at ved å klikke "lagre" blir data sendt
     * inn til controller.
     *
     * @param lytter
     */
    public void setKnappeLytter(ActionListener lytter) {
        lagreButton.addActionListener(lytter);
        visFlereBilderButton.addActionListener(lytter);
        bildeButton.addActionListener(lytter);
    }

    @Override
    public void visMelding(String overskrift, String melding) {
        Melding.visMelding(overskrift, melding);
    }
}
