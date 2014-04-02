package model;

import java.util.Calendar;

public class Enebolig extends Bolig {

    private Boligtype boligtype;
    private int antallEtasjer;
    private boolean harKjeller;
    private int tomtAreal;

    /**
     * En tom konstruktør for Enebolig.
     */
//    public Enebolig() {
//    }

    
    //TODO: alle getter og setter samt toString()
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

    
    
    
//    @Override
//    public String toString() {
//        return super.toString() + "boligtype=" + boligtype + ", antallEtasjer=" + antallEtasjer + ", harKjeller=" + harKjeller + ", tomtAreal=" + tomtAreal + '}';
//    }
    
    
}
