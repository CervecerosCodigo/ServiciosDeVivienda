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

/**
 * I klassen inngår metoder som gjør det mulig å søke igjennom alle annonser fra
 * boligsøker vindu gjennom å oppgi nødvendige parametre.<br><br>
 * <u>Viktig!</u><br>
 * Søknigen fungerer gjennom at den eksisterende listen over annonser blir kopiert til en filtrert liste. Dett skjer i en spesifikk rekkefølge der den første filtreringen må foretas etter poststed med metoden filtrerEtterPoststed() ettersom den metoden kopierer den første data til det filtrerte settet. 
 *<br><br>
 * File: AnnonseParameterSearch.java Package: search Project:
 * ServiciosDeVivienda Apr 20, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class AnnonseParameterSearch {

    private HashSet<Annonse> annonseListeOriginal;
    private HashSet<Annonse> annonseListeFiltrert;
    private HashSet<Annonse> annonseListeTmp;

    public AnnonseParameterSearch(HashSet<Annonse> annonseliste) {
        this.annonseListeOriginal = annonseliste;
        annonseListeFiltrert = new HashSet<>();
        annonseListeTmp = new HashSet<>();
    }
    
    
    //TODO: Den beste måten blir mest sannsynlig da man sender inn en oppsetting av søkeparametre fra gui og foreta en søkerutine etter det. Rekkefølge av filtreringstrinn må foregå på altid samme måte.

    //TODO: Det beste er eventuelt å hente opp all data til egen set og har flere metoder som filtrerer resultat avhengig av valgte parametre
    //TODO: En metode som resetter filtrerte resultat
    //TODO: En metode som returnerer annonser fra et vist dato
    
    
    public HashSet<Annonse> filtrerEtterParametre(String poststed, Boligtype boligtype, int prisMin, int prisMaks, int arealMin, int arealMaks, boolean harBalkong, boolean harFellesvask, boolean harHage, boolean harKjeller){
        //Sluttet her. Her skal det foretas trinnvis filtrering etter hvilke parametre blir tilgjengelige for filtrering. 
        return null;
    }
    
    
    
    /**
     * Filtrer tilgjengelige annonser etter poststed. 
     * Brukes som trinn 1 i filtreringen.
     * @param poststed
     */
    public void filtrerEtterPostSted(String poststed) {
        for (Annonse a : annonseListeOriginal) {
            String p = a.getBolig().getPoststed();
            if (p.equals(poststed)) {
                annonseListeTmp.add(a);
            }
        }
        kopierTilFiltrerteResultat();
    }
    
    
//    public void filtrerEtterBoligType(Boligtype t){
//        for(Annonse a : annonseListeFiltrert){
//            if(t.compareTo(a.getBolig().))
//        }
            
//    }
    
    /**
     * Brukes internt for å kopiere over resultat av filtreringen. Er tenkt å brukes emellom trinnene for filtrering slik at det filtrerte settet skrives over først etter at filtrering er foretatt. 
     */
    private void kopierTilFiltrerteResultat(){
        annonseListeFiltrert = annonseListeTmp;
    }
    
    /**
     * Brukes bare midlertidlig for testing, skal slettes etterpå. Returnerer en hash set med filtrerte resultater.
     * @return HashSet<Annonse>
     */
    public HashSet<Annonse> getFilteredResults(){
        return annonseListeFiltrert;
    }
    
    
    /**
     * Returnerer en sortert set med over alle poststeder med utannonserte
     * boliger.
     *
     * @return SortedSet<String>
     * @throws ParseException
     */
    public SortedSet<String> getPoststedList() throws ParseException {
        SortedSet<String> poststederMedAnnonser = new TreeSet<>(new RuleBasedCollator(Konstanter.KOLLATOR_REKKEFOLGE));

        for (Annonse a : annonseListeOriginal) {
            poststederMedAnnonser.add(a.getBolig().getPoststed());
        }
        return poststederMedAnnonser;
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
