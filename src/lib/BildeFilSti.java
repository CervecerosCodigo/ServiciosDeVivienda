package lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import model.Bolig;

/**
 * Klassen har metoder for å finne et eller flere bilder til en bolig gjennom å
 * bruke absolute path som skal være uavhengig operativsystem. File:
 * BildeFilSti.java Package: lib Project: ServiciosDeVivienda Apr 22, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class BildeFilSti {

//    InputStream instream;
//    OutputStream outstream;
    public BildeFilSti() {
    }

    /**
     * Returnerer an absolute filsti til alle bildegallerier for boliger.
     *
     * @return String med filsti
     */
    public String getAbsoluteGalleryPath() {
        Path currentPath = Paths.get("");
        String filsti = currentPath.toAbsolutePath().toString();
        return filsti + "/" + Konstanter.BILDEGALLERIER + "/";
    }

    /**
     * Returnerer filsti til standardbilde ved oprettelse av ny bolig. Denne
     * brukes dersom brukeren ikke laster opp egne bilder.
     *
     * @return String med filsti
     */
    public String getAbsolutePathToStandardBilde() {
        return getAbsoluteGalleryPath() + "default" + "/" + "1.jpg";
    }

    /**
     * Returnerer den absolute filstien til posisjonen med bildegallerier (skal
     * fungere uavhengig operativsystem) gitt en referanse til et boligobjekt.
     *
     * @param bolig
     * @return String med path til galleriet.
     */
    public String getBoligGalleryPath(Bolig bolig) {
        String boligidRef = String.valueOf(bolig.getBoligID());
        Path currentPath = Paths.get("");
        String filsti = currentPath.toAbsolutePath().toString();
        return filsti + "/" + Konstanter.BILDEGALLERIER + "/" + boligidRef;
    }

    /**
     * Sammen som getBoligGalleryPath(Bolig bolig) med returnerer en string som
     * begynner med "file:" slik lokale filer kan bli vist i html visning i feks
     * JEditPane
     *
     * @param bolig
     * @return String med path fil bildegalleri som begynner med file:
     */
    public String getBoligGalleryPathHTML(Bolig bolig) {
        return "file:" + getBoligGalleryPath(bolig);
    }

    /**
     * Returnerer filstien og filnavn fremsidebild for gitt bolig.
     *
     * @param bolig
     * @return String
     */
    public String getBoligFremsideBilde(Bolig bolig) {
        return getBoligGalleryPath(bolig) + "/" + Konstanter.STANDARDBILDE;
    }

    /**
     * Returnerer en html vennlig streng med referanse til fremsidebildet for
     * boligen inklusive filnavn. Referansen begynner med "file:" slik at den
     * kan vises i fesk html i JEditPane.
     *
     * @param bolig
     * @return String
     */
    public String getBoligFremsideBildeHTML(Bolig bolig) {
        return "file:" + getBoligFremsideBilde(bolig);
    }

    /**
     * Lager en gallerimappe for en ny bolig og kopierer inn standard bilde.
     * Standardbilde blir erstattet etter at brukeren legger til eget bilde(r)
     * for boligen.
     *
     * @param bolig
     */
    public void lagBildemappeForBolig(Bolig bolig) {
        File filsti = new File(getBoligGalleryPath(bolig));
        try {
            if (filsti.mkdirs()) {
                kopierStandardBilde(bolig);
//            kopierStandardBilde2(bolig);
            }

        } catch (SecurityException se) {
            Melding.visMelding("Sikkerhetsvarning", "Mislykket med å oprette gallerimappe\n" + se.getMessage());
        }
    }

    /**
     * Kopierer standard bilde for en annonse som ikke fått et bilde. Da vil det
     * bli hentet et standard bilde fra mappe programdata/img/default/1.jpg
     *
     * @param bolig
     */
    private void kopierStandardBilde(Bolig bolig) {
        File kildeFil = new File(getAbsolutePathToStandardBilde());
        File destinasjonsFil = new File(getBoligGalleryPath(bolig) + "/" + "1.jpg");

        try {
            Files.copy(kildeFil.toPath(), destinasjonsFil.toPath());
        } catch (IOException ex) {
            Melding.visMelding("kopierStandardBilde()", "IO Feil\n" + ex.getLocalizedMessage());
        }
    }

    /**
     * Alternativ metode for å kopiere standard bildefil. Kan brukes dersom den
     * første metoden vil får problem.
     *
     * @param bolig
     */
    private void kopierStandardBilde2(Bolig bolig) {
        InputStream instream = null;
        OutputStream outstream = null;

        try {
            File kildeFil = new File(getAbsolutePathToStandardBilde());
            File destinasjonsFil = new File(getBoligGalleryPath(bolig) + "/" + "1.jpg");

            instream = new FileInputStream(kildeFil);
            outstream = new FileOutputStream(destinasjonsFil);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = instream.read(buffer)) > 0) {
                outstream.write(buffer, 0, length);
            }

            instream.close();
            outstream.close();

        } catch (FileNotFoundException e) {
            Melding.visMelding("kopierStandardBilde2()", "Finne ikke fil: \n" + e.getMessage());
        } catch (IOException f) {
            Melding.visMelding("kopierStandardBilde2()", "IO Feil: \n" + f.getMessage());
        }
    }
}
