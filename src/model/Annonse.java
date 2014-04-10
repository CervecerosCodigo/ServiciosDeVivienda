package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Annonse {

    private int annonseID;
    private static int teller = 0;
    private int depositum;
    private int utleiepris;
    private boolean erSynlig;
    private Calendar utlopsDato;
    private SimpleDateFormat df;
    private Bolig bolig;
    

    public Annonse( int depositum, int utleiepris, Calendar utlopsDato, Bolig bolig ){
        annonseID = ++teller;
        this.erSynlig = true;
        this.depositum = depositum;
        this.utleiepris = utleiepris;
        this.utlopsDato = utlopsDato;
        this.bolig = bolig;
        df = new SimpleDateFormat("dd-MM-yyyy");
        
    }

    /**
     * Brukes for serialisering.
     * @return int
     */
    public static int getTeller() {
        return teller;
    }

    /**
     * Brukes for å gjenoprette etter serialisering.
     * @param teller int
     */
    public static void setTeller(int teller) {
        Annonse.teller = teller;
    }
    
    public int getAnnonseID() {
        return annonseID;
    }

    public int getDepositum() {
        return depositum;
    }

    public int getUtleiepris() {
        return utleiepris;
    }

    public boolean isErSynlig() {
        return erSynlig;
    }


    public Calendar getUtlopsDato() {
        return  utlopsDato;
    }

    public Bolig getBolig() {
        return bolig;
    }
    
    public int getBoligID() {
        return bolig.getBoligID();
    }

    public void setDepositum(int depositum) {
        this.depositum = depositum;
    }

    public void setUtleiepris(int utleiepris) {
        this.utleiepris = utleiepris;
    }

    public void setErSynlig(boolean erSynlig) {
        this.erSynlig = erSynlig;
    }

    public void setUtlopsDato(Calendar utlopsDato) {
        this.utlopsDato = utlopsDato;
    }

    @Override  //En "mini-toString" for testing
//    public String toString(){
//        return "Annonse{" + "annonseID=" + annonseID + ", depositum=" + depositum + ", utleiepris=" + utleiepris + ", utlopsDato=" + df.format(utlopsDato.getTime()) + '}';
//    }
    public String toString() {
        return "Annonse{" + "annonseID=" + annonseID + ", depositum=" + depositum + ", utleiepris=" + utleiepris + ", erSynlig=" + erSynlig + ", utlopsDato=" + df.format(utlopsDato.getTime()) + ", boligID=" + bolig.getBoligID() + '}';
    }
    
    
    
    
}
