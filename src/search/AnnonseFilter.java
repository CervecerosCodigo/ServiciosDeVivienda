package search;

import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;
import lib.Boligtype;
import lib.Konstanter;
import model.Annonse;
import model.Enebolig;
import model.Leilighet;

/**
 * Klassen sørger for filtrering av annoser som brukes av boligsøkende. Den har
 * også metoder som returnerer opplysninger om antall annonser, poststeder med
 * annonser og boligtyper som inngår i annonser. 
 * <br>
 * File: AnnonseFilter.java
 * Package: search Project: ServiciosDeVivienda Apr 20, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class AnnonseFilter {

    private HashSet<Annonse> annonseListeOriginal;
    private HashSet<Annonse> annonseListeFiltrert;
    private HashSet<Annonse> annonseListeTmp;

    public AnnonseFilter(HashSet<Annonse> annonseliste) {
        this.annonseListeOriginal = annonseliste;
        annonseListeFiltrert = new HashSet<>();
        annonseListeTmp = new HashSet<>();
    }

    //TODO: Den beste måten blir mest sannsynlig da man sender inn en oppsetting av søkeparametre fra gui og foreta en søkerutine etter det. Rekkefølge av filtreringstrinn må foregå på altid samme måte.
    //TODO: En metode som returnerer annonser fra et vist dato
    public HashSet<Annonse> filtrerEtterParametre(String poststed, Boligtype boligtype, int prisMin, int prisMaks, int arealMin, int arealMaks, boolean harBalkong, boolean harFellesvask, boolean harHage, boolean harKjeller) {

        filtrerEtterPostSted(poststed);
        filtrerEtterBoligType(boligtype);
        filtrerEtterPrisRange(prisMin, prisMaks);
        filtrerEtterBoArealRange(arealMin, arealMaks);
        if (harBalkong) {
            filtrerEtterBalkong();
        }
        if (harFellesvask) {
            filtrerEtterFellesvaskeri();
        }
        if (harHage) {
            filtrerEtterHage();
        }
        if (harKjeller) {
            filtrerEtterKjeller();
        }

        return getFilteredResults();
    }

    /**
     * Filtrer tilgjengelige annonser etter poststed. Brukes som trinn 1 i
     * interne filtrering.
     *
     * @param poststed Kan være null
     */
    private void filtrerEtterPostSted(String poststed) {
        if (poststed == null) {//All filtrering starter internt gjennom at man foretar første filtrering på poststed og på slik måte kan data fra set med annonse bli kopiert til set med filtrert data. Dersom bruker ikke velger poststed som inisjerer vi søkefunksjonen gjennom å kopiere all data fra annonseregisteret til filtrert set ettertsom det betyr at brukeren vil filtrerere på alle tilgjengelige poststeder.
            annonseListeTmp = new HashSet<>(annonseListeOriginal);
            //nå kan vi legge over data fra tmp set til filtrerte resultat
            kopierTilFiltrerteResultat();
        } else {
            for (Annonse a : annonseListeOriginal) {
                if (a.getBolig() != null) {
                    String p = a.getBolig().getPoststed();
                    if (p.equals(poststed)) {
                        annonseListeTmp.add(a);
                    }
                }
            }
            kopierTilFiltrerteResultat();
        }
    }

    /**
     * Filtrerer etter valgt boligtype. Ikke den peneste løsningen ettersom vi
     * initiliserer ikke datafelt Boligtype i superklassen. Vi gjør det først i
     * subklassen Leilighet eller Enebolig hvilket gjør det umulig her å vite
     * hvilke type av bolig vi har med å gjøre uten å se på selve objektet.
     * Dersom vi skal utøke dette slik at det skal være mulig å søke etter andre
     * boligytyper så bør vi flytte initilisering av boligtype til superklasse
     * Bolig. Dette er trinn 2 i den klasse interne filtreringen.
     *
     * @param t Boligtype
     */
    private void filtrerEtterBoligType(Boligtype t) {
        //Dersom parametern er tom trenger vi ikke å gjøre noe
        if (t != null) {
            for (Annonse a : annonseListeFiltrert) {
                if (a.getBolig() != null) {
                    //Foreløpig så bruker vi ikke alle boligtyper som er definiert i lib.boligtype så jeg tester kun på disse det medfører at vi kan bare ha instanser av Leilighet eller Enebolig.
                    if (t.equals(Boligtype.LEILIGHET) && a.getBolig() instanceof Leilighet) {
                        annonseListeTmp.add(a);
                    } else if (t.equals(Boligtype.ENEBOLIG) && a.getBolig() instanceof Enebolig) {
                        annonseListeTmp.add(a);
                    }
                }
            }
            kopierTilFiltrerteResultat();
        }
    }

    /**
     * Filtrerer etter prisrange for utleie. Trinn 3 i intern filtrering.
     *
     * @param min int
     * @param maks int
     */
    private void filtrerEtterPrisRange(int min, int maks) {
        if (min != 0 && maks != 0) {//Hvis det blir sendt in tomme parametre
            for (Annonse a : annonseListeFiltrert) {
                if (a.getBolig() != null) {
                    if (min <= a.getUtleiepris() && a.getUtleiepris() <= maks) {
                        annonseListeTmp.add(a);
                    }
                }
            }
            kopierTilFiltrerteResultat();
        }
    }

    /**
     * Filtrere etter range for boareal. Trinn 4 i itern filtrering.
     *
     * @param min
     * @param maks
     */
    private void filtrerEtterBoArealRange(int min, int maks) {
        if (min != 0 && maks != 0) {
            for (Annonse a : annonseListeFiltrert) {
                if (a.getBolig() != null) {
                    if (min <= a.getBolig().getBoAreal() && a.getBolig().getBoAreal() <= maks) {
                        annonseListeTmp.add(a);
                    }
                }
            }
            kopierTilFiltrerteResultat();
        }
    }

    /**
     * Filtrer etter balkong for leilighet.
     */
    private void filtrerEtterBalkong() {
        for (Annonse a : annonseListeFiltrert) {
            if (a.getBolig() != null && a.getBolig() instanceof Leilighet) {
                Leilighet b = (Leilighet) a.getBolig();
                if (b.getBalkongAreal() > 0) {
                    annonseListeTmp.add(a);
                }
            }
        }
        kopierTilFiltrerteResultat();
    }

    /**
     * Metoden skal returnere de boliger som har en tillegskrav som balkong,
     * fellesvaskeri, hage eller kjeller.
     */
    private void filtrerEtterFellesvaskeri() {
        for (Annonse a : annonseListeFiltrert) {
            if (a.getBolig() != null && a.getBolig() instanceof Leilighet) {
                Leilighet b = (Leilighet) a.getBolig();
                if (b.isHarFellesvaskeri()) {
                    annonseListeTmp.add(a);
                }
            }
        }
        kopierTilFiltrerteResultat();
    }

    /**
     * Vi har den type av filtrering i gui tegningen men vi har ikke oprettet
     * noen datafelt for hage. Derfor en enebolig blir definiert som at den har
     * hage dersom tomt arealen er 20% større enn boarealen. Vi kan låtses som
     * at slike er foreskriftene som definierer en hage :)
     */
    private void filtrerEtterHage() {
        for (Annonse a : annonseListeFiltrert) {
            if (a.getBolig() != null && a.getBolig() instanceof Enebolig) {
                Enebolig b = (Enebolig) a.getBolig();
                if (b.getTomtAreal() >= (b.getBoAreal() * 1.2)) {
                    annonseListeTmp.add(a);
                }
            }
        }
        kopierTilFiltrerteResultat();
    }

    /**
     * Filtrer etter bolig dersom den har kjeller.
     */
    private void filtrerEtterKjeller() {
        for (Annonse a : annonseListeFiltrert) {
            if (a.getBolig() != null && a.getBolig() instanceof Enebolig) {
                Enebolig b = (Enebolig) a.getBolig();
                if (b.isHarKjeller()) {
                    annonseListeTmp.add(a);
                }
            }
        }
        kopierTilFiltrerteResultat();
    }

    /**
     * Brukes internt for å kopiere over resultat av filtreringen. Er tenkt å
     * brukes emellom trinnene for filtrering slik at det filtrerte settet
     * skrives over først etter at filtrering er foretatt.
     */
    private void kopierTilFiltrerteResultat() {
        annonseListeFiltrert = new HashSet<>(annonseListeTmp);
        annonseListeTmp.clear();
    }

    /**
     * Brukes bare midlertidlig for testing, skal slettes etterpå. Returnerer en
     * hash set med filtrerte resultater.
     *
     * @return HashSet<Annonse>
     */
    public HashSet<Annonse> getFilteredResults() {
        return annonseListeFiltrert;
    }

    /**
     * Returnerer en sortert set med over alle poststeder med utannonserte
     * boliger.
     *
     * @return SortedSet<String>
     * @throws ParseException
     */
    public SortedSet<String> getPoststederIAnnonser() throws ParseException {
        SortedSet<String> poststederMedAnnonser = new TreeSet<>(new RuleBasedCollator(Konstanter.KOLLATOR_REKKEFOLGE));

        for (Annonse a : annonseListeOriginal) {
            poststederMedAnnonser.add(a.getBolig().getPoststed());
        }
        return poststederMedAnnonser;
    }

    /**
     * Returner set med enum typer over type av boliger som finnes i
     * boligregisteret. Foreløpig kun for enebolig og leilighet da det er kun
     * disse vi har oprettet klaaser av fra superklassen. //TODO: Kan egentlig
     * gjøre om denne til an map slik at man sender med en int som viser antal
     * annonser for forskjellige boligtype.
     *
     * @return SortedSet<Boligtype>
     * @throws ParseException
     */
    public SortedSet<Boligtype> getBoligtyperIAnnonser() throws ParseException {
        SortedSet<Boligtype> boligtyperIAnnonser = new TreeSet<>();

        for (Annonse a : annonseListeOriginal) {
            if (a.getBolig() instanceof Leilighet) {
                Leilighet b = (Leilighet) a.getBolig();
                boligtyperIAnnonser.add(b.getBoligtype());
            } else if (a.getBolig() instanceof Enebolig) {
                Enebolig c = (Enebolig) a.getBolig();
                boligtyperIAnnonser.add(c.getBoligtype());
            }
        }
        return boligtyperIAnnonser;
    }

    /**
     * Returnerer antall annonser i registeret.
     *
     * @return int
     */
    public int getAntallAnnonser() {
        return annonseListeOriginal.size();
    }

    /**
     * Virker ikke! Hele denne metoden har mange småfeil er derfor satt til
     * private. Bruker foreløpig ikke mer tid på denne dersom til vi eventuelt
     * bestemmer oss for at en slik funksjon er nødvendig.
     *
     * Returnerer en liste over annonser gitt et intervall. Kan brukes feks når
     * man skal hente opp seineste 10 annonser eller et annet intervall for å
     * vise i GUI.
     *
     * @param start int
     * @param stopp int
     * @return ArrayList<Annonse>
     * @throws SearchException
     */
    private ArrayList<Annonse> getAnnonseIntervall(int start, int stopp) throws SearchException {
        int antallAnnonserTot = getAntallAnnonser();
        int antallTilHenting = stopp - start;
        ArrayList<Annonse> retur = null;

        try {
            if (antallTilHenting <= antallAnnonserTot) {
                Annonse[] a = new Annonse[annonseListeOriginal.size()];
                a = (Annonse[]) annonseListeOriginal.toArray();
                annonseListeOriginal.toArray(a);

                for (int i = (start - 1); i <= (stopp - 1); i++) {
                    retur.add(a[i]);//FIXME: Må testes, kan resultere i nullpointer
                }
            } else {
                throw new SearchException("Ikke funnet");
            }
        } catch (SearchException ex) {
            //TODO: Her skal det gjøres noe
        }
        return retur;
    }

}
