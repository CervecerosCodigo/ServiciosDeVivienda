package model;

import java.io.Serializable;
import lib.Konstanter;
import search.Searchable;

public class Soknad implements Serializable, Searchable{

    private static final long serialVersionUID = Konstanter.SERNUM;
    
    private int soknadID;
    private static int teller = 0;
    private boolean erBehandlet;
    private boolean erGodkjent;
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
     * @return int
     */
    public static int getTeller() {
        return teller;
    }

    /**
     * Brukes for å gjeoprette telleren etter serialisering.
     * @param teller int
     */
    public static void setTeller(int teller) {
        Soknad.teller = teller;
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
    public String toString() {
        return "Søknad{SøknadID: " + soknadID + ", Er behandlet? " + erBehandlet + ", Er godkjent? " + erGodkjent
                + ", LeietakerID: " + leietaker.getPersonID() + ", AnnonseID: " + annonse.getAnnonseID() + "}";
    }

    @Override
    public String[] toSearch() {
        //TODO: Foreløpig implementerer jeg kun disse felt ettersom vi ikke har spsifisert for hvordan vi ønsker å finne søknader dvs hvilke søknadskriterier skal angis. 
        String[] searchFields = {String.valueOf(soknadID), String.valueOf(annonse.getAnnonseID()), leietaker.getEpost()};
        return searchFields;
    }

}
