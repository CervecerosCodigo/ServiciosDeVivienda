package controller;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.util.*;
import model.*;
import register.*;
import view.*;

public class Controller {

    private Register personRegister;
    private Register boligRegister;
    private Register annonseRegister;
    private Register kontraktRegister;
    private Register soknadRegister;
    private Register postRegister;
    
    private VelkomstMainFrame startGUI;
    

    public Controller( VelkomstMainFrame startGUI) {
        this.startGUI = startGUI;
        
        personRegister = new Personregister();
        boligRegister = new Boligregister();
        annonseRegister = new Annonseregister();
        kontraktRegister = new Kontraktregister();
        soknadRegister = new Soknadregister();
        postRegister = new Postregister();        
        testMetodeEspen();
        
    }

    private void testMetodePetter() {

    }

    private void testMetodeLukas() {

    }

    private void testMetodeEspen() {

        Post nyPost = new Post("Oslo", 1);
        Post nyPost2 = new Post("Oslo", 1);
        Post nyPost3 = new Post("Lillestrøm", 1);
        Post nyPost4 = new Post("Askim", 1);
        oppdaterPoststedRegister(nyPost, 1);
        oppdaterPoststedRegister(nyPost2, 1);
        oppdaterPoststedRegister(nyPost3, 1);
        oppdaterPoststedRegister(nyPost4, 1);
        System.out.println(postRegister.visRegister());


        Calendar tilgjengelig = new GregorianCalendar( 2014, 03, 29 );
        Calendar utlopsdato = new GregorianCalendar( 2014, 05, 26 );
        

        Bolig nyLeilighet = new Leilighet(3, 0, 10, false, false, true, 10, "Gladengveien 15A", 
                "0661", "Oslo", 65, 1972, "Flott leilighet, solvendt.", false, tilgjengelig);
        Bolig nyEnebolig = new Enebolig( Boligtype.ENEBOLIG, 2, true, 650, 11, "Ivar Aasens vei 23", 
                "0373", "Oslo", 190, 1939, "Villa på Vindern..", false, tilgjengelig);
        boligRegister.leggTilObjekt( nyLeilighet );
        boligRegister.leggTilObjekt( nyEnebolig );
        
        System.out.println("================================================");
        System.out.println( boligRegister.visRegister() );
        
        Annonse annonse1 = new Annonse( 30000, 10000, utlopsdato, nyLeilighet);
        Annonse annonse2 = new Annonse( 45000, 15000, utlopsdato, nyEnebolig);
        annonseRegister.leggTilObjekt( annonse1 );
        annonseRegister.leggTilObjekt( annonse2 );
        System.out.println("================================================");
        System.out.println( annonseRegister.visRegister() );
        
        System.out.println("================================================");
        Leietaker nyLeietaker = new Leietaker();
        Leietaker nyLeietaker2 = new Leietaker();      
        Soknad nySoknad = new Soknad( annonse1, nyLeietaker);
        soknadRegister.leggTilObjekt( nySoknad );
        System.out.println( soknadRegister.visRegister( ) );
    }

    /**
     * Hjelpemetode for å oppdatere postregisteret. Avhengig av parameteren
     * oppdateringAvAntall vil metoden utføre forskjellige operasjoner. 
     * @param post
     * @param oppdateringAvAntall 
     */
    private void oppdaterPoststedRegister(Post post, int oppdateringAvAntall) {

        if (post != null) {
            if (!postRegister.finnesObjektet(post) && oppdateringAvAntall > 0) {
                postRegister.leggTilObjekt(post);
                System.out.println("Lagt til poststedet " + post.getPostSted());
            } else {
                if ((post.getAntallBoliger() + oppdateringAvAntall) <= 0) {
                    postRegister.slettObjekt(post);
                    System.out.println("Poststedet har ingen boliger for utleie og har blitt slettet!");
                    return;
                }
                Post ny = new Post(post.getPostSted(), (post.getAntallBoliger() + oppdateringAvAntall));
                postRegister.slettObjekt(post);
                postRegister.leggTilObjekt(ny);
                System.out.println("Oppdatert antall boliger for " + ny.getPostSted() + " med " + oppdateringAvAntall);
                //System.out.println(ny.getPostSted() + " har " + ny.getAntallBoliger() + " boliger for utleie.");
            }
        }//System.out.println(post.toString());
        //register.visRegister(post);
    }

}
