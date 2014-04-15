package controller;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.event.*;
import javax.swing.JTable;
import lib.Konstanter;
import view.ArkfaneTemplate;



public class ControllerBunnPanel {

    public ControllerBunnPanel(ArkfaneTemplate meglerVindu, ArkfaneTemplate annonseVindu){
     
        meglerVindu.getBunnpanel().addKnappeLytter( new KnappeLytter( meglerVindu));
        annonseVindu.getBunnpanel().addKnappeLytter( new KnappeLytter( annonseVindu));
    }
    
    /**
     * private lytteklasse for Endre-knappen i bunnpanelet.
     */ 
    class KnappeLytter implements ActionListener{

        ArkfaneTemplate vindu;
        int datasettIBruk;
        int raderITabell;
        
        public KnappeLytter( ArkfaneTemplate vindu ){
            this.vindu = vindu;
            
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            datasettIBruk = ArrayTilHTMLMetoder.getDatasettIBruk();
            JTable tabell = vindu.getVenstrepanel().getTable();
            raderITabell = vindu.getVenstrepanel().getTabellModell().getRowCount();
            /**
             * FixME
             * Kan ikke lage ferdig før vi har vinduer for oppretting/endring av objekter.
             */
            if( e.getSource().equals(vindu.getBunnpanel().getEndreKnapp())){
                switch(datasettIBruk){
                    case Konstanter.PERSONOBJ:
                    case Konstanter.BOLIGOBJ:
                    case Konstanter.ANNONSEOBJ:
                    case Konstanter.KONTRAKTOBJ:
                    case Konstanter.SOKNADOBJ:
                }
            } else if(e.getSource().equals(vindu.getBunnpanel().getTilbakeKnapp())){
                vindu.getVenstrepanel().getTable().changeSelection(tabell.getSelectedRow() -1, 0, false, false);
                
            } else if(e.getSource().equals(vindu.getBunnpanel().getFremKnapp())){
                vindu.getVenstrepanel().getTable().changeSelection(tabell.getSelectedRow() +1, 0, false, false);
            }
        }
        
    }    
}
