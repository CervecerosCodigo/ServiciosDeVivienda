package controller;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import controller.registrer.ControllerRegistrerBolig;
import controller.registrer.ControllerRegistrerUtleier;
import java.io.*;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JTable;
import lib.*;
import model.*;
import register.*;
import test.filklasser.TestBildeFilSti;
import test.searchklasser.TestAnnonseFilter;
import test.searchklasser.TestFritekstSok;
import view.*;
import view.registrer.*;
import view.registrer.UtleierRegVindu;

/**
 * Dette er hovedkontrolleren mellom GUI og funksjonalitet. Mer info siden..
 *
 * @author espen
 */
public class MainController implements Serializable {

    private static final long serialVersionUID = Konstanter.SERNUM;

    private Register personRegister;
    private Register boligRegister;
    private Register annonseRegister;
    private Register kontraktRegister;
    private Register soknadRegister;
    private Postregister postRegister;

    private HashSet<Person> personliste;
    private HashSet<Bolig> boligliste;
    private HashSet<Kontrakt> kontraktliste;
    private HashSet<Annonse> annonseliste;
    private LinkedHashSet<Soknad> soknadsliste;

    private ArkfaneTemplate meglerVindu;
    private ArkfaneTemplate annonseVindu;
    private StartGUI startGUI;
    private InnloggingController innloggingController;
    private ControllerBunnPanel bunnController;
    private ControllerTabellOgOutput tabellControllerMegler;
    private ControllerTabellOgOutput tabellControllerAnnonse;
    private ControllerToppPanelMegler toppPanelControllerMegler;
    private ControllerToppPanelAnnonse toppPanelControllerAnnonse;
    
    private ControllerRegistrerUtleier controllerRegistrerUtleier;
    //Tester å bruke den nye klassen for registrering av bolig
    private ControllerRegistrerBolig controllerRegistrerBolig;

    public MainController(HashSet<Person> personliste, HashSet<Bolig> boligliste,
            HashSet<Annonse> annonseliste, HashSet<Kontrakt> kontraktliste,
            LinkedHashSet<Soknad> soknadsliste) {

        meglerVindu = new ArkfaneTemplate("megler");
        annonseVindu = new ArkfaneTemplate("annonse");
        startGUI = new StartGUI(meglerVindu, annonseVindu);

        innloggingController = new InnloggingController(startGUI);
        bunnController = new ControllerBunnPanel();
        tabellControllerMegler = new ControllerTabellOgOutput(personliste, boligliste, annonseliste, kontraktliste, soknadsliste);
        tabellControllerAnnonse = new ControllerTabellOgOutput(personliste, boligliste, annonseliste, kontraktliste, soknadsliste);
        toppPanelControllerMegler = new ControllerToppPanelMegler(meglerVindu, personliste, boligliste, annonseliste, kontraktliste, soknadsliste);
        toppPanelControllerAnnonse = new ControllerToppPanelAnnonse(annonseVindu, annonseliste);
        

        

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

        //////Setter Tabellen - Midlertidig kall på metodene herfra///////////////////
        /**
         * Man vil sende med resultatet fra søk i toppanel i stedet for hele
         * listen, der det er ønskelig.
         */

        tabellControllerMegler.settOppTabellLyttere(meglerVindu);
        tabellControllerMegler.settInnDataITabell(boligliste, ObjektType.BOLIGOBJ);
        
        tabellControllerAnnonse.settOppTabellLyttere(annonseVindu);
        tabellControllerAnnonse.settInnDataITabell(annonseliste, ObjektType.ANNONSEOBJ);

        bunnController.settKnappeLytter(meglerVindu);
        bunnController.settKnappeLytter(annonseVindu);

        ////////////////TEST AV SØKEKLASSER////////////////
        //Kommenter av metodene for å kjøre test
        //Test av filtrering fir boligsøker.
        TestAnnonseFilter testFilter = new TestAnnonseFilter(annonseliste);
//        testFilter.testBoligSokerEtterParametre();

        TestFritekstSok testFritekst = new TestFritekstSok();
//        testFritekst.testSokAnnonseListe(annonseliste, "6");
//        testFritekst.testSokBoligListe(boligliste, "grorud");
        ////////////////SLUTT AV TEST AV SØKEKLASSER////////////////

        /////TEST FOR HJELPEKLASSER AV FILSTIER OG FILER//////
//        for (Bolig a : boligliste) {
//            TestBildeFilSti testBildefilsti = new TestBildeFilSti(a);
//            return;//verden beste hack for å avbryte etter en iterasjon
//        }
        /////SLUTT PÅ TEST FOR HJELPEKLASSER AV FILSTIER OG FILER//////
        /**
         * Fyller inn data med søkeresultat som kommer fra
         * ControllerToppPanelMegler
         */
        toppPanelControllerMegler.setListListener(new ListListener() {

            @Override
            public void listReady(ArrayList liste, ObjektType objekttype) {
                switch (objekttype) {
                    case BOLIGOBJ:
                        tabellControllerMegler.tomTabellOgKlargjorForNyttDatasett();
                        tabellControllerMegler.settInnDataITabell(liste,  objekttype);
                        liste.clear();
                        break;
                    case PERSONOBJ:
                        tabellControllerMegler.tomTabellOgKlargjorForNyttDatasett();
                        tabellControllerMegler.settInnDataITabell(liste, objekttype);
                        liste.clear();
                        break;
                    case ANNONSEOBJ:
                        tabellControllerMegler.tomTabellOgKlargjorForNyttDatasett();
                        tabellControllerMegler.settInnDataITabell(liste, objekttype);
                        liste.clear();
                        break;
                    case KONTRAKTOBJ:
                        tabellControllerMegler.tomTabellOgKlargjorForNyttDatasett();
                        tabellControllerMegler.settInnDataITabell(liste, objekttype);
                        liste.clear();
                        break;
                    case SOKNADSOBJ:
                        tabellControllerMegler.tomTabellOgKlargjorForNyttDatasett();
                        tabellControllerMegler.settInnDataITabell(liste, objekttype);
                        liste.clear();
                        break;
                }
            }

            @Override
            public void listReady(HashSet liste, ObjektType obj) {
                //Brukes ikke her
            }
        });

        toppPanelControllerAnnonse.setListListener(new ListListener() {

            @Override
            public void listReady(ArrayList liste, ObjektType obj) {
                //Brukes ikke her
            }

            @Override
            public void listReady(HashSet liste, ObjektType objekttype) {
                tabellControllerAnnonse.tomTabellOgKlargjorForNyttDatasett();
                tabellControllerAnnonse.settInnDataITabell(liste, objekttype);
                liste.clear();
            }
        });
        
    }

