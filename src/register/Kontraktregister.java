package register;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.util.*;

/**
 * Kontraktregister arver Register. 
 * @author espen
 * @param <Kontrakt> 
 */

public class Kontraktregister<Kontrakt> extends Register<Kontrakt>{

    //protected HashSet<Kontrakt> kontraktRegister;
    
    public Kontraktregister( HashSet<Kontrakt> kontraktRegister ){
        //kontraktRegister = new HashSet<>();
        super.collection = kontraktRegister;
    }


}
