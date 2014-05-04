package view.registrer;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import lib.Boligtype;
import lib.RegexTester;
import model.Bolig;
import model.Enebolig;
import view.CustomJButton;
import view.CustomJCheckBox;
import view.CustomJTextField;


public class AnnonseRegVindu extends AbstractRegistreringsPanel{

    private JLabel boligIDLabel, boligAdresseLabel, boligPostNrLabel, boligPostStedLabel, boligEierFornavnLabel, boligEierEtternavnLabel, boligEierEpostLabel, boligEierTlfLabel;
    private JLabel boligIDInfo, boligAdresseInfo, boligPostNrInfo, boligPostStedInfo, boligEierFornavnInfo, boligEierEtternavnInfo, boligEierEpostInfo, boligEierTlfInfo;
    private JLabel depositumLabel, utleieprisLabel, utlopsdatoLabel, tilgjengligFraDatoLabel, eiersKravLabel, erAnnonseSynligLabel;
    private CustomJTextField depositum, utleiepris;
    private CustomJCheckBox erSynligSomAnnonse;
    private JTextArea eiersKrav;
    private CustomJButton avbrytButton, lagreButton;
    
    public AnnonseRegVindu(String tittel) {
        super(500, 500, tittel);
        venstrePanel = new CustomSubPanel("Boliginfo", 0, 250, new GridBagLayout());
        hoyrePanel = new CustomSubPanel("Annonseregistrering", 0, 250, new GridBagLayout());
        bunnPanel = new CustomSubPanel("", 40, 0, new GridBagLayout());
        add(venstrePanel);
        add(hoyrePanel);
        add(bunnPanel);
        
        boligIDLabel = new JLabel("BoligID:");
        boligAdresseLabel = new JLabel("Adresse:");
        boligPostNrLabel = new JLabel("PostNr:");
        boligPostStedLabel = new JLabel("Poststed:");
        boligEierFornavnLabel = new JLabel("Eiers fornavn:");
        boligEierEtternavnLabel = new JLabel("Eiers etternavn:");
        boligEierEpostLabel = new JLabel("Eiers epost:");
        boligEierTlfLabel = new JLabel("Eiers tlf:");
        boligIDInfo = new JLabel();
        boligAdresseInfo = new JLabel();
        boligPostNrInfo = new JLabel();
        boligPostStedInfo = new JLabel();
        boligEierFornavnInfo = new JLabel();
        boligEierEtternavnInfo = new JLabel();
        boligEierEpostInfo = new JLabel();
        boligEierTlfInfo = new JLabel();
        depositumLabel = new JLabel("Depositum:");
        utleieprisLabel = new JLabel("Utleiepris:");
        utlopsdatoLabel = new JLabel("Annonsen utløper:");
        tilgjengligFraDatoLabel = new JLabel("Tilgjenglig fra:");
        eiersKravLabel = new JLabel("Krav fra eier:");
        erAnnonseSynligLabel = new JLabel("Allerede publisert?");
        depositum = new CustomJTextField("Depositum", RegexTester.PRIS, 10);
        utleiepris = new CustomJTextField("Pris pr mnd", RegexTester.PRIS, 10);
        erSynligSomAnnonse = new CustomJCheckBox();
        eiersKrav = new JTextArea(10, 5);
        avbrytButton = new CustomJButton("Avbryt");
        lagreButton = new CustomJButton("Lagre");
        
        addAnnonsePanelListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource().equals(getAvbrytButton())){
                    dispose();
                }
            }
        });
        
        opprettLayout();
        
    }//End Constructor
    
    public void opprettLayout(){
        GridBagConstraints gc = new GridBagConstraints();
        
        //Rad 1
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        venstrePanel.add(boligIDLabel, gc);
        
        gc.gridx = 1;
        gc.weightx = 1;        
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        venstrePanel.add(boligIDInfo, gc);
        
        //Rad 2
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy++;
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        venstrePanel.add(boligAdresseLabel, gc);
        
        gc.gridx = 1;
        gc.weightx = 1;        
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        venstrePanel.add(boligAdresseInfo, gc);
        
        //Rad 3
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy++;
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        venstrePanel.add(boligPostNrLabel, gc);
        
        gc.gridx = 1;
        gc.weightx = 1;        
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        venstrePanel.add(boligPostNrInfo, gc);
        
        //Rad 4
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy++;
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        venstrePanel.add(boligPostStedLabel, gc);
        
        gc.gridx = 1;
        gc.weightx = 1;        
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        venstrePanel.add(boligPostStedInfo, gc);
        
        //Rad 5
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy++;
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        venstrePanel.add(boligEierEpostLabel, gc);
        
        gc.gridx = 1;
        gc.weightx = 1;        
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        venstrePanel.add(boligEierEpostInfo, gc);
        
        //Rad 6
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy++;
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        venstrePanel.add(boligEierTlfLabel, gc);
        
        gc.gridx = 1;
        gc.weightx = 1;        
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        venstrePanel.add(boligEierTlfInfo, gc);
        
    //////////////////////////////////////////////
    //             Slutt på VenstrePanel        //
    //             Start på HøyrePanel          //        
    //////////////////////////////////////////////        
        
        //Rad 1
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        venstrePanel.add(erAnnonseSynligLabel, gc);
        
        gc.gridx = 1;
        gc.weightx = 1;        
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        venstrePanel.add(erSynligSomAnnonse, gc);

        //Rad 2
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        venstrePanel.add(utleieprisLabel, gc);
        
        gc.gridx++;
        gc.weightx = 1;        
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        venstrePanel.add(utleiepris, gc);
        
        //Rad 3
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        venstrePanel.add(depositumLabel, gc);
        
        gc.gridx++;
        gc.weightx = 1;        
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        venstrePanel.add(depositum, gc);
        
        //Rad 3
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        venstrePanel.add(tilgjengligFraDatoLabel, gc);
        
        gc.gridx++;
        gc.weightx = 1;        
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        venstrePanel.add(null, gc);
        
        //Rad 4
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        venstrePanel.add(utlopsdatoLabel, gc);
        
        gc.gridx++;
        gc.weightx = 1;        
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        venstrePanel.add(null, gc);
        
        //Rad 5
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        venstrePanel.add(eiersKravLabel, gc);
        
        gc.gridx++;
        gc.weightx = 1;        
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        venstrePanel.add(eiersKrav, gc);

    //////////////////////////////////////////////
    //             Slutt på HøyrePanel          //
    //             Start på BunnPanel           //
    //////////////////////////////////////////////    
        
        //Rad 1
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        venstrePanel.add(avbrytButton, gc);
        
        gc.gridx++;
        gc.weightx = 1;        
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_END;
        venstrePanel.add(lagreButton, gc);
        
        
    }//End doLayout()

    public JLabel getBoligIDInfo() {
        return boligIDInfo;
    }

    public JLabel getBoligAdresseInfo() {
        return boligAdresseInfo;
    }

    public JLabel getBoligPostNrInfo() {
        return boligPostNrInfo;
    }

    public JLabel getBoligPostStedInfo() {
        return boligPostStedInfo;
    }

    public JLabel getBoligEierFornavnInfo() {
        return boligEierFornavnInfo;
    }

    public JLabel getBoligEierEtternavnInfo() {
        return boligEierEtternavnInfo;
    }

    public JLabel getBoligEierEpostInfo() {
        return boligEierEpostInfo;
    }

    public JLabel getBoligEierTlfInfo() {
        return boligEierTlfInfo;
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

}
