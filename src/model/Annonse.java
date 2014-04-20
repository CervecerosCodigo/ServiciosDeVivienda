package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import lib.Konstanter;
import search.Searchable;

public class Annonse implements Serializable, Searchable{
    
    private static final long serialVersionUID = Konstanter.SERNUM;

    private int annonseID;
    private static int teller = 0;
    private int depositum;
    private int utleiepris;
    private boolean erSynlig;
    private Calendar utlopsDato;
    private Calendar tilgjengeligFraDato;
    private String eiersKrav;
    private Bolig bolig;
    

    public Annonse( int depositum, int utleiepris, Calendar tilgjengligFraDato, Calendar utlopsDato, Bolig bolig, String eiersKrav ){
        annonseID = ++teller;
        this.erSynlig = true;
        this.depositum = depositum;
        this.utleiepris = utleiepris;
        this.utlopsDato = utlopsDato;
        this.bolig = bolig;
        this.eiersKrav = eiersKrav;
        this.tilgjengeligFraDato = tilgjengligFraDato;
        
        
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
    
    public Calendar getTilgjengeligFraDato() {
        return  tilgjengeligFraDato;
    }
    
    public String getEiersKrav() {
        return eiersKrav;
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
    
    public void setTilgjengligFraDato(Calendar tilgjengeligFraDato) {
        this.tilgjengeligFraDato = tilgjengeligFraDato;
    }
    
    public void setEiersKrav(String eiersKrav) {
        this.eiersKrav = eiersKrav;
    }

    @Override  //En "mini-toString" for testing
//    public String toString(){
//        return "Annonse{" + "annonseID=" + annonseID + ", depositum=" + depositum + ", utleiepris=" + utleiepris + ", utlopsDato=" + df.format(utlopsDato.getTime()) + '}';
//    }
    public String toString() {
        return "Annonse{" + "annonseID=" + annonseID + ", depositum=" + depositum + ", utleiepris=" + utleiepris + ", erSynlig=" + erSynlig + ", utlopsDato=" + Konstanter.df.format(utlopsDato.getTime()) + ", boligID=" + bolig.getBoligID() + '}';
    }

    @Override
    public String[] toSearch() {
        //Denne metoden sørger for meglerens sine søkeparemetre. Metode som sørger for søkning til boligsøkende kommer til å være mer spesifikk.
        String[] searchFields = {String.valueOf(annonseID), String.valueOf(bolig.getBoligID())};
        return searchFields;
    }
    
}
