package model;

import lib.Arbeidsforhold;
import lib.Sivilstatus;

public class Leietaker extends Person {

    private int fodselsAr, antPersoner;
    private Sivilstatus sivilistatus;
    private String yrke;
    private Arbeidsforhold arbeidsforhold;
    private String soknadsTekst;

    public Leietaker(String fornavn, String etternavn, String epost, String telefon) {
        super(fornavn, etternavn, epost, telefon);
    }

    public Leietaker(String ffornavn, String eetternavn, String eepost, String ttelefon, int fodselsAr, int antPersoner, Sivilstatus sivilistatus, String yrke, Arbeidsforhold arbeidsforhold, String soknadsTekst, String fornavn, String etternavn, String epost, String telefon) {
        super(ffornavn, eetternavn, eepost, ttelefon);
        this.fodselsAr = fodselsAr;
        this.antPersoner = antPersoner;
        this.sivilistatus = sivilistatus;
        this.yrke = yrke;
        this.arbeidsforhold = arbeidsforhold;
        this.soknadsTekst = soknadsTekst;
    }

    public int getFodselsAr() {
        return fodselsAr;
    }

    public void setFodselsAr(int fodselsAr) {
        this.fodselsAr = fodselsAr;
    }

    public int getAntPersoner() {
        return antPersoner;
    }

    public void setAntPersoner(int antPersoner) {
        this.antPersoner = antPersoner;
    }

    public Sivilstatus getSivilistatus() {
        return sivilistatus;
    }

    public void setSivilistatus(Sivilstatus sivilistatus) {
        this.sivilistatus = sivilistatus;
    }

    public String getYrke() {
        return yrke;
    }

    public void setYrke(String yrke) {
        this.yrke = yrke;
    }

    public Arbeidsforhold getArbeidsforhold() {
        return arbeidsforhold;
    }

    public void setArbeidsforhold(Arbeidsforhold arbeidsforhold) {
        this.arbeidsforhold = arbeidsforhold;
    }

    public String getSoknadsTekst() {
        return soknadsTekst;
    }

    public void setSoknadsTekst(String soknadsTekst) {
        this.soknadsTekst = soknadsTekst;
    }
    

    @Override
    public String toString() {
        return super.toString() + " (Leietaker)";
    }

}
