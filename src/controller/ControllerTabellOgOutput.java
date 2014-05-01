package controller;

/**
 * Denne klassen inneholder statiske metoder for alt som har med "utskrift" av
 * objektene til visningsruten, samt tabellen som viser objektene i listeformat.
 * Metoden settOppTabell oppretter en lytter på Tabellen. Metoden
 * setInnDataITabell tar i mot datasettet som skal vises, og hvilket vindu de
 * skal vises i.
 */
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
    private LinkedHashSet<Soknad> soknadsliste;

    private DefaultTableCellRenderer rightRenderer;

    private Object[] tabellData;
    private ObjektType objekttype;
    private Collection liste;
    private StyleSheet css;

    private JTable tabell;
    private TabellModell tabellModellBolig, tabellModellPerson, tabellModellAnnonse,
            tabellModellKontrakt, tabellModellSoknad;
    private JPopupMenu tabellMeny;
    private JMenu menyvalgBolig, menyvalgPerson, menyvalgAnnonse, menyvalgSoknad;
    private JMenuItem menyvalgNyPerson, menyvalgEndrePerson, menyvalgSlettPerson,
            menyvalgNyBolig, menyvalgEndreBolig, menyvalgSlettBolig,
            menyvalgForesporsel,
            menyvalgAksepter, menyvalgAvvis;
    private JCheckBoxMenuItem menyvalgPubliserToggle;

    public ControllerTabellOgOutput(HashSet<Person> personliste, HashSet<Bolig> boligliste,
            HashSet<Annonse> annonseliste, HashSet<Kontrakt> kontraktliste,
            LinkedHashSet<Soknad> soknadsliste) {

        this.personliste = personliste;
        this.boligliste = boligliste;
        this.annonseliste = annonseliste;
        this.kontraktliste = kontraktliste;
        this.soknadsliste = soknadsliste;

        tabellModellBolig = new TabellModellBolig();
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
        menyvalgPubliserToggle = new JCheckBoxMenuItem("Publiser bolig");
    }

    /**
     * Tar i mot det vinduet tabellen skal settes for. Metoden oppretter en
     * lytter på tabellen som finner hvilken rad/objekt som er valgt.
     * valueChanged-metoden sender også valgt objekt til output.
     *
     * @param vindu
     */
    public void settOppTabellLytter(final ArkfaneTemplate vindu) {
        //Setter en lytter som finner raden som er valgt
        tabell = vindu.getVenstrepanel().getTable();
        lyttereForPopupMenyITabell();
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

                    sendObjektFraTabellTilOutput(rad, objekttype, tabellData, vindu);
                } catch (ArrayIndexOutOfBoundsException aiobe) {

                }

            }
        });

        /**
         * Lytter for dobbelklikk i tabellen.
         */
        tabell.addMouseListener(new MouseAdapter() {

            TabellModell modell = null;

            @Override
            public void mouseClicked(MouseEvent e) {
                JTable temp = (JTable) e.getSource();
                if (e.getClickCount() == 2) {
                    int rad = temp.getSelectedRow();
                    rad = tabell.convertRowIndexToModel(rad);

                    if (tabellModellBolig.equals((TabellModell) tabell.getModel())) {
                        System.out.println("Bolig");
                    } else if (tabellModellPerson.equals((TabellModell) tabell.getModel())) {
                        System.out.println("Person");
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

    public void lyttereForPopupMenyITabell() {

        menyvalgNyBolig.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Du oppretter ny Bolig!");
            }

        });
        menyvalgNyPerson.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Du oppretter ny Person!");
            }
        });

    }

    public void tomTabellOgKlargjorForNyttDatasett() {
        tabell.clearSelection();
        tabell.removeAll();
    }

    /**
     * Oppretter en array med lengde av mottatt datasett. Denne metoden er
     * avhengig av søkeresultatene og må få inn parametere fra toppanel.
     */
    public void settInnDataITabell(Collection liste, ArkfaneTemplate vindu, ObjektType objekttype) {

        try {
            switch (objekttype) {
                case PERSONOBJ:
                    this.objekttype = ObjektType.PERSONOBJ;
                    this.liste = liste;
                    tabellData = liste.toArray();
                    tabellModellPerson.fyllTabellMedInnhold(tabellData);
                    tabell.setModel(tabellModellPerson);
                    tabellModellPerson.fireTableStructureChanged();
                    break;
                case BOLIGOBJ:
                    this.objekttype = ObjektType.BOLIGOBJ;
                    this.liste = liste;
                    tabellData = liste.toArray();
                    tabellModellBolig.fyllTabellMedInnhold(tabellData);
                    tabell.setModel(tabellModellBolig);
                    tabellModellBolig.fireTableStructureChanged();
                    break;
                case ANNONSEOBJ:
                    this.objekttype = ObjektType.ANNONSEOBJ;
                    tabellData = liste.toArray();
                    this.liste = liste;
                    tabellModellAnnonse.fyllTabellMedInnhold(tabellData);
                    vindu.getVenstrepanel().getTable().setModel(tabellModellAnnonse);
                    tabellModellAnnonse.fireTableStructureChanged();
                    tabell.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
                    tabell.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
                    break;
                case KONTRAKTOBJ:
                    this.objekttype = ObjektType.KONTRAKTOBJ;
                    tabellData = liste.toArray();
                    this.liste = liste;
                    tabellModellKontrakt.fyllTabellMedInnhold(tabellData);
                    vindu.getVenstrepanel().getTable().setModel(tabellModellKontrakt);
                    tabellModellKontrakt.fireTableStructureChanged();
                    break;
                case SOKNADSOBJ:
                    this.objekttype = ObjektType.SOKNADSOBJ;
                    tabellData = liste.toArray();
                    this.liste = liste;
                    tabellModellSoknad.fyllTabellMedInnhold(tabellData);
                    vindu.getVenstrepanel().getTable().setModel(tabellModellSoknad);
                    tabellModellSoknad.fireTableStructureChanged();
                    break;
            }

            resizeKolonneBredde();

            vindu.getVenstrepanel().sorterTabellVedOppstart();
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
     * Tar i mot data og bestemmer hvilken "HTML"-metode som skal kalles.
     *
     * @param valgtRad
     * @param datasettIBruk
     * @param tabellData
     * @param vindu
     */
    public void sendObjektFraTabellTilOutput(int valgtRad, ObjektType objekttype, Object[] tabellData, ArkfaneTemplate vindu) {
        Object valgtObjekt = null;
        css = vindu.getSenterpanel().getStyleSheet();
        setStyleSheet();
        try {
            switch (objekttype) {
                case PERSONOBJ:
                    valgtObjekt = (Person) tabellData[valgtRad];
                    visPersonObjektHTMLOutput(valgtObjekt, vindu);
                    break;
                case BOLIGOBJ:
                    valgtObjekt = (Bolig) tabellData[valgtRad];
                    visBoligObjektHTMLOutput(valgtObjekt, vindu);
                    break;
                case ANNONSEOBJ:
                    valgtObjekt = (Annonse) tabellData[valgtRad];
                    visAnnonseObjektHTMLOutput(valgtObjekt, vindu);
                    break;
                case KONTRAKTOBJ:
                    valgtObjekt = (Kontrakt) tabellData[valgtRad];
                    visKontraktObjektHTMLOutput(valgtObjekt, vindu);
                    break;
                case SOKNADSOBJ:
                    valgtObjekt = (Soknad) tabellData[valgtRad];
                    visSoknadObjektHTMLOutput(valgtObjekt, vindu);
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException aiobe) {

        }
    }

    /**
     * //Metoder for SøkeGUI// Tar i mot epost fra GUI. Kaller så opp
     * hjelpemetoden finnePersonIDBasertPaaEpost for å få tak i personID til
     * personen, og deretter leter etter denne personens boliger.
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
     * @param valgtObjekt
     * @param vindu
     */
    public void visPersonObjektHTMLOutput(Object valgtObjekt, ArkfaneTemplate vindu) {

        JEditorPane output = vindu.getSenterpanel().getEditorPane();
        Collection<Person> personliste = liste;

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

        if (utleier != null) {

            ArrayList<Bolig> boliger = finnBoligerRegistrertPaaEier(utleier.getPersonID());
            String tabellRad = new String("");
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
     * @param valgtObjekt
     * @param vindu
     */
    public void visBoligObjektHTMLOutput(Object valgtObjekt, ArkfaneTemplate vindu) {
        JEditorPane output = vindu.getSenterpanel().getEditorPane();
        Collection<Bolig> boligliste = liste;
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

//        String localImageSrc = ControllerTabellOgOutput.class.getClassLoader().getSystemResource("77_1655132553.jpg").toString();
        BildeFilSti bildefilsti = new BildeFilSti();
        String localImageSrc = bildefilsti.getBoligFremsideBildeHTML(skalVises);
        //new Melding("Melding", "Kommenter vekk linje 374 i ControllerTabellOutput.java for å få vekk denne\n Skal fjerne meldingen etter at ha testet BildeFilSti på Windows.\n\n"+localImageSrc); 

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

    public void visAnnonseObjektHTMLOutput(Object valgtObjekt, ArkfaneTemplate vindu) {
        JEditorPane output = vindu.getSenterpanel().getEditorPane();
        Collection<Annonse> annonseliste = liste;
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
        //html.append("<hr class='linjeEn'>");

//        String localImageSrc = ControllerTabellOgOutput.class.getClassLoader().getSystemResource("77_1655132553.jpg").toString();
        BildeFilSti bildefilsti = new BildeFilSti();
        String localImageSrc = bildefilsti.getBoligFremsideBildeHTML(skalVises.getBolig());
        //new Melding("Melding", "Kommenter vekk linje 374 i ControllerTabellOutput.java for å få vekk denne\n Skal fjerne meldingen etter at ha testet BildeFilSti på Windows.\n\n"+localImageSrc);         

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

    public void visKontraktObjektHTMLOutput(Object valgtObjekt, ArkfaneTemplate vindu) {

    }

    public void visSoknadObjektHTMLOutput(Object valgtObjekt, ArkfaneTemplate vindu) {

    }

    /**
     * Denne metoden definerer CSS-oppsettet for HTML-utskriftene.
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
