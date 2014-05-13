package search;

/**
 * Interfacet er påkrevd for alle klasser i hvilke det skal være mulig å
 * gjennomføre fritekstsøk. 
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
