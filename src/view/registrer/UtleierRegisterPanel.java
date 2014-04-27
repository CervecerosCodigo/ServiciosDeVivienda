package view.registrer;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import lib.RegexTester;
import view.CustomJTextField;

/**
 * Panel for registrering av utleiere. File: UtleierRegisterPanel.java Package:
 * view.registrer Project: ServiciosDeVivienda Apr 27, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class UtleierRegisterPanel extends JPanel {

    private JLabel fornavnLabel, etternavnLabel, epostLabel, telefonLabel, erRepresntantLabel, erRepresentatnForLabel;
    private CustomJTextField fornavnField, etternavnField, epostField, telefonField, erRepresentatForField;
    private JCheckBox erRepresentantCheckBox;
    private JButton avbrytButton, lagreButton;

    private static final int BREDDE1 = 10;
    private static final int BREDDE2 = 5;
    private static final int BREDDE3 = 15;

    public UtleierRegisterPanel() {
        Dimension dim = getPreferredSize();
        dim.width = 400;
        setPreferredSize(dim);
        //Labels
        fornavnLabel = new JLabel("Fornavn: ");
        etternavnLabel = new JLabel("Etternavn: ");
        epostLabel = new JLabel("Epost: ");
        telefonLabel = new JLabel("Telefon: ");
        erRepresntantLabel = new JLabel("Er representant: ");
        erRepresentatnForLabel = new JLabel("Representerer: ");
        //Fields
        fornavnField = new CustomJTextField("Ola", RegexTester.NAVN_PATTERN, BREDDE1);
        etternavnField = new CustomJTextField("Normann", RegexTester.NAVN_PATTERN, BREDDE1);
        epostField = new CustomJTextField("ola.normann@epost.com", RegexTester.EPOST_PATTERN, BREDDE3);
        telefonField = new CustomJTextField("XX XX XX XX", RegexTester.TEL_NUMMER_NORSK, BREDDE1);
        erRepresentatForField = new CustomJTextField("Navn på representat", RegexTester.NAVN_PATTERN, BREDDE1);
        //Cheboxes
        erRepresentantCheckBox = new JCheckBox();
        //Knapper
        avbrytButton = new JButton("Avbryt");
        lagreButton = new JButton("Lagre");
        
        //Oppstartkriterier
        erRepresentatnForLabel.setEnabled(false);
        erRepresentatForField.setEnabled(false);//Det går ikke å skru av denne

        erRepresentantCheckBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean erKrysset = erRepresentantCheckBox.isSelected();
                erRepresentatnForLabel.setEnabled(erKrysset);
                erRepresentatForField.setEnabled(erKrysset);
            }
        });

        //Borders
        Border indreRamme = BorderFactory.createTitledBorder("Registrer utleier");
        Border yttreRamme = BorderFactory.createEmptyBorder(BREDDE2, BREDDE2, BREDDE2, BREDDE2);
        CompoundBorder compoundBorder = BorderFactory.createCompoundBorder(yttreRamme, indreRamme);
        setBorder(compoundBorder);

        opprettLayout();
    }

    public void opprettLayout() {

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        //Rad 1
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy++;

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(fornavnLabel, gc);

        gc.gridx = 1;
//        gc.gridy++;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(fornavnField, gc);

        //Rad 2
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy++;

        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(etternavnLabel, gc);

        gc.gridx = 1;
//        gc.gridy++;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(etternavnField, gc);

        //Rad 3
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(epostLabel, gc);

        gc.gridx = 1;
//        gc.gridy++;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(epostField, gc);

        //Rad 4
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(telefonLabel, gc);

        gc.gridx = 1;
//        gc.gridy++;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(telefonField, gc);

        //Rad 5
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(erRepresntantLabel, gc);

        gc.gridx = 1;
//        gc.gridy++;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(erRepresentantCheckBox, gc);

        //Rad 6
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(erRepresentatnForLabel, gc);

        gc.gridx = 1;
//        gc.gridy++;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(erRepresentatForField, gc);

        //Rad 7
        gc.weightx = 0.1;
        gc.weighty = 1;

        gc.gridx = 1;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(avbrytButton, gc);

        gc.gridx = 1;
//        gc.gridy++;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(lagreButton, gc);
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

    public JButton getLagreButton() {
        return lagreButton;
    }

    public JButton getAvbrytButton() {
        return avbrytButton;
    }
    
    public JCheckBox getErRepresentantCheckBox() {
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

    public void setErRepresentantCheckBox(JCheckBox erRepresentantCheckBox) {
        this.erRepresentantCheckBox = erRepresentantCheckBox;
    }
    
    public void addUtleierPanelListener(ActionListener lytter){
        avbrytButton.addActionListener(lytter);
        lagreButton.addActionListener(lytter);
    }

}
