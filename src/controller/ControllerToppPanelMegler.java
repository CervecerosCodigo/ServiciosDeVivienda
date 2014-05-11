package controller;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.
//Modifisert av Lukas 24.04.14, implemtering av søk, se git for detaljer.

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import lib.ObjektType;
import lib.ObjektType2;
import model.Annonse;
import model.Bolig;
import model.Kontrakt;
import model.Person;
import model.Soknad;
import model.Utleier;
import search.FreeTextSearch;
import view.AbstraktArkfane;
import controller.registrer.ControllerRegistrerAnnonse;
import controller.registrer.ControllerRegistrerBolig;
import controller.registrer.ControllerRegistrerUtleier;
import model.TabellModell;

public class ControllerToppPanelMegler<E> implements VisMeldingInterface {

    AbstraktArkfane vindu;
    private String radioNavnKlikket;
    private ObjektType radioTypeValgt;
    private ObjektType2 radioTypeValgt2;
    private FreeTextSearch fsearch;

    private HashSet<Person> personliste;
    private HashSet<Bolig> boligliste;
    private HashSet<Annonse> annonseliste;
    private HashSet<Kontrakt> kontraktliste;
    private HashSet<Soknad> soknadsliste;

    private ArrayList<E> tabellData;
    private Object valgtObjekt;
    private int valgtRadItabell;
    private JTable tabell;

    private ListListenerInterface listListener;

    public ControllerToppPanelMegler(AbstraktArkfane vindu, HashSet<Person> personliste, HashSet<Bolig> boligliste,
            HashSet<Annonse> annonseliste, HashSet<Kontrakt> kontraktliste,
            HashSet<Soknad> soknadsliste) {
        this.vindu = vindu;
        this.personliste = personliste;
        this.boligliste = boligliste;
        this.annonseliste = annonseliste;
        this.kontraktliste = kontraktliste;
        this.soknadsliste = soknadsliste;

        //Setter lyttere i Toppanelet.
        vindu.getToppanelMegler().leggTilRadioLytter(new RadioLytter());
        vindu.getToppanelMegler().leggTilKnappeLytter(new KnappeLytter());

        valgtObjekt = -1;
        OppdaterStatistikk();
        finnValgtObjektITabell();

    }

    /**
     * Hjelpemetode som går igjennom registeret, og teller opp ledige boliger,
     * samt antall kontrakter hittil i år. Sender denne informasjonen videre til
     * TopPanelMegler.java for oppdatering der.
     */
    private void OppdaterStatistikk() {

        int antallLedigeBoliger = 0;
        int antallKontrakter = 0;
        Calendar dagsDato = Calendar.getInstance();
        Integer dagensAar = dagsDato.get(dagsDato.YEAR);

        for (Bolig boligIterator : boligliste) {
            if (boligIterator.isErUtleid() == false) {
                antallLedigeBoliger++;
            }
        }

        for (Kontrakt kontraktIterator : kontraktliste) {
            Calendar dato = kontraktIterator.getDatoOpprettet();
            int kontraktOpprettetAar = dato.get(dato.YEAR);

            if (kontraktOpprettetAar == dagensAar) {
                antallKontrakter++;
            }
        }

        vindu.getToppanelMegler().getStatistikkPanel().OppdaterStatistikk(antallLedigeBoliger, antallKontrakter);
    }

    /**
     * Tar imot ListListener interfacet med hensikt til å sende events og data
     * opp til overliggende controller (MainController).
     *
     * @param listListener
     */
    public void setListListener(ListListenerInterface listListener) {
        this.listListener = listListener;
    }

