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
    private TableCellRenderer celleRenderer;

    public VenstrePanel(String borderTitle, int dimHeight, int dimWidth) {
        super(borderTitle, dimHeight, dimWidth);
        setLayout(new GridLayout(1, 1));

        tabell = new JTable();
        
        tabell.setAutoCreateRowSorter(true);
        tabell.setFillsViewportHeight(true);
        tabell.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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

    /**
     * Formaterer søknadene i tabellen som er behandlet.
     */
    public void settCelleRenderer() {

        tabell.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                TabellModell modell = (TabellModell)tabell.getModel();
                
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if(modell instanceof TabellModellSoknad){
                    if(table.getValueAt(row, 2).equals("Ja")){
                        c.setForeground(new Color(200, 200, 200));                      
                    }else{
                        c.setForeground(Color.BLACK);                      
                    }
                }
                c.repaint();
                return c;
            }
            
        });

    }

}