    public Calendar opprettKalenderobjekt(int aar, int mnd, int dag) {
        Calendar kalender = new GregorianCalendar(aar, mnd, dag);
        return kalender;
    }

    public Bolig finnBoligFraBoligID(int id) {

        Iterator<Bolig> iter = boligliste.iterator();
        while (iter.hasNext()) {
            Bolig temp = iter.next();
            if (temp.getBoligID() == id) {
                return temp;
            }
        }
        return null;
    }
    /////////////////////////////////////////////////////////////////////////
    /**
     * //Metoder for Registrering av person og bolig
     */
    public void opprettUtleierOgLeggIRegister(String fornavn, String etternavn, String epost, String tlfnr, boolean erRepresentant, String representantFor) {
        Person utleier = new Utleier(fornavn, etternavn, epost, tlfnr, erRepresentant, representantFor);

        if (personRegister.leggTilObjekt(utleier)) {
            System.out.println("Utleier er lagt inn i registeret.");
            return;
        }
        System.out.println("Utleier ble ikke lagt inn i registeret.");
    }

    public void opprettLeietakerOgLeggIRegister(String fornavn, String etternavn, String epost, String tlfnr) {
        Person leietaker = new Leietaker(fornavn, etternavn, epost, tlfnr);

        if (personRegister.leggTilObjekt(leietaker)) {
            System.out.println("Leietaker er lagt inn i registeret.");
            return;
        }
        System.out.println("Leietaker ble ikke lagt inn i registeret.");
    }


    public void opprettEneboligOgLeggIRegister(Boligtype boligtype, int antallEtasjer, boolean harKjeller, int tomtAreal, int personID, String adresse, String postnummer, String poststed, int boAreal, int byggeAr, String beskrivelse, boolean erUtleid, Calendar tilgjengeligForUtleie) {
        Bolig enebolig = new Enebolig(boligtype, antallEtasjer, harKjeller, tomtAreal, personID, adresse, postnummer, poststed, boAreal, byggeAr, beskrivelse, erUtleid, tilgjengeligForUtleie);
        
        //Opretter en ny mappe for boligens bilder dersom den ikke finnes med fra før
        BildeFilSti gallerimappe = new BildeFilSti();
        gallerimappe.lagBildemappeForBolig(enebolig);
        
        if (boligRegister.leggTilObjekt(enebolig)) {
            System.out.println("Enebolig er lagt inn i registeret.");
            return;
        }
        System.out.println("Enebolig ble ikke lagt inn i registeret.");
    }

