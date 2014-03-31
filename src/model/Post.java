package model;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.util.*;

/**
 * Postobjekter ment for bruk i annonsene. Alle annonser er registrert med 
 * postnummer og poststed. Poststedet blir så lagret i Post-registeret 
 * sammen med antall boliger i det distriktet. Om det ikke finnes boliger tilgjenglig 
 * for leie vil Post-objektet slettes, og ikke vises i annonseoversikten.
 * @author espen
 */
public class Post {

    private String poststed;
    private int antallBoliger;
    
    public Post( String poststed, int oppdatering){
        this.poststed = poststed;
        antallBoliger = oppdatering;
    }
    
    public String getPostSted(){
        return poststed;
    }
    public int getAntallBoliger(){
        return antallBoliger;
    }
    
    
    @Override
    public String toString(){
        return poststed + "   " + antallBoliger + " boliger";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.poststed);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Post other = (Post) obj;
        if (!Objects.equals(this.poststed, other.poststed)) {
            return false;
        }
        return true;
    }

  

}
