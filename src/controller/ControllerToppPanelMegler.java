package controller;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.
//Modifisert av Lukas 24.04.14, implemtering av søk, se git for detaljer.

import java.awt.event.*;
import java.util.*;
import lib.Melding;
import lib.ObjektType;
import model.*;
import search.FreeTextSearch;
import view.ArkfaneTemplate;

public class ControllerToppPanelMegler<E> {

    ArkfaneTemplate vindu;
    private String radioNavnKlikket;
    private ObjektType radioTypeValgt;
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
            if (e.getSource().equals(vindu.getToppanelMegler().getLagNyKnapp())) {

            }
            if (e.getSource().equals(vindu.getToppanelMegler().getSokeKnapp())) {
                //TODO: Trenger å få tekstfeltet å gi tilbakemelding gjeldende regex
                
                String soketekst = vindu.getToppanelMegler().getSokeFelt().getText();
                if (soketekst.equals("Søk") || soketekst.equals("")) {
                    Melding.visMelding("Søk", "Søkefeltet er tomt");
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
                            Melding.visMelding(radioTypeValgt.name(), "Vi har ingen radiobutton for kontrakter og toSearch er ikke implementert i kontrakt klasse enda.");
                            break;
                        case SOKNADSOBJ:
                            Melding.visMelding(radioTypeValgt.name(), "Søkning etter søknader er ikke enda implemetert ettersom vi ikke har testet søkningen på LinkedHashSet enda.\nMer data trengs.");
                            break;
                    }

                    if (listListener != null) {
                        listListener.listReady(sokeResultat, radioTypeValgt);
                    }
                }
            }

        }

    }

}