    public void opprettLeilighetOgLeggIRegister(int etasjeNr, int balkongAreal, int bodAreal, boolean harHeis, boolean harGarsje, boolean harFellesvaskeri, int personID, String adresse, String postnummer, String poststed, int boAreal, int byggeAr, String beskrivelse, boolean erUtleid, Calendar tilgjengeligForUtleie) {

        Bolig leilighet = new Leilighet(etasjeNr, balkongAreal, bodAreal, harHeis, harGarsje, harFellesvaskeri, personID, adresse, postnummer, poststed, boAreal, byggeAr, beskrivelse, erUtleid, tilgjengeligForUtleie);
        
        //Opretter en ny mappe for boligens bilder dersom den ikke finnes med fra før
        BildeFilSti gallerimappe = new BildeFilSti();
        gallerimappe.lagBildemappeForBolig(leilighet);

        if (boligRegister.leggTilObjekt(leilighet)) {
            System.out.println("Leilighet er lagt inn i registeret.");
            return;
        }
        System.out.println("Leilighet ble ikke lagt inn i registeret.");
    }

    public void opprettAnnonseOgLeggIRegister(int depositum, int utleiepris, Calendar tilgjengeligFraDato, Calendar utlopsDato, Bolig bolig, String eiersKrav) {

        Annonse annonse = new Annonse(depositum, utleiepris, tilgjengeligFraDato, utlopsDato, bolig, eiersKrav);
        if (annonseRegister.leggTilObjekt(annonse)) {
            System.out.println("Annonsen er lagt inn i registeret");
            return;
        }
        System.out.println("Annonsen ble ikke lagt inn i registeret");
    }

