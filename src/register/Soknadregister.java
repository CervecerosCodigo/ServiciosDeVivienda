package register;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.util.*;


/**
 * Søknadsregister arver Register. Bruker et LinkedHashSet for å beholde rekkefølgen
 * på innsatte objektet.
 * @author espen
 * @param <Soknad> 
 */
public class Soknadregister<Soknad> extends Register<Soknad>{

    protected LinkedHashSet<Soknad> soknadRegister;
    
    public Soknadregister(){
        soknadRegister = new LinkedHashSet<>();
        super.collection = soknadRegister;
    }

}
