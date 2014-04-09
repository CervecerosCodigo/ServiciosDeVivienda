/**
 *  Klasse for brukerkontroll. Nye brukere (megler objekter) blir hardkodet i main
 */

package model;

public class Megler extends Person {

	private int meglerID;
	private String kontor;

	public Megler(String fornavn, String etternavn, String epost, String telefon, int meglerID, String kontor) {
		
		super(fornavn, etternavn, epost, telefon);
		this.meglerID = meglerID;
		this.kontor = kontor;
	}

    @Override
    public String toString() {
        return super.toString() + " (Megler)";
//        return "Megler{personID " + super.getPersonID() + ", meglerID=" + meglerID + ", kontor=" + kontor + "}\n";
    }

        
}