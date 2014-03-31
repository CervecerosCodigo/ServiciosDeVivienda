package register;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.util.*;

/**
 * Annonseregister arver Register
 * @author espen
 * @param <Annonse> 
 */
public class Annonseregister<Annonse> extends Register<Annonse> {

    protected HashSet<Annonse> annonseRegister;
    
    public Annonseregister() {
        annonseRegister = new HashSet<>();
        super.collection = annonseRegister;
    }

}
