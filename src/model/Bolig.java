package model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import sun.util.BuddhistCalendar;

public abstract class Bolig {

    private static int teller;//Brukes til å sette unik id for bolikobjektene
    private int boligID;
    private int personID; //En referanse til eier av boligen IKKE en generell person
    private int meglerID;
    private String boligtype;
    private String adresse;
    private String postnummer;
    private String poststed;
    private int boAreal;
    private int byggeAr;
    private String beskrivelse;
    private boolean erUtleid;
    private Calendar tilgjengeligForUtleie;
    private String pathBildemappe; //Denne variabeln skal peke til en mappe med samme navn som biligID der vi lagrer bildene om boligen. Si at boligID er 1234 da vil den peke til "img/1234". Det er ikke sikket at vi trenger denne variabeln ettersom path i hvilken alt er lagret kan vi hente opp fra boligID

    /**
     *
     * @param personID
     * @param boligtype
     * @param adresse
     * @param postnummer
     * @param poststed
     * @param boAreal
     * @param byggeAr
     * @param beskrivelse
     * @param erUtleid
     */
    public Bolig(int personID, String boligtype, String adresse, String postnummer, String poststed, int boAreal, int byggeAr, String beskrivelse, boolean erUtleid) {
        //Setter teller
        boligID = ++teller;
        this.personID = personID;
        this.boligtype = boligtype;
        this.adresse = adresse;
        this.postnummer = postnummer;
        this.poststed = poststed;
        this.boAreal = boAreal;
        this.byggeAr = byggeAr;
        this.beskrivelse = beskrivelse;
        this.erUtleid = erUtleid;
    }

    /**
     * Setter dato for når objektet blir tilgjengeligt for utleie.
     *
     * @param tilgjengeligForUtleie Calendar
     */
    public void setTilgjengeligForUtleie(Calendar tilgjengeligForUtleie) {
        this.tilgjengeligForUtleie = tilgjengeligForUtleie;
    }
    
    /**
     * Setter dato for når objektet blir tilgjengelig for utleie. 
     * @param dag int
     * @param mnd int
     * @param ar int
     */
    public void setTilgjengeligForUtleie(int dag, int mnd, int ar) {
        tilgjengeligForUtleie = new GregorianCalendar(dag, mnd, ar);
    }

    /**
     * En generell to string på de fleste datafelt.
     * @return String
     */
    @Override
    public String toString() {
        return "Bolig{" + "boligID=" + boligID + ", personID=" + personID + ", boligtype=" + boligtype + ", adresse=" + adresse + ", postnummer=" + postnummer + ", poststed=" + poststed + ", boAreal=" + boAreal + ", byggeAr=" + byggeAr + ", beskrivelse=" + beskrivelse + ", erUtleid=" + erUtleid + ", tilgjengeligForUtleie=" + tilgjengeligForUtleie + '}';
    }
    
    
}