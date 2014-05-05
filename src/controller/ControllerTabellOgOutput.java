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

public class ControllerTabellOgOutput {

    private HashSet<Person> personliste;
    private HashSet<Bolig> boligliste;
    private HashSet<Kontrakt> kontraktliste;
    private HashSet<Annonse> annonseliste;
    private HashSet<Soknad> soknadsliste;
    private ControllerBunnPanel bunnController;
    
    private DefaultTableCellRenderer rightRenderer;

    private ArkfaneTemplate vindu;
    private ArrayList<Object> tabellData;
    private ObjektType objekttype;
    private Collection liste;
    private StyleSheet css;
    private JEditorPane output;

    private JTable tabell;
    private TabellModell tabellModellBolig, tabellModellPerson, tabellModellAnnonse,
            tabellModellKontrakt, tabellModellSoknad;
    private JPopupMenu tabellMeny;
    private JMenu menyvalgBolig, menyvalgPerson, menyvalgAnnonse, menyvalgSoknad;
    private JMenuItem menyvalgNyPerson, menyvalgEndrePerson, menyvalgSlettPerson,
            menyvalgNyBolig, menyvalgEndreBolig, menyvalgSlettBolig, menyvalgPubliserToggle,
            menyvalgForesporsel,
            menyvalgAksepter, menyvalgAvvis;

    private int valgtRadItabell;

    public ControllerTabellOgOutput(HashSet<Person> personliste, HashSet<Bolig> boligliste,
            HashSet<Annonse> annonseliste, HashSet<Kontrakt> kontraktliste,
            HashSet<Soknad> soknadsliste) {

        this.personliste = personliste;
        this.boligliste = boligliste;
        this.annonseliste = annonseliste;
        this.kontraktliste = kontraktliste;
        this.soknadsliste = soknadsliste;
        
        bunnController = new ControllerBunnPanel(boligliste, personliste, annonseliste);

        tabellModellBolig = new TabellModellBolig(annonseliste);
        tabellModellPerson = new TabellModellPerson();
        tabellModellAnnonse = new TabellModellAnnonse();
        tabellModellKontrakt = new TabellModellKontrakt();
        tabellModellSoknad = new TabellModellSoknad();

        rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

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
        

    }



