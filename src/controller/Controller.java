package controller;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.util.*;
import model.*;
import view.*;

public class Controller {

    private Register register;
    private VelkomstMainFrame startGUI;
    
    public Controller( Register register, VelkomstMainFrame startGUI ){
        this.register = register;
        this.startGUI = startGUI;
        testMetodeEspen();
        
    }
    
    private void testMetodePetter(){
        
    }
    private void testMetodeLukas(){
        
    }
    private void testMetodeEspen(){
        
        Bolig nyLeilighet = new Leilighet(3, 0, 10, false, false, true, 10, "Leilighet", "Gladengveien 15A", "0661", "Oslo", 65, 1972, "Flott leilighet, solvendt.", false);
        Bolig nyEnebolig = new Enebolig( 2, true, 650, 11, "Enebolig", "Ivar Aasens vei 23", "0373", "Oslo", 190, 1939, "Villa på Vindern..", false);
        
        register.leggTilObjekt( nyLeilighet );
        register.leggTilObjekt( nyEnebolig );
        System.out.println("================================================");
        //System.out.println(register.finnesObjektet( nyLeilighet ));
//        System.out.println( nyLeilighet.toString() );
//        System.out.println( nyEnebolig.toString() );
        
        Calendar utlopsdato = new GregorianCalendar( 2014, 05, 26 );
        Annonse annonse1 = new Annonse( 30000, 10000, utlopsdato, nyLeilighet);
        Annonse annonse2 = new Annonse( 45000, 15000, utlopsdato, nyEnebolig);
        register.leggTilObjekt( annonse1 );
        register.leggTilObjekt( annonse2 );
        System.out.println("================================================");
        
        register.visRegister( annonse1 );


//        System.out.println(annonse1.toString());
//        System.out.println(annonse2.toString());
    }
    
    
    
}
