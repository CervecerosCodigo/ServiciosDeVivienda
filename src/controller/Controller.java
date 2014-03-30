package controller;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.util.*;
import model.*;
import view.*;

public class Controller {

    private Register register;
    private VelkomstMainFrame startGUI;

    public Controller(Register register, VelkomstMainFrame startGUI) {
        this.register = register;
        this.startGUI = startGUI;
        testMetodeEspen();

    }

    private void testMetodePetter() {

    }

    private void testMetodeLukas() {

    }

    private void testMetodeEspen() {

        Post nyPost = new Post("Oslo");
        Post nyPost2 = new Post("Oslo");
        oppdaterPoststedRegister(nyPost, 1);
        oppdaterPoststedRegister(nyPost2, 1);
        oppdaterPoststedRegister(nyPost2, -1);
        oppdaterPoststedRegister(nyPost2, -1);

//        Calendar tilgjengelig = new GregorianCalendar( 2014, 03, 29 );
//        Calendar utlopsdato = new GregorianCalendar( 2014, 05, 26 );
//        
//
//        Bolig nyLeilighet = new Leilighet(3, 0, 10, false, false, true, 10, "Gladengveien 15A", 
//                "0661", "Oslo", 65, 1972, "Flott leilighet, solvendt.", false, tilgjengelig);
//        Bolig nyEnebolig = new Enebolig( Boligtype.ENEBOLIG, 2, true, 650, 11, "Ivar Aasens vei 23", 
//                "0373", "Oslo", 190, 1939, "Villa på Vindern..", false, tilgjengelig);
//        
//        register.leggTilObjekt( nyLeilighet );
//        register.leggTilObjekt( nyEnebolig );
//        System.out.println("================================================");
//        register.visRegister( nyLeilighet );
//        
//        Annonse annonse1 = new Annonse( 30000, 10000, utlopsdato, nyLeilighet);
//        Annonse annonse2 = new Annonse( 45000, 15000, utlopsdato, nyEnebolig);
//        register.leggTilObjekt( annonse1 );
//        register.leggTilObjekt( annonse2 );
//        System.out.println("================================================");
//        
//        
//        register.visRegister( annonse1 );
//        
//        System.out.println("================================================");
//        Leietaker nyLeietaker = new Leietaker();
//        Leietaker nyLeietaker2 = new Leietaker();      
//        Soknad nySoknad = new Soknad( annonse1, nyLeietaker);
//        register.leggTilObjekt( nySoknad );
//        register.visRegister( nySoknad );;
    }

    private void oppdaterPoststedRegister(Post post, int oppdateringAvAntall) {

        if (post != null) {
            if (!register.finnesObjektet(post) && oppdateringAvAntall > 0) {
                register.leggTilObjekt(post);
                System.out.println("Lagt til poststedet " + post.getPostSted());
            } else {
                if ((post.getAntallBoliger() + oppdateringAvAntall) <= 0) {
                    register.slettObjekt(post);
                    System.out.println("Poststedet har ingen boliger for utleie og har blitt slettet!");
                    return;
                }
                post.oppdaterAntallBoliger(oppdateringAvAntall);
                System.out.println("Oppdatert antall boliger for " + post.getPostSted() + " med " + oppdateringAvAntall);
                System.out.println(post.getPostSted() + " " + post.getAntallBoliger());
            }
        }
    }

}
