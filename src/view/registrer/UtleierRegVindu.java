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

public class UtleierRegVindu extends JFrame {

    private CustomSubPanel panel;
    private String tittel;

    private JLabel fornavnLabel, etternavnLabel, epostLabel, telefonLabel, erRepresntantLabel, erRepresentatnForLabel;
    private CustomJTextField fornavnField, etternavnField, epostField, telefonField, erRepresentatForField;
    private CustomJCheckBox erRepresentantCheckBox;
    private CustomJButton avbrytButton, lagreButton;

    public UtleierRegVindu(String tittel) {
        super(tittel);
        setSize(350, 250);
        setLayout(new BorderLayout());
        setVisible(true);

        panel = new CustomSubPanel("", 0, 0);
        add(panel);

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

        addUtleierPanelListener(new ActionListener() {

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

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        //Rad 1
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        panel.add(fornavnLabel, gc);

        gc.gridx = 1;
        gc.weightx = 1;        
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(fornavnField, gc);

        //Rad 2
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy++;

        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        panel.add(etternavnLabel, gc);

        gc.gridx = 1;
//        gc.gridy++;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(etternavnField, gc);

        //Rad 3
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        panel.add(epostLabel, gc);

        gc.gridx = 1;
//        gc.gridy++;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(epostField, gc);

        //Rad 4
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        panel.add(telefonLabel, gc);

        gc.gridx = 1;
//        gc.gridy++;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(telefonField, gc);

        //Rad 5
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        panel.add(erRepresntantLabel, gc);

        gc.gridx = 1;
//        gc.gridy++;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(erRepresentantCheckBox, gc);

        //Rad 6
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        panel.add(erRepresentatnForLabel, gc);

        gc.gridx = 1;
//        gc.gridy++;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(erRepresentatForField, gc);

        //Rad 7
        gc.weightx = 0.1;
        gc.weighty = 1;

        gc.gridx = 1;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        panel.add(avbrytButton, gc);

        gc.gridx = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        panel.add(lagreButton, gc);
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

    public CustomJTextField getErRepresentatForField() {
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

    public void addUtleierPanelListener(ActionListener lytter) {
        avbrytButton.addActionListener(lytter);
        lagreButton.addActionListener(lytter);
    }

}
