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
    private Person megler;
    private Person leietaker;
    private int utleiepris;
    private int depositum;
    private int leietidIMnd;
    private int kontraktID;
    private static int teller = 0;
    
    public Kontrakt( Annonse annonse, Person megler, Person leietaker, int leietidIMnd ){
        this.annonse = annonse;
        this.megler = megler;
        this.leietaker = leietaker;
        
        kontraktID = ++teller;
        this.leietidIMnd = leietidIMnd;
        
    }
    
    /**
     * Brukes for serialisering.
     * @return int
     */
    public static int getTeller() {
        return teller;
    }

    /**
     * Brukes for å gjenoprette telleren etter serialisering.
     * @param teller int
     */
    public static void setTeller(int teller) {
        Kontrakt.teller = teller;
    }
    
    public int getAnnonseID() {
        return annonse.getAnnonseID();
    }
    
    //Må editeres når Bolig er klart.
    public Bolig getBoligID() {
        return annonse.getBolig();
    }

    public int getLeietakerID(){
        return leietaker.getPersonID();
    }

    public int getMeglerID() {
        return megler.getPersonID();
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
        return "Kontrakt{" + "annonse=" + annonse.getAnnonseID() + ", megler=" + megler.getPersonID() + ", leietaker=" 
                + leietaker.getPersonID() + ", utleiepris=" + utleiepris + ", depositum=" + depositum 
                + ", leietidIMnd=" + leietidIMnd + ", kontraktID=" + kontraktID + '}';
    }

}
