package model;

public class Leietaker extends Person {

	private boolean fodselsAr;

	public Leietaker(String fornavn, String etternavn, String epost, String telefon) {
		
		super(fornavn, etternavn, epost, telefon);
		this.fodselsAr = fodselsAr;
	}

	public boolean isFodselsAr() {
		return fodselsAr;
	}

    @Override
    public String toString() {
        return super.toString() + " (Leietaker)";
    }

        
}