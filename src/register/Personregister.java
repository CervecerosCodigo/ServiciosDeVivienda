package register;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author espen
 * @param <Person>
 */
public class Personregister<Person> extends Register implements Serializable{

    /**
     *
     * @param personRegister
     */
    public Personregister( HashSet<Person> personRegister){

        super.collection = personRegister;
    }





}
