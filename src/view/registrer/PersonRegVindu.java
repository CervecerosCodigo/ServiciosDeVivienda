package view.registrer;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import lib.RegexTester;
import view.CustomJButton;
import view.CustomJCheckBox;
import view.CustomJTextField;

public class PersonRegVindu extends AbstractRegistreringsPanel {


    
    private JLabel fornavnLabel, etternavnLabel, epostLabel, telefonLabel, erRepresntantLabel, erRepresentatnForLabel;
    private CustomJTextField fornavnField, etternavnField, epostField, telefonField, erRepresentatForField;
    private CustomJCheckBox erRepresentantCheckBox;
    private CustomJButton avbrytButton, lagreButton;

    public PersonRegVindu(String tittel) {
        super(350, 250, tittel);

        senterPanel = new CustomSubPanel("", 0, 0, new GridBagLayout());
        add(senterPanel);

        fornavnLabel = new JLabel("Fornavn: ");
        etternavnLabel = new JLabel("Etternavn: ");
        epostLabel = new JLabel("Epost: ");
        telefonLabel = new JLabel("Telefon: ");
        erRepresntantLabel = new JLabel("Er representant: ");
        erRepresentatnForLabel = new JLabel("Representerer: ");

        fornavnField = new CustomJTextField("Ola", RegexTester.NAVN_PATTERN, 10);
        etternavnField = new CustomJTextField("Normann", RegexTester.NAVN_PATTERN, 10);
        epostField = new CustomJTextField("ola.normann@epost.com", RegexTester.EPOST_PATTERN, 10);
        telefonField = new CustomJTextField("XX XX XX XX", RegexTester.TEL_NUMMER_NORSK, 10);
        erRepresentatForField = new CustomJTextField("Navn på representat", RegexTester.NAVN_PATTERN, 10);
        //Cheboxes
        erRepresentantCheckBox = new CustomJCheckBox();
        //Knapper
        avbrytButton = new CustomJButton("Avbryt");
        lagreButton = new CustomJButton("Lagre");

        erRepresentatnForLabel.setEnabled(false);
        erRepresentatForField.setEnabled(false);

        addPersonPanelListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource().equals(getAvbrytButton())){
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

    public void addPersonPanelListener(ActionListener lytter) {
        avbrytButton.addActionListener(lytter);
        lagreButton.addActionListener(lytter);
    }

}
