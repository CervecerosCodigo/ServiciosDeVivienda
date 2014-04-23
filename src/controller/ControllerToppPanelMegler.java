package controller;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.event.*;
import java.util.*;
import model.*;
import view.ArkfaneTemplate;



public class ControllerToppPanelMegler {

    ArkfaneTemplate vindu;
    
    public ControllerToppPanelMegler(ArkfaneTemplate vindu, HashSet<Person> personliste, HashSet<Bolig> boligliste,
            HashSet<Annonse> annonseliste, HashSet<Kontrakt> kontraktliste,
            LinkedHashSet<Soknad> soknadsliste){
        this.vindu = vindu;
        
        
        //Setter lyttere i Toppanelet.
        vindu.getToppanelMegler().leggTilRadioLytter( new RadioLytter());
        vindu.getToppanelMegler().leggTilKnappeLytter( new KnappeLytter());
        
    }
    
    
    /**
     * Lytteklasse for radioknappene i toppanelet.
     */
    class RadioLytter implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                if (e.getSource().equals(vindu.getToppanelMegler().getBoligerRadio())){
                    
                }
                if (e.getSource().equals(vindu.getToppanelMegler().getLeietakereRadio())){
                    
                }
                if (e.getSource().equals(vindu.getToppanelMegler().getUtleiereRadio())){
                    
                }
                if (e.getSource().equals(vindu.getToppanelMegler().getAnnonserRadio())){
                    
                }
                if (e.getSource().equals(vindu.getToppanelMegler().getSoknaderRadio())){
                    
                }
            } catch (NullPointerException npe) {

            }
        }

    }
    
    /**
     * Lytteklasse for knappene i TopPanelMegler.java
     */
    class KnappeLytter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(vindu.getToppanelMegler().getLagNyKnapp())){
                
            }
            if (e.getSource().equals(vindu.getToppanelMegler().getSokeKnapp())){
                
            }
        
        }
        
    }
    
    
    
}
