package view.registrer;

import java.awt.GridLayout;
import view.AbstractPanel;

/**
 * En custom panel som brukes for registreringsvinduer som innholder en
 * GridLayout for komponentene. File: CustomRegPanel.java Package:
 * view.registrer Project: ServiciosDeVivienda Apr 29, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class CustomRegPanel extends AbstractPanel {

    private GridLayout layout;

    /**
     * En konstruktør som har pålagt antall rader og kolonner for panelen.
     *
     * @param rader int som setter antall rader
     * @param kolonner int som setter antall kolonner
     */
    public CustomRegPanel(int rader, int kolonner) {
        layout = new GridLayout();
        layout.setRows(rader);
        layout.setColumns(kolonner);
    }

    /**
     * Setter antall rader og kolonner i panelen.
     *
     * @param rader int
     * @param kolonner int
     */
    public void setGrid(int rader, int kolonner) {
        layout.setRows(rader);
        layout.setColumns(kolonner);
    }

    /**
     * Setter mellomrom imellom komponentene.
     *
     * @param x
     * @param y
     */
    public void setPadding(int x, int y) {
        layout.setHgap(x);
        layout.setVgap(y);
    }
}
