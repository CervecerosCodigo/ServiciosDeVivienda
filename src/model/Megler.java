/**
 *  Klasse for brukerkontroll. Nye brukere (megler objekter) blir hardkodet i main
 */

package model;

public class Megler extends Person {

	private int meglerID;
	private String kontor;

	public Megler(int personID, String fornavn, String etternavn, String epost, String telefon, int meglerID, String kontor) {
		
		super(personID, fornavn, etternavn, epost, telefon);
		this.meglerID = meglerID;
		this.kontor = kontor;
	}
}