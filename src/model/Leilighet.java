package model;

import java.util.Calendar;

public class Leilighet extends Bolig {

    private Boligtype boligtype;
    private int etasjeNr;
    private int balkongAreal; //Settes til 0 dersom ingen balkong
    private int bodAreal; //Settes til 0 dersom ingen balkong
    private boolean harHeis;
    private boolean harGarsje;
    private boolean harFellesvaskeri;

    /**
     * En tom konstruktør for Leilighet
     */
//    public Leilighet(){
//        //Utgår fra et teller for boligID blir oppdatert etter arv prinsippet
//    }
    
    public Leilighet(int etasjeNr, int balkongAreal, int bodAreal, boolean harHeis, boolean harGarsje, boolean harFellesvaskeri, int personID, String adresse, String postnummer, String poststed, int boAreal, int byggeAr, String beskrivelse, boolean erUtleid, Calendar tilgjengeligForUtleie) {
        super(personID, adresse, postnummer, poststed, boAreal, byggeAr, beskrivelse, erUtleid, tilgjengeligForUtleie);
        boligtype = Boligtype.LEILIGHET;
        this.etasjeNr = etasjeNr;
        this.balkongAreal = balkongAreal;
        this.bodAreal = bodAreal;
        this.harHeis = harHeis;
        this.harGarsje = harGarsje;
        this.harFellesvaskeri = harFellesvaskeri;
    }

    public Boligtype getBoligtype() {
        return boligtype;
    }

    public void setBoligtype(Boligtype boligtype) {
        this.boligtype = boligtype;
    }

    public int getEtasjeNr() {
        return etasjeNr;
    }

    public void setEtasjeNr(int etasjeNr) {
        this.etasjeNr = etasjeNr;
    }

    public int getBalkongAreal() {
        return balkongAreal;
    }

    public void setBalkongAreal(int balkongAreal) {
        this.balkongAreal = balkongAreal;
    }

    public int getBodAreal() {
        return bodAreal;
    }

    public void setBodAreal(int bodAreal) {
        this.bodAreal = bodAreal;
    }

    public boolean isHarHeis() {
        return harHeis;
    }

    public void setHarHeis(boolean harHeis) {
        this.harHeis = harHeis;
    }

    public boolean isHarGarsje() {
        return harGarsje;
    }

    public void setHarGarsje(boolean harGarsje) {
        this.harGarsje = harGarsje;
    }

    public boolean isHarFellesvaskeri() {
        return harFellesvaskeri;
    }

    public void setHarFellesvaskeri(boolean harFellesvaskeri) {
        this.harFellesvaskeri = harFellesvaskeri;
    }
    
    

//    @Override
//    public String toString() {
//        return super.toString() + "boligtype=" + boligtype + ", etasjeNr=" + etasjeNr + ", balkongAreal=" + balkongAreal + ", bodAreal=" + bodAreal + ", harHeis=" + harHeis + ", harGarsje=" + harGarsje + ", harFellesvaskeri=" + harFellesvaskeri + '}';
//    }
    
    
}
