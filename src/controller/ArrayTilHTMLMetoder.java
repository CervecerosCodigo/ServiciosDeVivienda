package controller;

/**
 * Denne klassen inneholder statiske metoder for alt som har med "utskrift" av 
 * objektene til visningsruten, samt tabellen som viser objektene i listeformat.
 * Metoden settOppTabell oppretter en lytter på Tabellen.
 * Metoden setInnDataITabell tar i mot datasettet som skal vises, og hvilket 
 * vindu de skal vises i.
 */


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
    public static void settInnDataITabell(Collection liste, ArkfaneTemplate vindu, int datasettIBruk) {

        String[] kolonneNavn = null;

        switch (datasettIBruk) {
            case Konstanter.PERSONOBJ:
                kolonneNavn = new String[]{"ID", "Fornavn", "Etternavn", "Epost"};
                tabellData = liste.toArray();
                ArrayTilHTMLMetoder.liste = liste;
                ArrayTilHTMLMetoder.datasettIBruk = Konstanter.PERSONOBJ;
                vindu.getVenstrepanel().getTabellModell().fyllTabellMedInnhold(tabellData, kolonneNavn, Konstanter.PERSONOBJ);
                vindu.getVenstrepanel().getTabellModell().fireTableStructureChanged();
                break;
            case Konstanter.BOLIGOBJ:
                kolonneNavn = new String[]{"BoligID", "EierID", "Adresse", "Utleid"};
                tabellData = liste.toArray();
                ArrayTilHTMLMetoder.liste = liste;
                ArrayTilHTMLMetoder.datasettIBruk = Konstanter.BOLIGOBJ;
                vindu.getVenstrepanel().getTabellModell().fyllTabellMedInnhold(tabellData, kolonneNavn, datasettIBruk);
                vindu.getVenstrepanel().getTabellModell().fireTableStructureChanged();
                break;
            case Konstanter.ANNONSEOBJ:
                kolonneNavn = new String[]{"AnnonseID", "Utleiepris", "Utløpsdatao", "Synlig"};
                tabellData = liste.toArray();
                ArrayTilHTMLMetoder.liste = liste;
                ArrayTilHTMLMetoder.datasettIBruk = Konstanter.ANNONSEOBJ;
                vindu.getVenstrepanel().getTabellModell().fyllTabellMedInnhold(tabellData, kolonneNavn, Konstanter.ANNONSEOBJ);
                break;
            case Konstanter.KONTRAKTOBJ:
                kolonneNavn = new String[]{"KontraktID", "BoligID", "LeietakerID", "Varighet"};
                tabellData = liste.toArray();
                ArrayTilHTMLMetoder.liste = liste;
                ArrayTilHTMLMetoder.datasettIBruk = Konstanter.KONTRAKTOBJ;
                vindu.getVenstrepanel().getTabellModell().fyllTabellMedInnhold(tabellData, kolonneNavn, Konstanter.KONTRAKTOBJ);
                break;
            case Konstanter.SOKNADOBJ:
                kolonneNavn = new String[]{"AnnonseID", "Adresse", "Søkers fornavn", "Søkers etternavn"};
                tabellData = liste.toArray();
                ArrayTilHTMLMetoder.liste = liste;
                ArrayTilHTMLMetoder.datasettIBruk = Konstanter.SOKNADOBJ;
                vindu.getVenstrepanel().getTabellModell().fyllTabellMedInnhold(tabellData, kolonneNavn, Konstanter.SOKNADOBJ);
                break;
        }
    }

    /**
     * Tar i mot data og bestemmer hvilken "HTML"-metode som skal kalles.
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
                visBoligObjektHTMLOutput(valgtObjekt, vindu);
                break;
            case Konstanter.ANNONSEOBJ:
                valgtObjekt = (Annonse) tabellData[valgtRad];
                visAnnonseObjektHTMLOutput(valgtObjekt, vindu);
                break;
            case Konstanter.KONTRAKTOBJ:
                valgtObjekt = (Kontrakt) tabellData[valgtRad];
                visKontraktObjektHTMLOutput(valgtObjekt, vindu);
                break;
            case Konstanter.SOKNADOBJ:
                valgtObjekt = (Soknad) tabellData[valgtRad];
                visSoknadObjektHTMLOutput(valgtObjekt, vindu);
                break;
        }

    }

    /**
     * Tar imot data fra sendObjektFraTabellTilOutput-metoden.
     * Denne metoden skriver ut Personobjekter som HTML til output (JEditorPane)
     * @param valgtObjekt
     * @param vindu 
     */
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
        html.append("<h1><u>".concat(skalVises.getClass().getSimpleName()).concat("</u></h1>"));
        html.append("<table id= 'personinfo'>");
        html.append("<tr>");
        html.append("<td>ID");
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
        html.append("<td>Tlfnr");
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

        output.setText(html.toString());
    }


    /**
     * Tar imot data fra sendObjektFraTabellTilOutput-metoden.
     * Denne metoden skriver ut Boligobjekter som HTML til output (JEditorPane)
     * @param valgtObjekt
     * @param vindu 
     */
    public static void visBoligObjektHTMLOutput(Object valgtObjekt, ArkfaneTemplate vindu) {
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
            html.append((skalVises.isErUtleid() == true) ? "Ja": "Nei");            
            html.append("</td>");            
        html.append("</tr>");
        
        if(leilighet != null){
            html.append("<tr>");
                html.append("<td><b>Etasje</b>");
                html.append("</td>");
                html.append("<td>");
                html.append(leilighet.getEtasjeNr());
                html.append("</td>");
                html.append("<td><b>Bodareal</b>");
                html.append("</td>");
                html.append("<td>");
                String bod = (leilighet.getBodAreal()> 0) ? leilighet.getBodAreal()+ " m2" : "Ingen bod";                
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
                html.append((leilighet.isHarHeis() == true) ? "Ja": "Nei");                                
                html.append("</td>");
                html.append("<td><b>Har garasje?</b>");
                html.append("</td>");
                html.append("<td>");
                html.append((leilighet.isHarGarsje() == true) ? "Ja": "Nei");                   
                html.append("</td>");
                html.append("<td><b>Fellesvaskeri?</b>");
                html.append("</td>");
                html.append("<td>");
                html.append((leilighet.isHarFellesvaskeri() == true) ? "Ja": "Nei");                  
                html.append("</td>");            
            html.append("</tr>");

        } else if(enebolig != null){
            
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
                html.append((enebolig.isHarKjeller() == true) ? "Ja": "Nei");                
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
        html.append("<hr class='linjeEn'>");
        
        html.append("<div id='boligbeskrivelse'>");
        html.append("<b><u>Beskrivelse av boligen</u></b><br/>");
        html.append(skalVises.getBeskrivelse());
        html.append("</div>");
        html.append("<div id='boligbilde'>");
        html.append("</div>");
        output.setText(html.toString());
    }
    
    public static void visAnnonseObjektHTMLOutput(Object valgtObjekt, ArkfaneTemplate vindu) {
        
    }    
    public static void visKontraktObjektHTMLOutput(Object valgtObjekt, ArkfaneTemplate vindu) {
        
    }    
    public static void visSoknadObjektHTMLOutput(Object valgtObjekt, ArkfaneTemplate vindu) {
        
    }    
    
    /**
     * Denne metoden definerer CSS-oppsettet for HTML-utskriftene.
     */
    public static void setStyleSheet() {
        css.addRule("h1 {text-align: center}");
        css.addRule("h1 {font-size: 16}");
        css.addRule(".boligData {width: 150px}");
        css.addRule(".boligText {width: 80px}");
        css.addRule("#boliginfo {font-size: 12");
        css.addRule("#boliginfo {border-spacing: 0}");
        css.addRule("#boligbeskrivelse {width: 40%}");        
        css.addRule("#boligbeskrivelse {height: 200px}");        
        css.addRule("#boligbeskrivelse {border-right: 1px solid}");        
        css.addRule("#boligbilde {}");
    }    
}
