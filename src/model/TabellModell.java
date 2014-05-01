package model;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public abstract class TabellModell<E> extends DefaultTableModel {

    ArrayList<E> mottattArray;
    String[] overskrift;
    DefaultTableModel modell;

    public TabellModell() {

        mottattArray = new ArrayList<>();
    }

    public void fyllTabellMedInnhold(ArrayList<E> liste) {
        this.mottattArray = liste;
    }

    @Override
    public int getRowCount() {
        int str = 0;
        try {
            str = mottattArray.size();
        } catch (NullPointerException npe) {

        }
        return str;
    }

    /**
     * Returnerer antall kolonner i tabellen.
     *
     * @return
     */
    @Override
    public int getColumnCount() {
        return overskrift.length;
    }

    @Override
    public String getColumnName(int index) {
        return overskrift[index];
    }

    @Override
    public Class getColumnClass(int c) {
        if (mottattArray.size() > 0) {
            return getValueAt(0, c).getClass();
        }
        return null;
    }

    @Override
    public Object getValueAt(int rad, int kolonne) {

        return null;
    }

}
