package model;

/**
 * Kontrakt klassen er ganske spesiell ettersom denne skal bevare historikken
 * over alle annonser, leietakere, utleier og megler som var med da kontraktet
 * skrevs. I denne klassen skal vi derfor ta kopi av alle disse objekter for
 * fremtidig lagring. Dette blir egentlig dobbellagring men det er nødvendig
 * ettersom vi må se på dette som en "bevaring for fremtiden".
 */
public class Kontrakt {

    private Annonse annonse;
    private Megler megler;
    private Leietaker leietaker;
    private int utleiepris;
    private int depositum;
    private int leietidIMnd;
    private int kontraktID;
    private static int teller = 0;
    
    public Kontrakt( Annonse annonse, Megler megler, Leietaker leietaker, int leietidIMnd ){
        this.annonse = annonse;
        this.megler = megler;
        this.leietaker = leietaker;
        
        kontraktID = ++teller;
        this.leietidIMnd = leietidIMnd;
        
    }

    public int getAnnonseID() {
        return annonse.getAnnonseID();
    }
    
    //Må editeres når Bolig er klart.
    public Bolig getBoligID() {
        return annonse.getBolig();
    }
    //Må editeres når Leietaker er klart.
    public Leietaker getLeietakerID(){
        return leietaker;
    }

    //Må editeres når Megler er klar
    public Megler getMeglerID() {
        return megler;
    }
    public int getKontraktID() {
        return kontraktID;
    }

    public int getUtleiePris(){
        return annonse.getUtleiepris();
    }
    public int getDepositum(){
        return annonse.getDepositum();
    }
    public int getLeietidIMnd(){
        return leietidIMnd;
    }    

    @Override
    public String toString() {
        return "Kontrakt{" + "annonse=" + annonse + ", megler=" + megler + ", leietaker=" 
                + leietaker + ", utleiepris=" + utleiepris + ", depositum=" + depositum 
                + ", leietidIMnd=" + leietidIMnd + ", kontraktID=" + kontraktID + '}';
    }

}
