package model;

import java.io.Serializable;
import lib.Konstanter;
import search.Searchable;

public abstract class Person implements Serializable, Searchable {

    private static final long serialVersionUID = Konstanter.SERNUM;

    private static int teller = 10000;
    private int personID;
    private String fornavn;
    private String etternavn;
    private String epost;
    private String telefon;

    public Person(String fornavn, String etternavn, String epost, String telefon) {
        personID = ++teller;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.epost = epost;
        this.telefon = telefon;
    }

    /**
     * Brukes for serialisering.
     *
     * @return int
     */
    public static int getTeller() {
        return teller;
    }

    /**
     * Brukes for sette telleren tilbake etter serialisering.
     *
     * @param teller int
     */
    public static void setTeller(int teller) {
        Person.teller = --teller;
    }

    public int getPersonID() {
        return personID;
    }

    public String getFornavn() {
        return fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public String getEpost() {
        return epost;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((epost == null) ? 0 : epost.hashCode());
        result = prime * result
                + ((etternavn == null) ? 0 : etternavn.hashCode());
        result = prime * result + ((fornavn == null) ? 0 : fornavn.hashCode());
        result = prime * result + ((telefon == null) ? 0 : telefon.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        
        if (obj == null)
            return false;
            
        if (getClass() != obj.getClass())
            return false;
            
        Person other = (Person) obj;
        if (epost == null) {
            if (other.epost != null)
                return false;
        } 
        
        else if (!epost.equals(other.epost))
            return false;
        
        if (etternavn == null) {
            if (other.etternavn != null)
                return false;
        } 
        
        else if (!etternavn.equals(other.etternavn))
            return false;
            
        if (fornavn == null) {
            if (other.fornavn != null)
                return false;
        } 
        
        else if (!fornavn.equals(other.fornavn))
            return false;
        
        if (telefon == null) {
            if (other.telefon != null)
                return false;
        } 
        
        else if (!telefon.equals(other.telefon))
            return false;
        
        return true;

    } // end of equals method

    @Override
    public String toString() {
        return "{" + "personID=" + personID + ", fornavn=" + fornavn + ", etternavn=" + etternavn + ", epost=" + epost + ", telefon=" + telefon + "}";
    }
    
    /**
     * Datafelt som blir returnert til fritekstsøk for megler.
     * @return String[] med datafelt
     */
    @Override
    public String[] toSearch(){
        String[] searchFields = {
            String.valueOf(personID), 
            fornavn, 
            etternavn, 
            epost, 
            telefon};
        
        return searchFields;
    }
} // end of class