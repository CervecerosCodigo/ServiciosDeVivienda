package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import lib.Konstanter;
import search.*;

public abstract class Bolig implements Serializable,  Searchable{

    private static final long serialVersionUID = Konstanter.SERNUM;

    private static int teller = 20000;//Brukes til å sette unik id for bolikobjektene
    private int boligID;
    private int personID; //En referanse til eier av boligen IKKE en generell person
    private int meglerID;
    private String adresse;
    private String postnummer;
    private String poststed;
    private int boAreal;
    private int byggeAr;
    private String beskrivelse;
    private boolean erUtleid;
    private Calendar tilgjengeligForUtleie;
    private String pathBildemappe; //Denne variabeln skal peke til en mappe med samme navn som biligID der vi lagrer bildene om boligen. Si at boligID er 1234 da vil den peke til "img/1234". Det er ikke sikket at vi trenger denne variabeln ettersom path i hvilken alt er lagret kan vi hente opp fra boligID

    /**
     * Konstruktør for oprettelse av en ny bolig.
     *
     * @param personID - En referanse til eier av boligen. 
     * @param adresse - Adresse for boligen 
     * @param postnummer - Postnummer fir siffrer
     * @param poststed
     * @param boAreal - kvm
     * @param byggeAr - kvm
     * @param beskrivelse - String med beskrivelse av objektet.
     * @param erUtleid - boolean
     * @param tilgjengeligForUtleie - Når er boligen tilgjengelig for utleie. Et kalenderobjekt med dato.
     */
    public Bolig(int personID, String adresse, String postnummer, String poststed, int boAreal, int byggeAr, String beskrivelse, boolean erUtleid, Calendar tilgjengeligForUtleie) {
        boligID = ++teller;
        this.personID = personID;
        this.adresse = adresse;
        this.postnummer = postnummer;
        this.poststed = poststed;
        this.boAreal = boAreal;
        this.byggeAr = byggeAr;
        this.beskrivelse = beskrivelse;
        this.erUtleid = erUtleid;
        this.tilgjengeligForUtleie = tilgjengeligForUtleie;
    }

    /**
     * Brukes for å serialisere statics.
     *
     * @return int
     */
    public static int getTeller() {
        return teller;
    }

    /**
     * Brukes for å serialisere static tilbake til sammen status.
     *
     * @param teller int
     */
    public static void setTeller(int teller) {
        Bolig.teller = teller;
    }

    public int getBoligID() {
        return boligID;
    }

    public void setBoligID(int boligID) {
        this.boligID = boligID;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public int getMeglerID() {
        return meglerID;
    }

    public void setMeglerID(int meglerID) {
        this.meglerID = meglerID;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(String postnummer) {
        this.postnummer = postnummer;
    }

    public String getPoststed() {
        return poststed;
    }

    public void setPoststed(String poststed) {
        this.poststed = poststed;
    }

    public int getBoAreal() {
        return boAreal;
    }

    public void setBoAreal(int boAreal) {
        this.boAreal = boAreal;
    }

    public int getByggeAr() {
        return byggeAr;
    }

    public void setByggeAr(int byggeAr) {
        this.byggeAr = byggeAr;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public boolean isErUtleid() {
        return erUtleid;
    }

    public void setErUtleid(boolean erUtleid) {
        this.erUtleid = erUtleid;
    }

    public String getPathBildemappe() {
        return pathBildemappe;
    }

    public void setPathBildemappe(String pathBildemappe) {
        this.pathBildemappe = pathBildemappe;
    }

    /**
     * Setter dato for når objektet blir tilgjengeligt for utleie.
     *
     * @param tilgjengeligForUtleie Calendar
     */
    public void setTilgjengeligForUtleie(Calendar tilgjengeligForUtleie) {
        this.tilgjengeligForUtleie = tilgjengeligForUtleie;
    }

    /**
     * Setter dato for når objektet blir tilgjengelig for utleie.
     *
     * @param dag int
     * @param mnd int
     * @param ar int
     */
    public void setTilgjengeligForUtleie(int ar, int mnd, int dag) {
        tilgjengeligForUtleie = new GregorianCalendar(ar, mnd, dag);
    }

    /**
     * En generell to string på de fleste datafelt.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Bolig{" + "boligID=" + boligID + " Adresse: " + adresse + ", personID=" + personID + '}';
    }
//    @Override
//    public String toString() {
//        return "Bolig{" + "boligID=" + boligID + " Adresse: " + adresse + ", personID=" + personID + ", meglerID=" + meglerID + ", erUtleid=" + erUtleid + '}';
//    }

    /**
     * Datafelt som blir returnert til fritekssøk for megler.
     * @return String[] med datafelt konvertert til string
     */
    @Override
    public String[] toSearch() {
        String[] searchFields = {
            String.valueOf(boligID), 
            String.valueOf(personID), 
            String.valueOf(meglerID), 
            adresse, 
            postnummer, 
            poststed};
        return searchFields;
    }
}
