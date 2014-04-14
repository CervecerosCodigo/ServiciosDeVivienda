package controller;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.html.StyleSheet;
import lib.Konstanter;
import model.*;
import view.*;

public class ArrayTilHTMLMetoder {

    private static Object[] tabellData;
    private static int datasettIBruk;
    private static Collection liste;
    private static StyleSheet css;

    public ArrayTilHTMLMetoder() {

    }

    /**
     * Tar i mot det vinduet tabellen skal settes for. Metoden oppretter en
     * lytter på tabellen som finner hvilken rad/objekt som er valgt.
     *
     * @param vindu
     */
    public static void settOppTabell(final ArkfaneTemplate vindu) {
        //Setter en lytter som finner raden som er valgt
        final JTable tabell = vindu.getVenstrepanel().getTable();

        tabell.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                int rad = tabell.getSelectedRow();

                ArrayTilHTMLMetoder.sendObjektFraTabellTilOutput(rad, datasettIBruk, tabellData, vindu);
            }
        });
    }

    /**
     * Oppretter en array med lengde av mottatt datasett. Denne metoden er
     * avhengig av søkeresultatene og må få inn parametere fra toppanel.
     */
    public static void settInnDataITabell(Collection liste, ArkfaneTemplate vindu) {

//        String[] kolonneNavn = new String[]{"BoligID", "EierID", "Adresse", "Utleid"};
//        tabellData = liste.toArray();
//        datasettIBruk = Konstanter.BOLIGOBJ;
//        vindu.getVenstrepanel().getTabellModell().fyllTabellMedInnhold(tabellData, kolonneNavn, datasettIBruk);
//        vindu.getVenstrepanel().getTabellModell().fireTableStructureChanged();
        String[] kolonneNavn = new String[]{"ID", "Fornavn", "Etternavn", "Epost"};
        tabellData = liste.toArray();
        ArrayTilHTMLMetoder.liste = liste;
        datasettIBruk = Konstanter.PERSONOBJ;
        vindu.getVenstrepanel().getTabellModell().fyllTabellMedInnhold(tabellData, kolonneNavn, Konstanter.PERSONOBJ);
        vindu.getVenstrepanel().getTabellModell().fireTableStructureChanged();
//        
//        String[] kolonneNavn = new String[]{"AnnonseID", "Utleiepris", "Utløpsdatao", "Synlig"};        
//        Object[] liste = annonseliste.toArray();
//        datasettIBruk = Konstanter.ANNONSEOBJ;
//        meglerVindu.getVenstrepanel().fyllTabellMedInnhold(liste, kolonneNavn, Konstanter.ANNONSEOBJ);
//        
//        String[] kolonneNavn = new String[]{"KontraktID", "BoligID", "LeietakerID", "Varighet"};        
//        Object[] liste = kontraktliste.toArray();
//        datasettIBruk = Konstanter.KONTRAKTOBJ;
//        meglerVindu.getVenstrepanel().fyllTabellMedInnhold(liste, kolonneNavn, Konstanter.KONTRAKTOBJ);
//        
//        String[] kolonneNavn = new String[]{"AnnonseID", "Adresse", "Søkers fornavn", "Søkers etternavn"};        
//        Object[] liste = soknadsliste.toArray();
//        datasettIBruk = Konstanter.SOKNADOBJ;
//        meglerVindu.getVenstrepanel().fyllTabellMedInnhold(liste, kolonneNavn, Konstanter.SOKNADOBJ);
    }

    /**
     * 
     * @param valgtRad
     * @param datasettIBruk
     * @param tabellData
     * @param vindu 
     */
    public static void sendObjektFraTabellTilOutput(int valgtRad, int datasettIBruk, Object[] tabellData, ArkfaneTemplate vindu) {
        Object valgtObjekt = null;
        css = vindu.getSenterpanel().getStyleSheet();
        ArrayTilHTMLMetoder.setStyleSheet();

        switch (datasettIBruk) {
            case Konstanter.PERSONOBJ:
                valgtObjekt = (Person) tabellData[valgtRad];
                visPersonObjektHTMLOutput(valgtObjekt, vindu);
                break;
            case Konstanter.BOLIGOBJ:
                valgtObjekt = (Bolig) tabellData[valgtRad];
                break;
            case Konstanter.ANNONSEOBJ:
                valgtObjekt = (Annonse) tabellData[valgtRad];
                break;
            case Konstanter.KONTRAKTOBJ:
                valgtObjekt = (Kontrakt) tabellData[valgtRad];
                break;
            case Konstanter.SOKNADOBJ:
                valgtObjekt = (Soknad) tabellData[valgtRad];
                break;
        }

    }

    public static void visPersonObjektHTMLOutput(Object valgtObjekt, ArkfaneTemplate vindu) {

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
        html.append("<h1>".concat(skalVises.getClass().getSimpleName()).concat("</h1>"));
        html.append("<table id= 'personinfo'>");
        html.append("<tr>");
            html.append("<td>ID i systemet");
            html.append("</td>");
            html.append("<td>");
            html.append(skalVises.getPersonID());
            html.append("</td>");
        html.append("</tr>");
        html.append("<tr>");
            html.append("<td>Navn");
            html.append("</td>");
            html.append("<td>".concat(skalVises.getFornavn().concat(" ".concat(skalVises.getEtternavn()))));
            html.append("</td>");
        html.append("</tr>");
        html.append("<tr>");
            html.append("<td>Telefonnummer");
            html.append("</td>");
            html.append("<td>".concat(skalVises.getTelefon()));
            html.append("</td>");
        html.append("</tr>");
        html.append("<tr>");
            html.append("<td>Epost");
            html.append("</td>");
            html.append("<td>".concat(skalVises.getEpost()));
            html.append("</td>");
        html.append("</tr>");                
        html.append("</table>");
        
//        html.append("ID i systemet ");
//        html.append(skalVises.getPersonID());
//        html.append("<br/>");
//        html.append("Navn: ".concat(skalVises.getFornavn().concat(" ".concat(skalVises.getEtternavn()))));
//        html.append("<br/>");
//        html.append("Telefonnummer: ".concat(skalVises.getTelefon()));
//        html.append("<br/>");
//        html.append("Epost: ".concat(skalVises.getEpost()));
        
        output.setText(html.toString());
    }

    public static void setStyleSheet() {
        css.addRule("h1 {text-align: center}");
    }
}
