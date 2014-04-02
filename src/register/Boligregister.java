package register;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.util.*;

/**
 * Boligregister som arver Register, dens metoder og datasett.
 * @author espen
 * @param <Bolig> 
 */
public class Boligregister<Bolig> extends Register {

    //protected HashSet<Bolig> boligRegister;

    public Boligregister( HashSet<Bolig> boligRegister ) {
        //boligRegister = new HashSet<>();
        super.collection = boligRegister;
        
    }
    

}
