package model;

public class Utleier extends Person {

	private boolean erRepresentant;
	private String erRepresentantFor;
	private int assosiertMegler;
	
	public Utleier(int personID, String fornavn, String etternavn, String epost, String telefon, boolean erRepresentant, String erRepresentantFor) {
		
		super(personID, fornavn, etternavn, epost, telefon);
		this.erRepresentant = erRepresentant;
		this.erRepresentantFor = this.erRepresentantFor;
	}
}