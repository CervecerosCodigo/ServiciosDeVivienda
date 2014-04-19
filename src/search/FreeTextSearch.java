package search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * //Klassen er til for å teste først dersom en slik løsning kommer til å
 * fungere. Det kan være mer lempelig å bruke generics her. File:
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
