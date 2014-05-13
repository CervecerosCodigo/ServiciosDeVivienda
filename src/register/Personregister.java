package register;

import java.io.Serializable;
import java.util.*;

/**
 * @param <Person>
 */
public class Personregister<Person> extends Register implements Serializable{

    /**
     * @param personRegister
     */
    public Personregister( HashSet<Person> personRegister){
        super.collection = personRegister;
    }
}
