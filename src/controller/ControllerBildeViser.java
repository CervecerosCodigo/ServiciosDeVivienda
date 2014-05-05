package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import lib.BildeFilSti;
import lib.BoligBilde;
import lib.Melding;
import model.Bolig;
import view.BildeViser;

/**
 * Controllerklasse til bildeviseren. Bildeviseren finnes i view pakken og
 * brukes til å vise alle bilder som hører til en bolig. File:
 * ControllerBildeViser.java Package: controller Project: ServiciosDeVivienda
 * May 5, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class ControllerBildeViser {

    private Bolig bolig;
    private BildeViser bildeVindu;
    private BufferedImage[] bilder;
    private boolean erMegler;
    private int bildeSomVises, antallBilder;

    public ControllerBildeViser(Bolig bolig, boolean erMegler) {
        this.bolig = bolig;
        this.erMegler = erMegler;
        bildeVindu = new BildeViser(bolig, erMegler);
        antallBilder = new BoligBilde().antallBilder(bolig);
        bildeSomVises = 0;

        //Setter opp lytteren
        bildeVindu.addButtonPanelListener(new BildeKnappLytter());

        try {
            if (antallBilder == 0) {
                BufferedImage standardbilde = ImageIO.read(new File(new BildeFilSti().getAbsolutePathToStandardBilde()));
                visBilde(standardbilde);
                skruAvKnapper();
            } else {
                //Tester å vise bilder
                lesInnBilder(bolig);
            }
        } catch (IOException ex) {
            Melding.visMelding("Feil", "IO feil ved innlesning av bilder");
        }

        //Starter bildefremvisning
        visNesteBilde();
    }

    private void skruAvKnapper() {
        bildeVindu.getTilbakeButton().setEnabled(false);
        bildeVindu.getFremButton().setEnabled(false);
        bildeVindu.getSlettButton().setEnabled(false);
    }

    private String getGalleriSti(Bolig bolig) {
        return new BildeFilSti().getBoligGalleryPath(bolig);
    }

    /**
     * Leser inn alle bilder som han finner i galleriet inn i arrayen med
     * BufferedImage.
     *
     * @param bolig
     * @throws IOException
     */
    private void lesInnBilder(Bolig bolig) throws IOException {
        String galleriSti = getGalleriSti(bolig);
        antallBilder = new BoligBilde().antallBilder(bolig);
        bilder = null;
        bilder = new BufferedImage[antallBilder];

        for (int i = 0; i < bilder.length; i++) {
            bilder[i] = ImageIO.read(new File(galleriSti + "/" + (i + 1) + ".jpg"));
        }
    }

    /**
     * Setter bilde i panelen for visning
     *
     * @param bilde
     */
    private void visBilde(BufferedImage bilde) {
//        bildeVindu.getBildeLabel().repaint();
        bildeVindu.getBildeLabel().setIcon(new ImageIcon(bilde));
    }

    /**
     * Henter opp et bilde fra arrayen og viser det i panelen.
     *
     * @param bildenummer Et bildenummer for bilde som er lagret i galleriet
     * ikke indeks til oversikten over bilder.
     */
    private void visSpesifikkBilde(int bildenummer) {
        if (bildenummer > 0) {
            visBilde(bilder[bildenummer - 1]);
        } else {
            Melding.visMelding("visSpesifikkBilde", "For lavt bildenummer");
        }
    }

    private void kanppeStyring() {
        oppdaterBildeTeller();
    }

    /**
     * Viser neste bilde i den innleste bildearrayen hvis det finnnes.
     */
    private void visNesteBilde() {
//        oppdaterBildeTeller();

        bildeSomVises = bildeSomVises + 1;
        if (bildeSomVises > antallBilder) {
            bildeSomVises = 1;
            visSpesifikkBilde(bildeSomVises);
            oppdaterBildeTeller();
        } else {
            visSpesifikkBilde(bildeSomVises);
            oppdaterBildeTeller();
        }

    }

    /**
     * Viser forrige bilde i arrayen så lenge det finnes.
     */
    private void visForrigeBilde() {

//        oppdaterBildeTeller();
        bildeSomVises = bildeSomVises - 1;
        if (bildeSomVises >= 1) {
            visSpesifikkBilde(bildeSomVises);
            oppdaterBildeTeller();
        } else {
            bildeSomVises = antallBilder;
            visSpesifikkBilde(bildeSomVises);
            oppdaterBildeTeller();
        }
    }

    private boolean slettBilde(Bolig bolig, int bildenr) throws IOException {
        String bildemappeSti = new BildeFilSti().getBoligGalleryPath(bolig);
        String bildeSti = bildemappeSti + "/" + bildenr + ".jpg";
        File bildeTilSletting = new File(bildeSti);
        
//        BoligBilde boligBilder = new BoligBilde();
//        boligBilder.setInkrementellNavnAlleFiler(bolig);
        
        if (bildeTilSletting.delete()) {

            BoligBilde boligBilder = new BoligBilde();
            boligBilder.setInkrementellNavnAlleFiler(bolig);
            lesInnBilder(bolig);
            bildeSomVises = 0;
            visNesteBilde();

            Melding.visMelding("Bilde " + bildenr, "Bilde er slettet");
        } else {
            Melding.visMelding("Bilde " + bildenr, "Bilde ble ikke slettet");
        }

        return false;
    }

    private void oppdaterBildeTeller() {
        antallBilder = new BoligBilde().antallBilder(bolig);
        bildeVindu.getTellerLabel().setText("Bilde " + bildeSomVises + " av " + antallBilder);
    }

    class BildeKnappLytter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(bildeVindu.getTilbakeButton())) {
                visForrigeBilde();
            } else if (e.getSource().equals(bildeVindu.getFremButton())) {
                visNesteBilde();
            } else if (e.getSource().equals(bildeVindu.getSlettButton())) {
                try {
                    slettBilde(bolig, bildeSomVises);
                } catch (IOException ex) {
                    Melding.visMelding("Sletting av bild", "IO feil etter sletting av bilde");
                }
            } else if (e.getSource().equals(bildeVindu.getLukkButton())) {
                bildeVindu.dispose();
            }
        }

    }
}
