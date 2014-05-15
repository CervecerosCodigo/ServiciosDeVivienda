package controller;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import controller.registrer.ControllerRegistrerBolig;
import java.io.*;
import java.util.*;
import lib.*;
import model.*;
import register.*;
import view.*;

/**
 * Dette er hovedkontrolleren mellom GUI og funksjonalitet. 
 * Herfra starter klassen brukergrensesnittet og de andre kontrollerne.
 * Det er noe lyttefunsjonalitet her som kobler sammen flere komponenter
 * som normalt ikke kjenner til hverandre.
 */
public class MainController implements Serializable {

    private static final long serialVersionUID = Konstanter.SERNUM;

    private Register personRegister;
    private Register boligRegister;
    private Register annonseRegister;
    private Register kontraktRegister;
    private Register soknadRegister;

    private HashSet<Person> personliste;
    private HashSet<Bolig> boligliste;
    private HashSet<Kontrakt> kontraktliste;
    private HashSet<Annonse> annonseliste;
    private HashSet<Soknad> soknadsliste;

    private ArkfaneMegler meglerVindu;
    private ArkfaneAnnonse annonseVindu;
    private StartGUI startGUI;
    private InnloggingController innloggingController;

    private ControllerTabell tabellControllerMegler;
    private ControllerTabell tabellControllerAnnonse;
    private ControllerToppPanelMegler toppPanelControllerMegler;
    private ControllerToppPanelAnnonse toppPanelControllerAnnonse;
    private ControllerKeyBindings keyBindingsController;

    //Tester å bruke den nye klassen for registrering av bolig
    private ControllerRegistrerBolig controllerRegistrerBolig;

    public MainController(HashSet<Person> personliste, HashSet<Bolig> boligliste,
            HashSet<Annonse> annonseliste, HashSet<Kontrakt> kontraktliste,
            HashSet<Soknad> soknadsliste) {

        meglerVindu = new ArkfaneMegler("megler");
        annonseVindu = new ArkfaneAnnonse("annonse");
        startGUI = new StartGUI(meglerVindu, annonseVindu);

        innloggingController = new InnloggingController(startGUI, personliste);
        tabellControllerMegler = new ControllerTabell(personliste, boligliste, annonseliste, kontraktliste, soknadsliste);
        tabellControllerAnnonse = new ControllerTabell(personliste, boligliste, annonseliste, kontraktliste, soknadsliste);
        toppPanelControllerMegler = new ControllerToppPanelMegler(meglerVindu, personliste, boligliste, annonseliste, kontraktliste, soknadsliste);
        toppPanelControllerAnnonse = new ControllerToppPanelAnnonse(annonseVindu, annonseliste);
        keyBindingsController = new ControllerKeyBindings(meglerVindu, annonseVindu, startGUI);

        this.personliste = personliste;
        this.boligliste = boligliste;
        this.annonseliste = annonseliste;
        this.kontraktliste = kontraktliste;
        this.soknadsliste = soknadsliste;

        personRegister = new Personregister(personliste);
        boligRegister = new Boligregister(boligliste);
        annonseRegister = new Annonseregister(annonseliste);
        kontraktRegister = new Kontraktregister(kontraktliste);
        soknadRegister = new Soknadregister(soknadsliste);


        /**
         * Setter opp vinduene med to standard datasett.
         */
        tabellControllerMegler.settOppTabellLyttere(meglerVindu);
        tabellControllerMegler.settInnDataITabell(boligliste, ObjektType.BOLIGOBJ);
        tabellControllerAnnonse.settOppTabellLyttere(annonseVindu);
        tabellControllerAnnonse.settInnDataITabell(annonseliste, ObjektType.ANNONSEOBJ);


        /**
         * Fyller inn data med søkeresultat som kommer fra ControllerToppPanelMegler
         */
        toppPanelControllerMegler.setListListener(new ListListenerInterface() {

            @Override
            public void listReady(HashSet liste, ObjektType objekttype) {
                switch (objekttype) {
                    case BOLIGOBJ:
                        tabellControllerMegler.tomTabellOgKlargjorForNyttDatasett();
                        tabellControllerMegler.settInnDataITabell(liste, objekttype);
                        break;
                    case PERSONOBJ:
                        tabellControllerMegler.tomTabellOgKlargjorForNyttDatasett();
                        tabellControllerMegler.settInnDataITabell(liste, objekttype);
                        break;
                    case ANNONSEOBJ:
                        tabellControllerMegler.tomTabellOgKlargjorForNyttDatasett();
                        tabellControllerMegler.settInnDataITabell(liste, objekttype);
                        break;
                    case KONTRAKTOBJ:
                        tabellControllerMegler.tomTabellOgKlargjorForNyttDatasett();
                        tabellControllerMegler.settInnDataITabell(liste, objekttype);
                        break;
                    case SOKNADSOBJ:
                        tabellControllerMegler.tomTabellOgKlargjorForNyttDatasett();
                        tabellControllerMegler.settInnDataITabell(liste, objekttype);
                        break;
                }
            }
        });

        toppPanelControllerAnnonse.setListListener(new ListListenerInterface() {

            @Override
            public void listReady(HashSet liste, ObjektType objekttype) {
                tabellControllerAnnonse.tomTabellOgKlargjorForNyttDatasett();
                tabellControllerAnnonse.settInnDataITabell(liste, objekttype);
            }
        });
        
        
        /**
         * Denne lytteren kobler "Ny kontrakt"-knappen i meglerpanelet til 
         * ControllerTabell.java sin registrerKontrakt-metode.
         */
        toppPanelControllerMegler.setNyKontraktLytter(new NyKontraktInterface() {

            @Override
            public void runNyKontraktMetodeIControllerTabell() {
                tabellControllerMegler.registrerKontrakt();
            }
        });
       
    }//END CONSTRUCTOR
}
