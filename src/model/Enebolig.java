package model;

import lib.Boligtype;
import java.util.Calendar;

public class Enebolig extends Bolig {

    private Boligtype boligtype;
    private int antallEtasjer;
    private boolean harKjeller;
    private int tomtAreal;

    /**
     * Kontruktør for opprettelse av en enebolig.
     * @param boligtype
     * @param antallEtasjer
     * @param harKjeller
     * @param tomtAreal
     * @param personID EierID
     * @param adresse
     * @param postnummer
     * @param poststed
     * @param boAreal
     * @param byggeAr
     * @param beskrivelse
     * @param erUtleid
     * @param tilgjengeligForUtleie 
     */
    public Enebolig(Boligtype boligtype, int antallEtasjer, boolean harKjeller, int tomtAreal, int personID, String adresse, String postnummer, String poststed, int boAreal, int byggeAr, String beskrivelse, boolean erUtleid, Calendar tilgjengeligForUtleie) {
        super(personID, adresse, postnummer, poststed, boAreal, byggeAr, beskrivelse, erUtleid, tilgjengeligForUtleie);
        this.boligtype = boligtype;
        this.antallEtasjer = antallEtasjer;
        this.harKjeller = harKjeller;
        this.tomtAreal = tomtAreal;
    }

    public Boligtype getBoligtype() {
        return boligtype;
    }

    public void setBoligtype(Boligtype boligtype) {
        this.boligtype = boligtype;
    }

    public int getAntallEtasjer() {
        return antallEtasjer;
    }

    public void setAntallEtasjer(int antallEtasjer) {
        this.antallEtasjer = antallEtasjer;
    }

    public boolean isHarKjeller() {
        return harKjeller;
    }

    public void setHarKjeller(boolean harKjeller) {
        this.harKjeller = harKjeller;
    }

    public int getTomtAreal() {
        return tomtAreal;
    }

    public void setTomtAreal(int tomtAreal) {
        this.tomtAreal = tomtAreal;
    }
}
