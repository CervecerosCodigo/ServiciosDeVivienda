package model;

public class Enebolig extends Bolig {

    private int antallEtasjer;
    private boolean harKjeller;
    private int tomtAreal;

    public Enebolig(int antallEtasjer, boolean harKjeller, int tomtAreal, int personID, String boligtype, String adresse, String postnummer, String poststed, int boAreal, int byggeAr, String beskrivelse, boolean erUtleid) {
        super(personID, boligtype, adresse, postnummer, poststed, boAreal, byggeAr, beskrivelse, erUtleid);
        this.antallEtasjer = antallEtasjer;
        this.harKjeller = harKjeller;
        this.tomtAreal = tomtAreal;
    }

    //TODO: alle getter og setter samt toString()
}
