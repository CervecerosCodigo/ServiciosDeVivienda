package controller;

/**
 * Denne klassen inneholder statiske metoder for alt som har med "utskrift" av
 * objektene til visningsruten, samt tabellen som viser objektene i listeformat.
 * Metoden settOppTabell oppretter en lytter på Tabellen. Metoden
 * setInnDataITabell tar i mot datasettet som skal vises, og hvilket vindu de
 * skal vises i.
 */
import controller.registrer.ControllerRegistrerAnnonse;
import controller.registrer.ControllerRegistrerBolig;
import controller.registrer.ControllerRegistrerSoknad;
import controller.registrer.ControllerRegistrerUtleier;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.text.html.StyleSheet;
import lib.*;
import model.*;
import view.*;

public class ControllerTabell implements VisMeldingInterface {

    private HashSet<Person> personliste;
    private HashSet<Bolig> boligliste;
    private HashSet<Kontrakt> kontraktliste;
    private HashSet<Annonse> annonseliste;
    private HashSet<Soknad> soknadsliste;
    private ControllerBunnPanel bunnController;

    private DefaultTableCellRenderer hoyreStiltTekstRenderer;
    private TabellSendDataIBrukInterface tabellOppdateringslytter;

    private AbstraktArkfane vindu;
    private ArrayList<Object> tabellData;
    private ObjektType objekttype;
    private Collection liste;
    private StyleSheet css;
    private JEditorPane output;

    private JTable tabell;
    private TabellModell tabellModellBolig, tabellModellPerson, tabellModellAnnonse,
            tabellModellKontrakt, tabellModellSoknad, modellIBruk;
    private JPopupMenu tabellMeny;
    private JMenu menyvalgBolig, menyvalgPerson, menyvalgAnnonse, menyvalgSoknad;
    private JMenuItem menyvalgNyPerson, menyvalgEndrePerson, menyvalgSlettPerson,
            menyvalgNyBolig, menyvalgEndreBolig, menyvalgSlettBolig, menyvalgPubliserToggle, menyvalgSlettAnnonse,
            menyvalgForesporsel,
            menyvalgAksepter, menyvalgAvvis;

    private int valgtRadItabell;    //Viser til en hver tid hvilken rad som er valgt i tabellen

    public ControllerTabell(HashSet<Person> personliste, HashSet<Bolig> boligliste,
            HashSet<Annonse> annonseliste, HashSet<Kontrakt> kontraktliste,
            HashSet<Soknad> soknadsliste) {

        this.personliste = personliste;
        this.boligliste = boligliste;
        this.annonseliste = annonseliste;
        this.kontraktliste = kontraktliste;
        this.soknadsliste = soknadsliste;

        bunnController = new ControllerBunnPanel(boligliste, personliste, annonseliste, soknadsliste);

        tabellModellBolig = new TabellModellBolig(annonseliste);
        tabellModellPerson = new TabellModellPerson();
        tabellModellAnnonse = new TabellModellAnnonse();
        tabellModellKontrakt = new TabellModellKontrakt();
        tabellModellSoknad = new TabellModellSoknad();

        hoyreStiltTekstRenderer = new DefaultTableCellRenderer();
        hoyreStiltTekstRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        tabellMeny = new JPopupMenu();

        menyvalgPerson = new JMenu("Person");
        menyvalgBolig = new JMenu("Bolig");
        menyvalgAnnonse = new JMenu("Annonse");
        menyvalgSoknad = new JMenu("Søknad");
        menyvalgNyPerson = new JMenuItem("Ny");
        menyvalgEndrePerson = new JMenuItem("Endre");
        menyvalgSlettPerson = new JMenuItem("Slett");
        menyvalgNyBolig = new JMenuItem("Ny");
        menyvalgEndreBolig = new JMenuItem("Endre");
        menyvalgSlettBolig = new JMenuItem("Slett");
        menyvalgForesporsel = new JMenuItem("Send forespørsel");
        menyvalgAksepter = new JMenuItem("Aksepter søknad");
        menyvalgAvvis = new JMenuItem("Avvis søknad");
        menyvalgPubliserToggle = new JMenuItem("Endre publiseringsstatus");
        menyvalgSlettAnnonse = new JMenuItem("Slett annonse");

    }

