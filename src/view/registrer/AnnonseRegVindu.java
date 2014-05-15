package view.registrer;

import controller.VisMeldingInterface;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import lib.Boligtype;
import lib.GuiSizes;
import lib.Melding;
import lib.RegexTester;
import model.Bolig;
import model.Enebolig;
import view.ComboDatoVelger;
import view.CustomJButton;
import view.CustomJCheckBox;
import view.CustomJTextField;


public class AnnonseRegVindu extends AbstractRegistreringsPanel implements VisMeldingInterface {

    private JLabel boligIDLabel, boligAdresseLabel, boligPostNrLabel, boligPostStedLabel, boligEierFornavnLabel, boligEierEtternavnLabel, boligEierEpostLabel, boligEierTlfLabel;
    private CustomJTextField boligIDInfo, boligAdresseInfo, boligPostNrInfo, boligPostStedInfo, boligEierFornavnInfo, boligEierEtternavnInfo, boligEierEpostInfo, boligEierTlfInfo;
    private JLabel depositumLabel, utleieprisLabel, utlopsdatoLabel, tilgjengligFraDatoLabel, eiersKravLabel, erAnnonseSynligLabel;
    private CustomJTextField depositum, utleiepris;
    private CustomJCheckBox erSynligSomAnnonse;
    private ComboDatoVelger utlopsDato, tilgjengligFraDato;
    private JTextArea eiersKrav;
    private CustomJButton avbrytButton, lagreButton;
    
