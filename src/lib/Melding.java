package lib;

import javax.swing.JOptionPane;

/**
 * Brukes til å rask vise meldinger. Brukes mye i samband med kontroll debugging
 * eller for å gi info til bruker. File: Melding.java Package: lib Project:
 * ServiciosDeVivienda Apr 22, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class Melding {

    String metode, melding;

    public Melding(String metode, String melding) {
        this.metode = metode;
        this.melding = melding;
        visMelding(metode, melding);
    }

    private void visMelding(String metode, String melding) {
        JOptionPane.showMessageDialog(null, melding, metode, JOptionPane.INFORMATION_MESSAGE);
    }
}