    /**
     * Tar i mot det vinduet tabellen skal settes for. Metoden oppretter en
     * lytter på tabellen som finner hvilken rad/objekt som er valgt.
     * valueChanged-metoden sender også valgt objekt til output.
     *
     * @param vindu Tar i mot det vinduet som metoden gjelder for.
     */
    public void settOppTabellLyttere(final AbstraktArkfane vindu) {

        this.vindu = vindu;

        bunnController.settKnappeLytter(vindu);
        tabell = this.vindu.getVenstrepanel().getTable();

        //Kaller opp metoden som lager lyttere for popupmenyen i tabellen.
        settOpplyttereForPopupMenyITabell();

        //Kobler popupmenyen til tabellen.
        tabell.setComponentPopupMenu(tabellMeny);

        tabell.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                try {
                    int rad = tabell.getSelectedRow();
                    rad = tabell.convertRowIndexToModel(rad);

                    //Lagrer raden i en klassevariabel, som brukes i andre metoder.
                    valgtRadItabell = rad;

                    sendObjektFraTabellTilOutput(rad, objekttype, tabellData);

                } catch (ArrayIndexOutOfBoundsException aiobe) {

                }
            }
        });

        /**
         * Lytter på museklikk i Output-vinduet.
         */
        vindu.getSenterpanel().getEditorPane().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (modellIBruk instanceof TabellModellAnnonse) {
                        Annonse valgtObjekt = returnerAnnonseObjekt();
                        if (vindu instanceof ArkfaneMegler) {
                            new ControllerBildeViser(valgtObjekt.getBolig(), true);
                        } else {
                            new ControllerBildeViser(valgtObjekt.getBolig(), false);
                        }
                    }
                }
            }

        });

        /**
         * Lytter for dobbelklikk i tabellen.
         */
        tabell.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                JTable temp = (JTable) e.getSource();
                if (e.getClickCount() == 2) {

                    if (tabellModellBolig.equals((TabellModell) tabell.getModel())) {
                        ControllerRegistrerBolig cont = new ControllerRegistrerBolig(boligliste, returnerBoligObjekt());
                        cont.settTabellOppdateringsLytter(new TabellFireDataChangedInterface() {

                            @Override
                            public void oppdaterTabellEtterEndring() {
                                tabellModellBolig.fireTableStructureChanged();
                            }
                        });
                    } else if (tabellModellPerson.equals((TabellModell) tabell.getModel())) {
                        ControllerRegistrerUtleier cont = new ControllerRegistrerUtleier(personliste, (Utleier) returnerPersonObjekt());
                        cont.settTabellOppdateringsLytter(new TabellFireDataChangedInterface() {

                            @Override
                            public void oppdaterTabellEtterEndring() {
                                tabellModellPerson.fireTableStructureChanged();
                            }
                        });
                    } else if (tabellModellAnnonse.equals((TabellModell) tabell.getModel())) {
                        if (vindu instanceof ArkfaneMegler) {
                            ControllerRegistrerAnnonse cont = new ControllerRegistrerAnnonse(annonseliste, personliste, returnerAnnonseObjekt());
                            cont.settTabellOppdateringsLytter(new TabellFireDataChangedInterface() {

                                @Override
                                public void oppdaterTabellEtterEndring() {
                                    tabellModellPerson.fireTableStructureChanged();
                                }
                            });
                        } else {
                            new ControllerRegistrerSoknad(personliste, annonseliste, soknadsliste, returnerAnnonseObjekt());
                        }
                    }

                }//end if
            }//end method

            /**
             * Funksjonalitet for høyreklikking i tabellen
             *
             * @param e
             */
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {

                    //Tømmer menyen før den tegnes på nytt.
                    tabellMeny.removeAll();

                    try {
                        if (tabellModellBolig.equals((TabellModell) tabell.getModel())) {
                            tabellMeny.add(menyvalgBolig);
                            //menyvalgBolig.add(menyvalgNyBolig);
                            menyvalgBolig.add(menyvalgEndreBolig);
                            menyvalgBolig.add(menyvalgSlettBolig);
                            menyvalgBolig.add(menyvalgPubliserToggle);
                        } else if (tabellModellPerson.equals((TabellModell) tabell.getModel())) {
                            tabellMeny.add(menyvalgPerson);
                            tabellMeny.add(menyvalgBolig);
                            menyvalgPerson.add(menyvalgNyPerson);
                            menyvalgPerson.add(menyvalgEndrePerson);
                            menyvalgPerson.add(menyvalgSlettPerson);

                            if (returnerPersonObjekt() instanceof Utleier) {
                                menyvalgBolig.add(menyvalgNyBolig);
                            }

                        } else if (tabellModellAnnonse.equals((TabellModell) tabell.getModel())) {
                            tabellMeny.add(menyvalgForesporsel);
                            if (vindu instanceof ArkfaneMegler) {
                                tabellMeny.add(menyvalgSlettAnnonse);
                            }
                        } else if (tabellModellKontrakt.equals((TabellModell) tabell.getModel())) {

                        } else if (tabellModellSoknad.equals((TabellModell) tabell.getModel())) {
                            tabellMeny.add(menyvalgAksepter);
                            tabellMeny.add(menyvalgAvvis);
                        }
                    } catch (ClassCastException cce) {

                    }
                    tabellMeny.show(e.getComponent(), e.getX(), e.getY());
                }

            }
        });
    }

    /**
     * Setter lyttere for popupmenyen i tabellen. Flere av elementene i menyen
     * setter igjen lyttere på vinduene de starter slik at når registreringen er
     * fullført så blir tabellen oppdatert.
     */
    public void settOpplyttereForPopupMenyITabell() {

        //Man ser bare dette valger om man høyreklikker på en utleier i tabellen
        menyvalgNyBolig.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Person valgtObjekt = returnerPersonObjekt();
                if (valgtObjekt != null && valgtObjekt instanceof Utleier) {
                    ControllerRegistrerBolig cont = new ControllerRegistrerBolig(boligliste, (Utleier) valgtObjekt);

                    cont.settTabellOppdateringsLytter(new TabellFireDataChangedInterface() {

                        @Override
                        public void oppdaterTabellEtterEndring() {
                            tabellModellPerson.fireTableStructureChanged();
                        }
                    });
                }
            }
        });
        menyvalgEndreBolig.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Bolig bolig = returnerBoligObjekt();
                if (bolig != null) {
                    ControllerRegistrerBolig cont = new ControllerRegistrerBolig(boligliste, bolig);
                    cont.settTabellOppdateringsLytter(new TabellFireDataChangedInterface() {

                        @Override
                        public void oppdaterTabellEtterEndring() {
                            tabellModellPerson.fireTableStructureChanged();
                        }
                    });
                }
            }

        });
        menyvalgSlettBolig.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                slettBolig();
            }

        });
        menyvalgNyPerson.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerRegistrerUtleier cont = new ControllerRegistrerUtleier(personliste);
                cont.settTabellOppdateringsLytter(new TabellFireDataChangedInterface() {

                    @Override
                    public void oppdaterTabellEtterEndring() {
                        tabellModellPerson.fireTableStructureChanged();
                    }
                });
            }
        });
        menyvalgEndrePerson.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerRegistrerUtleier cont = new ControllerRegistrerUtleier(personliste, (Utleier) returnerPersonObjekt());
                cont.settTabellOppdateringsLytter(new TabellFireDataChangedInterface() {

                    @Override
                    public void oppdaterTabellEtterEndring() {
                        tabellModellPerson.fireTableStructureChanged();
                    }
                });
            }
        });
        menyvalgSlettPerson.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                slettPerson();
            }
        });

        //Om boligsøker høyreklikker..
        menyvalgForesporsel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (vindu instanceof ArkfaneAnnonse) {
                    new ControllerRegistrerSoknad(personliste, annonseliste, soknadsliste, returnerAnnonseObjekt());
                }
            }
        });

        //Aksepter søknad. Alle andre søknader på denne annonsen avvises da.
        menyvalgAksepter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                registrerKontrakt();
            }
        });

        //Avvis søknad
        menyvalgAvvis.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                avvisSoknad(returnerSoknadObjekt());
            }
        });

        //Registrer ny annonse eller endrer annonse. Er det en aktiv annonse 
        //så gjøres den innaktiv.
        menyvalgPubliserToggle.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                nyEllerEndreAnnonse();
            }
        });

        //Sletter annonsen
        menyvalgSlettAnnonse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                slettAnnonseFraRegisteret();
            }
        });
    }

    /**
     * Denne metoden registrerer valgt søknad som kontrakt. Hvis en søknad blir
     * registrert som kontrakt skal alle andre søknader på samme annonse
     * avvises.
     */
    private void registrerKontrakt() {
        Soknad soknad = returnerSoknadObjekt();

        /**
         * En boligsøker legges ikke inn i personregisteret før en søknad er
         * godkjent. Hvis leietakerobjekter fra en søknad ligger i
         * personregisteret kan denne søkeren ikke få godkjent en ny kontrakt.
         */
        if (!personliste.contains(soknad.getLeietakerObjekt())) {

            int annonseID = soknad.getAnnonseObjekt().getAnnonseID();
            ArrayList<Soknad> soknaderPaaSammeAnnonse = new ArrayList<>();

            //Finner megler-objektet 
            Iterator<Person> personIter = personliste.iterator();
            Person tempPerson = null;
            while (personIter.hasNext()) {
                tempPerson = personIter.next();
                if (tempPerson instanceof Megler) {
                    if (tempPerson.getPersonID() == soknad.getAnnonseObjekt().getBolig().getMeglerID()) {
                        tempPerson = (Megler) tempPerson;
                        return;
                    }
                }
            }

            //Finner alle søknader som gelder for samme Annonse
            Iterator<Soknad> soknadIter = soknadsliste.iterator();
            Soknad tempSoknad = null;
            while (soknadIter.hasNext()) {
                tempSoknad = soknadIter.next();
                if (tempSoknad.getAnnonseObjekt().getAnnonseID() == annonseID) {
                    soknaderPaaSammeAnnonse.add(tempSoknad);
                }
            }

            //Finner dagens dato
            Calendar dagensDato = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_WEEK));

            //Oppretter et kontraktobjekt basert på søknaden.
            Kontrakt kontrakt = new Kontrakt(soknad.getAnnonseObjekt(), tempPerson, soknad.getLeietakerObjekt(), 12, dagensDato);

            //Hvis kontrakten legges inn i kontraktliste skal alle andre søknader på valgt
            //annonse avvises.
            if (kontraktliste.add(kontrakt)) {
                if (personliste.add(soknad.getLeietakerObjekt())) {

                    soknad.setErBehandlet(true);
                    soknad.setErGodkjent(true);
                    //Sletter søknaden som er godkjent fra listen med søknader på samme annonse.
                    soknaderPaaSammeAnnonse.remove(soknad);

                    soknadIter = soknaderPaaSammeAnnonse.iterator();
                    tempSoknad = null;
                    while (soknadIter.hasNext()) {
                        tempSoknad = soknadIter.next();
                        tempSoknad.setErBehandlet(true);
                        tempSoknad.setErGodkjent(false);
                    }

                    visMelding(null, "Kontrakten er opprettet!");
                    tabellModellSoknad.fireTableStructureChanged();
                    vindu.getVenstrepanel().sorterTabellSoknadData();
                }
            } else {
                visMelding(null, "Kontrakten ble IKKE opprettet!");
            }

        } else {
            visMelding("Leietaker finnes i registeret!", "Denne personen finnes i registeret.\n"
                    + "Personen kan dermed ikke registrere ny kontrakt.");
            soknad.setErBehandlet(true);
            soknad.setErGodkjent(false);
            tabellModellSoknad.fireTableStructureChanged();
            vindu.getVenstrepanel().sorterTabellSoknadData();
        }

    }//End registrerKontrakt

    /**
     * Setter søknaden som behandlet og ikke godkjent.
     *
     * @param soknad
     */
    private void avvisSoknad(Soknad soknad) {
        soknad.setErBehandlet(true);
        soknad.setErGodkjent(false);
        tabellModellSoknad.fireTableStructureChanged();
    }

    /**
     * Tømmer tabellen før nytt datasett settes.
     */
    public void tomTabellOgKlargjorForNyttDatasett() {
        tabell.clearSelection();
        tabell.removeAll();
    }

    public Soknad returnerSoknadObjekt() {

        Soknad valgtObjekt = (Soknad) tabellData.get(valgtRadItabell);
        if (valgtObjekt != null) {
            return valgtObjekt;
        }
        return null;
    }

    /**
     * Returnerer boligobjekt basert på valgt rad i tabellen.
     *
     * @return
     */
    public Bolig returnerBoligObjekt() {

        Bolig valgtObjekt = (Bolig) tabellData.get(valgtRadItabell);
        if (valgtObjekt != null) {
            return valgtObjekt;
        }
        return null;
    }

    /**
     * Returnerer Personobjekt basert på valgt rad i tabellen.
     *
     * @return
     */
    public Person returnerPersonObjekt() {
        Person valgtObjekt = (Person) tabellData.get(valgtRadItabell);
        if (valgtObjekt != null) {
            return valgtObjekt;
        }
        return null;
    }

    /**
     * Returnerer Annonseobjekt basert på valgt rad i tabellen.
     *
     * @return
     */
    public Annonse returnerAnnonseObjekt() {
        Annonse valgtObjekt = (Annonse) tabellData.get(valgtRadItabell);
        if (valgtObjekt != null) {
            return valgtObjekt;
        }
        return null;
    }

    /**
     * Metoden finner boligen som skal slettes. Om den ikke er utleid får
     * brukeren spørsmål om å slette.
     */
    public void slettBolig() {

        Boolean ok = true;
        Bolig valgtObjekt = (Bolig) tabellData.get(valgtRadItabell);
        if (valgtObjekt != null) {

            if (!valgtObjekt.isErUtleid() || !valgtObjekt.isErUtleid()) {
                int valg = Melding.visBekreftelseDialog("Ønsker du virkelig å slette boligen?",
                        "Slette bolig", "Nei");
                if (valg == 0) {
                    try {
                        tabellData.remove(valgtRadItabell);
                        ok = boligliste.remove(valgtObjekt);
                        if (ok) {
                            tabellModellBolig.fireTableRowsDeleted(valgtRadItabell, valgtRadItabell);
                            visMelding(null, "Bolig med ID " + valgtObjekt.getBoligID() + " er slettet");
                        } else {
                            visMelding(null, "Bolig med ID " + valgtObjekt.getBoligID() + " ble IKKE slettet");
                        }
                    } catch (ArrayIndexOutOfBoundsException aiobe) {
                    }
                } else {
                }
            } else {
                visMelding("Feil under sletting", "Kunne ikke slettet boligen.\n"
                        + "Den er enten utleid eller annonsert.");
            }
        }//end if
    }

    /**
     * Metoden finner person som skal slettes. Om den ikke har boliger
     * registrert får brukeren spørsmål om å slette.
     */
    public void slettPerson() {

        Boolean ok = true;
        Person valgtObjekt = (Person) tabellData.get(valgtRadItabell);
        ArrayList<Integer> registrerteBoliger = new ArrayList<>();
        Iterator<Bolig> iter = boligliste.iterator();
        while (iter.hasNext()) {
            Bolig temp = iter.next();
            if (temp.getPersonID() == valgtObjekt.getPersonID()) {
                registrerteBoliger.add(temp.getBoligID());
            }
        }
        if (registrerteBoliger.size() > 0) {
            visMelding(null, valgtObjekt.getFornavn() + " " + valgtObjekt.getEtternavn()
                    + " er registrert med boliger.\n"
                    + "Kan ikke utføre slettingen.");
        } else {
            int valg = Melding.visBekreftelseDialog("Ønsker du virkelig å slette personen fra systemet?",
                    "Slette person", "Nei");
            if (valg == 0) {
                try {
                    tabellData.remove(valgtRadItabell);

                    ok = personliste.remove(valgtObjekt);
                    if (ok) {
                        tabellModellPerson.fireTableRowsDeleted(valgtRadItabell, valgtRadItabell);
                        visMelding(null, "Person med ID " + valgtObjekt.getPersonID() + " er slettet");
                    } else {
                        visMelding(null, "Person med ID " + valgtObjekt.getPersonID() + " ble IKKE slettet");
                    }
                } catch (ArrayIndexOutOfBoundsException aiobe) {
                }
            } else {
            }
        }//end if
    }

    /**
     * Om boligen er publisert så tas den av nett. Om den ikke er publisert så
     * sjekkes det om boligen finnes i annonseregistert. Om den gjør det så
     * åpnes vindu for å endre annonsen, ellers lages det ny tom annonse.
     */
    public void nyEllerEndreAnnonse() {

        int rad;
        Annonse tempAnnonse;
        Bolig bolig = (Bolig) tabellData.get(valgtRadItabell);
        if (bolig != null) {

            //Er boligen ikke utleid?
            if (!bolig.isErUtleid()) {
                ControllerRegistrerAnnonse cont = null;
                //Sjekker om boligen ligger i annonseregisteret
                Iterator<Annonse> iter = annonseliste.iterator();
                while (iter.hasNext()) {
                    tempAnnonse = iter.next();
                    if (tempAnnonse.getBoligID() == bolig.getBoligID()) {
                        //Boligen er annonsert og kan endres
                        cont = new ControllerRegistrerAnnonse(annonseliste, personliste, tempAnnonse);
                        cont.settTabellOppdateringsLytter(new TabellFireDataChangedInterface() {

                            @Override
                            public void oppdaterTabellEtterEndring() {
                                modellIBruk.fireTableStructureChanged();
                            }
                        });
                        return;
                    }//end if
                }//end while
                //Boligen er ikke i annonseregisteret og ny annonse opprettes.
                cont = new ControllerRegistrerAnnonse(annonseliste, personliste, bolig);
                cont.settTabellOppdateringsLytter(new TabellFireDataChangedInterface() {

                    @Override
                    public void oppdaterTabellEtterEndring() {
                        modellIBruk.fireTableStructureChanged();
                    }
                });
            } else {
                //Boligen er utleid og kan ikke legges i annonseregisteret
            }
        }//end if
    }

    public void slettAnnonseFraRegisteret() {
        Annonse annonse = returnerAnnonseObjekt();
        if (annonse != null) {
            if (annonseliste.remove(annonse)) {
                visMelding("Sletting fullført!", "Annonsen er slettet!");
                modellIBruk.fireTableRowsDeleted(valgtRadItabell, valgtRadItabell);
            } else {
                visMelding("Feil!", "Annonsen ble IKKE slettet!");
            }
        }
    }

    /**
     * Denne lytteren er ledd i komunikasjonen med toppanelets Controller.
     * MainController initialiserer komunikasjonen.
     *
     * @param lytter
     */
    public void settTabellLytterSendDataSett(TabellSendDataIBrukInterface lytter) {
        this.tabellOppdateringslytter = lytter;
    }

    /**
     * Oppretter en arraylist med lengde av mottatt datasett. Denne metoden er
     * avhengig av søkeresultatene og må få inn parametere fra toppanel.
     */
    public void settInnDataITabell(Collection innkommendeDatasett, ObjektType objekttypeEnum) {

        modellIBruk = null;
        if (innkommendeDatasett.size() > 0) {
            tabellData = new ArrayList<>();
            Iterator<?> iter = innkommendeDatasett.iterator();
            while (iter.hasNext()) {
                tabellData.add(iter.next());
            }

            try {
                switch (objekttypeEnum) {
                    case PERSONOBJ:
                        this.objekttype = ObjektType.PERSONOBJ;
                        tabellModellPerson.fyllTabellMedInnhold(tabellData);
                        tabell.setModel(tabellModellPerson);
                        tabellModellPerson.fireTableStructureChanged();
                        modellIBruk = tabellModellPerson;
                        vindu.getVenstrepanel().sorterTabellVedOppstart();
                        break;
                    case BOLIGOBJ:
                        this.objekttype = ObjektType.BOLIGOBJ;
                        tabellModellBolig.fyllTabellMedInnhold(tabellData);
                        tabell.setModel(tabellModellBolig);
                        tabellModellBolig.fireTableStructureChanged();
                        modellIBruk = tabellModellBolig;
                        vindu.getVenstrepanel().sorterTabellVedOppstart();
                        break;
                    case ANNONSEOBJ:
                        this.objekttype = ObjektType.ANNONSEOBJ;
                        tabellModellAnnonse.fyllTabellMedInnhold(tabellData);
                        tabell.setModel(tabellModellAnnonse);
                        tabellModellAnnonse.fireTableStructureChanged();
                        tabell.getColumnModel().getColumn(2).setCellRenderer(hoyreStiltTekstRenderer);
                        tabell.getColumnModel().getColumn(3).setCellRenderer(hoyreStiltTekstRenderer);
                        modellIBruk = tabellModellAnnonse;
                        vindu.getVenstrepanel().sorterTabellVedOppstart();
                        break;
                    case KONTRAKTOBJ:
                        this.objekttype = ObjektType.KONTRAKTOBJ;
                        tabellModellKontrakt.fyllTabellMedInnhold(tabellData);
                        tabell.setModel(tabellModellKontrakt);
                        tabellModellKontrakt.fireTableStructureChanged();
                        modellIBruk = tabellModellKontrakt;
                        vindu.getVenstrepanel().sorterTabellVedOppstart();
                        break;
                    case SOKNADSOBJ:
                        this.objekttype = ObjektType.SOKNADSOBJ;
                        tabellModellSoknad.fyllTabellMedInnhold(tabellData);
                        tabell.setModel(tabellModellSoknad);
                        tabellModellSoknad.fireTableStructureChanged();
                        modellIBruk = tabellModellSoknad;
                        vindu.getVenstrepanel().sorterTabellSoknadData();
                        break;
                }
                resizeKolonneBredde();

                vindu.getVenstrepanel().settCelleRenderer();

                bunnController.settOppTabellData(tabellData, modellIBruk);

            } catch (ArrayIndexOutOfBoundsException aiobe) {
                System.out.println("Tabell gir ArrayOutOfBounds ved innlegging av nytt datasett");
            } catch (NullPointerException npe) {
                System.out.println("Tabell gir NullPointer ved innlegging av nytt datasett");
            }//End Try/Catch
            
            /**
             * Sender ToppPanel liste over datasettet om settes i tabellen.
             */
            if (tabellOppdateringslytter != null) {
                tabellOppdateringslytter.sendTabellDataIBruk(tabellData);
            }
        }//End If datasett > 0
    }//End Metodet settInnDataITabell

    /**
     * Setter kolonnebredden etter innholdet i tabellen. Sammen med tabellens
     * Auto-resize så blir tabellen fyllt ut i maks bredde, men samtidig med
     * rett kolonnebredde.
     */
    public void resizeKolonneBredde() {
        TableColumnModel kolonneModell = tabell.getColumnModel();
        Component comp = null;
        TableCellRenderer renderer = null;

        for (int kol = 0; kol < tabell.getColumnCount(); kol++) {
            int bredde = 50; //minste bredde
            for (int rad = 0; rad < tabell.getRowCount(); rad++) {
                renderer = tabell.getCellRenderer(rad, kol);
                comp = tabell.prepareRenderer(renderer, rad, kol);
                bredde = Math.max(comp.getPreferredSize().width, bredde);
            }
            kolonneModell.getColumn(kol).setPreferredWidth(bredde);
        }
    }

    /**
     * Tar i mot data og bestemmer hvilken "HTML"-metode som skal brukes.
     *
     * @param valgtRad Raden i arrayen som skal vises.
     * @param tabellData Arrayen tabellen er bygget på.
     * @param vindu Vinduet som skal vise resultatet.
     */
    public void sendObjektFraTabellTilOutput(int valgtRad, ObjektType objekttype, ArrayList tabellData) {
        Object valgtObjekt = null;
        css = vindu.getSenterpanel().getStyleSheet();
        ControllerOutput.setStyleSheet(css);
        try {
            switch (objekttype) {
                case PERSONOBJ:
                    valgtObjekt = (Person) tabellData.get(valgtRad);
                    ControllerOutput.visPersonObjektHTMLOutput(output, vindu, valgtObjekt, personliste, boligliste, soknadsliste);
                    break;
                case BOLIGOBJ:
                    valgtObjekt = (Bolig) tabellData.get(valgtRad);
                    ControllerOutput.visBoligObjektHTMLOutput(valgtObjekt, output, vindu, boligliste);
                    break;
                case ANNONSEOBJ:
                    valgtObjekt = (Annonse) tabellData.get(valgtRad);
                    ControllerOutput.visAnnonseObjektHTMLOutput(valgtObjekt, output, vindu, annonseliste);
                    break;
                case KONTRAKTOBJ:
                    valgtObjekt = (Kontrakt) tabellData.get(valgtRad);
                    ControllerOutput.visKontraktObjektHTMLOutput(valgtObjekt, output, vindu);
                    break;
                case SOKNADSOBJ:
                    valgtObjekt = (Soknad) tabellData.get(valgtRad);
                    ControllerOutput.visSoknadObjektHTMLOutput(valgtObjekt, output, vindu, soknadsliste, personliste);

                    break;
            }
        } catch (ArrayIndexOutOfBoundsException aiobe) {

        }
    }

    @Override
    public void visMelding(String overskrift, String melding) {
        vindu.visMelding(overskrift, melding);
    }

}
