package lib;

import javax.swing.JOptionPane;

/**
 * Brukes til å rask vise meldinger. Brukes mye i samband med kontroll debugging
 * eller for å gi info til bruker.
 */
public class Melding {

    private String metode, melding, sporsmaal, overskrift, standardvalg;
    private static Object[] alternativer;

    public Melding(String metode, String melding) {
        this.metode = metode;
        this.melding = melding;
    }
    
    public Melding(String sporsmaal, String overskrift, String standardvalg){
        this.sporsmaal = sporsmaal;
        this.overskrift = overskrift;
        this.standardvalg = standardvalg;
        Melding.alternativer = new Object[]{"Ja", "Nei"};
    }

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
                        null, Konstanter.VALG_JA_NEI, standardvalg);
        return valg;
    }
    
}