    /////////////////////////////////////////////////////////////////////////
    public void testData() {

        Calendar tilgjenglig1 = opprettKalenderobjekt(2014, 5, 29);
        Calendar tilgjenglig2 = opprettKalenderobjekt(2014, 7, 1);
        Calendar tilgjenglig3 = opprettKalenderobjekt(2014, 8, 15);
        Calendar utlopsdato1 = opprettKalenderobjekt(2014, 5, 26);
        Calendar utlopsdato2 = opprettKalenderobjekt(2014, 6, 30);
        Calendar utlopsdato3 = opprettKalenderobjekt(2014, 7, 1);

        opprettUtleierOgLeggIRegister("Hans", "Pedersen", "pedersen@boflott.no", "90006788", true, "Bo flott AS");
        opprettUtleierOgLeggIRegister("Petter", "Stordalen", "pstordalen@yahoo.com", "23904532", false, null);
        opprettUtleierOgLeggIRegister("Kristian", "Stormare", "kstor@stormare.no", "21304050", true, "Stormare AS");
        opprettUtleierOgLeggIRegister("Knut", "Bjorøy", "knutb@yahoo.com", "56320069", false, null);
        opprettUtleierOgLeggIRegister("Richard", "Heia", "rickyh@gmail.com", "32440350", false, null);

        opprettLeietakerOgLeggIRegister("Line", "Larsen", "line@gmail.com", "48009067");
        opprettLeietakerOgLeggIRegister("Geir", "Fjæra", "geirf@gmail.com", "67004599");
        opprettLeietakerOgLeggIRegister("Nils", "Plassen", "nilsp@gmail.com", "22449044");
        opprettLeietakerOgLeggIRegister("Nils", "Treet", "nilst@gmail.com", "91990034");
        opprettLeietakerOgLeggIRegister("Hasse", "Hansen", "hh@gmail.com", "90124434");
        opprettLeietakerOgLeggIRegister("Tore", "Strand", "tstrand@online.no", "45673432");
        opprettLeietakerOgLeggIRegister("Tone", "Nilsen", "tnils@online.no", "41234056");

        System.out.println(personRegister.visRegister());
        System.out.println("================================================");
        ////////////////////////////////////////////////////////////////////////

        opprettEneboligOgLeggIRegister(Boligtype.ENEBOLIG, 2, true, 650, 10002, "Ivar Aasens vei 23",
                "0373", "Oslo", 190, 1939, "Villa på Vindern..", false, tilgjenglig2);
        opprettEneboligOgLeggIRegister(Boligtype.ENEBOLIG, 3, true, 1000, 10006, "Ivar Aasens vei 24",
                "0373", "Oslo", 230, 1898, "Villa på Vindern..", false, tilgjenglig3);
        opprettEneboligOgLeggIRegister(Boligtype.ENEBOLIG, 1, false, 450, 10003, "Prof. Hansens vei 22",
                "1373", "Lørenskog", 120, 1976, "Ganske kjedlige og lite hus..", false, tilgjenglig2);
        opprettEneboligOgLeggIRegister(Boligtype.ENEBOLIG, 2, true, 750, 10002, "Utsikten",
                "2022", "Lillestrøm", 140, 1969, "Ikke så værst hus.", false, tilgjenglig1);
        opprettEneboligOgLeggIRegister(Boligtype.ENEBOLIG, 3, true, 890, 10002, "Eneboligstrøket 44",
                "0377", "Slemdal", 250, 1920, "Villa på Slemdal..", false, tilgjenglig3);

        opprettLeilighetOgLeggIRegister(3, 0, 10, false, false, true, 10005, "Gladengveien 15A",
                "0661", "Oslo", 65, 1972, "Flott leilighet, solvendt.", false, tilgjenglig1);
        opprettLeilighetOgLeggIRegister(1, 10, 0, false, true, false, 10005, "Sinsenveien 34",
                "0345", "Oslo", 45, 1963, "Ikke så fin leilighet.", false, tilgjenglig1);
        opprettLeilighetOgLeggIRegister(8, 0, 10, true, false, true, 10002, "Groruddalen 1",
                "0453", "Oslo", 75, 1970, "Flott leilighet, solvendt.", false, tilgjenglig2);
        opprettLeilighetOgLeggIRegister(2, 5, 5, true, true, true, 10002, "Knatten 22",
                "1453", "Lørenskog", 70, 1950, "Koselig leilighet med mye potensiale.",
                false, tilgjenglig3);
        opprettLeilighetOgLeggIRegister(7, 0, 10, true, false, true, 10003, "Groruddalen 1",
                "0453", "Oslo", 75, 1970, "Trenger oppussing.", false, tilgjenglig1);
        opprettLeilighetOgLeggIRegister(2, 0, 10, true, false, true, 10004, "Groruddalen 1",
                "0453", "Oslo", 75, 1970, "Ligger i skygge for solen.", false, tilgjenglig3);
        //Tester slik at mappen blir oprettet
        opprettLeilighetOgLeggIRegister(2, 0, 10, true, false, true, 10004, "Pilestredet 35",
                "0166", "Oslo", 300, 1950, "HiOA", false, tilgjenglig3);

//        ArrayTilHTMLMetoder.settInnDataITabell(personliste, meglerVindu, Konstanter.PERSONOBJ);
        tabellControllerMegler.settInnDataITabell(boligliste, ObjektType.BOLIGOBJ);
        System.out.println(boligRegister.visRegister());
        System.out.println("================================================");

        opprettAnnonseOgLeggIRegister(30000, 10000, tilgjenglig1, utlopsdato3, finnBoligFraBoligID(20008), "Ikke lov å røyke i boligen.");
        opprettAnnonseOgLeggIRegister(25000, 8000, tilgjenglig1, utlopsdato2, finnBoligFraBoligID(20007), "Ikke lov å røyke i boligen.");
        opprettAnnonseOgLeggIRegister(45000, 15000, tilgjenglig2, utlopsdato3, finnBoligFraBoligID(20003), "Ikke lov å røyke i boligen.<br>Ikke lov med husdyr.");
        opprettAnnonseOgLeggIRegister(50000, 17500, tilgjenglig2, utlopsdato3, finnBoligFraBoligID(20004), "Ikke lov å røyke i boligen.<br>Ikke lov med husdyr.");
        opprettAnnonseOgLeggIRegister(30000, 10000, tilgjenglig3, utlopsdato1, finnBoligFraBoligID(20009), "");
        opprettAnnonseOgLeggIRegister(20000, 700, tilgjenglig3, utlopsdato2, finnBoligFraBoligID(20010), "Ikke lov å røyke i boligen.");
        System.out.println("================================================");
        ////////////////////////////////////////////////////////////////////////        
    }

}
