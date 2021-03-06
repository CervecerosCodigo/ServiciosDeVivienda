package search;

import java.util.ArrayList;
import java.util.HashSet;
import lib.Melding;
import model.Leietaker;
import model.Utleier;

/**
 * Klassen kan brukes for fritekssøk over datafelt fra registre.
 * @param <T>
 */
public class FreeTextSearch<T extends Searchable> {

    private final HashSet<T> resultList;
    private String[] checkMeForResults;

    public FreeTextSearch() {
        resultList = new HashSet<>();
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
    public HashSet<T> searchForPattern(HashSet<? extends Searchable> liste, String pattern) {

        pattern = pattern.trim();
        pattern = pattern.toLowerCase();

        if (liste != null) {
            if (pattern.equalsIgnoreCase("søk") || pattern.equals("") || pattern.equals("*")) {
                for (Searchable o : liste) {
                    resultList.add((T) o);
                }
            } 
            
            else {
                for (Searchable o : liste) {
                    checkMeForResults = o.toSearch();

                    for (String s : checkMeForResults) {
                        s = s.toLowerCase();
                        if (s.contains(pattern))
                            resultList.add((T) o);
                    }
                }
            }
            return resultList;
        } 
        
        else {
            System.out.println("En tom liste ble sendt inn til søkemetoden");
            return null;
        }
    }

    /**
     * Denne metoden eksisterer kun fordi at jeg ikke får til å sende et
     * generisk objekt til metoden og deretter teste på objektets instans. Har
     * brukt mye tid og prøvd alt så jeg hardkoder to metoder for utleier og
     * leietaker her isteden.
     *
     * @param liste
     * @param pattern
     * @return
     */
    public HashSet<T> searchForPatternIUtleier(HashSet<? extends Searchable> liste, String pattern) {

        pattern = pattern.trim();
        pattern = pattern.toLowerCase();

        if (liste != null) {
            if (pattern.equalsIgnoreCase("søk") || pattern.equals("") || pattern.equals("*")) {
                for (Searchable o : liste) {
                    if (o instanceof Utleier)
                        resultList.add((T) o);
                }
            } 
            
            else {

                for (Searchable o : liste) {
                    if (o instanceof Utleier) {
                        checkMeForResults = o.toSearch();

                        for (String s : checkMeForResults) {
                            s = s.toLowerCase();
                            if (s.contains(pattern))
                                resultList.add((T) o);
                        }
                    }
                }
            }
            return resultList;
        } 
        
        else {
            System.out.println("En tom liste ble sendt inn til søkemetoden");
            return null;
        }
    }

    /**
     * Denne metoden eksisterer kun fordi at jeg ikke får til å sende et
     * generisk objekt tile metoden og deretter teste på objektets instans. Har
     * brukt mye tid og prøvd alt så jeg hardkoder to metoder for utleier og
     * leietaker her isteden. 
     *
     * @param liste
     * @param pattern
     * @return
     */
    public HashSet<T> searchForPatternILeietaker(HashSet<? extends Searchable> liste, String pattern) {

        pattern = pattern.trim();
        pattern = pattern.toLowerCase();

        if (liste != null) {
            if (pattern.equalsIgnoreCase("søk") || pattern.equals("") || pattern.equals("*")) {
                for (Searchable o : liste) {
                    if (o instanceof Leietaker)
                        resultList.add((T) o);
                }
            } 
            
            else {
                for (Searchable o : liste) {
                    if (o instanceof Leietaker) {
                        checkMeForResults = o.toSearch();

                        for (String s : checkMeForResults) {
                            s = s.toLowerCase();
                            if (s.contains(pattern))
                                resultList.add((T) o);
                        }
                    }
                }
            }
            return resultList;
        } 
        
        else {
            System.out.println("En tom liste ble sendt inn til søkemetoden");
            return null;
        }
    }
}
