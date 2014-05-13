package register;

import java.io.Serializable;
import java.util.*;

/**
 * Kontraktregister arver Register. 
 * @param <Kontrakt> 
 */

public class Kontraktregister<Kontrakt> extends Register<Kontrakt> implements Serializable{
 
    /**
     * @param kontraktRegister
     */
    public Kontraktregister( HashSet<Kontrakt> kontraktRegister ){
        super.collection = kontraktRegister;
    }
}
