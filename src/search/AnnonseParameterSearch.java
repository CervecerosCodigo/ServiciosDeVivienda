package search;

import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;
import lib.Konstanter;
import model.Annonse;

/**
 * I klassen inngår metoder som gjør det mulig å søke igjennom alle annonser fra
 * boligsøker vindu gjennom å oppgi nødvendige parametre. File:
 * AnnonseParameterSearch.java Package: search Project: ServiciosDeVivienda Apr
 * 20, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class AnnonseParameterSearch {

    private HashSet<Annonse> annonselisteOriginal; //Original liste over annonser. Beholdes dersom brukeren foretar en nullstilling av filtre, da har vi kvar all data og kan filtrere denne på nytt med nye parametre
    private HashSet<Annonse> annonselisteFiltrert; //Fitrerte resultat. Denne blir oppdatert med hvert nytt parameter som sendes med til metodene.

    public AnnonseParameterSearch(HashSet<Annonse> annonseliste) {
        this.annonselisteOriginal = annonseliste;
    }

    //TODO: Det beste er eventuelt å hente opp all data til egen set og har flere metoder som filtrerer resultat avhengig av valgte parametre
    //TODO: En metode som resetter filtrerte resultat
    //TODO: En metode som returnerer annonser fra et vist dato
    
    /**
     * Returnerer en sortert set med over alle poststeder med utannonserte
     * boliger.
     *
     * @return SortedSet<String>
     * @throws ParseException
     */
    public SortedSet<String> getPoststedList() throws ParseException {
        SortedSet<String> poststederMedAnnonser = new TreeSet<>(new RuleBasedCollator(Konstanter.KOLLATOR_REKKEFOLGE));

        for (Annonse a : annonselisteOriginal) {
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
        return annonselisteOriginal.size();
    }

    /**
     * Returnerer en liste over annonser gitt et intervall. Kan brukes feks når man skal hente opp seineste 10 annonser eller et annet intervall for å vise i GUI. 
     * @param start int
     * @param stopp int
     * @return ArrayList<Annonse>
     * @throws SearchException 
     */
    public ArrayList<Annonse> getAnnonseIntervall(int start, int stopp) throws SearchException {
        int antallAnnonserTot = getAntallAnnonser();
        int antallTilHenting = stopp - start;
        ArrayList<Annonse> retur = null;

        try {
            if (antallTilHenting <= antallAnnonserTot) {
                Annonse[] a = (Annonse[]) annonselisteOriginal.toArray();
                for (int i = start - 1; i < stopp - 1; i++) {
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

    //Sluttet her
}
