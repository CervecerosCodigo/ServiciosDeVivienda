package register;

import java.io.Serializable;
import java.util.*;

/**
 * Boligregister som arver Register, dens metoder og datasett.
 * @param <Bolig> 
 */
public class Boligregister<Bolig> extends Register implements Serializable{

    /**
     * @param boligRegister
     */
    public Boligregister( HashSet<Bolig> boligRegister ) {
        super.collection = boligRegister;
    }
}