    @Override
    public void visMelding(String overskrift, String melding) {
        vindu.visMelding(overskrift, melding);
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
                    radioTypeValgt2 = ObjektType2.Bolig;
                }
                if (e.getSource().equals(vindu.getToppanelMegler().getLeietakereRadio())) {
                    radioTypeValgt = ObjektType.PERSONOBJ;
                    radioTypeValgt2 = ObjektType2.Leietaker;
                }
                if (e.getSource().equals(vindu.getToppanelMegler().getUtleiereRadio())) {
                    radioTypeValgt = ObjektType.PERSONOBJ;
                    radioTypeValgt2 = ObjektType2.Utleier;
                }
                if (e.getSource().equals(vindu.getToppanelMegler().getAnnonserRadio())) {
                    radioTypeValgt = ObjektType.ANNONSEOBJ;
                    radioTypeValgt2 = ObjektType2.Annonse;
                }
                if (e.getSource().equals(vindu.getToppanelMegler().getSoknaderRadio())) {
                    radioTypeValgt = ObjektType.SOKNADSOBJ;
                    radioTypeValgt2 = ObjektType2.Soknad;
                }
                if (e.getSource().equals(vindu.getToppanelMegler().getKontraktRadio())) {
                    radioTypeValgt = ObjektType.KONTRAKTOBJ;
                    radioTypeValgt2 = ObjektType2.Kontrakt;
                }
            } catch (NullPointerException npe) {

            }
        }

    }

    /**
     * Metoden finner valgt rad i tabellen og hvilket objekt som ligger på
     * raden.
     */
    private void finnValgtObjektITabell() {

        tabell = vindu.getVenstrepanel().getTable();
        
        tabell.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                try {
                    int rad = tabell.getSelectedRow();
                    if (rad != -1) {
                        rad = tabell.convertRowIndexToModel(rad);
                        valgtRadItabell = rad;
                        TabellModell modellIBruk = (TabellModell)tabell.getModel();
                        valgtObjekt = modellIBruk.finnObjektIModell(valgtRadItabell);
                    }

                } catch (ArrayIndexOutOfBoundsException aiobe) {
                    System.out.println("Tabell (ToppPanelMegler) ConvertRowIndexToModel ArrayIndexOufOfBounds");
                } catch (IndexOutOfBoundsException iobe) {
                    System.out.println("Tabell ToppPanelMegler) ConvertEowIndexToModel IndexOufOfBounds");
                }
            }
        });

    }

    /**
     * Tar i mot tabellData fra MainController, og derfra fra TabellController.
     * Målet er å vite hvilket objekt som er valgt i tabellen når en skal velge
     * ny..
     *
     * @param tabellData
     */
    public void setTabellDataIBrukFraMainController(ArrayList<E> tabellData) {
        this.tabellData = tabellData;
    }

    public void sendSokeResultat(ArrayList<E> sokeResultat) {
        String soketekst = vindu.getToppanelMegler().getSokeFelt().getText();

        if (false) {
            visMelding("Søk", "Søkefeltet er tomt.\nBruk * for å vise hele registeret.");
        } else {

            fsearch = new FreeTextSearch();

            switch (radioTypeValgt2) {
                case Bolig:
                    sokeResultat = fsearch.searchForPattern(boligliste, soketekst);
                    break;
                case Utleier:
                    sokeResultat = fsearch.searchForPatternIUtleier(personliste, soketekst);
                    break;
                case Leietaker:
                    sokeResultat = fsearch.searchForPatternILeietaker(personliste, soketekst);
                    break;
                case Annonse:
                    sokeResultat = fsearch.searchForPattern(annonseliste, soketekst);
                    break;
                case Kontrakt:
                    sokeResultat = fsearch.searchForPattern(kontraktliste, soketekst);
                    break;
                case Soknad:
                    sokeResultat = fsearch.searchForPattern(soknadsliste, soketekst);
                    break;
                default:
                    visMelding("Søk", "Mangler valg");
            }

            if (sokeResultat.isEmpty()) {
                visMelding("Søkeresultat", "Søket gav ingen resultat.");
            } //Sender søkeresultat til MainController via interface
            else if (listListener != null) {
                listListener.listReady(sokeResultat, radioTypeValgt);
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

                if (valgtObjekt instanceof Bolig) {
                    new ControllerRegistrerAnnonse(annonseliste, personliste, (Bolig) valgtObjekt);
                }
            }
            if (e.getSource().equals(vindu.getToppanelMegler().getNyBoligItem())) {
                if (valgtObjekt instanceof Utleier) {
                    new ControllerRegistrerBolig(boligliste, (Utleier) valgtObjekt);
                }
            }
            if (e.getSource().equals(vindu.getToppanelMegler().getNyUtleierItem())) {
                new ControllerRegistrerUtleier(personliste);
            }
            if (e.getSource().equals(vindu.getToppanelMegler().getSokeKnapp())) {
                sendSokeResultat(sokeResultat);
            }
        }
    }
}
