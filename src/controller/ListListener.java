package controller;

import java.util.ArrayList;
import lib.ObjektType;

/**
 * Interfacet brukes til kommunikasjon mellom controllere og lister. Brukes for
 * å sende søkeresultat fra toppanel megler til maninkotroller slik at tabellen
 * kan populeres. File: ListListener.java Package: controller Project:
 * ServiciosDeVivienda Apr 24, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public interface ListListener<E> {

    public void listReady(ArrayList<E> liste, ObjektType obj);
}
