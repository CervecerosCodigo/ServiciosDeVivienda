package search;

/**
 * Interfacet er påkrevd for alle klasser i hvilke det skal være mulig å
 * gjennomføre fritekstsøk. File: Searchable.java Package: search Project:
 * ServiciosDeVivienda Apr 19, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public interface Searchable {

    /**
     * Metoden må returnere alle datafelt i en klasse som skal inngå i
     * fritekstsøk. Datafeltene skal castes til String og legges i en array.
     *
     * @return String[]
     */
    String[] toSearch();
}
