package view;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import javax.swing.table.AbstractTableModel;
import model.Person;

public class PersonTabellModell extends AbstractTableModel {

    Object[] liste;
    String[] overskrift = new String[]{"ID", "Fornavn", "Etternavn", "Epost"};

    public void fyllTabellMedInnhold(Object[] liste) {
        this.liste = liste;
    }

    @Override
    public int getRowCount() {
        return liste.length;
    }

    @Override
    public int getColumnCount() {
        return overskrift.length;
    }

    @Override
    public Object getValueAt(int rad, int kolonne) {
        Person person = null;
        person = (Person) liste[rad];
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

    @Override
    public String getColumnName(int index) {
        return overskrift[index];
    }
}
