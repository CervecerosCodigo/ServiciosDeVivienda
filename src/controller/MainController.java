package controller;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import lib.*;
import model.*;
import register.*;
import search.AnnonseFilter;
import search.FreeTextSearch;
import search.SearchException;
import view.*;

/**
 * Dette er hovedkontrolleren mellom GUI og funksjonalitet. Mer info siden..
 *
 * @author espen
 */
public class MainController implements Serializable {

    private static final long serialVersionUID = Konstanter.SERNUM;

    FreeTextSearch freeTextSearch = new FreeTextSearch();

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
    private ControllerBunnPanel bunnController;
    private ControllerTabellOgOutput tabellControllerMegler;
    private ControllerTabellOgOutput tabellControllerAnnonse;

    public MainController(HashSet<Person> personliste, HashSet<Bolig> boligliste,
            HashSet<Annonse> annonseliste, HashSet<Kontrakt> kontraktliste,
            LinkedHashSet<Soknad> soknadsliste) {

        meglerVindu = new ArkfaneTemplate("megler");
        annonseVindu = new ArkfaneTemplate("annonse");
        startGUI = new StartGUI(meglerVindu, annonseVindu);

        bunnController = new ControllerBunnPanel();
        tabellControllerMegler = new ControllerTabellOgOutput();
        tabellControllerAnnonse = new ControllerTabellOgOutput();

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
        tabellControllerMegler.settInnDataITabell(boligliste, meglerVindu, Konstanter.BOLIGOBJ);
        tabellControllerAnnonse.settInnDataITabell(annonseliste, annonseVindu, Konstanter.ANNONSEOBJ);
        tabellControllerMegler.settOppTabellLytter(meglerVindu);
        tabellControllerAnnonse.settOppTabellLytter(annonseVindu);

        bunnController.settKnappeLytter(meglerVindu);
        bunnController.settKnappeLytter(annonseVindu);

        //Inisjering av søketest
//        testFritekstSok();
        testBoligSoker();
    }

    ////////////////TEST AV SØKEKLASSER////////////////
    /**
     * Denne er til å teste søkemetoder som brukes fra meglerpanelen
     */
    public void testFritekstSok() {
        //Søk etter bolig
        ArrayList<Bolig> testList = freeTextSearch.searchForPattern(boligliste, "grorud");
        for (Bolig b : testList) {
            System.out.println("Søkeresultat" + b.toString());
        }

        //Søk etter en annonse med fritekst
        ArrayList<Annonse> testList2 = freeTextSearch.searchForPattern(annonseliste, "6");
        for (Annonse a : testList2) {
            System.out.println("Søkeresultat annonser: " + a.toString());
        }
    }

