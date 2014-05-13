package register;

import java.io.Serializable;
import java.util.*;

/**
 * Abstract klasse for alle registerne.
 * Subklassene arver alle metoder og datasettet collection.
 * @param <T> 
 */
public abstract class Register<T> implements Serializable{

    protected Collection<T> collection;

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
    
    public Collection getDatasett(){
        return collection;
    }

}
