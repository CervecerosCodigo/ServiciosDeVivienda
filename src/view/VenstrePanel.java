package view;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import model.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * Denne klassen er et JPanel (arvet) som inneholder tabellen. Den definerer
 * Tabellen, samt oppretter TabellModell, som definerer selve funksjonaliteten
 * til tabellen.
 *
 * @author espen
 */
public class VenstrePanel extends AbstractPanel {

    private JTable tabell;
    private TabellModell tabellModell;
    private TableRowSorter<TabellModell> sorterer;

    public VenstrePanel(String borderTitle, int dimHeight, int dimWidth) {
        super(borderTitle, dimHeight, dimWidth);
        setLayout(new GridLayout(1, 1));

        tabell = new JTable();
        tabell.setFillsViewportHeight(true);
        add(new JScrollPane(tabell));

    }//End Constructor

    /**
     * Returnerer tabellen.
     *
     * @return
     */
    public JTable getTable() {
        return tabell;
    }

    public TableRowSorter<TabellModell> getSorterer() {
        return sorterer;
    }
    
    public void settTabellSortering() {
        tabell.setRowSorter(sorterer);
    }

    public void setSorterer(TableRowSorter<TabellModell> sorterer) {
        this.sorterer = sorterer;
    }
}
