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

    private String metode, melding, sporsmaal, overskrift, standardvalg;
    private static String[] alternativer;

    public Melding(String metode, String melding) {
        this.metode = metode;
        this.melding = melding;
//        visMelding(metode, melding);
    }
    
    public Melding(String sporsmaal, String overskrift, String standardvalg){
        this.sporsmaal = sporsmaal;
        this.overskrift = overskrift;
        this.standardvalg = standardvalg;
        this.alternativer = new String[]{"Ja", "Nei"};
    }

//    private void visMelding(String metode, String melding) {
//        JOptionPane.showMessageDialog(null, melding, metode, JOptionPane.INFORMATION_MESSAGE);
//    }
    
    /**
     * En static metode for å vise meldinger.
     * @param metode String
     * @param melding String
     */
    public static void visMelding(String metode, String melding) {
        JOptionPane.showMessageDialog(null, melding, metode, JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    /**
     * Metode for å bekrefte noe. 
     * @param sporsmaal Spørsmålet som stilles brukeren
     * @param overskrift Overskriften på meldingsboksen
     * @param standardvalg Standardvalget som er foretrukket
     * @return 
     */
    public static int visBekreftelseDialog(String sporsmaal, String overskrift, String standardvalg){
        int valg = JOptionPane.showOptionDialog(null,
                        sporsmaal, overskrift,
                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        null, alternativer, standardvalg);
        return valg;
    }
    
}
