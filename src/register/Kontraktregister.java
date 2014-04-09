package register;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.io.Serializable;
import java.util.*;

/**
 * Kontraktregister arver Register. 
 * @author espen
 * @param <Kontrakt> 
 */

public class Kontraktregister<Kontrakt> extends Register<Kontrakt> implements Serializable{
 
    /**
     *
     * @param kontraktRegister
     */
    public Kontraktregister( HashSet<Kontrakt> kontraktRegister ){

        super.collection = kontraktRegister;
    }


}
