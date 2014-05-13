package view;

import model.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;

/**
 * Denne klassen er et JPanel (arvet) som inneholder tabellen. Den definerer
 * Tabellen, samt oppretter TabellModell, som definerer selve funksjonaliteten
 * til tabellen.
 */
public class VenstrePanel extends AbstractPanel {

    private JTable tabell;
    private DefaultTableCellRenderer hoyreStiltTekstRenderer;

    public VenstrePanel(String borderTitle, int dimHeight, int dimWidth) {
        super(borderTitle, dimHeight, dimWidth);
        setLayout(new GridLayout(1, 1));

        tabell = new JTable();
        tabell.setAutoCreateRowSorter(true);
        tabell.setFillsViewportHeight(true);
        tabell.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabell.setShowGrid(true);
        tabell.setGridColor(Color.gray);
        tabell.setRowHeight(20);
        tabell.setSelectionForeground(Color.BLACK);

        add(new JScrollPane(tabell));

        hoyreStiltTekstRenderer = new DefaultTableCellRenderer();
        hoyreStiltTekstRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

    }//End Constructor

    /**
     * Returnerer tabellen.
     *
     * @return
     */
    public JTable getTable() {
        return tabell;
    }

    /**
     * Standard sortering på objektets ID, i første kolonne
     */
    public void sorterTabellVedOppstart() {
        RowSorter sorterer = tabell.getRowSorter();
        ArrayList sorteringsnokler = new ArrayList();
        sorteringsnokler.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorterer.setSortKeys(sorteringsnokler);
    }

    /**
     * Sortering på søknadsobjektene. Sorterer synkende på Kolonnen Behandlet og
     * deretter stigende på SøknadsID
     */
    public void sorterTabellSoknadData() {
        RowSorter sorterer = tabell.getRowSorter();
        ArrayList sorteringsnokler = new ArrayList();
        sorteringsnokler.add(new RowSorter.SortKey(2, SortOrder.DESCENDING));
        sorteringsnokler.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));

        sorterer.setSortKeys(sorteringsnokler);
    }

    /**
     * Formaterer søknadene i tabellen som er behandlet.
     */
    public void settCelleRenderer() {

        tabell.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable tabell, Object verdi, boolean erValgt, boolean harFokus, int rad, int kolonne) {
                TabellModell modell = (TabellModell) tabell.getModel();

                Component c = super.getTableCellRendererComponent(tabell, verdi, erValgt, harFokus, rad, kolonne);
                if (modell instanceof TabellModellSoknad) {
                    if (tabell.getValueAt(rad, 2).equals("Ja"))
                        c.setForeground(new Color(200, 200, 200));
                    
                    else c.setForeground(Color.BLACK);
                }
                c.repaint();
                return c;
            }
        });
    }

    /**
     * Setter høyrestillt formatering på kolonnen som angis.
     * @return
     */
    public DefaultTableCellRenderer settHoyrestilltFormateringPaaTabell() {
        return hoyreStiltTekstRenderer;
    }

    /**
     * Setter kolonnebredden etter innholdet i tabellen. Sammen med tabellens
     * Auto-resize så blir tabellen fyllt ut i maks bredde, men samtidig med
     * rett kolonnebredde.
     */
    public void resizeKolonneBredde() {
        TableColumnModel kolonneModell = tabell.getColumnModel();
        Component comp = null;
        TableCellRenderer renderer = null;

        for (int kol = 0; kol < tabell.getColumnCount(); kol++) {
            int bredde = 50; //minste bredde
            for (int rad = 0; rad < tabell.getRowCount(); rad++) {
                renderer = tabell.getCellRenderer(rad, kol);
                comp = tabell.prepareRenderer(renderer, rad, kol);
                bredde = Math.max(comp.getPreferredSize().width, bredde);
            }
            kolonneModell.getColumn(kol).setPreferredWidth(bredde);
        }
    }
}
