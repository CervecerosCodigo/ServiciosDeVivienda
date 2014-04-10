package view;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import model.Person;

public class VenstrePanel extends AbstractPanel {

    private JTable tabell;
    private Object[] personObjekt;
    private PersonTabellModell personModel;

    public VenstrePanel(String borderTitle, int dimHeight, int dimWidth) {
        super(borderTitle, dimHeight, dimWidth);
        setLayout(new GridLayout(1, 1));

        personObjekt = new Object[0];
        personModel = new PersonTabellModell();

        tabell = new JTable(personModel);
        tabell.setFillsViewportHeight(true);

        add(new JScrollPane(tabell));

        //Send inn en tom Arraylist til modellen
        personModel.fyllTabellMedInnhold(personObjekt);
    }

    public AbstractTableModel getModel() {
        return personModel;
    }

    public void fyllTabellMedInnhold(Object[] data) {
        personModel.fyllTabellMedInnhold(data);
    }

    public JTable getTable() {
        return tabell;
    }

}