    /**
     * Denne er til å teste søkeklasser som finnes for boligsøkere
     */
    public void testBoligSoker() {
        AnnonseFilter search = new AnnonseFilter(annonseliste);
        StringBuilder utskrift = new StringBuilder();
        utskrift.append("Kommenter bort testBoligSøker() i konstruktør for å få bort den testmeldingen.\n\n");

        //Test for antall tilgjengelige annonser
        utskrift.append("Antall annonser: " + search.getAntallAnnonser());

        //Test for utskrift av alle poststeder med annonser
        try {
            SortedSet<String> p = search.getPoststederIAnnonser();
            utskrift.append("\n\nAlle poststeder med annonser:\n");
            for (String s : p) {
                utskrift.append(s).append(", ");
            }
            utskrift.append("\n");
        } catch (ParseException ex) {
            System.out.println("Parseexception i getPoststederIAnnonser()");
        }

        //Test for utskrift av alle boligtyper som finnes i annonseregisteret.
        try {
            SortedSet<Boligtype> bt = search.getBoligtyperIAnnonser();
            utskrift.append("\nAlle boligtyper i annonser:\n");
            for(Boligtype btt : bt ){
                utskrift.append(btt.toString()).append(", ");
            }
            utskrift.append("\n");
        }catch(ParseException ex){
            System.out.println("Parsexception i getBoligTyperIAnnonser()");
        }

        //Test for annonseintervall
//        try {
//            ArrayList<Annonse> annonseIntervall = search.getAnnonseIntervall(2, 4);
//            for(Annonse a : annonseIntervall){
//                System.out.println("Annonseintervall: "+a.toString());
//            }
//        } catch (SearchException ex) {
//            System.out.println("En Search exception har intreffet");
//        }
        //Test for filtrering etter poststed
        String poststed = "Lørenskog";
        search.filtrerEtterPostSted(poststed);
        HashSet<Annonse> e = search.getFilteredResults();
        utskrift.append("\nFiltrere etter poststed <<" + poststed + ">>:\n");
        for (Annonse a : e) {
            utskrift.append(a.toString()).append("\n");
        }

        //Test for filtrering etter boligtype
        search.filtrerEtterBoligType(Boligtype.LEILIGHET);
        HashSet<Annonse> f = search.getFilteredResults();
        utskrift.append("\nFiltrere etter boligtype <<" + Boligtype.ENEBOLIG.toString() + ">>:\n");
        for (Annonse b : f) {
            utskrift.append(b.toString()).append("\n");
        }

        //Test for sortering etter pris
        int prisMin = 8000;
        int prisMaks = 12000;
        search.filtrerEtterPrisRange(prisMin, prisMaks);
        HashSet<Annonse> g = search.getFilteredResults();
        utskrift.append("\nFiltrere etter prisrange <<" + prisMin + " - " + prisMaks + ">>:\n");
        for (Annonse c : g) {
            utskrift.append(c.toString()).append("\n");
        }

        //Test for sortering etter boareal
        int kvmMin = 60;
        int kvmMaks = 80;
        search.filtrerEtterBoArealRange(kvmMin, kvmMaks);
        HashSet<Annonse> h = search.getFilteredResults();
        utskrift.append("\nFiltrere etter boareal range <<" + kvmMin + " - " + kvmMaks + ">>:\n");
        for (Annonse d : h) {
            utskrift.append(d.toString()).append("\n");
        }

        //Test for fellesvaskeri
        //Filtre over andre checkbokser skal implementeres i like måte. Har foreløpig ikke testet dem alle.
        search.filtrerEtterFellesvaskeri();
        HashSet<Annonse> i = search.getFilteredResults();
        utskrift.append("\nFiltrere etter fellevaskeri\n");
        for (Annonse fellesvask : i) {
            utskrift.append(fellesvask.toString()).append("\n");
        }

        //Viser resultat av filtreringer
        visMelding("testBoligSoker()", utskrift.toString());
    }

    ////////////////SLUTT AV TEST AV SØKEKLASSER////////////////
    /**
     * Brukes sammen med testing ettersom det ble litt for rotede å søke etter
     * tekst i terminal.
     *
     * @param metode
     * @param melding
     */
    private void visMelding(String metode, String melding) {
        JOptionPane.showMessageDialog(null, melding, metode, JOptionPane.INFORMATION_MESSAGE);
    }

    public Calendar opprettKalenderobjekt(int aar, int mnd, int dag) {
        Calendar kalender = new GregorianCalendar(aar, mnd, dag);
        return kalender;
    }

/////////////////////////////////////////////////////////////////////////
    /**
     * //Annonsevinduet// Finn boliger basert på Areal minimum, Areal maksimum,
     * Max leiepris, Antall rom, Boligtype, Poststed
     */
/////////////////////////////////////////////////////////////////////////
    /**
     * //Metoder for SøkeGUI// Tar i mot epost fra GUI. Kaller så opp
     * hjelpemetoden finnePersonIDBasertPaaEpost for å få tak i personID til
     * personen, og deretter leter etter denne personens boliger.
     */
    public void finnBoligerRegistrertPaaEier(String epost) {

        int personID = finnePersonIDBasertPaaEpost(epost);

        if (personID != -1) {
            System.out.println("Boligeier " + personID + " har følgende boliger:");

            Iterator<Bolig> iter2 = boligliste.iterator();
            while (iter2.hasNext()) {
                Bolig temp = iter2.next();
                if (temp.getPersonID() == personID) {
                    try {
                        System.out.println("Bolig: " + temp.getBoligID());
                    } catch (NoSuchElementException nse) {
                        //System.out.println(" Ingen boliger");
                    }
                }
            }//End while
        }
        System.out.println("Fant ingen personer med epost " + epost + " !");

    }//End method

