package lib;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.Bolig;

/**
 * Klassen brukes til bildebehandlig for boliger. File: BoligBilde.java Package:
 * lib Project: ServiciosDeVivienda May 3, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class BoligBilde {

    private BildeFilSti bildeSti;
    private BufferedImage bilde;
    private int bildeType;
    private String galleriMappe;

    public BoligBilde() {
        bildeSti = new BildeFilSti();
    }

    /**
     * Returnerer antall bilder som finnes opplastet for en bolig.
     *
     * @param bolig
     * @return int antall lagrede bilder for boligen
     */
    public int antallBilder(Bolig bolig) {
        String sti = bildeSti.getBoligGalleryPath(bolig);
        return new File(sti).listFiles().length;
    }

    /**
     * Leser inn en nytt bilde og lagrer i klassen.
     *
     * @param bildeFil
     * @throws IOException
     */
    private void lesInnBilde(File bildeFil) throws IOException {
        bilde = ImageIO.read(bildeFil);
        bildeType = bilde.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bilde.getType();
    }

    /**
     * Foretar endring av bildet sitt størrelse til standard størrelse som
     * brukes i alle boligobjekter. Størrelsen hentes fra lib.Konstanter
     *
     * @param originalBilde
     * @param bildeType
     * @return BufferedImage
     */
    private BufferedImage endreBildeTilStandardStorrelse(BufferedImage originalBilde, int bildeType) {
        BufferedImage nyttBilde = new BufferedImage(Konstanter.BILDE_WIDTH, Konstanter.BILDE_HEIGHT, bildeType);
        Graphics2D grafikk = nyttBilde.createGraphics();
        grafikk.drawImage(originalBilde, 0, 0, Konstanter.BILDE_WIDTH, Konstanter.BILDE_HEIGHT, null);
        grafikk.dispose();
        return nyttBilde;
    }

    /**
     * Bruker bildefilsti til å hente inn lagringsmappen til bildet.
     *
     * @param bolig
     * @return
     */
    private String getGalleriSti(Bolig bolig) {
        return bildeSti.getBoligGalleryPath(bolig);
    }

    /**
     * KOntrollerer hvor mange bilde som alerede er lagret for boligen og
     * returnerer nummer hvilket neste bilde som skal lagres skal tildeles.
     *
     * @param bolig
     * @return
     */
    private int getNesteFilnummer(Bolig bolig) {
        return antallBilder(bolig) + 1;
    }

    /**
     * Lagrer et nytt jpg bilde for boligobjektet.
     *
     * @param bilde BufferedImage
     * @param path String
     * @throws IOException
     */
    private void lagreBilde(BufferedImage bilde, String path) throws IOException {
        ImageIO.write(bilde, "jpg", new File(path));
    }

    /**
     * Gitt boligobjekt og ny fil objekt som henviser til åpnet jpg fil. Denne
     * metoden vil endre størrelse på innlest bilde og lagre det med neste
     * filanvn (teller) i gallerimappen for objektet.
     *
     * @param bolig Bolig objekt som skal behandles
     * @param innlestFil et File objekt med peker til plassering av et nytt
     * bilde som skal brukes
     * @throws IOException
     */
    public void lagreNyttBildeForBolig(Bolig bolig, File innlestFil) throws IOException {
        lesInnBilde(innlestFil);
        BufferedImage tmpBilde = endreBildeTilStandardStorrelse(bilde, bildeType);
        String galleriSti = getGalleriSti(bolig);
        String fullSti = galleriSti + "/" + String.valueOf(getNesteFilnummer(bolig)) + ".jpg";
        lagreBilde(tmpBilde, fullSti);
    }

    public void setInkrementellNavnAlleFiler(Bolig bolig) {
        String bildemappe = bildeSti.getBoligGalleryPath(bolig);
        File mappe = new File(bildemappe);
        File[] filListe = mappe.listFiles();

//        String utskrift = "";
//        for (int i = 0; i < filListe.length; i++) {
//            utskrift += filListe[i].toString() + "\n";
//
//        }
//        Melding.visMelding("setInkrementellNavnAlleFiler", utskrift);
        for (int i = 0; i < filListe.length; i++) {
            if (filListe[i].isFile()) {
                String filnavn = bildemappe + "/" + String.valueOf(i + 1) + ".jpg";
                filListe[i].renameTo(new File(filnavn));

//                filListe[i].renameTo(new File(filnavn));
            }
//            if (!filListe[i].renameTo(new File(filnavn))) {
//                Melding.visMelding("Endrefilnavn", "Har mislykkets å endre navn på et bilde.");
//            }
        }
    }
}
