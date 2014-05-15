package controller;

import java.util.ArrayList;
import java.util.HashSet;
import lib.ObjektType;

/**
 * Interfacet brukes til kommunikasjon mellom controllere og lister. Brukes for
 * å sende søkeresultat fra toppanel megler til maninkotroller slik at tabellen
 * kan populeres.
 *
 * @param <E>
 */
public interface ListListenerInterface<E> {

    /**
     * Brukes mellom TopPanelMegler og ControllerToppPanelMegler.
     * @param liste
     * @param obj 
     */
    public void listReady(HashSet<E> liste, ObjektType obj);
    
}

