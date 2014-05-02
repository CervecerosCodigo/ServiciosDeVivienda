package view.registrer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import lib.Melding;
import lib.RegexTester;
import view.CustomJButton;
import view.CustomJCheckBox;
import view.CustomJRadioButton;
import view.CustomJTextField;
import view.VenstrePanel;

/**
 *
 * File: BoligRegVindu.java Package: view.registrer Project: ServiciosDeVivienda
 * Apr 29, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class BoligRegVindu extends JFrame {

    ///PANELER///
    private CustomSubPanel toppPanel, venstrePanel, senterPanel, hoyrePanel, bunnPanel;
    private BorderLayout borderLayout;
    private CustomSubPanel boligPanel, leilighetPanel, eneboligPanel;
    ///SLUTT PÅ PANELER///
    //
    ///BOLIG///
    private JLabel boligTypeLabel, eierLabel, meglerLabel, adresseLabel, postNrLabel, postStedLabel, boArealLabel, byggeArLabel, erUtleidLabel, beskrivelseLabel, tilgjengeligForUtleieLabel;
    private CustomJRadioButton leilighetRButton, eneboligRButton;
    private JComboBox dagCombo, manedCombo, arCombo;
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
        hoyrePanel = new CustomSubPanel("Høyre", 0, 300);
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
        tilgjengeligForUtleieLabel = new JLabel("Kan leies fra: ");
        erUtleidLabel = new JLabel("Utleid: ");
        beskrivelseLabel = new JLabel("Beskrivelse: ");

        eierField = new CustomJTextField("XXXX", RegexTester.PRIS, 10);
        meglerField = new CustomJTextField("XXXX", RegexTester.PRIS, 10);
        adresseField = new CustomJTextField("Gate", RegexTester.GATE_ADRESSE, 10);//TODO: Her må det endres til gateadresse + husnummer regex
        postNrField = new CustomJTextField("XXXX", RegexTester.POST_NUMMER_PATTERN, 10);
        postStedField = new CustomJTextField("Oslo", RegexTester.POSTORT_NAVN, 10);
        boArealField = new CustomJTextField("XX(X)", RegexTester.KVM_BOLIG, 10);
        byggeArField = new CustomJTextField("XXXX", RegexTester.YEAR, 10);
        dagCombo = new JComboBox<>();
        manedCombo = new JComboBox<>();
        arCombo = new JComboBox<>();
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
        boligPanel = new CustomSubPanel(BoligLayout);
        GridBagConstraints gcBolig = new GridBagConstraints();
        CustomSubPanel datoPickPanel = new CustomSubPanel(new FlowLayout());
        datoPickPanel.add(arCombo);
        datoPickPanel.add(manedCombo);
        datoPickPanel.add(dagCombo);

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
        boligPanel.add(datoPickPanel, gcBolig);

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
        boligPanel.add(beskrivelseTextArea, gcBolig);

        venstrePanel.add(boligPanel);
        ////////SLUTT PÅ GENERELL REGISTRERING AV BOLIG////////

        ////////START PÅ REGISTRERING AV LEILIGHET////////
        GridBagLayout leilighetLayout = new GridBagLayout();
        leilighetPanel = new CustomSubPanel(leilighetLayout);
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
        GridBagLayout eneboligLayout = new GridBagLayout();
        eneboligPanel = new CustomSubPanel(BoligLayout);
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

        hoyrePanel.add(eneboligPanel);
        ////////SLUTT PÅ REGISTRERING AV ENEBOLIG////////
        //
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
        //
        ///START PÅ AKTIVERING OG DEAKTIVERING AV KOMPONENTER///
        leilighetRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deaktiverAlleKomponenter();
                aktiverBoligKomponenter();
                aktiverLeilighetKomponenter();
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
        fyllDatoComboBoxerMndAr();
        setAntallDager();

        ///START VALG AV DATO///
        /**
         * Dersom man endrer år elle måned endres antall dager i comboboksen for
         * dager
         */
        arCombo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setAntallDager();
            }
        });
        manedCombo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setAntallDager();
            }
        });
        ///SLUTT PÅ VALG AV DATO///

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
        //Må sette disse manuellt ettersom de ikke vil fungere som resten
        arCombo.setEnabled(false);
        manedCombo.setEnabled(false);
        dagCombo.setEnabled(false);
        lagreButton.setEnabled(false);
    }

    private void aktiverBoligKomponenter() {
        Component[] compBolig = boligPanel.getComponents();
        for (Component comp1 : compBolig) {
            comp1.setEnabled(true);
        }
        //Må sette disse manuellt ettersom de ikke vil fungere som resten
        arCombo.setEnabled(true);
        manedCombo.setEnabled(true);
        dagCombo.setEnabled(true);
        lagreButton.setEnabled(true);
    }

    private void aktiverLeilighetKomponenter() {
        Component[] compLeilighet = leilighetPanel.getComponents();
        for (Component comp1 : compLeilighet) {
            comp1.setEnabled(true);
        }
    }

    private void aktiverEneboligKomponenter() {
        Component[] compEnebolig = eneboligPanel.getComponents();
        for (Component comp1 : compEnebolig) {
            comp1.setEnabled(true);
        }
    }

    /**
     * Fyller combobokser for år og måneder.
     */
    private void fyllDatoComboBoxerMndAr() {
        int ar = Calendar.getInstance().get(Calendar.YEAR);

        for (int i = ar; i <= (ar + 10); i++) {
            arCombo.addItem(i);
        }
        for (int i = 1; i <= 12; i++) {
            String mnd = String.valueOf(i);
            if (i < 10) {
                mnd = "0" + mnd;
            }
            manedCombo.addItem(mnd);
        }

    }

    /**
     * Setter antall dager for comboboxen etter at man har valgt år og måned.
     */
    private void setAntallDager() {
        dagCombo.removeAllItems();
        int ar = Integer.parseInt(arCombo.getSelectedItem().toString());
        int mnd = Integer.parseInt(manedCombo.getSelectedItem().toString());
        int dager = getAntallDager(ar, mnd);

        for (int i = 1; i <= dager; i++) {
            String dag = String.valueOf(i);
            if (i < 10) {
                dag = "0" + dag;
            }
            dagCombo.addItem(dag);
        }

//        Melding.visMelding("", "År: "+ar+"\nMåned:"+mnd+"\nAntall dager:"+getAntallDager(ar, mnd));
    }

    /**
     * Returnerer antall dager i en måned girr år og måned.
     *
     * @param ar
     * @param mnd
     * @return int antall dager mellom 1 og 31
     */
    private int getAntallDager(int ar, int mnd) {
        Calendar cal = new GregorianCalendar(ar, mnd - 1, 1);
        int antallDager = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return antallDager;
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

    public JComboBox getDagCombo() {
        return dagCombo;
    }

    public void setDagCombo(JComboBox dagCombo) {
        this.dagCombo = dagCombo;
    }

    public JComboBox getManedCombo() {
        return manedCombo;
    }

    public void setManedCombo(JComboBox manedCombo) {
        this.manedCombo = manedCombo;
    }

    public JComboBox getArCombo() {
        return arCombo;
    }

    public void setArCombo(JComboBox arCombo) {
        this.arCombo = arCombo;
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
    }

}
