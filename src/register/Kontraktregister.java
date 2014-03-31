package register;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.util.*;

/**
 * Kontraktregister arver Register. 
 * @author espen
 * @param <Kontrakt> 
 */

public class Kontraktregister<Kontrakt> extends Register<Kontrakt>{

    protected HashSet<Kontrakt> kontraktRegister;
    
    public Kontraktregister(){
        kontraktRegister = new HashSet<>();
        super.collection = kontraktRegister;
    }


}
