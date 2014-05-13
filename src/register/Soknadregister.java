package register;

import java.io.Serializable;
import java.util.*;

/**
 * Søknadsregister arver Register. Bruker et LinkedHashSet for å beholde rekkefølgen
 * på innsatte objektet.
 * @param <Soknad> 
 */
public class Soknadregister<Soknad> extends Register<Soknad> implements Serializable{

    /**
     * @param soknadRegister
     */
    public Soknadregister( HashSet<Soknad> soknadRegister ){

        super.collection = soknadRegister;
    }
}
