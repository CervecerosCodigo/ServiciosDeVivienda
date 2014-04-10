package view;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import model.Person;

public class VenstrePanel extends AbstractPanel {

    private JTable tabell;
    private List objekt;
    private String[] overskrift;
    private TableModelEvent event;
    private PersonTabellModell personModel;

    public VenstrePanel(String borderTitle, int dimHeight, int dimWidth) {
        super(borderTitle, dimHeight, dimWidth);
        setLayout(new GridLayout(1, 1));

        overskrift = new String[]{"kolonne1", "kolonne2",
            "kolonne3", "kolonne4"};
        objekt = new ArrayList();

        personModel = new PersonTabellModell(overskrift);
        tabell = new JTable(personModel);
        tabell.setFillsViewportHeight(true);

        add(new JScrollPane(tabell));

        //Midlertidige løsninger..
        personModel.setTabellData(objekt);
    }

    public AbstractTableModel getModel() {
        return personModel;
    }

    public void setPersonTabellData(List<Person> data) {
        personModel.setTabellData(data);
    }

    public JTable getTable() {
        return tabell;
    }

    class PersonTabellModell extends AbstractTableModel {

        List<Person> objekt;
        String[] overskrifter;

        public PersonTabellModell(String[] innkOverskrifter) {

            this.overskrifter = innkOverskrifter;

        }

        public void setTabellData(List<Person> objekt) {
            this.objekt = objekt;
        }

        @Override
        public int getRowCount() {
            return objekt.size();
        }

        @Override
        public int getColumnCount() {
            return overskrift.length;
        }

        @Override
        public Object getValueAt(int rad, int kolonne) {
            Person person = objekt.get(rad);
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

    public void setTabellLytter(TableModelEvent event) {
        this.event = event;
    }
}
