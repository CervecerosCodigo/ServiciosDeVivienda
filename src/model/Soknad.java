package model;

public class Soknad {

    private int soknadID;
    private static int teller = 0;
    private boolean erBehandlet;
    private boolean erGodkjent;
    private Annonse annonse;
    private Leietaker leietaker;

    public Soknad( Annonse a, Leietaker l ){
        soknadID = ++teller;
        erGodkjent = false;
        erBehandlet = false;
        annonse = a;
        leietaker = l;
    }

    public int getSoknadID() {
        return soknadID;
    }

    public boolean ErBehandlet() {
        return erBehandlet;
    }

    public boolean ErGodkjent() {
        return erGodkjent;
    }

    public Annonse getAnnonseObjekt() {
        return annonse;
    }

    public Leietaker getLeietakerObjekt() {
        return leietaker;
    }

    public void setErBehandlet(boolean erBehandlet) {
        this.erBehandlet = erBehandlet;
    }

    public void setErGodkjent(boolean erGodkjent) {
        this.erGodkjent = erGodkjent;
    }

    //Foreløpig er ikke toString riktig. Må legge inn annonse og leietakerinformasjon riktig.
    @Override
    public String toString() {
        return "Søknad{SøknadID: " + soknadID + ", AnnonseID: " + annonse.getAnnonseID() + "}";
    }
//    public String toString() {
//        return "{ SøknadID: " + soknadID + ", Er behandlet? " + erBehandlet + ", Er godkjent? " + erGodkjent + 
//                "LeietakerID: " + leietaker.toString() + ", AnnonseID: " + annonse.toString() + "\n}";
//    }
    
    
    
}
