package view;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import javax.swing.table.AbstractTableModel;
import lib.Konstanter;
import model.*;

public class TabellModell extends AbstractTableModel {

    private Object[] mottattTabell = new Object[0];
    private String[] overskrift = new String[0];
    private int objektType;

    /**
     * Tar i mot og fyller tabellen med data og kolonnenavn
     *
     * @param liste
     * @param kolonneNavn
     */
    public void fyllTabellMedInnhold(Object[] liste, String[] kolonneNavn, int objektType) {
        this.mottattTabell = liste;
        this.overskrift = kolonneNavn;
        this.objektType = objektType;
    }

    @Override
    public int getRowCount() {
        return mottattTabell.length;
    }

    @Override
    public int getColumnCount() {
        return overskrift.length;
    }

    public void settKolonnenavn(String[] kolonnenavn) {
        overskrift = kolonnenavn;
    }

    @Override
    public Object getValueAt(int rad, int kolonne) {

        if (objektType == Konstanter.BOLIGOBJ) {
            Bolig bolig = null;
            bolig = (Bolig) mottattTabell[rad];
            switch (kolonne) {
                case 0:
                    return bolig.getBoligID();
                case 1:
                    return bolig.getPersonID();
                case 2:
                    return bolig.getAdresse();
                case 3:
                    return bolig.isErUtleid();
            }
        } else if (objektType == Konstanter.PERSONOBJ) {

            Person person = null;
            person = (Person) mottattTabell[rad];
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
        } else if (objektType == Konstanter.ANNONSEOBJ) {
            Annonse annonse = null;
            annonse = (Annonse) mottattTabell[rad];
            switch (kolonne) {
                case 0:
                    return annonse.getAnnonseID();
                case 1:
                    return annonse.getUtleiepris();
                case 2:
                    return annonse.getUtlopsDato();
                case 3:
                    return annonse.isErSynlig();
            }
        } else if (objektType == Konstanter.KONTRAKTOBJ) {
            Kontrakt kontrakt = null;
            kontrakt = (Kontrakt) mottattTabell[rad];
            switch (kolonne) {
                case 0:
                    return kontrakt.getAnnonseID();
                case 1:
                    return kontrakt.getBoligID().getBoligID();
                case 2:
                    return kontrakt.getLeietakerID();
                case 3:
                    return kontrakt.getLeietidIMnd();
            }
        } else if (objektType == Konstanter.SOKNADOBJ) {
            Soknad soknad = null;
            soknad = (Soknad) mottattTabell[rad];
            switch (kolonne) {
                case 0:
                    return soknad.getAnnonseObjekt().getAnnonseID();
                case 1:
                    return soknad.getAnnonseObjekt().getBolig().getAdresse();
                case 2:
                    return soknad.getLeietakerObjekt().getFornavn();
                case 3:
                    return soknad.getLeietakerObjekt().getEtternavn();
            }
        }
        return null;
    }

    @Override
    public String getColumnName(int index) {
        return overskrift[index];
    }
}
