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

    private Register personRegister;
    private Register boligRegister;
    private Register annonseRegister;
    private Register kontraktRegister;
    private Register soknadRegister;
    private Postregister postRegister;

    private VelkomstMainFrame startGUI;

    public MainController(VelkomstMainFrame startGUI) {
        this.startGUI = startGUI;

        personRegister = new Personregister();
        boligRegister = new Boligregister();
        annonseRegister = new Annonseregister();
        kontraktRegister = new Kontraktregister();
        soknadRegister = new Soknadregister();
        postRegister = new Postregister();
        testData();

    }

    public Calendar opprettKalenderobjekt(int aar, int mnd, int dag) {
        Calendar kalender = new GregorianCalendar(aar, mnd, dag);
        return kalender;
    }

    private void testData() {

        Calendar tilgjengelig = new GregorianCalendar(2014, 03, 29);
        Calendar utlopsdato = new GregorianCalendar(2014, 05, 26);

        Person megler1 = new Megler("Per", "Meglersen", "megler@serviciosdevivienda.no", "45673300", 1000, "Oslokontoret");
        Person leietaker1 = new Leietaker("Line", "Larsen", "line@gmail.com", "48009067");
        Person leietaker2 = new Leietaker("Geir", "Fjæra", "geirf@gmail.com", "67004599");
        Person leietaker3 = new Leietaker("Nils", "Plassen", "nilsp@gmail.com", "22449044");
        Person utleier1 = new Utleier("Hans", "Pedersen", "pedersen@boflott.no", "90006788", true, "Bo flott AS");
        Person utleier2 = new Utleier("Petter", "Stordalen", "pstordalen@yahoo.com", "23904532", false, null);
        personRegister.leggTilObjekt(megler1);
        personRegister.leggTilObjekt(leietaker1);
        personRegister.leggTilObjekt(leietaker2);
        personRegister.leggTilObjekt(leietaker3);
        personRegister.leggTilObjekt(utleier1);
        personRegister.leggTilObjekt(utleier2);
        System.out.println(personRegister.visRegister());
        System.out.println("================================================");
        ////////////////////////////////////////////////////////////////////////
        Bolig nyLeilighet1 = new Leilighet(3, 0, 10, false, false, true, 5, "Gladengveien 15A",
                "0661", "Oslo", 65, 1972, "Flott leilighet, solvendt.", false, tilgjengelig);
        Bolig nyLeilighet2 = new Leilighet(1, 10, 0, false, true, false, 5, "Sinsenveien 34",
                "0345", "Oslo", 45, 1963, "Ikke så fin leilighet.", false, tilgjengelig);
        Bolig nyLeilighet3 = new Leilighet(8, 0, 10, true, false, true, 6, "Groruddalen 1",
                "0453", "Oslo", 75, 1970, "Flott leilighet, solvendt.", false, tilgjengelig);
        Bolig nyEnebolig1 = new Enebolig(Boligtype.ENEBOLIG, 2, true, 650, 6, "Ivar Aasens vei 23",
                "0373", "Oslo", 190, 1939, "Villa på Vindern..", false, tilgjengelig);
        Bolig nyEnebolig2 = new Enebolig(Boligtype.ENEBOLIG, 3, true, 1000, 6, "Ivar Aasens vei 24",
                "0373", "Oslo", 230, 1898, "Villa på Vindern..", false, tilgjengelig);
        boligRegister.leggTilObjekt(nyLeilighet1);
        boligRegister.leggTilObjekt(nyLeilighet2);
        boligRegister.leggTilObjekt(nyLeilighet3);
        boligRegister.leggTilObjekt(nyEnebolig1);
        boligRegister.leggTilObjekt(nyEnebolig2);
        System.out.println(boligRegister.visRegister());
        System.out.println("================================================");

        ////////////////////////////////////////////////////////////////////////
        Annonse annonse1 = new Annonse(30000, 10000, utlopsdato, nyLeilighet1);
        Annonse annonse2 = new Annonse(24000, 8000, utlopsdato, nyLeilighet2);
        Annonse annonse3 = new Annonse(21000, 7000, utlopsdato, nyLeilighet3);
        Annonse annonse4 = new Annonse(45000, 15000, utlopsdato, nyEnebolig1);
        Annonse annonse5 = new Annonse(60000, 20000, utlopsdato, nyEnebolig2);
        annonseRegister.leggTilObjekt(annonse1);
        annonseRegister.leggTilObjekt(annonse2);
        annonseRegister.leggTilObjekt(annonse3);
        annonseRegister.leggTilObjekt(annonse4);
        annonseRegister.leggTilObjekt(annonse5);
        System.out.println(annonseRegister.visRegister());
        System.out.println("================================================");
        ////////////////////////////////////////////////////////////////////////

        Kontrakt kontrakt1 = new Kontrakt(annonse1, megler1, leietaker1, 36);
        Kontrakt kontrakt2 = new Kontrakt(annonse2, megler1, leietaker2, 36);
        Kontrakt kontrakt3 = new Kontrakt(annonse5, megler1, leietaker3, 36);
        kontraktRegister.leggTilObjekt(kontrakt1);
        kontraktRegister.leggTilObjekt(kontrakt2);
        kontraktRegister.leggTilObjekt(kontrakt3);
        System.out.println(kontraktRegister.visRegister());
        System.out.println("================================================");
        ////////////////////////////////////////////////////////////////////////

        Soknad soknad1 = new Soknad(annonse1, leietaker1);
        Soknad soknad2 = new Soknad(annonse5, leietaker2);
        Soknad soknad3 = new Soknad(annonse4, leietaker3);
        soknadRegister.leggTilObjekt(soknad1);
        soknadRegister.leggTilObjekt(soknad2);
        soknadRegister.leggTilObjekt(soknad3);
        System.out.println(soknadRegister.visRegister());
        System.out.println("================================================");
        ////////////////////////////////////////////////////////////////////////    

        postRegister.leggTill("Oslo", 1);
        postRegister.leggTill("Oslo", 1);
        postRegister.leggTill("Oslo", 3);
        postRegister.leggTill("Drøbak", 3);
        postRegister.leggTill("Kristiansand", 3);
        postRegister.leggTill("Kristiansand", 3);
        System.out.println(postRegister.getStatistikk());
        postRegister.slettBoligerFraPoststed("Oslo", 5);
        System.out.println(postRegister.getStatistikk());
        System.out.println("================================================");
        ////////////////////////////////////////////////////////////////////////
    }

}
