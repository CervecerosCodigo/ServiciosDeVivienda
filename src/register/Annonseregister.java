package register;

import java.io.Serializable;
import java.util.*;

/**
 * Annonseregister arver Register
 * @param <Annonse> 
 */
public class Annonseregister<Annonse> extends Register<Annonse> implements Serializable{

    /**
     * @param annonseRegister
     */
    public Annonseregister( HashSet<Annonse> annonseRegister ) {
        super.collection = annonseRegister;
    }

}
