package register;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.io.Serializable;
import java.util.*;

/**
 * Annonseregister arver Register
 * @author espen
 * @param <Annonse> 
 */
public class Annonseregister<Annonse> extends Register<Annonse> implements Serializable{

    /**
     *
     * @param annonseRegister
     */
    public Annonseregister( HashSet<Annonse> annonseRegister ) {

        super.collection = annonseRegister;
    }

}
