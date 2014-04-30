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
     * @param tittel
     * @param rader int som setter antall rader
     * @param kolonner int som setter antall kolonner
     */
    public CustomRegPanel(String tittel, int rader, int kolonner) {
        super();
        layout = new GridLayout(rader, kolonner);
        setLayout(layout);

        setVisible(true);
    }

  

}
