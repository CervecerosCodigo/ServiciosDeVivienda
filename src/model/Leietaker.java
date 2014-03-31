package model;

public class Leietaker extends Person {

	private boolean fodselsAr;

	public Leietaker(int personID, String fornavn, String etternavn, String epost, String telefon) {
		
		super(personID, fornavn, etternavn, epost, telefon);
		this.fodselsAr = fodselsAr;
	}

	public boolean isFodselsAr() {
		return fodselsAr;
	}

    @Override
    public String toString() {
        return "Leietaker" +super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

        
}