package controller;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.
//Modifisert av Lukas 24.04.14, implemtering av søk, se git for detaljer.

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;


import lib.Melding;
import lib.ObjektType;
import model.Annonse;
import model.Bolig;
import model.Kontrakt;
import model.Person;
import model.Soknad;
import search.FreeTextSearch;
import view.ArkfaneTemplate;
import controller.registrer.ControllerRegistrerBolig;
import controller.registrer.ControllerRegistrerUtleier;

public class ControllerToppPanelMegler<E> {

    ArkfaneTemplate vindu;
    private String radioNavnKlikket;
    private ObjektType radioTypeValgt;
    private FreeTextSearch fsearch;

    private HashSet<Person> personliste;
    private HashSet<Bolig> boligliste;
    private HashSet<Annonse> annonseliste;
    private HashSet<Kontrakt> kontraktliste;
    private HashSet<Soknad> soknadsliste;

    private ListListener listListener;

    public ControllerToppPanelMegler(ArkfaneTemplate vindu, HashSet<Person> personliste, HashSet<Bolig> boligliste,
            HashSet<Annonse> annonseliste, HashSet<Kontrakt> kontraktliste,
            HashSet<Soknad> soknadsliste) {
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
        
        OppdaterStatistikk();
    }
    
    
    /**
     * Hjelpemetode som går igjennom registeret, og teller opp ledige boliger, samt antall kontrakter hittil i år.
     * Sender denne informasjonen videre til TopPanelMegler.java for oppdatering der.
     */
    private void OppdaterStatistikk() {
    	
    	int antallLedigeBoliger = 0;
    	int antallKontrakter = 0;
    	Calendar dagsDato = Calendar.getInstance();
    	Integer dagensAar = dagsDato.get(dagsDato.YEAR);
    	
    	for(Bolig boligIterator : boligliste)
    		if(boligIterator.isErUtleid() == false)
    			antallLedigeBoliger++;
    	
    	for(Kontrakt kontraktIterator : kontraktliste) {
    		Calendar dato = kontraktIterator.getDatoOpprettet();
    		int kontraktOpprettetAar = dato.get(dato.YEAR);
    		
    		if(kontraktOpprettetAar == dagensAar)
    			antallKontrakter++;
    	}
    	
    	vindu.getToppanelMegler().getStatistikkPanel().OppdaterStatistikk(antallLedigeBoliger, antallKontrakter);
    }

    /**
     * Tar imot ListListener interfacet med hensikt til å sende events og data
     * opp til overliggende controller (MainController).
     * 
     * @param listListener 
     */
    public void setListListener(ListListener listListener) {
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
                    radioTypeValgt = ObjektType.BOLIGOBJ;
                }
                if (e.getSource().equals(vindu.getToppanelMegler().getLeietakereRadio())) {
                    radioTypeValgt = ObjektType.PERSONOBJ;
                }
                if (e.getSource().equals(vindu.getToppanelMegler().getUtleiereRadio())) {
                    radioTypeValgt = ObjektType.PERSONOBJ;
                }
                if (e.getSource().equals(vindu.getToppanelMegler().getAnnonserRadio())) {
                    radioTypeValgt = ObjektType.ANNONSEOBJ;
                }
                if (e.getSource().equals(vindu.getToppanelMegler().getSoknaderRadio())) {
                    radioTypeValgt = ObjektType.SOKNADSOBJ;
                }
                if (e.getSource().equals(vindu.getToppanelMegler().getKontraktRadio())) {
                    radioTypeValgt = ObjektType.KONTRAKTOBJ;
                }
            } catch (NullPointerException npe) {

            }
        }

    }

    /**
     * Lytteklasse for knappene i TopPanelMegler.java
     */
    class KnappeLytter implements ActionListener {

        private ArrayList<E> sokeResultat;
        private String utskrift;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(vindu.getToppanelMegler().getNyAnnonseItem())) {
                
            }
            if(e.getSource().equals(vindu.getToppanelMegler().getNyBoligItem())){
                new ControllerRegistrerBolig(boligliste);
            }
            if(e.getSource().equals(vindu.getToppanelMegler().getNyUtleierItem())){
                new ControllerRegistrerUtleier(personliste);
            }
            if(e.getSource().equals(vindu.getToppanelMegler().getNyAnnonseItem())){
                
            }
            if (e.getSource().equals(vindu.getToppanelMegler().getSokeKnapp())) {
                //TODO: Trenger å få tekstfeltet å gi tilbakemelding gjeldende regex
                
                String soketekst = vindu.getToppanelMegler().getSokeFelt().getText();
                if (soketekst.equals("Søk") || soketekst.equals("")) {
                    Melding.visMelding("Søk", "Søkefeltet er tomt.\nBruk * for å vise hele registeret.");
                } else {

                    //TODO: Dersom vi får tid må vi fjerne alle typer av slik hardkoding for swing komponentnavn
                    switch (radioTypeValgt) {
                        case BOLIGOBJ:
                            sokeResultat = fsearch.searchForPattern(boligliste, soketekst);
                            break;
                        case PERSONOBJ:
                            sokeResultat = fsearch.searchForPattern(personliste, soketekst);
                            break;
                        case ANNONSEOBJ:
                            sokeResultat = fsearch.searchForPattern(annonseliste, soketekst);
                            break;
                        case KONTRAKTOBJ:
                            //TODO: vi har ingen radiobutton for å søke etter kontrakter.
                            sokeResultat = fsearch.searchForPattern(kontraktliste, soketekst);
                            break;
                        case SOKNADSOBJ:
                            sokeResultat = fsearch.searchForPattern(soknadsliste, soketekst);
                            break;
                        default: 
                            Melding.visMelding("Søk", "Mangler valg");
                    }
                    
                    //Sender søkeresultat til MainController via interface
                    if (listListener != null) {
                        listListener.listReady(sokeResultat, radioTypeValgt);
                    }
                }
            }

        }

    }
    
}
