package controller;

import java.util.ArrayList;
import java.util.HashSet;
import lib.ObjektType;

/**
 * Interfacet brukes til kommunikasjon mellom controllere og lister. Brukes for
 * å sende søkeresultat fra toppanel megler til maninkotroller slik at tabellen
 * kan populeres. File: ListListener.java Package: controller Project:
 * ServiciosDeVivienda Apr 24, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 * @param <E>
 */
public interface ListListener<E> {

    /**
     * Brukes mellom TopPanelMegler og ControllerToppPanelMegler.
     * @param liste
     * @param obj 
     */
    public void listReady(ArrayList<E> liste, ObjektType obj);
    
    /**
     * Brukes mellom TopPanelAnnonse og ControllerToppPanelAnnonse
     * @param liste
     * @param obj 
     */
    public void listReady(HashSet<E> liste, ObjektType obj);
}
