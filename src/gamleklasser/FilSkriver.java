package gamleklasser;

import controller.MainController;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import model.Annonse;
import model.Bolig;
import model.Kontrakt;
import model.Person;
import model.Soknad;

/**
 * Serialiserer alle registre på toppnivå. File: FilSkriver.java Package: lib
 * Project: ServiciosDeVivienda Apr 9, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class FilSkriver implements Serializable {

    private File fil;
    private FileOutputStream fos;
    private ObjectOutputStream out;

    public FilSkriver(File fil) {
        this.fil = fil;
    }

    /**
     * Serialiserer valgt objekt til filbane valgt i kontruktøren.
     *
     * @param o Generelt objekt.
     */
    public void skrivTilFil(Object o) {
        try {
            fos = new FileOutputStream(fil);
            out = new ObjectOutputStream(fos);
            out.writeObject(o);
            out.close();
        } catch (IOException e) {
            System.out.println(e.fillInStackTrace());
        }
    }

    public void skrivMainControllerTilFil(MainController c) {
        try {
            fos = new FileOutputStream(fil);
            out = new ObjectOutputStream(fos);

            out.writeInt(Person.getTeller());
            out.writeInt(Bolig.getTeller());
            out.writeInt(Annonse.getTeller());
            out.writeInt(Soknad.getTeller());
            out.writeInt(Kontrakt.getTeller());

            out.writeObject(c);

            out.close();
        } catch (IOException e) {//FIXME: trenger en felles plass for å fange opp de
            System.out.println(e.fillInStackTrace());
        }
    }
}