    /**
     * Tar i mot det vinduet tabellen skal settes for. Metoden oppretter en
     * lytter på tabellen som finner hvilken rad/objekt som er valgt.
     * valueChanged-metoden sender også valgt objekt til output.
     *
     * @param vindu Tar i mot det vinduet som metoden gjelder for.
     */
    public void settOppTabellLyttere(final ArkfaneTemplate vindu) {

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
         * Lytter for dobbelklikk i tabellen.
         */
        tabell.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                JTable temp = (JTable) e.getSource();
                if (e.getClickCount() == 2) {
                    int rad = temp.getSelectedRow();
                    rad = tabell.convertRowIndexToModel(rad);

                    if (tabellModellBolig.equals((TabellModell) tabell.getModel())) {
                        System.out.println("Bolig");
                    } else if (tabellModellPerson.equals((TabellModell) tabell.getModel())) {
                        new ControllerRegistrerUtleier(personliste, (Person) tabellData.get(rad));
                    } else if (tabellModellAnnonse.equals((TabellModell) tabell.getModel())) {
                        System.out.println("Annonse");
                    } else if (tabellModellKontrakt.equals((TabellModell) tabell.getModel())) {
                        System.out.println("Kontrakt");
                    } else if (tabellModellSoknad.equals((TabellModell) tabell.getModel())) {
                        System.out.println("Søknad");
                    }

                }//end if
            }//end method

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {

                    //Tømmer menyen før den tegnes på nytt.
                    tabellMeny.removeAll();

                    try {
                        if (tabellModellBolig.equals((TabellModell) tabell.getModel())) {
                            tabellMeny.add(menyvalgBolig);
                            menyvalgBolig.add(menyvalgNyBolig);
                            menyvalgBolig.add(menyvalgEndreBolig);
                            menyvalgBolig.add(menyvalgSlettBolig);
                            menyvalgBolig.add(menyvalgPubliserToggle);
                        } else if (tabellModellPerson.equals((TabellModell) tabell.getModel())) {
                            tabellMeny.add(menyvalgPerson);
                            tabellMeny.add(menyvalgBolig);
                            menyvalgPerson.add(menyvalgNyPerson);
                            menyvalgPerson.add(menyvalgEndrePerson);
                            menyvalgPerson.add(menyvalgSlettPerson);
                            menyvalgBolig.add(menyvalgNyBolig);
                            menyvalgBolig.add(menyvalgEndreBolig);
                            menyvalgBolig.add(menyvalgSlettBolig);
                        } else if (tabellModellAnnonse.equals((TabellModell) tabell.getModel())) {
                            tabellMeny.add(menyvalgAnnonse);
                            menyvalgAnnonse.add(menyvalgForesporsel);
                        } else if (tabellModellKontrakt.equals((TabellModell) tabell.getModel())) {

                        } else if (tabellModellSoknad.equals((TabellModell) tabell.getModel())) {
                            tabellMeny.add(menyvalgSoknad);
                            menyvalgSoknad.add(menyvalgAksepter);
                            menyvalgSoknad.add(menyvalgAvvis);
                        }
                    } catch (ClassCastException cce) {

                    }
                    tabellMeny.show(e.getComponent(), e.getX(), e.getY());
                }

            }
        });
    }

    /**
     * Setter lyttere for popupmenyen i tabellen.
     */
    public void settOpplyttereForPopupMenyITabell() {

        menyvalgNyBolig.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new ControllerRegistrerBolig(boligliste);
            }

        });
        menyvalgEndreBolig.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Bolig bolig = endreBoligObjekt();
                if (bolig != null) {
                    new ControllerRegistrerBolig(boligliste, bolig);
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
                new ControllerRegistrerUtleier(personliste);
            }
        });
        menyvalgEndrePerson.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //FIXME:
                new ControllerRegistrerUtleier(personliste, (Person) tabellData.get(3));
            }
        });
        menyvalgSlettPerson.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                slettPerson();
            }
        });
        menyvalgForesporsel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //////////////////////////////////////////////////
            }
        });
        menyvalgAksepter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //////////////////////////////////////////////////
            }
        });
        menyvalgAvvis.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //////////////////////////////////////////////////
            }
        });
        menyvalgPubliserToggle.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                toggleBoligPubliserSomAnnonseEllerIkke();
            }
        });
    }

    /**
     * Tømmer tabellen før nytt datasett settes.
     */
    public void tomTabellOgKlargjorForNyttDatasett() {
        tabell.clearSelection();
        tabell.removeAll();
    }

    public Bolig endreBoligObjekt() {

        Bolig valgtObjekt = (Bolig) tabellData.get(valgtRadItabell);
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
            if (!valgtObjekt.isErUtleid()) {
                int valg = Melding.visBekreftelseDialog("Ønsker du virkelig å slette boligen?",
                        "Slette bolig", "Nei");
                if (valg == 0) {
                    try {
                        tabellModellBolig.fireTableRowsDeleted(valgtRadItabell, valgtRadItabell);
                        tabellData.remove(valgtRadItabell);
                        ok = boligliste.remove(valgtObjekt);
                        if (ok) {
                            Melding.visMelding(null, "Bolig med ID " + valgtObjekt.getBoligID() + " er slettet");
                        } else {
                            Melding.visMelding(null, "Bolig med ID " + valgtObjekt.getBoligID() + " ble IKKE slettet");
                        }
                    } catch (ArrayIndexOutOfBoundsException aiobe) {
                    }
                } else {
                }
            }//end if
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
            Melding.visMelding(null, valgtObjekt.getFornavn() + " " + valgtObjekt.getEtternavn()
                    + " er registrert med boliger.\n"
                    + "Kan ikke utføre slettingen.");
        } else {
            int valg = Melding.visBekreftelseDialog("Ønsker du virkelig å slette personen fra systemet?",
                    "Slette person", "Nei");
            if (valg == 0) {
                try {
                    tabellModellPerson.fireTableRowsDeleted(valgtRadItabell, valgtRadItabell);
                    tabellData.remove(valgtRadItabell);
                    ok = personliste.remove(valgtObjekt);
                    if (ok) {
                        Melding.visMelding(null, "Person med ID " + valgtObjekt.getPersonID() + " er slettet");
                    } else {
                        Melding.visMelding(null, "Person med ID " + valgtObjekt.getPersonID() + " ble IKKE slettet");
                    }
                } catch (ArrayIndexOutOfBoundsException aiobe) {
                }
            } else {
            }
        }//end if
    }

    /**
     * Om boligen er publisert så tas den av nett. Om den ikke er publisert så
     * sjekkes det om boligen finnes i annonseregistert. Omden gjør det så åpned
     * vindu for å endre annonsen, ellers lages det ny tom annonse.
     */
    public void toggleBoligPubliserSomAnnonseEllerIkke() {

        int rad;
        Annonse temp;
        Bolig valgtObjekt = (Bolig) tabellData.get(valgtRadItabell);
        if (valgtObjekt != null) {
            if (!valgtObjekt.isErUtleid()) {

                //Sjekker om boligen ligger i annonseregisteret
                Iterator<Annonse> iter = annonseliste.iterator();
                while (iter.hasNext()) {
                    temp = iter.next();
                    if (temp.getBoligID() == valgtObjekt.getBoligID()) {
                        if (temp.isErSynlig()) {
                            temp.setErSynlig(false);
                        } else {
                            new ControllerRegistrerAnnonse(annonseliste, personliste, temp);
                        }
                        return;
                    }//end if
                }//end while

                new ControllerRegistrerAnnonse(annonseliste, personliste, valgtObjekt);
                
            } else {
                //Boligen er utleid og kan ikke legges i annonseregisteret
            }
        }//end if

    }

    /**
     * Oppretter en arraylist med lengde av mottatt datasett. Denne metoden er
     * avhengig av søkeresultatene og må få inn parametere fra toppanel.
     */
    public void settInnDataITabell(Collection innkommendeDatasett, ObjektType objekttypeEnum) {

        TabellModell modellIBruk = null;
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
                    break;
                case BOLIGOBJ:
                    this.objekttype = ObjektType.BOLIGOBJ;
                    tabellModellBolig.fyllTabellMedInnhold(tabellData);
                    tabell.setModel(tabellModellBolig);
                    tabellModellBolig.fireTableStructureChanged();
                    modellIBruk = tabellModellBolig;
                    break;
                case ANNONSEOBJ:
                    this.objekttype = ObjektType.ANNONSEOBJ;
                    tabellModellAnnonse.fyllTabellMedInnhold(tabellData);
                    tabell.setModel(tabellModellAnnonse);
                    tabellModellAnnonse.fireTableStructureChanged();
                    tabell.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
                    tabell.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
                    modellIBruk = tabellModellAnnonse;
                    break;
                case KONTRAKTOBJ:
                    this.objekttype = ObjektType.KONTRAKTOBJ;
                    tabellModellKontrakt.fyllTabellMedInnhold(tabellData);
                    tabell.setModel(tabellModellKontrakt);
                    tabellModellKontrakt.fireTableStructureChanged();
                    modellIBruk = tabellModellKontrakt;
                    break;
                case SOKNADSOBJ:
                    this.objekttype = ObjektType.SOKNADSOBJ;
                    tabellModellSoknad.fyllTabellMedInnhold(tabellData);
                    tabell.setModel(tabellModellSoknad);
                    tabellModellSoknad.fireTableStructureChanged();
                    modellIBruk = tabellModellSoknad;
                    break;
            }
            resizeKolonneBredde();
            vindu.getVenstrepanel().sorterTabellVedOppstart();

            bunnController.settOppTabellData(tabellData, modellIBruk);
            
        } catch (ArrayIndexOutOfBoundsException aiobe) {

        } catch (NullPointerException npe) {

        }

    }

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
        setStyleSheet();
        try {
            switch (objekttype) {
                case PERSONOBJ:
                    valgtObjekt = (Person) tabellData.get(valgtRad);
                    visPersonObjektHTMLOutput(valgtObjekt);
                    break;
                case BOLIGOBJ:
                    valgtObjekt = (Bolig) tabellData.get(valgtRad);
                    visBoligObjektHTMLOutput(valgtObjekt);
                    break;
                case ANNONSEOBJ:
                    valgtObjekt = (Annonse) tabellData.get(valgtRad);
                    visAnnonseObjektHTMLOutput(valgtObjekt);
                    break;
                case KONTRAKTOBJ:
                    valgtObjekt = (Kontrakt) tabellData.get(valgtRad);
                    visKontraktObjektHTMLOutput(valgtObjekt);
                    break;
                case SOKNADSOBJ:
                    valgtObjekt = (Soknad) tabellData.get(valgtRad);
                    visSoknadObjektHTMLOutput(valgtObjekt);
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException aiobe) {

        }
    }

    /**
     * Hjelpemetode som returnerer alle boliger registrert på en eier.
     */
    public ArrayList<Bolig> finnBoligerRegistrertPaaEier(int personID) {

        ArrayList<Bolig> boliger = new ArrayList<>();
        if (personID != -1) {

            Iterator<Bolig> iter2 = boligliste.iterator();
            while (iter2.hasNext()) {
                Bolig temp = iter2.next();
                if (temp.getPersonID() == personID) {
                    boliger.add(temp);
                }
            }//End while
        }
        return boliger;
    }//End method

    /**
     * Tar imot data fra sendObjektFraTabellTilOutput-metoden. Denne metoden
     * skriver ut Personobjekter som HTML til output (JEditorPane)
     *
     * @param valgtObjekt Objektet som skal vises i Output
     */
    public void visPersonObjektHTMLOutput(Object valgtObjekt) {

        output = vindu.getSenterpanel().getEditorPane();

        Person skalVises = (Person) valgtObjekt;
        Megler megler = null;
        Leietaker leietaker = null;
        Utleier utleier = null;

        switch (skalVises.getClass().getSimpleName()) {
            case "Megler":
                megler = (Megler) skalVises;
                break;
            case "Leietaker":
                leietaker = (Leietaker) skalVises;
                break;
            case "Utleier":
                utleier = (Utleier) skalVises;
                break;
        }

        StringBuilder html = new StringBuilder();
        html.append("<h1><u>".concat(skalVises.getClass().getSimpleName()).concat("</u></h1>"));
        html.append("<table id= 'personinfo'>");
        html.append("<tr>");
        html.append("<td><b>ID</b>");
        html.append("</td>");
        html.append("<td>");
        html.append(skalVises.getPersonID());
        html.append("</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td><b>Navn</b>");
        html.append("</td>");
        html.append("<td>".concat(skalVises.getFornavn().concat(" ".concat(skalVises.getEtternavn()))));
        html.append("</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td><b>Tlfnr</b>");
        html.append("</td>");
        html.append("<td>".concat(skalVises.getTelefon()));
        html.append("</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td><b>Epost</b>");
        html.append("</td>");
        html.append("<td>".concat(skalVises.getEpost()));
        html.append("</td>");
        html.append("</tr>");
        html.append("</table>");

        //Hvis objektet er utleier så skal boligene til personen finnes og vises.
        if (utleier != null) {

            ArrayList<Bolig> boliger = finnBoligerRegistrertPaaEier(utleier.getPersonID());
            html.append("<br><br>");
            html.append("<h3><u>".concat(skalVises.getFornavn().concat(" ").concat(skalVises.getEtternavn())));
            html.append(" har følgende boliger:</u></h3>");
            html.append("<table id='boligerPrPerson'>");
            Iterator<Bolig> iter = boliger.iterator();
            while (iter.hasNext()) {
                Bolig temp = iter.next();

                html.append("<tr id='boligerPrPersonRad'>");
                html.append("<td id='boligerPrPersonKol1'>");
                html.append("<b>BoligID:</b> ");
                html.append(temp.getBoligID());
                html.append("</td>");
                html.append("<td id='boligerPrPersonKol2'>");
                html.append("<b>Boligtype:</b> ".concat(temp.getClass().getSimpleName()));
                html.append("<td id='boligerPrPersonKol3'>");
                html.append("<b>Adresse:</b> ".concat(temp.getAdresse()));
                html.append("</td>");
                html.append("<td id='boligerPrPersonKol4'>");
                html.append("<b>Er utleid:</b> ".concat(temp.isErUtleid() ? "Ja" : "Nei"));
                html.append("</td>");
                html.append("</tr>");
            }
            html.append("</table>");
        }//End if

        output.setText(html.toString());
    }

    /**
     * Tar imot data fra sendObjektFraTabellTilOutput-metoden. Denne metoden
     * skriver ut Boligobjekter som HTML til output (JEditorPane)
     *
     * @param valgtObjekt Objektet som skal vises.
     */
    public void visBoligObjektHTMLOutput(Object valgtObjekt) {
        output = vindu.getSenterpanel().getEditorPane();
        Bolig skalVises = (Bolig) valgtObjekt;
        Leilighet leilighet = null;
        Enebolig enebolig = null;

        switch (skalVises.getClass().getSimpleName()) {
            case "Leilighet":
                leilighet = (Leilighet) skalVises;
                break;
            case "Enebolig":
                enebolig = (Enebolig) skalVises;
                break;
        }

        StringBuilder html = new StringBuilder();

        html.append("<h1><u>".concat(skalVises.getClass().getSimpleName()).concat("</u></h1>"));
        html.append("<table id= 'boliginfo'>");
        html.append("<tr>");
        html.append("<td class='boligText'><b>Eier ID</b>");
        html.append("</td>");
        html.append("<td class='boligData'>");
        html.append(skalVises.getPersonID());
        html.append("</td>");
        html.append("<td class='boligText'><b>Bolig ID</b>");
        html.append("</td>");
        html.append("<td class='boligData'>");
        html.append(skalVises.getBoligID());
        html.append("</td>");
        html.append("<td class='boligText'>");
        html.append("</td>");
        html.append("<td class='boligData'>");
        html.append("</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td><b>Adresse</b>");
        html.append("</td>");
        html.append("<td>");
        html.append(skalVises.getAdresse());
        html.append("</td>");
        html.append("<td><b>Postnr</b>");
        html.append("</td>");
        html.append("<td>");
        html.append(skalVises.getPostnummer());
        html.append("</td>");
        html.append("<td><b>Poststed</b>");
        html.append("</td>");
        html.append("<td>");
        html.append(skalVises.getPoststed());
        html.append("</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td><b>Byggeår</b>");
        html.append("</td>");
        html.append("<td>");
        html.append(skalVises.getByggeAr());
        html.append("</td>");
        html.append("<td><b>Boareal</b>");
        html.append("</td>");
        html.append("<td>");
        html.append(skalVises.getBoAreal());
        html.append(" m2");
        html.append("</td>");
        html.append("<td><b>Utleid?</b>");
        html.append("</td>");
        html.append("<td>");
        html.append((skalVises.isErUtleid() == true) ? "Ja" : "Nei");
        html.append("</td>");
        html.append("</tr>");

        if (leilighet != null) {
            html.append("<tr>");
            html.append("<td><b>Etasje</b>");
            html.append("</td>");
            html.append("<td>");
            html.append(leilighet.getEtasjeNr());
            html.append("</td>");
            html.append("<td><b>Bodareal</b>");
            html.append("</td>");
            html.append("<td>");
            String bod = (leilighet.getBodAreal() > 0) ? leilighet.getBodAreal() + " m2" : "Ingen bod";
            html.append(bod);
            html.append("</td>");
            html.append("<td><b>Balkong</b>");
            html.append("</td>");
            html.append("<td>");
            String balkong = (leilighet.getBalkongAreal() > 0) ? leilighet.getBalkongAreal() + " m2" : "Ingen balkong";
            html.append(balkong);
            html.append("</td>");
            html.append("</tr>");
            html.append("<tr>");
            html.append("<td><b>Har heis?</b>");
            html.append("</td>");
            html.append("<td>");
            html.append((leilighet.isHarHeis() == true) ? "Ja" : "Nei");
            html.append("</td>");
            html.append("<td><b>Har garasje?</b>");
            html.append("</td>");
            html.append("<td>");
            html.append((leilighet.isHarGarsje() == true) ? "Ja" : "Nei");
            html.append("</td>");
            html.append("<td><b>Fellesvaskeri?</b>");
            html.append("</td>");
            html.append("<td>");
            html.append((leilighet.isHarFellesvaskeri() == true) ? "Ja" : "Nei");
            html.append("</td>");
            html.append("</tr>");

        } else if (enebolig != null) {

            html.append("<tr>");
            html.append("<td><b>Tomteareal</b>");
            html.append("</td>");
            html.append("<td>");
            html.append(enebolig.getTomtAreal());
            html.append(" m2");
            html.append("</td>");
            html.append("<td><b>Har kjeller?</b>");
            html.append("</td>");
            html.append("<td>");
            html.append((enebolig.isHarKjeller() == true) ? "Ja" : "Nei");
            html.append("</td>");
            html.append("<td><b>Ant. etasjer</b>");
            html.append("</td>");
            html.append("<td>");
            html.append(enebolig.getAntallEtasjer());
            html.append("</td>");
            html.append("</tr>");
            html.append("<tr>");
            html.append("<td>");
            html.append("</td>");
            html.append("<td>");
            html.append("</td>");
            html.append("<td>");
            html.append("</td>");
            html.append("<td>");
            html.append("</td>");
            html.append("<td>");
            html.append("</td>");
            html.append("<td>");
            html.append("</td>");
            html.append("</tr>");
        }
        html.append("</table>");

        BildeFilSti bildefilsti = new BildeFilSti();
        String localImageSrc = bildefilsti.getBoligFremsideBildeHTML(skalVises);

        html.append("<table id='bildetabell'>");
        html.append("<tr id='bilderad'>");
        html.append("<td id='tekstkolonne'>");
        html.append("<b>Beskrivelse av bolig</b><br/>");
        html.append(skalVises.getBeskrivelse());
        html.append("<br/><br/><br/><br/><br/><br/><br/><br/><br/>");
        html.append("</td>");

        html.append("<td id='bildekolonne'>");
        html.append("<img src=\"" + localImageSrc + "\">");
        html.append("</td>");

        html.append("</tr>");
        html.append("</table>");

        output.setText(html.toString());
    }

    /**
     * Viser Annonsene i Output-vinduet.
     *
     * @param valgtObjekt Objektet som skal vises i output.
     * @param vindu Vinduet som skal vise resultatet
     */
    public void visAnnonseObjektHTMLOutput(Object valgtObjekt) {
        output = vindu.getSenterpanel().getEditorPane();
        Annonse skalVises = (Annonse) valgtObjekt;

        Leilighet leilighet = null;
        Enebolig enebolig = null;

        switch (skalVises.getBolig().getClass().getSimpleName()) {
            case "Leilighet":
                leilighet = (Leilighet) skalVises.getBolig();
                break;
            case "Enebolig":
                enebolig = (Enebolig) skalVises.getBolig();
                break;
        }

        StringBuilder html = new StringBuilder();

        html.append("<h1>");
        html.append(skalVises.getBolig().getAdresse().concat(",    ".concat(skalVises.getBolig().getPostnummer().concat(" ".concat(skalVises.getBolig().getPoststed())))));
        html.append("</h1>");
        html.append("<table id= 'annonseinfo'>");
        html.append("<tr>");
        html.append("<td class='boligText'><b>Annonse ID</b>");
        html.append("</td>");
        html.append("<td class='annonseData'>");
        html.append(skalVises.getAnnonseID());
        html.append("</td>");
        html.append("<td class='annonseText'><b>Depositum</b>");
        html.append("</td>");
        html.append("<td class='annonseData'>");
        html.append("kr. ");
        html.append(Konstanter.nf.format(skalVises.getDepositum()));
        html.append(",-");
        html.append("</td>");
        html.append("<td class='annonseText'><b>Pris pr mnd</b>");
        html.append("</td>");
        html.append("<td class='annonseData'>");
        html.append("kr. ");
        html.append(Konstanter.nf.format(skalVises.getUtleiepris()));
        html.append(",-");
        html.append("</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td><b>Byggeår</b>");
        html.append("</td>");
        html.append("<td>");
        html.append(skalVises.getBolig().getByggeAr());
        html.append("</td>");
        html.append("<td><b>Boareal</b>");
        html.append("</td>");
        html.append("<td>");
        html.append(skalVises.getBolig().getBoAreal());
        html.append(" m2");
        html.append("</td>");
        html.append("<td><b>Tilgjenglig fra:</b>");
        html.append("</td>");
        html.append("<td>");
        html.append(Konstanter.df.format(skalVises.getTilgjengeligFraDato().getTime()));
        html.append("</td>");
        html.append("</tr>");

        if (leilighet != null) {
            html.append("<tr>");
            html.append("<td><b>Etasje</b>");
            html.append("</td>");
            html.append("<td>");
            html.append(leilighet.getEtasjeNr());
            html.append("</td>");
            html.append("<td><b>Bodareal</b>");
            html.append("</td>");
            html.append("<td>");
            String bod = (leilighet.getBodAreal() > 0) ? leilighet.getBodAreal() + " m2" : "Ingen bod";
            html.append(bod);
            html.append("</td>");
            html.append("<td><b>Balkong</b>");
            html.append("</td>");
            html.append("<td>");
            String balkong = (leilighet.getBalkongAreal() > 0) ? leilighet.getBalkongAreal() + " m2" : "Ingen balkong";
            html.append(balkong);
            html.append("</td>");
            html.append("</tr>");
            html.append("<tr>");
            html.append("<td><b>Har heis?</b>");
            html.append("</td>");
            html.append("<td>");
            html.append((leilighet.isHarHeis() == true) ? "Ja" : "Nei");
            html.append("</td>");
            html.append("<td><b>Har garasje?</b>");
            html.append("</td>");
            html.append("<td>");
            html.append((leilighet.isHarGarsje() == true) ? "Ja" : "Nei");
            html.append("</td>");
            html.append("<td><b>Fellesvaskeri?</b>");
            html.append("</td>");
            html.append("<td>");
            html.append((leilighet.isHarFellesvaskeri() == true) ? "Ja" : "Nei");
            html.append("</td>");
            html.append("</tr>");

        } else if (enebolig != null) {

            html.append("<tr>");
            html.append("<td><b>Tomteareal</b>");
            html.append("</td>");
            html.append("<td>");
            html.append(enebolig.getTomtAreal());
            html.append(" m2");
            html.append("</td>");
            html.append("<td><b>Har kjeller?</b>");
            html.append("</td>");
            html.append("<td>");
            html.append((enebolig.isHarKjeller() == true) ? "Ja" : "Nei");
            html.append("</td>");
            html.append("<td><b>Ant. etasjer</b>");
            html.append("</td>");
            html.append("<td>");
            html.append(enebolig.getAntallEtasjer());
            html.append("</td>");
            html.append("</tr>");
            html.append("<tr>");
            html.append("<td>");
            html.append("</td>");
            html.append("<td>");
            html.append("</td>");
            html.append("<td>");
            html.append("</td>");
            html.append("<td>");
            html.append("</td>");
            html.append("<td>");
            html.append("</td>");
            html.append("<td>");
            html.append("</td>");
            html.append("</tr>");
        }
        html.append("</table>");

        BildeFilSti bildefilsti = new BildeFilSti();
        String localImageSrc = bildefilsti.getBoligFremsideBildeHTML(skalVises.getBolig());

        html.append("<table id='bildetabell'>");

        html.append("<tr>");
        html.append("<td id='tekstkolonne'>");
        html.append("<table>");
        html.append("<tr>");
        html.append("<td>");
        html.append("<b>Beskrivelse av bolig</b>");
        html.append("</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td>");
        html.append(skalVises.getBolig().getBeskrivelse());
        html.append("</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td>");
        html.append("</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td>");

        if (!skalVises.getEiersKrav().equals("")) {
            html.append("<b>Selgers krav til leietaker</b>");
        }

        html.append("</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td>");
        html.append(skalVises.getEiersKrav());
        html.append("</td>");
        html.append("</tr>");
        html.append("</table>");

        html.append("<td id='bildekolonne'>");
        html.append("<img src=\"" + localImageSrc + "\">");
        html.append("</td>");

        html.append("</tr>");
        html.append("</table>");

        output.setText(html.toString());

    }

    public void visKontraktObjektHTMLOutput(Object valgtObjekt) {

    }

    public void visSoknadObjektHTMLOutput(Object valgtObjekt) {

    }

    /**
     * Definerer CSS-oppsettet for HTML-utskriftene.
     */
    public void setStyleSheet() {

        css.addRule("h1 {text-align: center}");
        css.addRule("h1 {font-size: 16}");
        css.addRule("body {font-family: mono-space}");
        css.addRule("body {border: 1px solid #cecece}");

        //CSS for Bolig
        css.addRule(".boligData {width: 150px}");
        css.addRule(".boligText {width: 90px}");
        css.addRule("#boliginfo {font-size: 12");
        css.addRule("#boliginfo {border-spacing: 0}");
        css.addRule("#boliginfo {border: 1px solid #cecece}");

        //CSS felles for Bolig og Annonser
        css.addRule("#bildetabell {align: right}");
        css.addRule("#tekstkolonne {border: 1px solid #cecece}");
        css.addRule("#tekstkolonne {width:290px}");
        css.addRule("#bildekolonne {border: 1px solid #cecece}");

        //CSS for Annonser
        css.addRule(".annonseData {width: 150px}");
        css.addRule(".annonseText {width: 100px}");
        css.addRule("#annonseinfo {font-size: 12");
        css.addRule("#annonseinfo {border-spacing: 0}");
        css.addRule("#annonseinfo {border: 1px solid #cecece}");

        //CSS for boliger registrert på person
        css.addRule("#boligerPrPerson {border-spacing: 0}");
        css.addRule("#boligerPrPersonKol1 {width: 100px}");
        css.addRule("#boligerPrPersonKol2 {width: 120px}");
        css.addRule("#boligerPrPersonKol3 {width: 200px}");
        css.addRule("#boligerPrPersonKol4 {width: 100px}");

    }
}
