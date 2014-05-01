package model;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public abstract class TabellModell extends AbstractTableModel {

    Object[] mottattArray;
    String[] overskrift;
    DefaultTableModel modell;

    public TabellModell() {
        
        
    }

    public void fyllTabellMedInnhold(Object[] liste) {
        this.mottattArray = liste;
    }

    @Override
    public int getRowCount() {
        return mottattArray.length;
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
        if (mottattArray.length > 0) {
            return getValueAt(0, c).getClass();
        }
        return null;
    }

    @Override
    public Object getValueAt(int rad, int kolonne) {

        return null;
    }

    
}
