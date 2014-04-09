package model;

public class Utleier extends Person {

	private boolean erRepresentant;
	private String erRepresentantFor;
	private int assosiertMegler;
	
	public Utleier(String fornavn, String etternavn, String epost, String telefon, boolean erRepresentant, String erRepresentantFor) {
		
		super(fornavn, etternavn, epost, telefon);
		this.erRepresentant = erRepresentant;
		this.erRepresentantFor = this.erRepresentantFor;
	}

    @Override
    public String toString() {
        return super.toString() + " (Utleier)"; //To change body of generated methods, choose Tools | Templates.
    }
        
}