package controller;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.util.*;
import model.*;
import register.*;
import view.*;

/**
 * Controller.java er koblingen mellom GUI og dataobjektene. Det opprettes her
 * Dataregistre, og logikken i forhold til hva som skal leses og skrives til
 * registrene blir utført her.
 *
 * @author espen
 */
public class MainController {

    protected VelkomstMainFrame startGUI;

    protected Register personRegister;
    protected Register boligRegister;
    protected Register annonseRegister;
    protected Register kontraktRegister;
    protected Register soknadRegister;
    protected Postregister postRegister;

    protected HashSet<Person> personliste;
    protected HashSet<Bolig> boligliste;
    protected HashSet<Kontrakt> kontraktliste;
    protected HashSet<Annonse> annonseliste;
    protected LinkedHashSet<Soknad> soknadsliste;
    //protected TreeMap<String, Integer> postliste;

    public MainController(VelkomstMainFrame startGUI) {
        this.startGUI = startGUI;

        personliste = new HashSet<>();
        boligliste = new HashSet<>();
        annonseliste = new HashSet<>();
        kontraktliste = new HashSet<>();
        soknadsliste = new LinkedHashSet<>();
        //postliste = new TreeMap<>();

        personRegister = new Personregister(personliste);
        boligRegister = new Boligregister(boligliste);
        annonseRegister = new Annonseregister(annonseliste);
        kontraktRegister = new Kontraktregister(kontraktliste);
        soknadRegister = new Soknadregister(soknadsliste);
        //postRegister = new Postregister();
        testData();
        //finnBoligerRegistrertPaaEier("pedersen@boflott.no");
        //finnBoligerRegistrertPaaAdresse( "Ivar Aasens vei 25" );

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
        if( teller == 0 ){
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
    public Kontrakt finneKontrakterBasertPaaKontraktNr( int kontraktNr){
        Kontrakt retur = null;
        Iterator<Kontrakt> iter = kontraktliste.iterator();
        
        while( iter.hasNext() ){
            retur = iter.next();
            if( retur.getAnnonseID() == kontraktNr ) {
                return retur;
            }
        }
        return null;
    }
    /////////////////////////////////////////////////////////////////////////
    /**
     * //Metoder for Registrering av person og bolig
     */
    public void opprettUtleierOgLeggIRegister( String fornavn, String etternavn, String epost, String tlfnr, boolean erRepresentant, String representantFor ){
        Person utleier = new Utleier( fornavn, etternavn, epost, tlfnr, erRepresentant, representantFor );

        if( personRegister.leggTilObjekt( utleier ) ){
            System.out.println("Utleier er lagt inn i registeret.");
            return;
        }
        System.out.println("Utleier ble ikke lagt inn i registeret.");
    }
    
    
    public void opprettLeietakerOgLeggIRegister( String fornavn, String etternavn, String epost, String tlfnr ){
        Person leietaker = new Leietaker( fornavn, etternavn, epost, tlfnr );

        if( personRegister.leggTilObjekt( leietaker ) ){
            System.out.println("Leietaker er lagt inn i registeret.");
            return;
        }
        System.out.println("Leietaker ble ikke lagt inn i registeret.");
    }
    
    public void opprettMeglerOgLeggIRegister( String fornavn, String etternavn, String epost, String tlfnr, int meglerID, String kontor ){
        Person megler = new Megler( fornavn, etternavn, epost, tlfnr, meglerID, kontor );

        if( personRegister.leggTilObjekt( megler ) ){
            System.out.println("Megler er lagt inn i registeret.");
            return;
        }
        System.out.println("Megler ble ikke lagt inn i registeret.");
    }
    
    public void opprettEneboligOgLeggIRegister( Boligtype boligtype, int antallEtasjer, boolean harKjeller, int tomtAreal, int personID, String adresse, String postnummer, String poststed, int boAreal, int byggeAr, String beskrivelse, boolean erUtleid, Calendar tilgjengeligForUtleie ){
        Bolig enebolig = new Enebolig(boligtype, antallEtasjer, harKjeller, tomtAreal, personID, adresse, postnummer, poststed, boAreal, byggeAr, beskrivelse, erUtleid, tilgjengeligForUtleie);
        
        if( boligRegister.leggTilObjekt( enebolig ) ){
            System.out.println("Enebolig er lagt inn i registeret.");
            return;
        }
        System.out.println("Enebolig ble ikke lagt inn i registeret.");
    }
    
    public void opprettLeilighetOgLeggIRegister( int etasjeNr, int balkongAreal, int bodAreal, boolean harHeis, boolean harGarsje, boolean harFellesvaskeri, int personID, String adresse, String postnummer, String poststed, int boAreal, int byggeAr, String beskrivelse, boolean erUtleid, Calendar tilgjengeligForUtleie) {
        
        Bolig leilighet = new Leilighet( etasjeNr, balkongAreal, bodAreal, harHeis, harGarsje, harFellesvaskeri, personID, adresse, postnummer, poststed, boAreal, byggeAr, beskrivelse, erUtleid, tilgjengeligForUtleie );
        
        if( boligRegister.leggTilObjekt( leilighet ) ){
            System.out.println("Leilighet er lagt inn i registeret.");
            return;
        }
        System.out.println("Leilighet ble ikke lagt inn i registeret.");
    }
    /////////////////////////////////////////////////////////////////////////
    private void testData() {

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

        opprettEneboligOgLeggIRegister( Boligtype.ENEBOLIG, 2, true, 650, 2, "Ivar Aasens vei 23",
                "0373", "Oslo", 190, 1939, "Villa på Vindern..", false, tilgjenglig2 );
        opprettEneboligOgLeggIRegister( Boligtype.ENEBOLIG, 3, true, 1000, 6, "Ivar Aasens vei 24",
                "0373", "Oslo", 230, 1898, "Villa på Vindern..", false, tilgjenglig3 );
        opprettEneboligOgLeggIRegister( Boligtype.ENEBOLIG, 1, false, 450, 3, "Prof. Hansens vei 22",
                "1373", "Lørenskog", 120, 1976, "Ganske kjedlige og lite hus..", false, tilgjenglig2 );
        opprettEneboligOgLeggIRegister( Boligtype.ENEBOLIG, 2, true, 750, 2, "Utsikten",
                "2022", "Lillestrøm", 140, 1969, "Ikke så værst hus.", false, tilgjenglig1 );
        opprettEneboligOgLeggIRegister( Boligtype.ENEBOLIG, 3, true, 890, 2, "Eneboligstrøket 44",
                "0377", "Slemdal", 250, 1920, "Villa på Slemdal..", false, tilgjenglig3 );
        
        opprettLeilighetOgLeggIRegister( 3, 0, 10, false, false, true, 5, "Gladengveien 15A",
                "0661", "Oslo", 65, 1972, "Flott leilighet, solvendt.", false, tilgjenglig1 );
        opprettLeilighetOgLeggIRegister( 1, 10, 0, false, true, false, 5, "Sinsenveien 34",
                "0345", "Oslo", 45, 1963, "Ikke så fin leilighet.", false, tilgjenglig1 );
        opprettLeilighetOgLeggIRegister( 8, 0, 10, true, false, true, 2, "Groruddalen 1",
                "0453", "Oslo", 75, 1970, "Flott leilighet, solvendt.", false, tilgjenglig2 );
        opprettLeilighetOgLeggIRegister( 2, 5, 5, true, true, true, 2, "Knatten 22",
                "1453", "Lørenskog", 70, 1950, "Koselig leilighet med mye potensiale.", false, tilgjenglig3 );
        opprettLeilighetOgLeggIRegister( 7, 0, 10, true, false, true, 3, "Groruddalen 1",
                "0453", "Oslo", 75, 1970, "Trenger oppussing.", false, tilgjenglig1 );
        opprettLeilighetOgLeggIRegister( 2, 0, 10, true, false, true, 4, "Groruddalen 1",
                "0453", "Oslo", 75, 1970, "Ligger i skygge for solen.", false, tilgjenglig3 );
 
        System.out.println(boligRegister.visRegister());
        System.out.println("================================================");

//        ////////////////////////////////////////////////////////////////////////
//        Annonse annonse1 = new Annonse(30000, 10000, utlopsdato1, nyLeilighet1);
//        Annonse annonse2 = new Annonse(24000, 8000, utlopsdato1, nyLeilighet2);
//        Annonse annonse3 = new Annonse(21000, 7000, utlopsdato3, nyLeilighet3);
//        Annonse annonse4 = new Annonse(45000, 15000, utlopsdato2, nyEnebolig1);
//        Annonse annonse5 = new Annonse(27000, 9000, utlopsdato3, nyLeilighet4);
//        Annonse annonse6 = new Annonse(21000, 7000, utlopsdato2, nyLeilighet5);
//        Annonse annonse7 = new Annonse(60000, 20000, utlopsdato2, nyEnebolig2);
//        Annonse annonse8 = new Annonse(45000, 15000, utlopsdato1, nyEnebolig4);
//        Annonse annonse9 = new Annonse(60000, 20000, utlopsdato1, nyEnebolig5);
//        annonseRegister.leggTilObjekt(annonse1);
//        annonseRegister.leggTilObjekt(annonse2);
//        annonseRegister.leggTilObjekt(annonse3);
//        annonseRegister.leggTilObjekt(annonse4);
//        annonseRegister.leggTilObjekt(annonse5);
//        annonseRegister.leggTilObjekt(annonse6);
//        annonseRegister.leggTilObjekt(annonse7);
//        annonseRegister.leggTilObjekt(annonse8);
//        annonseRegister.leggTilObjekt(annonse9);
//        System.out.println(annonseRegister.visRegister());
//        System.out.println("================================================");
//        ////////////////////////////////////////////////////////////////////////
//
//        Kontrakt kontrakt1 = new Kontrakt(annonse1, megler1, leietaker1, 36);
//        Kontrakt kontrakt2 = new Kontrakt(annonse2, megler1, leietaker2, 36);
//        Kontrakt kontrakt3 = new Kontrakt(annonse5, megler1, leietaker3, 36);
//        Kontrakt kontrakt4 = new Kontrakt(annonse6, megler1, leietaker6, 36);
//        Kontrakt kontrakt5 = new Kontrakt(annonse8, megler1, leietaker7, 36);
//        kontraktRegister.leggTilObjekt(kontrakt1);
//        kontraktRegister.leggTilObjekt(kontrakt2);
//        kontraktRegister.leggTilObjekt(kontrakt3);
//        kontraktRegister.leggTilObjekt(kontrakt4);
//        kontraktRegister.leggTilObjekt(kontrakt5);
//        System.out.println(kontraktRegister.visRegister());
//        System.out.println("================================================");
//        ////////////////////////////////////////////////////////////////////////
//
//        Soknad soknad1 = new Soknad(annonse1, leietaker1);
//        Soknad soknad2 = new Soknad(annonse5, leietaker2);
//        Soknad soknad3 = new Soknad(annonse4, leietaker3);
//        Soknad soknad4 = new Soknad(annonse4, leietaker6);
//        Soknad soknad5 = new Soknad(annonse4, leietaker4);
//        Soknad soknad6 = new Soknad(annonse4, leietaker7);
//        Soknad soknad7 = new Soknad(annonse2, leietaker1);
//        soknadRegister.leggTilObjekt(soknad1);
//        soknadRegister.leggTilObjekt(soknad2);
//        soknadRegister.leggTilObjekt(soknad3);
//        soknadRegister.leggTilObjekt(soknad4);
//        soknadRegister.leggTilObjekt(soknad5);
//        soknadRegister.leggTilObjekt(soknad6);
//        soknadRegister.leggTilObjekt(soknad7);
//        System.out.println(soknadRegister.visRegister());
//        System.out.println("================================================");
//        ////////////////////////////////////////////////////////////////////////        
    }

}
