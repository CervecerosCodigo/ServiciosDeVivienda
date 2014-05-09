package model;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 * Den abstrakte klassen som tabellens datamodeller arver.
 *
 * @author espen
 * @param <E>
 */
public abstract class TabellModell<E> extends DefaultTableModel {

    ArrayList<E> mottattArray;
    String[] overskrift;

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

    @Override
    public void removeRow(int row) {
        mottattArray.remove(row);
    }

    public Object finnObjektIModell(int rad) {
        return mottattArray.get(rad);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
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
        return Object.class;
    }

    @Override
    public Object getValueAt(int rad, int kolonne) {

        return mottattArray.get(rad);
    }

}
