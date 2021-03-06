package serviciosdevivienda;

import controller.MainController;
import java.io.*;
import java.util.*;
import lib.Konstanter;
import model.*;

/**
 * Denne klassen kalles fra Mainmetoden og er den siste klassen som kjøres ved
 * avsluttning av programmet. Her leses det fra og skrives det til fil
 */
public class SkrivTilLesFraFil {

    private HashSet<Person> personliste;
    private HashSet<Bolig> boligliste;
    private HashSet<Kontrakt> kontraktliste;
    private HashSet<Annonse> annonseliste;
    private HashSet<Soknad> soknadsliste;

    private MainController mainController;

    public SkrivTilLesFraFil() {

        File fil = new File(Konstanter.FILNANV);
        if (!fil.exists()) {

            this.personliste = new HashSet<>();
            this.boligliste = new HashSet<>();
            this.annonseliste = new HashSet<>();
            this.kontraktliste = new HashSet<>();
            this.soknadsliste = new LinkedHashSet<>();

            mainController = new MainController(personliste, boligliste, annonseliste, kontraktliste, soknadsliste);
            System.out.println("Filen " + Konstanter.FILNANV + " eksisterer IKKE, fyller med dummydata.");
        } 
        
        else {
            lesInnData();
            System.out.println("Leser inn data fra fil.");
        }
    }

    /**
     * Denne metoden skriver de serialiserte dataene til filen data.iso
     */
    public void lagreData() {
        try {
            FileOutputStream fos = new FileOutputStream(new File(Konstanter.FILNANV));
            ObjectOutputStream out = new ObjectOutputStream(fos);

            out.writeObject(personliste);
            out.writeObject(boligliste);
            out.writeObject(annonseliste);
            out.writeObject(kontraktliste);
            out.writeObject(soknadsliste);

            out.writeInt(Person.getTeller());
            out.writeInt(Bolig.getTeller());
            out.writeInt(Annonse.getTeller());
            out.writeInt(Soknad.getTeller());
            out.writeInt(Kontrakt.getTeller());

            out.close();

        } catch (IOException e) {
            System.out.println(e.fillInStackTrace());
        }
    }

    /**
     * Metoden leser inn data fra datafilen data.iso.
     */
    public void lesInnData() {
        try {
            FileInputStream fis = new FileInputStream(new File(Konstanter.FILNANV));
            ObjectInputStream in = new ObjectInputStream(fis);

            personliste = (HashSet<Person>) in.readObject();
            boligliste = (HashSet<Bolig>) in.readObject();
            annonseliste = (HashSet<Annonse>) in.readObject();
            kontraktliste = (HashSet<Kontrakt>) in.readObject();
            soknadsliste = (HashSet<Soknad>) in.readObject();

            Person.setTeller(in.readInt());
            Bolig.setTeller(in.readInt());
            Annonse.setTeller(in.readInt());
            Soknad.setTeller(in.readInt());
            Kontrakt.setTeller(in.readInt());

            in.close();
            mainController = new MainController(personliste, boligliste, annonseliste, kontraktliste, soknadsliste);

        } catch (IOException e) {
            System.out.println(e.fillInStackTrace());

        } catch (ClassNotFoundException e) {
            System.out.println(e.fillInStackTrace());
        }
    }
}
