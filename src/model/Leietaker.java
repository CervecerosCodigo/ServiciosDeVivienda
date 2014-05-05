package model;

public class Leietaker extends Person {

    private boolean fodselsAr;
    private int antPersoner, sivilstatus;
    private String yrke, arbeidsforhold, soknadsTekst;

    public Leietaker(String fornavn, String etternavn, String epost, String telefon) {

        super(fornavn, etternavn, epost, telefon);
        this.fodselsAr = fodselsAr;
    }

    public Leietaker(String fornavn, String etternavn, String epost, String telefon, int antPersoner, int sivilstatus, String yrke, String arbeidsforhold, String soknadsTekst) {

        super(fornavn, etternavn, epost, telefon);
        this.fodselsAr = fodselsAr;
        this.antPersoner = antPersoner;
        this.sivilstatus = sivilstatus;
        this.yrke = yrke;
        this.arbeidsforhold = arbeidsforhold;
        this.soknadsTekst = soknadsTekst;
    }

    public boolean isFodselsAr() {
        return fodselsAr;
    }

    public int getAntPersoner() {
        return antPersoner;
    }

    public int getSivilstatus() {
        return sivilstatus;
    }

    public String getYrke() {
        return yrke;
    }

    public String getArbeidsforhold() {
        return arbeidsforhold;
    }

    public String getSoknadsTekst() {
        return soknadsTekst;
    }

    
    @Override
    public String toString() {
        return super.toString() + " (Leietaker)";
    }

}
