package controller;

/**
 * Denne klassen inneholder statiske metoder for alt som har med "utskrift" av 
 * objektene til visningsruten, samt tabellen som viser objektene i listeformat.
 * Metoden settOppTabell oppretter en lytter på Tabellen.
 * Metoden setInnDataITabell tar i mot datasettet som skal vises, og hvilket 
 * vindu de skal vises i.
 */


import java.awt.Component;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.html.StyleSheet;
import lib.*;
import model.*;
import view.*;

public class ControllerTabellOgOutput {

    private JTable tabell;
    private TabellModell tabellModellBolig;
    private TabellModell tabellModellPerson;
    private TabellModell tabellModellAnnonse;
    private TabellModell tabellModellKontrakt;
    private TabellModell tabellModellSoknad;
    
    private TableRowSorter<TabellModell> sorterer;
    
    private Object[] tabellData;
    private ObjektType objekttype;
    private Collection liste;
    private StyleSheet css;

    public ControllerTabellOgOutput() {

        tabellModellBolig = new TabellModellBolig();
        tabellModellPerson = new TabellModellPerson();
        tabellModellAnnonse = new TabellModellAnnonse();
        tabellModellKontrakt = new TabellModellKontrakt();
        tabellModellSoknad = new TabellModellSoknad();
        sorterer = new TableRowSorter();                
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
        
        tabell.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if( e.getValueIsAdjusting())
                    return;
                int rad = tabell.getSelectedRow();
                rad = tabell.convertRowIndexToModel(rad);

                sendObjektFraTabellTilOutput(rad, objekttype, tabellData, vindu);
            }
        });
    }

    /**
     * Oppretter en array med lengde av mottatt datasett. Denne metoden er
     * avhengig av søkeresultatene og må få inn parametere fra toppanel.
     */
    public void settInnDataITabell(Collection liste, ArkfaneTemplate vindu, ObjektType objekttype) {

        String[] kolonneNavn = null;

        switch (objekttype) {
            case PERSONOBJ:
                this.objekttype = ObjektType.PERSONOBJ;
                this.liste = liste;
                tabellData = liste.toArray();
                tabellModellPerson.fyllTabellMedInnhold(tabellData);
                vindu.getVenstrepanel().getTable().setModel(tabellModellPerson);                
                tabellModellPerson.fireTableStructureChanged();
                break;
            case BOLIGOBJ:
                this.objekttype = ObjektType.BOLIGOBJ;
                this.liste = liste;
                tabellData = liste.toArray();
                tabellModellBolig.fyllTabellMedInnhold(tabellData);
                vindu.getVenstrepanel().getTable().setModel(tabellModellBolig);
                tabellModellBolig.fireTableStructureChanged();
                
                break;
            case ANNONSEOBJ:
                this.objekttype = ObjektType.ANNONSEOBJ;
                tabellData = liste.toArray();
                this.liste = liste;
                tabellModellAnnonse.fyllTabellMedInnhold(tabellData);
                vindu.getVenstrepanel().getTable().setModel(tabellModellAnnonse);                
                tabellModellAnnonse.fireTableStructureChanged();
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
        vindu.getVenstrepanel().settTabellSortering();        
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
     * @param valgtRad
     * @param datasettIBruk
     * @param tabellData
     * @param vindu
     */
    public void sendObjektFraTabellTilOutput(int valgtRad, ObjektType objekttype, Object[] tabellData, ArkfaneTemplate vindu) {
        Object valgtObjekt = null;
        css = vindu.getSenterpanel().getStyleSheet();
        setStyleSheet();
        try{
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
        }catch(ArrayIndexOutOfBoundsException aiobe){
            
        }
    }

    /**
     * Tar imot data fra sendObjektFraTabellTilOutput-metoden.
     * Denne metoden skriver ut Personobjekter som HTML til output (JEditorPane)
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
            html.append("<img src=\""+localImageSrc+"\">");             
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
                    
                    if( !skalVises.getEiersKrav().equals("") ){
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
            html.append("<img src=\""+localImageSrc+"\">");             
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
//        css.addRule("body {background-color: #cecece}");
        css.addRule("body {border: 1px solid #cecece}");
        
        //CSS for Bolig
        css.addRule(".boligData {width: 150px}");
        css.addRule(".boligText {width: 80px}");
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
        css.addRule(".annonseText {width: 80px}");
        css.addRule("#annonseinfo {font-size: 12");
        css.addRule("#annonseinfo {border-spacing: 0}");
        css.addRule("#annonseinfo {border: 1px solid #cecece}");        
    }    
}
