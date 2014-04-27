/**
 *  Klasse for brukerkontroll. Nye brukere (megler objekter) blir hardkodet i main
 */

package model;

public class Megler extends Person {

	private String brukernavn;
	private String passord;
	private String kontor;

	public Megler(String fornavn, String etternavn, String epost, String telefon, String brukernavn, String passord, String kontor) {
		
		super(fornavn, etternavn, epost, telefon);
		this.brukernavn = brukernavn;
		this.passord = passord;
		this.kontor = kontor;
	}
}