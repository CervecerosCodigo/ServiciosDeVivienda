package serviciosdevivienda;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import controller.MainController;
import java.io.*;
import java.util.*;
import lib.Konstanter;
import model.*;

public class SkrivTilLesFraFil {

    private HashSet<Person> personliste;
    private HashSet<Bolig> boligliste;
    private HashSet<Kontrakt> kontraktliste;
    private HashSet<Annonse> annonseliste;
    private HashSet<Soknad> soknadsliste;

    private MainController mainController;

    public SkrivTilLesFraFil() {

        File fil = new File(Konstanter.FILNANV);
        if (!fil.exists()) {//Et lite hack som brukes foreløpig

            this.personliste = new HashSet<>();
            this.boligliste = new HashSet<>();
            this.annonseliste = new HashSet<>();
            this.kontraktliste = new HashSet<>();
            this.soknadsliste = new LinkedHashSet<>();
            
            mainController = new MainController(personliste, boligliste, annonseliste, kontraktliste, soknadsliste);
            
            mainController.testData();

            System.out.println("Filen " + Konstanter.FILNANV + " eksisterer IKKE, fyller med dummydata.");
        } else {
            lesInnData();
            System.out.println("Leser inn data fra fil.");
        }
    }

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

        } catch (IOException e) {//FIXME: trenger en felles plass for å fange opp de
            System.out.println(e.fillInStackTrace());
        }
    }

    /**
     * FIXME: Flyttes til egen klasse
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

        } catch (IOException e) {//FIXME: trenger en felles plass for å fange opp de
            System.out.println(e.fillInStackTrace());

        } catch (ClassNotFoundException e) {
            System.out.println(e.fillInStackTrace());
        }
    }
}
