package gamleklasser;

import controller.MainController;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import model.Annonse;
import model.Bolig;
import model.Kontrakt;
import model.Person;
import model.Soknad;

/**
 *
 * File: FilLeser.java Package: lib Project: ServiciosDeVivienda Apr 10, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class FilLeser {

    File fil;
    FileInputStream fis;
    ObjectInputStream in;
    MainController c;

    public FilLeser(File fil) {
        this.fil = fil;
    }

    public MainController lesMainControllerFraFil() {
        try {
            fis = new FileInputStream(fil);
            in = new ObjectInputStream(fis);

            Person.setTeller(in.readInt());
            Bolig.setTeller(in.readInt());
            Annonse.setTeller(in.readInt());
            Soknad.setTeller(in.readInt());
            Kontrakt.setTeller(in.readInt());

            c = (MainController) in.readObject();//FIXME: Kaster classNotFound

            in.close();


        } catch (IOException e) {//FIXME: trenger en felles plass for å fange opp de
            System.out.println(e.fillInStackTrace());
        } catch (ClassNotFoundException e) {
            System.out.println(e.fillInStackTrace());
        }

        return c;
    }
}
