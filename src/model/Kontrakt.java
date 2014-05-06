package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

import search.Searchable;

/**
 * Kontrakt klassen er ganske spesiell ettersom denne skal bevare historikken
 * over alle annonser, leietakere, utleier og megler som var med da kontraktet
 * skrevs. I denne klassen skal vi derfor ta kopi av alle disse objekter for
 * fremtidig lagring. Dette blir egentlig dobbellagring men det er nødvendig
 * ettersom vi må se på dette som en "bevaring for fremtiden".
 */
public class Kontrakt implements Serializable, Searchable {

    private Annonse annonse;
    private Person megler;
    private Person leietaker;
    private int utleiepris;
    private int depositum;
    private int leietidIMnd;
    private int kontraktID;
    private Calendar datoOpprettet;
    private static int teller = 40000;

    public Kontrakt(Annonse annonse, Person megler, Person leietaker, int leietidIMnd, Calendar datoOpprettet) {
        this.annonse = annonse;
        this.megler = megler;
        this.leietaker = leietaker;
        this.datoOpprettet = datoOpprettet;

        kontraktID = ++teller;
        this.leietidIMnd = leietidIMnd;
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
     * Brukes for å gjenoprette telleren etter serialisering.
     *
     * @param teller int
     */
    public static void setTeller(int teller) {
        Kontrakt.teller = teller;
    }

    public int getAnnonseID() {
        return annonse.getAnnonseID();
    }

    public Calendar getDatoOpprettet() {
        return datoOpprettet;
    }

    public Annonse getAnnonse() {
        return annonse;
    }

    public Person getMegler() {
        return megler;
    }

    public Person getLeietaker() {
        return leietaker;
    }

    public int getUtleiepris() {
        return utleiepris;
    }

    //Må editeres når Bolig er klart.
    public Bolig getBoligID() {
        return annonse.getBolig();
    }

    public int getLeietakerID() {
        return leietaker.getPersonID();
    }

    public int getMeglerID() {
        return megler.getPersonID();
    }

    public int getKontraktID() {
        return kontraktID;
    }

    public int getUtleiePris() {
        return annonse.getUtleiepris();
    }

    public int getDepositum() {
        return annonse.getDepositum();
    }

    public int getLeietidIMnd() {
        return leietidIMnd;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.annonse);
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
        final Kontrakt other = (Kontrakt) obj;
        if (!Objects.equals(this.annonse, other.annonse)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Kontrakt{" + "annonse=" + annonse.getAnnonseID() + ", megler=" + megler.getPersonID() + ", leietaker="
                + leietaker.getPersonID() + ", utleiepris=" + utleiepris + ", depositum=" + depositum
                + ", leietidIMnd=" + leietidIMnd + ", kontraktID=" + kontraktID + '}';
    }

    /**
     * Datafelt som blir returnert til meglersøk (fritekstsøk).
     *
     * @return String[] med datafelt.
     */
    @Override
    public String[] toSearch() {
        String[] searchFields = {
            String.valueOf(annonse.getBoligID()),
            annonse.getBolig().getAdresse(),
            annonse.getBolig().getPoststed(),
            annonse.getBolig().getPostnummer(),
            megler.getEpost(),
            megler.getEtternavn(),
            megler.getFornavn(),
            megler.getTelefon(),
            leietaker.getEpost(),
            leietaker.getEtternavn(),
            leietaker.getFornavn(),
            leietaker.getTelefon(),
            String.valueOf(kontraktID)
        };
        return searchFields;
    }
}
