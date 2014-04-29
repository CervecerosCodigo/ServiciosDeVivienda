package view;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import model.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;
import lib.Konstanter;

/**
 * Denne klassen er et JPanel (arvet) som inneholder tabellen. Den definerer
 * Tabellen, samt oppretter TabellModell, som definerer selve funksjonaliteten
 * til tabellen.
 *
 * @author espen
 */
public class VenstrePanel extends AbstractPanel {

    private JTable tabell;
    private TableRowSorter<TabellModell> sorterer;

    public VenstrePanel(String borderTitle, int dimHeight, int dimWidth) {
        super(borderTitle, dimHeight, dimWidth);
        setLayout(new GridLayout(1, 1));
        tabell = new JTable();
        tabell.setAutoCreateRowSorter(true);
        tabell.setFillsViewportHeight(true);
        tabell.setShowGrid(true);        
        tabell.setGridColor(Color.gray);
        tabell.setSelectionBackground(new Color(210, 210, 210));
        tabell.setSelectionForeground(Color.BLACK);
        
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

    public void setSorterer(TableRowSorter<TabellModell> sorterer) {
        this.sorterer = sorterer;
    }

    public void sorterTabellVedOppstart() {
        RowSorter sorterer = tabell.getRowSorter();
        ArrayList sorteringsnokler = new ArrayList();
        sorteringsnokler.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorterer.setSortKeys(sorteringsnokler);
    }
}
