package model;

public class Leilighet extends Bolig {

  private int etasjeNr;
  private int balkongAreal; //Settes til 0 dersom ingen balkong
  private int bodAreal; //Settes til 0 dersom ingen balkong
  private boolean harHeis;
  private boolean harGarsje;
  private boolean harFellesvaskeri;

    public Leilighet(int etasjeNr, int balkongAreal, int bodAreal, boolean harHeis, boolean harGarsje, boolean harFellesvaskeri, int personID, String boligtype, String adresse, String postnummer, String poststed, int boAreal, int byggeAr, String beskrivelse, boolean erUtleid) {
        super(personID, boligtype, adresse, postnummer, poststed, boAreal, byggeAr, beskrivelse, erUtleid);
        this.etasjeNr = etasjeNr;
        this.balkongAreal = balkongAreal;
        this.bodAreal = bodAreal;
        this.harHeis = harHeis;
        this.harGarsje = harGarsje;
        this.harFellesvaskeri = harFellesvaskeri;
    }

    //TODO: alle getters og setter samt en eller flere toString()
}