package controller;

/**
 * Denne klassen spesifiserer funksjonaliteten til tabellen og kommunikasjonen
 * mellom tabellen og andre komponenter i programmet. Metoden settOppTabell
 * oppretter en lytter på Tabellen. Metoden setInnDataITabell tar i mot
 * datasettet som skal vises, og hvilket vindu de skal vises i.
 */
import controller.registrer.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
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
            menyvalgAksepter, menyvalgAvvis, menyvalgSlettSoknad;

    private int valgtRadItabell;    //Viser til en hver tid hvilken rad som er valgt i tabellen
    private InputMap inputMap;
    private ActionMap actionMap;

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
        menyvalgSlettSoknad = new JMenuItem("Slett søknad");
        menyvalgPubliserToggle = new JMenuItem("Endre publiseringsstatus");
        menyvalgSlettAnnonse = new JMenuItem("Slett annonse");

    }//End Contructor

    /**
     * Denne metoden kalles opp når man trykker på sletteknappen på tastaturet.
     * Metoden finner ut hvilket datasett som er i bruk og sletter rett objekt.
     */
    public void generellSletteMetodeSomKallerOppRettSletteMetode() {

        try {
            if (modellIBruk instanceof TabellModellPerson) {
                slettPerson();
            }
            if (modellIBruk instanceof TabellModellBolig) {
                slettBolig();
            }
            if (modellIBruk instanceof TabellModellAnnonse) {
                slettAnnonseFraRegisteret();
            }
            if (modellIBruk instanceof TabellModellSoknad) {
                slettSoknad(returnerSoknadObjekt());
            }
        } catch (ClassCastException cce) {
            System.out.println("ClassCastException ved sletting med deleteknapp");
        }
    }

    /**
     * Tar i mot det vinduet tabellen skal settes for. Det settes opp flere
     * lyttere relatert til tabllens funksjonalitet.
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

        /////////////////////////////////////////////////////////////////
        //Sletteknappfunksjonalitet når man trykker Delete på tastaturet
        inputMap = tabell.getInputMap(JTable.WHEN_FOCUSED);
        actionMap = tabell.getActionMap();

        Action sletteknappFunksjon = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generellSletteMetodeSomKallerOppRettSletteMetode();
            }
        };

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "Slett");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "Slett");
        actionMap.put("Slett", sletteknappFunksjon);
        //Slutt på sletteknappfunksjonalitet
        /////////////////////////////////////////////////////////////////

        /**
         * Lytter på endring i valgt rad i tabellen og returnerer indexen til
         * ArrayListen som danner grunnlaget for tabellens innhold (i valgt
         * TabellModell).
         */
        tabell.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (e.getValueIsAdjusting()) {
                    return;
                }

                try {
                    int rad = tabell.getSelectedRow();
                    if (rad > -1) {
                        rad = tabell.convertRowIndexToModel(rad);

                        //Lagrer raden i en variabel, som brukes i andre metoder.
                        valgtRadItabell = rad;
                        sendObjektFraTabellTilOutput(objekttype);
                    }

                } catch (ArrayIndexOutOfBoundsException aiobe) {
                    System.out.println("Tabell ConvertRowIndexToModel ArrayIndexOufOfBounds");
                } catch (IndexOutOfBoundsException iobe) {
                    System.out.println("Tabell ConvertRowIndexToModel IndexOufOfBounds");
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
         * Lytter for dobbelklikk i tabellen. Objekter skal bare kunne endres.
         */
        tabell.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) {

                    if (tabellModellBolig.equals((TabellModell) tabell.getModel())) {
                        ControllerRegistrerBolig cont = new ControllerRegistrerBolig(boligliste, returnerBoligObjekt());
                        cont.settTabellOppdateringsLytter(new TabellFireDataChangedInterface() {

                            @Override
                            public void oppdaterTabellEtterEndring() {
                                tabellModellBolig.fireTableDataChanged();
                            }
                        });
                    } else if (tabellModellPerson.equals((TabellModell) tabell.getModel())) {
                        if (returnerPersonObjekt() instanceof Utleier) {
                            ControllerRegistrerUtleier cont = new ControllerRegistrerUtleier(personliste, (Utleier) returnerPersonObjekt());
                            cont.settTabellOppdateringsLytter(new TabellFireDataChangedInterface() {

                                @Override
                                public void oppdaterTabellEtterEndring() {
                                    tabellModellPerson.fireTableDataChanged();
                                }
                            });
                        }
                    } else if (tabellModellAnnonse.equals((TabellModell) tabell.getModel())) {
                        if (vindu instanceof ArkfaneMegler) {
                            ControllerRegistrerAnnonse cont = new ControllerRegistrerAnnonse(annonseliste, personliste, returnerAnnonseObjekt());
                            cont.settTabellOppdateringsLytter(new TabellFireDataChangedInterface() {

                                @Override
                                public void oppdaterTabellEtterEndring() {
                                    tabellModellPerson.fireTableDataChanged();
                                }
                            });
                            //Det dobbelklikkes i fra annonsevinduet
                        } else {
                            new ControllerRegistrerSoknad(personliste, annonseliste, soknadsliste, returnerAnnonseObjekt());
                        }
                    }
                }//end if
            }//end method

            /**
             * Høyreklikkmetode, fungerer bare i Windows.
             * @param e 
             */
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    hoyreKlikkEventMetode();
                    tabellMeny.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            /**
             * Høyreklikkmetode, fungerer bare i Linux/Mac
             *
             * @param e
             */
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    hoyreKlikkEventMetode();
                    tabellMeny.show(e.getComponent(), e.getX(), e.getY());
                }

            }//End MouseReleased
        });//end addMouseListener
    }//End settOppTabellLyttere

    
    /**
     * Setter opp høyreklikkmenyen. Kalles opp fra to metoder. Én for Windows
     * og én for Linux/Mac
     */
    public void hoyreKlikkEventMetode() {
        //Tømmer menyen før den tegnes på nytt.
        tabellMeny.removeAll();

        try {
            if (tabellModellBolig.equals((TabellModell) tabell.getModel())) {
                tabellMeny.add(menyvalgBolig);
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
                if (vindu instanceof ArkfaneAnnonse) {
                    tabellMeny.add(menyvalgForesporsel);
                }
                if (vindu instanceof ArkfaneMegler) {
                    tabellMeny.add(menyvalgPubliserToggle);
                    tabellMeny.add(menyvalgSlettAnnonse);
                }
            } else if (tabellModellSoknad.equals((TabellModell) tabell.getModel())) {
                tabellMeny.add(menyvalgAksepter);
                tabellMeny.add(menyvalgAvvis);
                tabellMeny.add(menyvalgSlettSoknad);
            }

        } catch (ClassCastException cce) {
            System.out.println("Popupmeny kunne ikke finne rett TabellModell");
        }
    }

    /**
     * Setter lyttere for popupmenyen i tabellen. Flere av elementene i menyen
     * setter igjen lyttere på vinduene de starter slik at når registreringen er
     * fullført så blir tabellen oppdatert.
     */
    public void settOpplyttereForPopupMenyITabell() {

        //Man ser bare dette valget om man høyreklikker på en utleier i tabellen
        menyvalgNyBolig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Person valgtObjekt = returnerPersonObjekt();

                if (valgtObjekt instanceof Utleier) {
                    ControllerRegistrerBolig cont = new ControllerRegistrerBolig(boligliste, (Utleier) valgtObjekt);
                    cont.settTabellOppdateringsLytter(new TabellFireDataChangedInterface() {
                        @Override
                        public void oppdaterTabellEtterEndring() {
                            tabellModellPerson.fireTableDataChanged();
                        }
                    });
                }
            }
        });

        //Endre bolig
        menyvalgEndreBolig.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Bolig bolig = returnerBoligObjekt();
                if (bolig != null) {
                    ControllerRegistrerBolig cont = new ControllerRegistrerBolig(boligliste, bolig);
                    cont.settTabellOppdateringsLytter(new TabellFireDataChangedInterface() {
                        @Override
                        public void oppdaterTabellEtterEndring() {
                            tabellModellPerson.fireTableDataChanged();
                        }
                    });
                }
            }

        });

        //Slett bolig
        menyvalgSlettBolig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                slettBolig();
            }
        });

        //Ny person
        menyvalgNyPerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerRegistrerUtleier cont = new ControllerRegistrerUtleier(personliste);
                cont.settTabellOppdateringsLytter(new TabellFireDataChangedInterface() {
                    @Override
                    public void oppdaterTabellEtterEndring() {
                        tabellModellPerson.fireTableDataChanged();
                    }
                });
            }
        });

        //Endre person
        menyvalgEndrePerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerRegistrerUtleier cont = new ControllerRegistrerUtleier(personliste, (Utleier) returnerPersonObjekt());
                cont.settTabellOppdateringsLytter(new TabellFireDataChangedInterface() {

                    @Override
                    public void oppdaterTabellEtterEndring() {
                        tabellModellPerson.fireTableDataChanged();
                    }
                });
            }
        });

        //Slett person
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
        //Slett søknad
        menyvalgSlettSoknad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                slettSoknad(returnerSoknadObjekt());
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

    }//End settOppLyttereForPopupMenyTabell

    /**
     * hjelpemetode for å finne megleren som skal registreres i søknaden
     *
     * @param soknad
     * @return
     */
    public Megler returnererMeglerObjektFraSoknad(Soknad soknad) {
        //Finner megler-objektet 
        Iterator<Person> personIter = personliste.iterator();
        Person tempPerson = null;
        while (personIter.hasNext()) {
            tempPerson = personIter.next();
            if (tempPerson instanceof Megler) {
                if (tempPerson.getPersonID() == soknad.getAnnonseObjekt().getBolig().getMeglerID()) {
                    return (Megler) tempPerson;
                }
            }
        }
        return null;
    }

    /**
     * Hjelpemetode for å finne alle soknader som gjelder samme annonse
     *
     * @param annonseID
     * @return
     */
    public ArrayList<Soknad> returnerAlleSoknaderPaaSammeAnnonse(int annonseID) {

        ArrayList<Soknad> soknaderPaaSammeAnnonse = new ArrayList<>();
        Iterator<Soknad> soknadIter = soknadsliste.iterator();
        Soknad tempSoknad = null;

        while (soknadIter.hasNext()) {
            tempSoknad = soknadIter.next();
            if (tempSoknad.getAnnonseObjekt().getAnnonseID() == annonseID) {
                soknaderPaaSammeAnnonse.add(tempSoknad);
            }
        }
        return soknaderPaaSammeAnnonse;
    }

    /**
     * Denne metoden registrerer valgt søknad som kontrakt. Hvis en søknad blir
     * registrert som kontrakt skal alle andre søknader på samme annonse
     * avvises.
     */
    public void registrerKontrakt() {
        Soknad soknad = returnerSoknadObjekt();

        /**
         * En boligsøker legges ikke inn i personregisteret før en søknad er
         * godkjent. Hvis leietakerobjekter fra en søknad ligger i
         * personregisteret kan denne søkeren ikke få godkjent en ny kontrakt.
         */
        if (!personliste.contains(soknad.getLeietakerObjekt())) {

            int annonseID = soknad.getAnnonseObjekt().getAnnonseID();
            Megler megler = returnererMeglerObjektFraSoknad(soknad);

            int valg = Melding.visBekreftelseDialog("Ønsker du godkjenne denne søknaden?",
                    "Godkjenn søknad", "Nei");
            if (valg == 0) {

                //Finner alle søknader som gelder for samme Annonse
                ArrayList<Soknad> soknaderPaaSammeAnnonse = returnerAlleSoknaderPaaSammeAnnonse(annonseID);

                //Finner dagens dato
                Calendar dagensDato = new GregorianCalendar(
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_WEEK));

                //Oppretter et kontraktobjekt basert på søknaden.
                Kontrakt kontrakt = new Kontrakt(soknad.getAnnonseObjekt(), megler, soknad.getLeietakerObjekt(), 12, dagensDato);

                //Hvis kontrakten legges inn i kontraktliste skal alle andre søknader på valgt annonse avvises.
                if (kontraktliste.add(kontrakt)) {
                    if (personliste.add(soknad.getLeietakerObjekt())) {

                        soknad.setErBehandlet(true);
                        soknad.setErGodkjent(true);
                        soknad.getAnnonseObjekt().getBolig().setErUtleid(true);
                        //Sletter søknaden som er godkjent fra listen med søknader på samme annonse.
                        soknaderPaaSammeAnnonse.remove(soknad);

                        Iterator<Soknad> soknadIter = soknaderPaaSammeAnnonse.iterator();
                        Soknad tempSoknad = null;

                        while (soknadIter.hasNext()) {
                            tempSoknad = soknadIter.next();
                            tempSoknad.setErBehandlet(true);
                            tempSoknad.setErGodkjent(false);
                        }
                        //Setter annonsen "ikke aktiv"
                        soknad.getAnnonseObjekt().setErSynlig(false);
                        visMelding("Fullført!", "Kontrakten er opprettet!");
                        tabellModellSoknad.fireTableDataChanged();
                        vindu.getVenstrepanel().sorterTabellSoknadData();
                    }

                } else {
                    visMelding(null, "Kontrakten ble IKKE opprettet!");
                }
            }
        } else {
            visMelding("Leietaker finnes i registeret!", "Denne personen finnes i registeret.\n"
                    + "Personen kan dermed ikke registrere ny kontrakt.");
            soknad.setErBehandlet(true);
            soknad.setErGodkjent(false);
            tabellModellSoknad.fireTableDataChanged();
            vindu.getVenstrepanel().sorterTabellSoknadData();
        }

    }//End registrerKontrakt

    /**
     * Setter søknaden som behandlet og ikke godkjent.
     *
     * @param soknad
     */
    public void avvisSoknad(Soknad soknad) {
        soknad.setErBehandlet(true);
        soknad.setErGodkjent(false);
        tabellModellSoknad.fireTableStructureChanged();
    }
    
    /**
     * Sletter valgt søknad
     * @param soknad 
     */
    public void slettSoknad(Soknad soknad) {
        if(soknadsliste.remove(soknad)){
            visMelding("Sletting fullført", "Søknaden er slettet!");
            tabellModellSoknad.fireTableDataChanged();
        }else{
            visMelding("Feil ved sletting", "Noe gikk galt. Søknaden ble IKKE slettet");
        }
    }

    /**
     * Tømmer tabellen før nytt datasett settes.
     */
    public void tomTabellOgKlargjorForNyttDatasett() {
        tabell.clearSelection();
        tabell.removeAll();
    }

    /**
     * Returnerer søknadsobjektet som er valgt i tabellen
     *
     * @return Soknad
     */
    public Soknad returnerSoknadObjekt() {
        Soknad valgtObjekt = (Soknad) tabellModellSoknad.finnObjektIModell(valgtRadItabell);

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
        Bolig valgtObjekt = (Bolig) tabellModellBolig.finnObjektIModell(valgtRadItabell);
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
        Person valgtObjekt = (Person) tabellModellPerson.finnObjektIModell(valgtRadItabell);
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
        Annonse valgtObjekt = (Annonse) tabellModellAnnonse.finnObjektIModell(valgtRadItabell);
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
        Bolig valgtObjekt = returnerBoligObjekt();
        if (valgtObjekt != null) {

            if (!valgtObjekt.isErUtleid()) {
                int valg = Melding.visBekreftelseDialog("Ønsker du virkelig å slette boligen?", "Slette bolig", "Nei");
                if (valg == 0) {
                    try {
                        ok = boligliste.remove(valgtObjekt);
                        tabellModellBolig.removeRow(valgtRadItabell);

                        if (ok) {
                            tabellModellBolig.fireTableRowsDeleted(valgtRadItabell, valgtRadItabell);
                            visMelding(null, "Bolig med ID " + valgtObjekt.getBoligID() + " er slettet");
                        } else {
                            visMelding(null, "Bolig med ID " + valgtObjekt.getBoligID() + " ble IKKE slettet");
                        }

                    } catch (ArrayIndexOutOfBoundsException aiobe) {
                        System.out.println("ArrayIndexOutofBounds ved sletting av bolig");
                    }
                }
            } else {
                visMelding("Feil under sletting", "Kunne ikke slettet boligen.\n" + "Den er enten utleid eller annonsert.");
            }
        }//end if
    }

    /**
     * Metoden finner person som skal slettes. Om den ikke har boliger
     * registrert får brukeren spørsmål om å slette.
     */
    public void slettPerson() {

        Boolean ok = true;
        Person valgtObjekt = returnerPersonObjekt();
        if (!harUtleierBoligerRegistrert(valgtObjekt)) {

            //Personen har ikke boliger registrert og kan dermed slettes
            int valg = Melding.visBekreftelseDialog("Ønsker du virkelig å slette personen fra systemet?", "Slette person", "Nei");
            if (valg == 0) {
                try {
                    ok = personliste.remove(valgtObjekt);
                    if (ok) {
                        tabellModellPerson.removeRow(valgtRadItabell);
                        tabellModellPerson.fireTableRowsDeleted(valgtRadItabell, valgtRadItabell);
                        visMelding(null, "Person med ID " + valgtObjekt.getPersonID() + " er slettet");
                    } else {
                        visMelding(null, "Person med ID " + valgtObjekt.getPersonID() + " ble IKKE slettet");
                    }

                } catch (ArrayIndexOutOfBoundsException aiobe) {
                    System.out.println("ArrayIndexOutOfBounds på remove av Person");
                }
            }
        }//end if
    }

    /**
     * Hjelpemetode for slettePerson-metoden.
     *
     * @param person
     * @return
     */
    public boolean harUtleierBoligerRegistrert(Person person) {

        ArrayList<Bolig> registrerteBoliger = new ArrayList<>();
        Iterator<Bolig> iter = boligliste.iterator();

        while (iter.hasNext()) {
            Bolig temp = iter.next();

            if (temp.getPersonID() == person.getPersonID()) {
                registrerteBoliger.add(temp);
            }
        }

        if (registrerteBoliger.size() > 0) {
            visMelding(null, person.getFornavn() + " " + person.getEtternavn() + " er registrert med boliger.\n" + "Kan ikke utføre slettingen.");
            return true;
        }
        return false;
    }//End Method

    /**
     * Om boligen er publisert så tas den av nett. Om den ikke er publisert så
     * sjekkes det om boligen finnes i annonseregistert. Om den gjør det så
     * åpnes vindu for å endre annonsen, ellers lages det ny tom annonse.
     */
    public void nyEllerEndreAnnonse() {

        Annonse tempAnnonse;
        Bolig bolig = returnerBoligObjekt();
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
                                modellIBruk.fireTableDataChanged();
                            }
                        });
                        return;
                    }//End if

                }//End while

                //Boligen er ikke i annonseregisteret og ny annonse opprettes.
                cont = new ControllerRegistrerAnnonse(annonseliste, personliste, bolig);
                cont.settTabellOppdateringsLytter(new TabellFireDataChangedInterface() {

                    @Override
                    public void oppdaterTabellEtterEndring() {
                        modellIBruk.fireTableDataChanged();
                    }
                });
            } else {
                visMelding("Kan ikke utføre operasjonen", "Boligen er utleid og kan ikke oppdateres");
            }
        }//End if
    }//End method

    /**
     * Sletter valgt annonse fra registeret og tabellen.
     */
    public void slettAnnonseFraRegisteret() {
        Annonse annonse = returnerAnnonseObjekt();
        if (annonse != null) {
            Boolean ok = annonseliste.remove(annonse);
            if (ok) {
                tabellModellAnnonse.removeRow(valgtRadItabell);
                tabellModellAnnonse.fireTableRowsDeleted(valgtRadItabell, valgtRadItabell);
                visMelding("Sletting fullført!", "Annonsen er slettet!");
            } else {
                visMelding("Feil!", "Annonsen ble IKKE slettet!");
            }
        }
    }

    /**
     * Oppretter en arraylist med lengde av mottatt datasett. Denne metoden er
     * avhengig av søkeresultatene og må få inn parametere fra toppanel.
     */
    public void settInnDataITabell(Collection innkommendeDatasett, ObjektType objekttypeEnum) {

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
                        tabell.getColumnModel().getColumn(2).setCellRenderer(vindu.getVenstrepanel().settHoyrestilltFormateringPaaTabell());
                        tabell.getColumnModel().getColumn(3).setCellRenderer(vindu.getVenstrepanel().settHoyrestilltFormateringPaaTabell());
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
                vindu.getVenstrepanel().resizeKolonneBredde();
                vindu.getVenstrepanel().settCelleRenderer();
                bunnController.settOppTabellData(modellIBruk);

            } catch (ArrayIndexOutOfBoundsException aiobe) {
                System.out.println("settInnDataITabell gir ArrayOutOfBounds ved innlegging av nytt datasett");
            } catch (NullPointerException npe) {
                System.out.println("settInnDataITabell gir NullPointer ved innlegging av nytt datasett");
            }//End Try/Catch

        }//End If datasett > 0
    }//End Metodet settInnDataITabell

    /**
     * Tar i mot en Enum og bestemmer hvilken "HTML"-metode som skal brukes.
     *
     * @param valgtRad Raden i arrayen som skal vises.
     * @param tabellData Arrayen tabellen er bygget på.
     * @param vindu Vinduet som skal vise resultatet.
     */
    public void sendObjektFraTabellTilOutput(ObjektType objekttype) {
        Object valgtObjekt = null;
        css = vindu.getSenterpanel().getStyleSheet();
        ControllerOutput.setStyleSheet(css);
        try {
            switch (objekttype) {
                case PERSONOBJ:
                    valgtObjekt = tabellModellPerson.finnObjektIModell(valgtRadItabell);
                    ControllerOutput.visPersonObjektHTMLOutput(output, vindu, valgtObjekt, personliste, boligliste, soknadsliste);
                    break;
                case BOLIGOBJ:
                    valgtObjekt = tabellModellBolig.finnObjektIModell(valgtRadItabell);
                    ControllerOutput.visBoligObjektHTMLOutput(valgtObjekt, output, vindu, boligliste);
                    break;
                case ANNONSEOBJ:
                    valgtObjekt = tabellModellAnnonse.finnObjektIModell(valgtRadItabell);
                    ControllerOutput.visAnnonseObjektHTMLOutput(valgtObjekt, output, vindu, annonseliste);
                    break;
                case KONTRAKTOBJ:
                    valgtObjekt = tabellModellKontrakt.finnObjektIModell(valgtRadItabell);
                    ControllerOutput.visKontraktObjektHTMLOutput(valgtObjekt, output, vindu);
                    break;
                case SOKNADSOBJ:
                    valgtObjekt = tabellModellSoknad.finnObjektIModell(valgtRadItabell);
                    ControllerOutput.visSoknadObjektHTMLOutput(valgtObjekt, output, vindu, soknadsliste, personliste);

                    break;
            }
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            System.out.println("ArrayOutOfBoundsException i SendObjektFraTabellTilOutput i ControllerTabell");
        }
    }//End sendObjektFraTabellTilOutput

    @Override
    public void visMelding(String overskrift, String melding) {
        vindu.visMelding(overskrift, melding);
    }

}
