package model;

public class Soknad {

    private int soknadID;
    private static int teller = 0;
    private boolean erBehandlet;
    private boolean erGodkjent;
    private Annonse annonse;
    private Person leietaker;

    public Soknad(Annonse a, Person l) {
        soknadID = ++teller;
        erGodkjent = false;
        erBehandlet = false;
        annonse = a;
        leietaker = l;
    }

    public int getSoknadID() {
        return soknadID;
    }

    public int getLeietakerID() {
        return leietaker.getPersonID();
    }

    public int getBoligIDFraAnnonse() {
        return annonse.getBoligID();
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
        return (Leietaker) leietaker;
    }

    public void setErBehandlet(boolean erBehandlet) {
        this.erBehandlet = erBehandlet;
    }

    public void setErGodkjent(boolean erGodkjent) {
        this.erGodkjent = erGodkjent;
    }

    @Override
    public String toString() {
        return "Søknad{SøknadID: " + soknadID + ", Er behandlet? " + erBehandlet + ", Er godkjent? " + erGodkjent
                + ", LeietakerID: " + leietaker.getPersonID() + ", AnnonseID: " + annonse.getAnnonseID() + "}";
    }

}
