package controller;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.
//Modifisert av Lukas 24.04.14, implemtering av søk, se git for detaljer.

import java.awt.event.*;
import java.util.*;
import lib.Melding;
import model.*;
import search.FreeTextSearch;
import view.ArkfaneTemplate;

public class ControllerToppPanelMegler<E> {

    ArkfaneTemplate vindu;
    private String radioNavnKlikket;
    private FreeTextSearch fsearch;
    
    private HashSet<Person> personliste;
    private HashSet<Bolig> boligliste;
    private HashSet<Annonse> annonseliste;
    private HashSet<Kontrakt> kontraktliste;
    private LinkedHashSet<Soknad> soknadsliste;
    
    private ListListener listListener;

    public ControllerToppPanelMegler(ArkfaneTemplate vindu, HashSet<Person> personliste, HashSet<Bolig> boligliste,
            HashSet<Annonse> annonseliste, HashSet<Kontrakt> kontraktliste,
            LinkedHashSet<Soknad> soknadsliste) {
        this.vindu = vindu;
        this.personliste = personliste;
        this.boligliste = boligliste;
        this.annonseliste = annonseliste;
        this.kontraktliste = kontraktliste;
        this.soknadsliste = soknadsliste;

        fsearch = new FreeTextSearch();

        //Setter lyttere i Toppanelet.
        vindu.getToppanelMegler().leggTilRadioLytter(new RadioLytter());
        vindu.getToppanelMegler().leggTilKnappeLytter(new KnappeLytter());


    }
    
    public void setListListener(ListListener listListener){
        this.listListener = listListener;
    }

    /**
     * Lytteklasse for radioknappene i toppanelet.
     */
    class RadioLytter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                if (e.getSource().equals(vindu.getToppanelMegler().getBoligerRadio())) {
                    radioNavnKlikket = vindu.getToppanelMegler().getBoligerRadio().getText();
                }
                if (e.getSource().equals(vindu.getToppanelMegler().getLeietakereRadio())) {
                    radioNavnKlikket = vindu.getToppanelMegler().getLeietakereRadio().getText();

                }
                if (e.getSource().equals(vindu.getToppanelMegler().getUtleiereRadio())) {
                    radioNavnKlikket = vindu.getToppanelMegler().getUtleiereRadio().getText();

                }
                if (e.getSource().equals(vindu.getToppanelMegler().getAnnonserRadio())) {
                    radioNavnKlikket = vindu.getToppanelMegler().getAnnonserRadio().getText();

                }
                if (e.getSource().equals(vindu.getToppanelMegler().getSoknaderRadio())) {
                    radioNavnKlikket = vindu.getToppanelMegler().getSoknaderRadio().getText();

                }
            } catch (NullPointerException npe) {

            }
        }

    }

    /**
     * Lytteklasse for knappene i TopPanelMegler.java
     */
    class KnappeLytter implements ActionListener {

        private ArrayList<Bolig> sokeResultat;
        private String utskrift;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(vindu.getToppanelMegler().getLagNyKnapp())) {

            }
            if (e.getSource().equals(vindu.getToppanelMegler().getSokeKnapp())) {
                //TODO: Trenger å få tekstfeltet å gi tilbakemelding gjeldende regex
                String soketekst = vindu.getToppanelMegler().getSokeFelt().getText();

                //TODO: Dersom vi får tid må vi fjerne alle typer av slik hardkoding for swing komponentnavn
                switch (radioNavnKlikket) {
                    case "Boliger":
                        sokeResultat = fsearch.searchForPattern(boligliste, soketekst);
                        for (Object a : sokeResultat) {
                            utskrift += a.toString();
                        }
//                        Melding.visMelding(soketekst, utskrift);
                        break;
                }
                
                if(listListener != null){
                    listListener.listReady(sokeResultat);
                }
            }

        }

    }

}
