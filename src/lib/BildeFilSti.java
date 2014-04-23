package lib;

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
}
