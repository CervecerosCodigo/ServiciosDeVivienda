package search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Klassen kan brukes for fritekssøk over datafelt fra registre. 
 * File:
 * FreeTextSearch.java Package: search Project: ServiciosDeVivienda Apr 19, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 * @param <T>
 */
public class FreeTextSearch<T extends Searchable> {

    private final ArrayList<T> resultList;
    private String[] checkMeForResults;

    public FreeTextSearch() {
        resultList = new ArrayList<>();
    }

    /**
     * Metoden søker gjennom datafelt i gitt register over datafelt dersom
     * registret implementerer inteface Searchable der alle datafelt kan hentes
     * opp gjennom en String[] via metoden toSearch()
     *
     * @param liste
     * @param pattern
     * @return
     */
    public ArrayList<T> searchForPattern(HashSet<? extends Searchable> liste, String pattern) {
        
        pattern = pattern.trim();
        pattern = pattern.toLowerCase();

        if (pattern.equalsIgnoreCase("søk") || pattern.equals("") || pattern.equals("*")) {
            for (Searchable o : liste) {
                resultList.add((T) o);
            }
        } else {

            for (Searchable o : liste) {
                checkMeForResults = o.toSearch();

                for (String s : checkMeForResults) {
                    s = s.toLowerCase();
                    if (s.contains(pattern)) {
                        resultList.add((T) o);
                    }
                }
            }
        }
        return resultList;
    }
}
