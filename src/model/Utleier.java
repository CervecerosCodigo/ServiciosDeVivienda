package model;

public class Utleier extends Person {

	private boolean erRepresentant;
	private String erRepresentantFor;
	private int assosiertMegler;
	
	public Utleier(String fornavn, String etternavn, String epost, String telefon, boolean erRepresentant, String erRepresentantFor) {
		
		super(fornavn, etternavn, epost, telefon);
		this.erRepresentant = erRepresentant;
		this.erRepresentantFor = erRepresentantFor;
	}

    public boolean isErRepresentant() {
        return erRepresentant;
    }

    public void setErRepresentant(boolean erRepresentant) {
        this.erRepresentant = erRepresentant;
    }

    public String getErRepresentantFor() {
        return erRepresentantFor;
    }

    public void setErRepresentantFor(String erRepresentantFor) {
        this.erRepresentantFor = erRepresentantFor;
    }
        
    @Override
    public String toString() {
        return super.toString() + " (Utleier)";
    }
 
    
}