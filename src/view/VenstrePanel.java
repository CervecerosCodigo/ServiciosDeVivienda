package view;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * Denne klassen er et JPanel (arvet) som inneholder tabellen.
 * Den definerer Tabellen, samt oppretter TabellModell, som definerer 
 * selve funksjonaliteten til tabellen.
 * @author espen
 */
public class VenstrePanel extends AbstractPanel {

    private JTable tabell;
    private TabellModell tabellModell;
    private TableRowSorter<TabellModell> sorterer;

    public VenstrePanel(String borderTitle, int dimHeight, int dimWidth) {
        super(borderTitle, dimHeight, dimWidth);
        setLayout(new GridLayout(1, 1));

        tabellModell = new TabellModell();
        tabell = new JTable(tabellModell);
        sorterer = new TableRowSorter<>( tabellModell );
        
        
        tabell.setFillsViewportHeight(true);
        
        add(new JScrollPane(tabell));

    }//End Constructor

    /**
     * Returnerer tabellmodellen som definerer tabellens innhold og funksjon.
     * @return
     */
    public TabellModell getTabellModell() {
        return tabellModell;
    }
    
    public void settTabellSortering(){
        tabell.setRowSorter(sorterer);
    }

    /**
     * Setter kolonnebredden etter innholdet i tabellen. 
     * Sammen med tabellens Auto-resize så blir tabellen fyllt ut i maks bredde,
     * men samtidig med rett kolonnebredde.
     */
    public void resizeKolonneBredde() {
        final TableColumnModel kolonneModell = tabell.getColumnModel();
        Component comp = null;
        for (int kol = 0; kol < tabell.getColumnCount(); kol++) {
            int bredde = 50; //minste bredde
            for (int rad = 0; rad < tabell.getRowCount(); rad++) {
                TableCellRenderer renderer = tabell.getCellRenderer(rad, kol);
                comp = tabell.prepareRenderer(renderer, rad, kol);
                bredde = Math.max(comp.getPreferredSize().width, bredde);
            }
            kolonneModell.getColumn(kol).setPreferredWidth(bredde);
        }
    }
    /**
     * Returnerer tabellen.
     * @return
     */
    public JTable getTable() {
        return tabell;
    }



}
