package model;

import java.util.Calendar;

public class Enebolig extends Bolig {

    private Boligtype boligtype;
    private int antallEtasjer;
    private boolean harKjeller;
    private int tomtAreal;

    //TODO: alle getter og setter samt toString()
    public Enebolig(Boligtype boligtype, int antallEtasjer, boolean harKjeller, int tomtAreal, int personID, String adresse, String postnummer, String poststed, int boAreal, int byggeAr, String beskrivelse, boolean erUtleid, Calendar tilgjengeligForUtleie) {
        super(personID, adresse, postnummer, poststed, boAreal, byggeAr, beskrivelse, erUtleid, tilgjengeligForUtleie);
        this.boligtype = boligtype;
        this.antallEtasjer = antallEtasjer;
        this.harKjeller = harKjeller;
        this.tomtAreal = tomtAreal;
    }

    @Override
    public String toString() {
        return "Enebolig{" + "boligtype=" + boligtype + ", antallEtasjer=" + antallEtasjer + ", harKjeller=" + harKjeller + ", tomtAreal=" + tomtAreal + '}';
    }
    
    
}
