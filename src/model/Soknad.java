package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import lib.Konstanter;
import search.Searchable;

public class Soknad implements Serializable, Searchable {

    private static final long serialVersionUID = Konstanter.SERNUM;

    private int soknadID;
    private static int teller = 50000;
    private boolean erBehandlet;
    private boolean erGodkjent;
    private Calendar godkjentDato;


    private Annonse annonse;
    private Person leietaker;

    public Soknad(Annonse a, Person l) {
        soknadID = ++teller;
        erGodkjent = false;
        erBehandlet = false;
        annonse = a;
        leietaker = l;
    }

    /**
     * Brukes for serialsiering.
     *
     * @return int
     */
    public static int getTeller() {
        return teller;
    }

    /**
     * Brukes for å gjeoprette telleren etter serialisering.
     *
     * @param teller int
     */
    public static void setTeller(int teller) {
        Soknad.teller = teller;
    }

    public Calendar getGodkjentDato() {
        return godkjentDato;
    }

    public void setGodkjentDato(Calendar godkjentDato) {
        this.godkjentDato = godkjentDato;
    }
    
    public int getSoknadID() {
        return soknadID;
    }

    public int getLeietakerID() {
        return leietaker.getPersonID();
    }

    public int getBoligIDFraAnnonse() {
        return annonse.getBoligID();
    }

    public boolean ErBehandlet() {
        return erBehandlet;
    }

    public boolean ErGodkjent() {
        return erGodkjent;
    }

    public Annonse getAnnonseObjekt() {
        return annonse;
    }

    public Leietaker getLeietakerObjekt() {
        return (Leietaker) leietaker;
    }

    public void setErBehandlet(boolean erBehandlet) {
        this.erBehandlet = erBehandlet;
    }

    public void setErGodkjent(boolean erGodkjent) {
        this.erGodkjent = erGodkjent;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.annonse);
        hash = 11 * hash + Objects.hashCode(this.leietaker);
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
        final Soknad other = (Soknad) obj;
        if (!Objects.equals(this.annonse, other.annonse)) {
            return false;
        }
        if (!Objects.equals(this.leietaker, other.leietaker)) {
            return false;
        }
        return true;
    }

    
    
    
    @Override
    public String toString() {
        return "Søknad{SøknadID: " + soknadID + ", Er behandlet? " + erBehandlet + ", Er godkjent? " + erGodkjent
                + ", LeietakerID: " + leietaker.getPersonID() + ", AnnonseID: " + annonse.getAnnonseID() + "}";
    }

    /**
     * Returnerer datafelt til fritekstsøk for megler.
     * @return String[] med datafelt.
     */
    @Override
    public String[] toSearch() {
        String[] searchFields = {
            String.valueOf(soknadID),
            String.valueOf(annonse.getAnnonseID()),
            leietaker.getEpost()
        };
        return searchFields;
    }

}
