package view.registrer;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import lib.GuiSizes;
import lib.Konstanter;
import lib.RegexTester;
import view.CustomJButton;
import view.CustomJCheckBox;
import view.CustomJTextField;

public class PersonRegVindu extends AbstractRegistreringsPanel {

    private JLabel fornavnLabel, etternavnLabel, epostLabel, telefonLabel, erRepresntantLabel, erRepresentatnForLabel;
    private CustomJTextField fornavnField, etternavnField, epostField, telefonField, erRepresentatForField;
    private CustomJCheckBox erRepresentantCheckBox;
    private CustomJButton avbrytButton, lagreButton;

    //Componenter for en Leietaker
    private JLabel fodselsArLabel, antPersonerHusholdLabel, sivilStatusLabel, arbeidsForholdLabel, yrkeLabel, soknadsTekstLabel;
    private JComboBox fodselsArCombo, antPersonerHusholdCombo, sivilStatusCombo, arbeidsForholdCombo;
    private CustomJTextField yrkeField;
    private JTextArea soknadsTextArea;
    private JScrollPane soknadsScroll;

//     this.fodselsAr = fodselsAr;
//        this.antPersoner = antPersoner;
//        this.sivilstatus = sivilstatus;
//        this.yrke = yrke;
//        this.arbeidsforhold = arbeidsforhold;
//        this.soknadsTekst = soknadsTekst;
    public PersonRegVindu(int bredde, int hoyde, String tittel) {
        super(450, 400, tittel);

        senterPanel = new CustomSubPanel("", 0, 0, new GridBagLayout());
        add(senterPanel);

        fornavnLabel = new JLabel("Fornavn: ");
        etternavnLabel = new JLabel("Etternavn: ");
        epostLabel = new JLabel("Epost: ");
        telefonLabel = new JLabel("Telefon: ");
        erRepresntantLabel = new JLabel("Er representant: ");
        erRepresentatnForLabel = new JLabel("Representerer: ");

        fornavnField = new CustomJTextField("Ola", RegexTester.NAVN_PATTERN, GuiSizes.FIELD_MEDIUM);
        etternavnField = new CustomJTextField("Normann", RegexTester.NAVN_PATTERN, GuiSizes.FIELD_MEDIUM);
        epostField = new CustomJTextField("ola.normann@epost.com", RegexTester.EPOST_PATTERN, GuiSizes.FIELD_MEDIUM);
        telefonField = new CustomJTextField("XX XX XX XX", RegexTester.TEL_NUMMER_NORSK, GuiSizes.FIELD_MEDIUM);
        erRepresentatForField = new CustomJTextField("Navn på representat", RegexTester.NAVN_PATTERN, GuiSizes.FIELD_MEDIUM);
        //Cheboxes
        erRepresentantCheckBox = new CustomJCheckBox();
        //Componenter for en leietaker
        fodselsArLabel = new JLabel("Fødselsår: ");
        antPersonerHusholdLabel = new JLabel("Pers i husholdet: ");
        sivilStatusLabel = new JLabel("Sivilstatus: ");
        arbeidsForholdLabel = new JLabel("Arbeidsforhold: ");
        yrkeLabel = new JLabel("Yrke: ");
        soknadsTekstLabel = new JLabel("Søknadstekst: ");
        fodselsArCombo = new JComboBox();
        //Begrenser slik at kun folk som er minst 18 år får leie boligen.
        for (int i = 1920; i <= (Calendar.getInstance().get(Calendar.YEAR) - 18); i++) {
            fodselsArCombo.addItem(i);
        }
        fodselsArCombo.setSelectedIndex(fodselsArCombo.getItemCount() - 1);
        fodselsArCombo.setPreferredSize(GuiSizes.COMBOBOX_MEDIUM);

        antPersonerHusholdCombo = new JComboBox();
        for (int i = 1; i < 10; i++) {
            antPersonerHusholdCombo.addItem(i);
        }
        antPersonerHusholdCombo.setPreferredSize(GuiSizes.COMBOBOX_MEDIUM);
        
        
        sivilStatusCombo = new JComboBox(Konstanter.SIVILSTATUS);
        sivilStatusCombo.setPreferredSize(GuiSizes.COMBOBOX_MEDIUM);
        
        arbeidsForholdCombo = new JComboBox(Konstanter.ARBEIDSFORHOLD);
        arbeidsForholdCombo.setPreferredSize(GuiSizes.COMBOBOX_MEDIUM);

        yrkeField = new CustomJTextField("Sjåfør", RegexTester.KUN_BOKSTAVER, GuiSizes.FIELD_MEDIUM);
        soknadsTextArea = new JTextArea(GuiSizes.TEXTAREA_ROW_MEDIUM, GuiSizes.TEXTAREA_COL_MEDIUM);
        soknadsTextArea.setLineWrap(true);
        soknadsTextArea.setWrapStyleWord(true);
        soknadsTextArea.setMargin(new Insets(3, 3, 3, 3));
        soknadsScroll = new JScrollPane(soknadsTextArea);
        

        //Knapper
        avbrytButton = new CustomJButton("Avbryt");
        lagreButton = new CustomJButton("Lagre");

        erRepresentatnForLabel.setEnabled(false);
        erRepresentatForField.setEnabled(false);

        addPersonPanelListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(getAvbrytButton())) {
                    dispose();
                }
            }
        });

        erRepresentantCheckBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean erKrysset = erRepresentantCheckBox.isSelected();
                erRepresentatnForLabel.setEnabled(erKrysset);
                erRepresentatForField.setEnabled(erKrysset);
            }
        });

        opprettLayout();
    }

    public void opprettLayout() {

        GridBagConstraints gc = new GridBagConstraints();

        //Rad 1
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        senterPanel.add(fornavnLabel, gc);

        gc.gridx = 1;
        gc.weightx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        senterPanel.add(fornavnField, gc);

        //Rad 2
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy++;

        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        senterPanel.add(etternavnLabel, gc);

        gc.gridx = 1;
//        gc.gridy++;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        senterPanel.add(etternavnField, gc);

        //Rad 3
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        senterPanel.add(epostLabel, gc);

        gc.gridx = 1;
//        gc.gridy++;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        senterPanel.add(epostField, gc);

        //Rad 4
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        senterPanel.add(telefonLabel, gc);

        gc.gridx = 1;
//        gc.gridy++;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        senterPanel.add(telefonField, gc);

        //Rad 5
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        senterPanel.add(erRepresntantLabel, gc);

        gc.gridx = 1;
//        gc.gridy++;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        senterPanel.add(erRepresentantCheckBox, gc);

        //Rad 6
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        senterPanel.add(erRepresentatnForLabel, gc);

        gc.gridx = 1;
//        gc.gridy++;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        senterPanel.add(erRepresentatForField, gc);

        //Ny rad for leietaker
        //Fødselår
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        senterPanel.add(fodselsArLabel, gc);

        gc.gridx = 1;
//        gc.gridy++;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        senterPanel.add(fodselsArCombo, gc);

        //Personer i husholdet
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        senterPanel.add(antPersonerHusholdLabel, gc);

        gc.gridx = 1;
//        gc.gridy++;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        senterPanel.add(antPersonerHusholdCombo, gc);

        //Sivilstatus
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        senterPanel.add(sivilStatusLabel, gc);

        gc.gridx = 1;
//        gc.gridy++;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        senterPanel.add(sivilStatusCombo, gc);

        //Arbeidsforhold
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        senterPanel.add(arbeidsForholdLabel, gc);

        gc.gridx = 1;
//        gc.gridy++;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        senterPanel.add(arbeidsForholdCombo, gc);

        //Yrke
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        senterPanel.add(yrkeLabel, gc);

        gc.gridx = 1;
//        gc.gridy++;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        senterPanel.add(yrkeField, gc);
        
        //soknadsTekst
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        senterPanel.add(soknadsTekstLabel, gc);

        gc.gridx = 1;
//        gc.gridy++;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        senterPanel.add(soknadsScroll, gc);

        //Rad 7
        gc.weightx = 0.1;
        gc.weighty = 1;

        gc.gridx = 1;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        senterPanel.add(avbrytButton, gc);

        gc.gridx = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        senterPanel.add(lagreButton, gc);
    }

    //Getters
    public CustomJTextField getFornavnField() {
        return fornavnField;
    }

    public CustomJTextField getEtternavnField() {
        return etternavnField;
    }

    public CustomJTextField getEpostField() {
        return epostField;
    }

    public CustomJTextField getTelefonField() {
        return telefonField;
    }

    public CustomJTextField getErRepresentantForField() {
        return erRepresentatForField;
    }

    public CustomJButton getLagreButton() {
        return lagreButton;
    }

    public CustomJButton getAvbrytButton() {
        return avbrytButton;
    }

    public CustomJCheckBox getErRepresentantCheckBox() {
        return erRepresentantCheckBox;
    }

    public JLabel getErRepresentantLabel() {
        return erRepresntantLabel;
    }

    public JLabel getErRepresentantForLabel() {
        return erRepresentatnForLabel;
    }

    //Setters
    public void setFornavnField(CustomJTextField fornavnField) {
        this.fornavnField = fornavnField;
    }

    public void setEtternavnField(CustomJTextField etternavnField) {
        this.etternavnField = etternavnField;
    }

    public void setEpostField(CustomJTextField epostField) {
        this.epostField = epostField;
    }

    public void setTelefonField(CustomJTextField telefonField) {
        this.telefonField = telefonField;
    }

    public void setErRepresentatForField(CustomJTextField erRepresentatForField) {
        this.erRepresentatForField = erRepresentatForField;
    }

    public void setErRepresentantCheckBox(CustomJCheckBox erRepresentantCheckBox) {
        this.erRepresentantCheckBox = erRepresentantCheckBox;
    }

    //__//__//__//__//__//__//__//__//__//__//__//__//__//__//__//__//__//__//__//__//
    //GETTERs FOR LEIETAKER
    //__//__//__//__//__//__//__//__//__//__//__//__//__//__//__//__//__//__//__//__//
    public JComboBox getFodselsArCombo() {
        return fodselsArCombo;
    }

    public JComboBox getAntPersonerHusholdCombo() {
        return antPersonerHusholdCombo;
    }

    public JComboBox getSivilStatusCombo() {
        return sivilStatusCombo;
    }

    public JComboBox getArbeidsForholdCombo() {
        return arbeidsForholdCombo;
    }

    public CustomJTextField getYrkeField() {
        return yrkeField;
    }

    public JTextArea getSoknadsTextArea() {
        return soknadsTextArea;
    }

    public JLabel getFodselsArLabel() {
        return fodselsArLabel;
    }

    public JLabel getAntPersonerHusholdLabel() {
        return antPersonerHusholdLabel;
    }

    public JLabel getSivilStatusLabel() {
        return sivilStatusLabel;
    }

    public JLabel getArbeidsForholdLabel() {
        return arbeidsForholdLabel;
    }

    public JLabel getYrkeLabel() {
        return yrkeLabel;
    }

    public JLabel getSoknadsTekstLabel() {
        return soknadsTekstLabel;
    }

    public JScrollPane getSoknadsScroll() {
        return soknadsScroll;
    }

    
    
    
    /**
     * Setter opp lytter
     * @param lytter 
     */
    public void addPersonPanelListener(ActionListener lytter) {
        avbrytButton.addActionListener(lytter);
        lagreButton.addActionListener(lytter);
    }

}
