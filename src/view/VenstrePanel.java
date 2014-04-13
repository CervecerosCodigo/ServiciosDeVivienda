package view;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.GridLayout;
import javax.swing.*;

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
     * Returnerer tabellmodellen som definerer tabellens innhold og funksjon.
     * @return 
     */
    public TabellModell getTabellModell() {
        return tabellModell;
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
