package register;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.util.*;



public class Personregister<Person> extends Register{

     protected HashSet<Person> personRegister;
    
    public Personregister(){
        personRegister = new HashSet<>();
        super.collection = personRegister;
    }





}
