package view;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class VenstrePanel extends AbstractPanel {

    private JTable tabell;
    private TabellModell tabellModell;

    public VenstrePanel(String borderTitle, int dimHeight, int dimWidth) {
        super(borderTitle, dimHeight, dimWidth);
        setLayout(new GridLayout(1, 1));

        tabellModell = new TabellModell();
        tabell = new JTable(tabellModell);

        tabell.setFillsViewportHeight(true);
        add(new JScrollPane(tabell));

    }//End Constructor


    /**
     * Setter Tabellmodell.
     *
     * @return
     */
    public TabellModell getModel() {
        return tabellModell;
    }

    /**
     * Tar imot en array fra MainController og setter i Tabellmodellen.
     *
     * @param data
     */
    public void fyllTabellMedInnhold(Object[] data, String[] kolonneNavn, int objektType) {
        tabellModell.fyllTabellMedInnhold(data, kolonneNavn, objektType);
        tabellModell.fireTableStructureChanged();

    }

    /**
     * Returnerer tabellen.
     *
     * @return
     */
    public JTable getTable() {
        return tabell;
    }

}
