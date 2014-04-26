package controller;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.event.*;
import javax.swing.JTable;
import lib.ObjektType;
import view.ArkfaneTemplate;



public class ControllerBunnPanel {

    private KnappeLytter lytter;
    
    public ControllerBunnPanel(){
     
        
    }
    
    public void settKnappeLytter(ArkfaneTemplate vindu){
        vindu.getBunnpanel().addKnappeLytter( lytter = new KnappeLytter( vindu ));
    }
    
    public void settDatasettIBruk( ObjektType objekttype ){
        lytter.settDatasettIBruk(objekttype);
    }
    
    /**
     * private lytteklasse for Endre-knappen i bunnpanelet.
     */ 
    class KnappeLytter implements ActionListener{

        ArkfaneTemplate vindu;
        ObjektType datasettIBruk;
        int raderITabell;
        
        public KnappeLytter( ArkfaneTemplate vindu ){
            this.vindu = vindu;
            
        }
        
        public void settDatasettIBruk( ObjektType objekttype ){
            this.datasettIBruk = objekttype;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            //datasettIBruk = ControllerTabellOgOutput.getDatasettIBruk();
            JTable tabell = vindu.getVenstrepanel().getTable();
            raderITabell = vindu.getVenstrepanel().getTable().getModel().getRowCount();
            /**
             * FixME
             * Kan ikke lage ferdig før vi har vinduer for oppretting/endring av objekter.
             */
            if( e.getSource().equals(vindu.getBunnpanel().getEndreKnapp())){
                switch(datasettIBruk){
                    case PERSONOBJ:
                    case BOLIGOBJ:
                    case ANNONSEOBJ:
                    case KONTRAKTOBJ:
                    case SOKNADSOBJ:
                }
            } else if(e.getSource().equals(vindu.getBunnpanel().getTilbakeKnapp())){
                vindu.getVenstrepanel().getTable().changeSelection(tabell.getSelectedRow() -1, 0, false, false);
                
            } else if(e.getSource().equals(vindu.getBunnpanel().getFremKnapp())){
                int valgtRad = vindu.getVenstrepanel().getTable().getSelectedRow();
                
                if( valgtRad +1 < vindu.getVenstrepanel().getTable().getRowCount() )
                    vindu.getVenstrepanel().getTable().changeSelection(tabell.getSelectedRow() +1, 0, false, false);
            }
        }
        
    }    
}
