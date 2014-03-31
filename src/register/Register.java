package register;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.util.*;

/**
 * Abstract klasse for alle registerne.
 * Subklassene arver alle metoder og datasettet collection.
 * @author espen
 * @param <T> 
 */
public abstract class Register<T> {

    protected Set<T> collection;

    public boolean leggTilObjekt(T e) {
        return collection.add(e);
    }

    public boolean finnesObjektet(T e) {
        return collection.contains(e);
    }

    public boolean slettObjekt(T e) {
        return collection.remove(e);
    }

    public boolean erRegisterTomt(T e) {
        return collection.isEmpty();
    }

    public int strAvRegister(T e) {
        return collection.size();
    }

    public String visRegister() {
        Iterator iter = collection.iterator();
        String info = "";
        while (iter.hasNext()) {
            info += iter.next().toString() + "\n";
        }
        return info;
    }

}
