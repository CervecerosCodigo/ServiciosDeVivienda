package model;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

public class TabellModellPerson extends TabellModell {

    public TabellModellPerson() {
        super.overskrift = new String[]{"ID", "Fornavn", "Etternavn", "Epost"};
    }

    @Override
    public Object getValueAt(int rad, int kolonne) {
        Person person = (Person) mottattArray.get(rad);        
        switch (kolonne) {
            case 0:
                return person.getPersonID();
            case 1:
                return person.getFornavn();
            case 2:
                return person.getEtternavn();
            case 3:
                return person.getEpost();
        }
        return null;
    }

}
