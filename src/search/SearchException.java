
package search;

/**
 * En egen exception som brukes sammen med søkefunksjoner.
 * File: SearchException.java
 * Package: search
 * Project: ServiciosDeVivienda
 * Apr 20, 2014
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class SearchException extends Exception {

    /**
     * Creates a new instance of <code>SearchException</code> without detail message.
     */
    public SearchException() {
    }


    /**
     * Constructs an instance of <code>SearchException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public SearchException(String msg) {
        super(msg);
    }
}
