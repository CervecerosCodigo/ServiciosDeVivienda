package register;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.util.*;



public class Personregister<Person> extends Register{

     protected HashSet<Person> personRegister;
    
    public Personregister(){
        personRegister = new HashSet<>();
        super.collection = personRegister;
    }


  
    @Override
    public String visRegister() {
        Iterator iter = super.collection.iterator();
        String info = "";
        while( iter.hasNext() ){
           info += iter.next().toString() ;
        }
        return info;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
