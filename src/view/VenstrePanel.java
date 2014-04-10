package view;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import model.Person;

public class VenstrePanel extends AbstractPanel {

    private JTable tabell;
    private Object[] liste;
    private PersonTabellModell tabellModell;
    private AbstractTableModel valgtModel;

    public VenstrePanel(String borderTitle, int dimHeight, int dimWidth) {
        super(borderTitle, dimHeight, dimWidth);
        setLayout(new GridLayout(1, 1));

        liste = new Object[0];
        tabellModell = new PersonTabellModell();
        valgtModel = tabellModell;

        tabell = new JTable(tabellModell);
        tabell.setFillsViewportHeight(true);

        add(new JScrollPane(tabell));

        //Send inn en tom Arraylist til modellen
        tabellModell.fyllTabellMedInnhold(liste);


    }

    public AbstractTableModel getModel() {
        return valgtModel;
    }

    public void fyllTabellMedInnhold(Object[] data) {
        tabellModell.fyllTabellMedInnhold(data);
    }

    public JTable getTable() {
        return tabell;
    }

}
