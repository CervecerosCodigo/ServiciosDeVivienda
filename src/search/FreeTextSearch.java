package search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Klassen kan brukes for fritekssøk over datafelt fra registre.
 * File:
 FreeTextSearch.java Package: search Project: ServiciosDeVivienda Apr 19,
 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class FreeTextSearch<T extends Searchable> {

    private final ArrayList<T> resultList;
    private String[] checkMeForResults;
            

    public FreeTextSearch() {
        resultList = new ArrayList<>();
    }
    
    /**
     * Metoden søker gjennom datafelt i gitt register over datafelt dersom registret implementerer inteface Searchable der alle datafelt kan hentes opp gjennom en String[] via metoden toSearch()
     * @param liste
     * @param pattern
     * @return 
     */
    public ArrayList<T> searchForPattern(HashSet<? extends Searchable> liste, String pattern) {
        pattern = pattern.trim();
        pattern = pattern.toLowerCase();
        
        for (Searchable o : liste) {
            checkMeForResults = o.toSearch();

            for (String s : checkMeForResults) {
                if (s.contains(pattern)) {
                    resultList.add((T) o);
                    System.out.println("Funnet: "+o.toString());
                }
            }
        }
        return resultList;
    }
}