    public AnnonseRegVindu(String tittel) {
        super(850, 450, tittel);
        venstrePanel = new CustomSubPanel("Boliginfo", 0, 400, new GridBagLayout());
        hoyrePanel = new CustomSubPanel("Annonseregistrering", 0, 450, new GridBagLayout());
        bunnPanel = new CustomSubPanel("", 50, 0, new GridBagLayout());
        add(venstrePanel, BorderLayout.WEST);
        add(hoyrePanel, BorderLayout.EAST);
        add(bunnPanel, BorderLayout.SOUTH);
        
        boligIDLabel = new JLabel("BoligID:");
        boligAdresseLabel = new JLabel("Adresse:");
        boligPostNrLabel = new JLabel("PostNr:");
        boligPostStedLabel = new JLabel("Poststed:");
        boligEierFornavnLabel = new JLabel("Eiers fornavn:");
        boligEierEtternavnLabel = new JLabel("Eiers etternavn:");
        boligEierEpostLabel = new JLabel("Eiers epost:");
        boligEierTlfLabel = new JLabel("Eiers tlf:");
        boligIDInfo = new CustomJTextField("", RegexTester.ID, GuiSizes.FIELD_MEDIUM);
        boligAdresseInfo = new CustomJTextField("", RegexTester.GATE_ADRESSE, GuiSizes.FIELD_MEDIUM);
        boligPostNrInfo = new CustomJTextField("", RegexTester.POST_NUMMER_PATTERN, GuiSizes.FIELD_MEDIUM);
        boligPostStedInfo = new CustomJTextField("", RegexTester.POSTORT_NAVN, GuiSizes.FIELD_MEDIUM);
        boligEierFornavnInfo = new CustomJTextField("", RegexTester.EPOST_PATTERN, GuiSizes.FIELD_MEDIUM);
        boligEierEtternavnInfo = new CustomJTextField("", RegexTester.NAVN_PATTERN, GuiSizes.FIELD_MEDIUM);
        boligEierEpostInfo = new CustomJTextField("", RegexTester.NAVN_PATTERN, GuiSizes.FIELD_MEDIUM);
        boligEierTlfInfo = new CustomJTextField("", RegexTester.TEL_NUMMER_NORSK, GuiSizes.FIELD_MEDIUM);
        boligIDInfo.setEnabled(false);
        boligAdresseInfo.setEnabled(false);
        boligPostNrInfo.setEnabled(false);
        boligPostStedInfo.setEnabled(false);
        boligEierFornavnInfo.setEnabled(false);
        boligEierEtternavnInfo.setEnabled(false);
        boligEierEpostInfo.setEnabled(false);
        boligEierTlfInfo.setEnabled(false);
        boligIDInfo.setEnabled(false);
        depositumLabel = new JLabel("Depositum:");
        utleieprisLabel = new JLabel("Utleiepris:");
        utlopsdatoLabel = new JLabel("Annonsen utløper:");
        tilgjengligFraDatoLabel = new JLabel("Tilgjenglig fra:");
        utlopsDato = new ComboDatoVelger();
        tilgjengligFraDato = new ComboDatoVelger();
        eiersKravLabel = new JLabel("Krav fra eier:");
        erAnnonseSynligLabel = new JLabel("Allerede publisert?");
        depositum = new CustomJTextField("Depositum", RegexTester.PRIS, 10);
        utleiepris = new CustomJTextField("Pris pr mnd", RegexTester.PRIS, 10);
        erSynligSomAnnonse = new CustomJCheckBox();
        eiersKrav = new JTextArea(5, 25);
        eiersKrav.setLineWrap(true);
        eiersKrav.setWrapStyleWord(true);
        eiersKrav.setMargin(new Insets(3, 3, 3, 3));
        avbrytButton = new CustomJButton("Avbryt");
        lagreButton = new CustomJButton("Lagre");
        
        addAnnonsePanelListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource().equals(getAvbrytButton()))
                    dispose();
            }
        });
        
        opprettLayout();
        
    }//End Constructor
    
    public void opprettLayout() {
        GridBagConstraints gcVenstre = new GridBagConstraints();
        
        //Rad 1
        gcVenstre.weightx = 0.5;
        gcVenstre.weighty = 1;
        gcVenstre.gridx = 0;
        gcVenstre.gridy = 0;
        
        gcVenstre.fill = GridBagConstraints.NONE;
        gcVenstre.anchor = GridBagConstraints.LINE_START;
        gcVenstre.insets = new Insets(0, 0, 0, 5);
        venstrePanel.add(boligIDLabel, gcVenstre);
        
        gcVenstre.gridx = 1;
        gcVenstre.weightx = 1;        
        gcVenstre.insets = new Insets(0, 0, 0, 0);
        gcVenstre.anchor = GridBagConstraints.LINE_START;
        venstrePanel.add(boligIDInfo, gcVenstre);
        
        //Rad 2
        gcVenstre.weightx = 0.5;
        gcVenstre.weighty = 1;
        gcVenstre.gridx = 0;
        gcVenstre.gridy++;
        
        gcVenstre.fill = GridBagConstraints.NONE;
        gcVenstre.anchor = GridBagConstraints.LINE_START;
        gcVenstre.insets = new Insets(0, 0, 0, 5);
        venstrePanel.add(boligAdresseLabel, gcVenstre);
        
        gcVenstre.gridx = 1;
        gcVenstre.weightx = 1;        
        gcVenstre.insets = new Insets(0, 0, 0, 0);
        gcVenstre.anchor = GridBagConstraints.LINE_START;
        venstrePanel.add(boligAdresseInfo, gcVenstre);
        
        //Rad 3
        gcVenstre.weightx = 0.5;
        gcVenstre.weighty = 1;
        gcVenstre.gridx = 0;
        gcVenstre.gridy++;
        
        gcVenstre.fill = GridBagConstraints.NONE;
        gcVenstre.anchor = GridBagConstraints.LINE_START;
        gcVenstre.insets = new Insets(0, 0, 0, 5);
        venstrePanel.add(boligPostNrLabel, gcVenstre);
        
        gcVenstre.gridx = 1;
        gcVenstre.weightx = 1;        
        gcVenstre.insets = new Insets(0, 0, 0, 0);
        gcVenstre.anchor = GridBagConstraints.LINE_START;
        venstrePanel.add(boligPostNrInfo, gcVenstre);
        
        //Rad 4
        gcVenstre.weightx = 0.5;
        gcVenstre.weighty = 1;
        gcVenstre.gridx = 0;
        gcVenstre.gridy++;
        
        gcVenstre.fill = GridBagConstraints.NONE;
        gcVenstre.anchor = GridBagConstraints.LINE_START;
        gcVenstre.insets = new Insets(0, 0, 0, 5);
        venstrePanel.add(boligPostStedLabel, gcVenstre);
        
        gcVenstre.gridx = 1;
        gcVenstre.weightx = 1;        
        gcVenstre.insets = new Insets(0, 0, 0, 0);
        gcVenstre.anchor = GridBagConstraints.LINE_START;
        venstrePanel.add(boligPostStedInfo, gcVenstre);
        
        //Rad 5
        gcVenstre.weightx = 0.5;
        gcVenstre.weighty = 1;
        gcVenstre.gridx = 0;
        gcVenstre.gridy++;
        
        gcVenstre.fill = GridBagConstraints.NONE;
        gcVenstre.anchor = GridBagConstraints.LINE_START;
        gcVenstre.insets = new Insets(0, 0, 0, 5);
        venstrePanel.add(boligEierEpostLabel, gcVenstre);
        
        gcVenstre.gridx = 1;
        gcVenstre.weightx = 1;        
        gcVenstre.insets = new Insets(0, 0, 0, 0);
        gcVenstre.anchor = GridBagConstraints.LINE_START;
        venstrePanel.add(boligEierEpostInfo, gcVenstre);
        
        //Rad 6
        gcVenstre.weightx = 0.5;
        gcVenstre.weighty = 1;
        gcVenstre.gridx = 0;
        gcVenstre.gridy++;
        
        gcVenstre.fill = GridBagConstraints.NONE;
        gcVenstre.anchor = GridBagConstraints.LINE_START;
        gcVenstre.insets = new Insets(0, 0, 70, 0);
        venstrePanel.add(boligEierTlfLabel, gcVenstre);
        
        gcVenstre.gridx = 1;
        gcVenstre.weightx = 1;        
        gcVenstre.weighty = 1;        
        gcVenstre.insets = new Insets(0, 0, 70, 0);
        gcVenstre.anchor = GridBagConstraints.LINE_START;
        venstrePanel.add(boligEierTlfInfo, gcVenstre);
        
      
        
    //////////////////////////////////////////////
    //             Slutt på VenstrePanel        //
    //             Start på HøyrePanel          //        
    //////////////////////////////////////////////        
        
        //Rad 1
        GridBagConstraints gcHoyre = new GridBagConstraints();
        gcHoyre.weightx = 1;
        gcHoyre.weighty = 1;
        gcHoyre.gridx = 0;
        gcHoyre.gridy = 0;
        
        gcHoyre.fill = GridBagConstraints.NONE;
        gcHoyre.anchor = GridBagConstraints.LINE_START;
        gcHoyre.insets = new Insets(0, 0, 0, 5);
        hoyrePanel.add(erAnnonseSynligLabel, gcHoyre);
        
        gcHoyre.gridx = 1;
        gcHoyre.weightx = 1;        
        gcHoyre.insets = new Insets(0, 0, 0, 0);
        gcHoyre.anchor = GridBagConstraints.LINE_START;
        hoyrePanel.add(erSynligSomAnnonse, gcHoyre);

        //Rad 2
        gcHoyre.weightx = 1;
        gcHoyre.weighty = 1;
        gcHoyre.gridx = 0;
        gcHoyre.gridy++;
        
        gcHoyre.fill = GridBagConstraints.NONE;
        gcHoyre.anchor = GridBagConstraints.LINE_START;
        gcHoyre.insets = new Insets(0, 0, 0, 5);
        hoyrePanel.add(utleieprisLabel, gcHoyre);
        
        gcHoyre.gridx = 1;
        gcHoyre.weightx = 1;        
        gcHoyre.insets = new Insets(0, 0, 0, 0);
        gcHoyre.anchor = GridBagConstraints.LINE_START;
        hoyrePanel.add(utleiepris, gcHoyre);
        
        //Rad 3
        gcHoyre.weightx = 1;
        gcHoyre.weighty = 1;
        gcHoyre.gridx = 0;
        gcHoyre.gridy++;
        
        gcHoyre.fill = GridBagConstraints.NONE;
        gcHoyre.anchor = GridBagConstraints.LINE_START;
        gcHoyre.insets = new Insets(0, 0, 0, 5);
        hoyrePanel.add(depositumLabel, gcHoyre);
        
        gcHoyre.gridx = 1;
        gcHoyre.weightx = 1;        
        gcHoyre.insets = new Insets(0, 0, 0, 0);
        gcHoyre.anchor = GridBagConstraints.LINE_START;
        hoyrePanel.add(depositum, gcHoyre);
        
        //Rad 4
        gcHoyre.weightx = 1;
        gcHoyre.weighty = 1;
        gcHoyre.gridx = 0;
        gcHoyre.gridy++;
        
        gcHoyre.fill = GridBagConstraints.NONE;
        gcHoyre.anchor = GridBagConstraints.LINE_START;
        gcHoyre.insets = new Insets(0, 0, 0, 5);
        hoyrePanel.add(tilgjengligFraDatoLabel, gcHoyre);
        
        gcHoyre.gridx = 1;
        gcHoyre.weightx = 1;        
        gcHoyre.insets = new Insets(0, 0, 0, 0);
        gcHoyre.anchor = GridBagConstraints.LINE_START;
        hoyrePanel.add(tilgjengligFraDato, gcHoyre);
        
        //Rad 5
        gcHoyre.weightx = 1;
        gcHoyre.weighty = 1;
        gcHoyre.gridx = 0;
        gcHoyre.gridy++;
        
        gcHoyre.fill = GridBagConstraints.NONE;
        gcHoyre.anchor = GridBagConstraints.LINE_START;
        gcHoyre.insets = new Insets(0, 0, 0, 5);
        hoyrePanel.add(utlopsdatoLabel, gcHoyre);
        
        gcHoyre.gridx = 1;
        gcHoyre.weightx = 1;        
        gcHoyre.insets = new Insets(0, 0, 0, 0);
        gcHoyre.anchor = GridBagConstraints.LINE_START;
        hoyrePanel.add(utlopsDato, gcHoyre);
        
        //Rad 6
        gcHoyre.weightx = 1;
        gcHoyre.weighty = 1;
        gcHoyre.gridx = 0;
        gcHoyre.gridy++;
        
        gcHoyre.fill = GridBagConstraints.NONE;
        gcHoyre.anchor = GridBagConstraints.LINE_START;
        gcHoyre.insets = new Insets(0, 0, 0, 5);
        hoyrePanel.add(eiersKravLabel, gcHoyre);
        
        gcHoyre.gridx = 1;
        gcHoyre.weightx = 1;        
        gcHoyre.insets = new Insets(0, 0, 0, 0);
        gcHoyre.anchor = GridBagConstraints.LINE_START;
        hoyrePanel.add(eiersKrav, gcHoyre);

    //////////////////////////////////////////////
    //             Slutt på HøyrePanel          //
    //             Start på BunnPanel           //
    //////////////////////////////////////////////    
        
        GridBagConstraints gcBunn = new GridBagConstraints();
        //Rad 1
        gcBunn.weightx = 1;
        gcBunn.weighty = 1;
        gcBunn.gridx = 0;
        gcBunn.gridy = 0;
        
        gcBunn.fill = GridBagConstraints.NONE;
        gcBunn.anchor = GridBagConstraints.LINE_END;
        gcBunn.insets = new Insets(0, 0, 0, 5);
        bunnPanel.add(avbrytButton, gcBunn);
        
        gcBunn.gridx = 1;
        gcBunn.weightx = 1;        
        gcBunn.insets = new Insets(0, 0, 0, 0);
        gcBunn.anchor = GridBagConstraints.LINE_START;
        bunnPanel.add(lagreButton, gcBunn);
        
        
    }//End doLayout()

    public CustomJTextField getBoligIDInfo() {
        return boligIDInfo;
    }

    public CustomJTextField getBoligAdresseInfo() {
        return boligAdresseInfo;
    }

    public CustomJTextField getBoligPostNrInfo() {
        return boligPostNrInfo;
    }

    public CustomJTextField getBoligPostStedInfo() {
        return boligPostStedInfo;
    }

    public CustomJTextField getBoligEierFornavnInfo() {
        return boligEierFornavnInfo;
    }

    public CustomJTextField getBoligEierEtternavnInfo() {
        return boligEierEtternavnInfo;
    }

    public CustomJTextField getBoligEierEpostInfo() {
        return boligEierEpostInfo;
    }

    public CustomJTextField getBoligEierTlfInfo() {
        return boligEierTlfInfo;
    }

    public ComboDatoVelger getUtlopsDato() {
        return utlopsDato;
    }

    public ComboDatoVelger getTilgjengligFraDato() {
        return tilgjengligFraDato;
    }

    

    public CustomJTextField getDepositum() {
        return depositum;
    }

    public CustomJTextField getUtleiepris() {
        return utleiepris;
    }

    public CustomJCheckBox getErSynligSomAnnonse() {
        return erSynligSomAnnonse;
    }

    public JTextArea getEiersKrav() {
        return eiersKrav;
    }

    public CustomJButton getAvbrytButton() {
        return avbrytButton;
    }

    public CustomJButton getLagreButton() {
        return lagreButton;
    }

    public void addAnnonsePanelListener(ActionListener lytter) {
        avbrytButton.addActionListener(lytter);
        lagreButton.addActionListener(lytter);
    }    


    @Override
    public void visMelding(String overskrift, String melding) {
        Melding.visMelding(overskrift, melding);
    }
}