    public void finnBoligerRegistrertPaaAdresse(String adresse) {

        int teller = 0;

        Iterator<Bolig> iter2 = boligliste.iterator();
        while (iter2.hasNext()) {
            Bolig temp = iter2.next();
            if (temp.getAdresse().equals(adresse)) {
                try {
                    System.out.println("Bolig: " + temp.getBoligID() + " finnes på " + adresse);
                    teller++;
                } catch (NoSuchElementException nse) {
                    //System.out.println(" Ingen boliger");
                }
            }
        }//End while
        if (teller == 0) {
            System.out.println("Fant ingen boliger på den adressen!");
        }
    }//End method

    /**
     * @param epost
     * @return Hjelpemetode som tar i mot epostadresse og returnerer personID
     * til personen. Returnerer -1 om personen ikke finnes.
     */
    int finnePersonIDBasertPaaEpost(String epost) {

        int personID = -1;

        Iterator<Person> iter = personliste.iterator();

        while (iter.hasNext()) {
            Person temp = iter.next();
            if (temp.getEpost().equals(epost)) {
                try {
                    personID = temp.getPersonID();
                } catch (NoSuchElementException nse) {
                    System.out.println("Fant ikke personen!");
                }
            }
        }//End while        

        return personID;
    }

    /**
     *
     * @param kontraktNr
     * @return
     */
    public Kontrakt finneKontrakterBasertPaaKontraktNr(int kontraktNr) {
        Kontrakt retur = null;
        Iterator<Kontrakt> iter = kontraktliste.iterator();

        while (iter.hasNext()) {
            retur = iter.next();
            if (retur.getAnnonseID() == kontraktNr) {
                return retur;
            }
        }
        return null;
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

    public void opprettMeglerOgLeggIRegister(String fornavn, String etternavn, String epost, String tlfnr, int meglerID, String kontor) {
        Person megler = new Megler(fornavn, etternavn, epost, tlfnr, meglerID, kontor);

        if (personRegister.leggTilObjekt(megler)) {
            System.out.println("Megler er lagt inn i registeret.");
            return;
        }
        System.out.println("Megler ble ikke lagt inn i registeret.");
    }

    public void opprettEneboligOgLeggIRegister(Boligtype boligtype, int antallEtasjer, boolean harKjeller, int tomtAreal, int personID, String adresse, String postnummer, String poststed, int boAreal, int byggeAr, String beskrivelse, boolean erUtleid, Calendar tilgjengeligForUtleie) {
        Bolig enebolig = new Enebolig(boligtype, antallEtasjer, harKjeller, tomtAreal, personID, adresse, postnummer, poststed, boAreal, byggeAr, beskrivelse, erUtleid, tilgjengeligForUtleie);

        if (boligRegister.leggTilObjekt(enebolig)) {
            System.out.println("Enebolig er lagt inn i registeret.");
            return;
        }
        System.out.println("Enebolig ble ikke lagt inn i registeret.");
    }

    public void opprettLeilighetOgLeggIRegister(int etasjeNr, int balkongAreal, int bodAreal, boolean harHeis, boolean harGarsje, boolean harFellesvaskeri, int personID, String adresse, String postnummer, String poststed, int boAreal, int byggeAr, String beskrivelse, boolean erUtleid, Calendar tilgjengeligForUtleie) {

        Bolig leilighet = new Leilighet(etasjeNr, balkongAreal, bodAreal, harHeis, harGarsje, harFellesvaskeri, personID, adresse, postnummer, poststed, boAreal, byggeAr, beskrivelse, erUtleid, tilgjengeligForUtleie);

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

        opprettMeglerOgLeggIRegister("Per", "Meglersen", "megler@serviciosdevivienda.no", "45673300", 1000, "Oslokontoret");
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

        opprettEneboligOgLeggIRegister(Boligtype.ENEBOLIG, 2, true, 650, 2, "Ivar Aasens vei 23",
                "0373", "Oslo", 190, 1939, "Villa på Vindern..", false, tilgjenglig2);
        opprettEneboligOgLeggIRegister(Boligtype.ENEBOLIG, 3, true, 1000, 6, "Ivar Aasens vei 24",
                "0373", "Oslo", 230, 1898, "Villa på Vindern..", false, tilgjenglig3);
        opprettEneboligOgLeggIRegister(Boligtype.ENEBOLIG, 1, false, 450, 3, "Prof. Hansens vei 22",
                "1373", "Lørenskog", 120, 1976, "Ganske kjedlige og lite hus..", false, tilgjenglig2);
        opprettEneboligOgLeggIRegister(Boligtype.ENEBOLIG, 2, true, 750, 2, "Utsikten",
                "2022", "Lillestrøm", 140, 1969, "Ikke så værst hus.", false, tilgjenglig1);
        opprettEneboligOgLeggIRegister(Boligtype.ENEBOLIG, 3, true, 890, 2, "Eneboligstrøket 44",
                "0377", "Slemdal", 250, 1920, "Villa på Slemdal..", false, tilgjenglig3);

        opprettLeilighetOgLeggIRegister(3, 0, 10, false, false, true, 5, "Gladengveien 15A",
                "0661", "Oslo", 65, 1972, "Flott leilighet, solvendt.", false, tilgjenglig1);
        opprettLeilighetOgLeggIRegister(1, 10, 0, false, true, false, 5, "Sinsenveien 34",
                "0345", "Oslo", 45, 1963, "Ikke så fin leilighet.", false, tilgjenglig1);
        opprettLeilighetOgLeggIRegister(8, 0, 10, true, false, true, 2, "Groruddalen 1",
                "0453", "Oslo", 75, 1970, "Flott leilighet, solvendt.", false, tilgjenglig2);
        opprettLeilighetOgLeggIRegister(2, 5, 5, true, true, true, 2, "Knatten 22",
                "1453", "Lørenskog", 70, 1950, "Koselig leilighet med mye potensiale.",
                false, tilgjenglig3);
        opprettLeilighetOgLeggIRegister(7, 0, 10, true, false, true, 3, "Groruddalen 1",
                "0453", "Oslo", 75, 1970, "Trenger oppussing.", false, tilgjenglig1);
        opprettLeilighetOgLeggIRegister(2, 0, 10, true, false, true, 4, "Groruddalen 1",
                "0453", "Oslo", 75, 1970, "Ligger i skygge for solen.", false, tilgjenglig3);

//        ArrayTilHTMLMetoder.settInnDataITabell(personliste, meglerVindu, Konstanter.PERSONOBJ);
        tabellControllerMegler.settInnDataITabell(boligliste, meglerVindu, Konstanter.BOLIGOBJ);
        System.out.println(boligRegister.visRegister());
        System.out.println("================================================");

        opprettAnnonseOgLeggIRegister(30000, 10000, tilgjenglig1, utlopsdato3, finnBoligFraBoligID(8), "Ikke lov å røyke i boligen.");
        opprettAnnonseOgLeggIRegister(25000, 8000, tilgjenglig1, utlopsdato2, finnBoligFraBoligID(7), "Ikke lov å røyke i boligen.");
        opprettAnnonseOgLeggIRegister(45000, 15000, tilgjenglig2, utlopsdato3, finnBoligFraBoligID(3), "Ikke lov å røyke i boligen.<br>Ikke lov med husdyr.");
        opprettAnnonseOgLeggIRegister(50000, 17500, tilgjenglig2, utlopsdato3, finnBoligFraBoligID(4), "Ikke lov å røyke i boligen.<br>Ikke lov med husdyr.");
        opprettAnnonseOgLeggIRegister(30000, 10000, tilgjenglig3, utlopsdato1, finnBoligFraBoligID(9), "");
        opprettAnnonseOgLeggIRegister(20000, 700, tilgjenglig3, utlopsdato2, finnBoligFraBoligID(10), "Ikke lov å røyke i boligen.");
        System.out.println("================================================");
        ////////////////////////////////////////////////////////////////////////        
    }

}
