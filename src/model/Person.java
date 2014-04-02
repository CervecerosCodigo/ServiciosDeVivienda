package model;

public abstract class Person {

	private int personID;
        private static int teller = 0;
	private String fornavn;
	private String etternavn;
	private String epost;
	private String telefon;

	public Person(String fornavn, String etternavn, String epost, String telefon) {
                this.personID = ++teller;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.epost = epost;
		this.telefon = telefon;
	}



	public int getPersonID() {
		return personID;
	}

	public String getFornavn() {
		return fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public String getEpost() {
		return epost;
	}

	public String getTelefon() {
		return telefon;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((epost == null) ? 0 : epost.hashCode());
		result = prime * result
				+ ((etternavn == null) ? 0 : etternavn.hashCode());
		result = prime * result + ((fornavn == null) ? 0 : fornavn.hashCode());
		result = prime * result + ((telefon == null) ? 0 : telefon.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (epost == null) {
			if (other.epost != null)
				return false;
		} else if (!epost.equals(other.epost))
			return false;
		if (etternavn == null) {
			if (other.etternavn != null)
				return false;
		} else if (!etternavn.equals(other.etternavn))
			return false;
		if (fornavn == null) {
			if (other.fornavn != null)
				return false;
		} else if (!fornavn.equals(other.fornavn))
			return false;
		if (telefon == null) {
			if (other.telefon != null)
				return false;
		} else if (!telefon.equals(other.telefon))
			return false;
		return true;
		
	} // end of equals method

            @Override
    public String toString() {
        return "{" + "personID=" + personID + ", fornavn=" + fornavn + ", etternavn=" + etternavn + ", epost=" + epost + ", telefon=" + telefon + "}";
    }
} // end of class
