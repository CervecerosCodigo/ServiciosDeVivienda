package controller;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JEditorPane;
import javax.swing.text.html.StyleSheet;
import lib.BildeFilSti;
import lib.Konstanter;
import model.Annonse;
import model.Bolig;
import model.Enebolig;
import model.Kontrakt;
import model.Leietaker;
import model.Leilighet;
import model.Megler;
import model.Person;
import model.Soknad;
import model.Utleier;
import view.ArkfaneTemplate;



public class ControllerOutput {

    
    
    /**
     * Hjelpemetode som returnerer alle boliger registrert på en eier.
     */
    public static ArrayList<Bolig> finnBoligerRegistrertPaaEier(HashSet<Bolig> boligliste, int personID) {

        ArrayList<Bolig> boliger = new ArrayList<>();
        if (personID > 0) {

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
     * Hjelpemetode for å finne informasjon om en boligeier.
     *
     * @param personID
     * @return
     */
    public static Person finnPersonInformasjon(HashSet<Person> personliste, int personID) {
        Iterator<Person> iter = personliste.iterator();
        Person temp = null;
        while (iter.hasNext()) {
            temp = iter.next();
            if (temp.getPersonID() == personID) {
                return temp;
            }
        }
        return null;
    }    

    
    /**
     * Hjelpemetode som finner alle annonser en bestem person har vist interesse
     * for
     *
     * @param personID
     * @return
     */
    public static  ArrayList<Annonse> finnAnnonserAvInteressePerLeietaker(HashSet<Soknad> soknadsliste, int personID) {
        ArrayList<Annonse> annonseAvInteresse = new ArrayList<>();

        if (personID > 0) {
            Iterator<Soknad> iter = soknadsliste.iterator();
            while (iter.hasNext()) {
                Soknad temp = iter.next();
                if (temp.getLeietakerID() == personID) {
                    annonseAvInteresse.add(temp.getAnnonseObjekt());
                }
            }
            return annonseAvInteresse;
        }
        return null;
    }    
    
    /**
     * Tar imot data fra sendObjektFraTabellTilOutput-metoden. Denne metoden
     * skriver ut Personobjekter som HTML til output (JEditorPane)
     *
     * @param valgtObjekt Objektet som skal vises i Output
     */
    public static void visPersonObjektHTMLOutput(JEditorPane output, ArkfaneTemplate vindu, Object valgtObjekt, HashSet<Person> personliste, HashSet<Bolig> boligliste, HashSet<Soknad> soknadsliste) {

        output = vindu.getSenterpanel().getEditorPane();

        Person skalVises = (Person) valgtObjekt;
        Megler megler = null;
        Leietaker leietaker = null;
        Utleier utleier = null;

        if (skalVises instanceof Megler) {
            megler = (Megler) skalVises;
        }

        if (skalVises instanceof Leietaker) {
            leietaker = (Leietaker) skalVises;

        }

        if (skalVises instanceof Utleier) {
            utleier = (Utleier) skalVises;
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

        if (leietaker != null) {
            html.append("<tr>");
            html.append("<td>").append("<b>Yrke</b>");
            html.append("</td>");
            html.append("<td>").append(leietaker.getYrke());
            html.append("</td>");
            html.append("</tr>");

            html.append("<tr>");
            html.append("<td>").append("<b>Arbeidsforhold</b>");
            html.append("</td>");
            html.append("<td>").append(leietaker.getArbeidsforhold());
            html.append("</td>");
            html.append("</tr>");

            html.append("<tr>");
            html.append("<td>").append("<b>Født</b>");
            html.append("</td>");
            html.append("<td>").append(leietaker.getFodselsAr());
            html.append("</td>");
            html.append("</tr>");
        }
        html.append("</table>");

        //Hvis objektet er utleier så skal boligene til personen finnes og vises.
        if (utleier != null) {

            ArrayList<Bolig> boliger = finnBoligerRegistrertPaaEier(boligliste, utleier.getPersonID());
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

        //Hvis objektet er Leietaker/boligsøker så skal boligene til personen finnes og vises.
        if (leietaker != null) {

            ArrayList<Annonse> annonseAvInteresse = finnAnnonserAvInteressePerLeietaker(soknadsliste, leietaker.getPersonID());

            html.append("<br><br>");
            if (annonseAvInteresse != null) {
                html.append("<h3><u>".concat(leietaker.getFornavn().concat(" ").concat(leietaker.getEtternavn())));
                html.append(" har vist interesse i følgende annonser:</u></h3>");
                html.append("<table id='annonserAvInteresse'>");

                Iterator<Annonse> iter = annonseAvInteresse.iterator();
                while (iter.hasNext()) {
                    Annonse temp = iter.next();

                    html.append("<tr id='annonserAvInteresseRad'>");
                    html.append("<td id='annonserAvInteresseKol1'>");
                    html.append("<b>AnnonseID:</b> ").append(temp.getAnnonseID());
                    html.append("</td>");
                    html.append("<td id='annonserAvInteresseKol2'>");
                    html.append("<b>Adresse:</b> ").append(temp.getBolig().getAdresse());
                    html.append("</td>");
                    html.append("<td id='annonserAvInteresseKol3'>");
                    html.append("<b>Utleierpris</b> ").append(temp.getUtleiepris());
                    html.append("</td>");
                    html.append("</tr>");
                }
                html.append("</table>");

            }
        }//End if

        output.setText(html.toString());
    }

    /**
     * Tar imot data fra sendObjektFraTabellTilOutput-metoden. Denne metoden
     * skriver ut Boligobjekter som HTML til output (JEditorPane)
     *
     * @param valgtObjekt Objektet som skal vises.
     */
    public static void visBoligObjektHTMLOutput(Object valgtObjekt, JEditorPane output, ArkfaneTemplate vindu, HashSet<Bolig> boligliste) {
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
    public static void visAnnonseObjektHTMLOutput(Object valgtObjekt, JEditorPane output, ArkfaneTemplate vindu, HashSet<Annonse> annonseliste) {
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

    public static void visKontraktObjektHTMLOutput(Object valgtObjekt, JEditorPane output, ArkfaneTemplate vindu) {
        output = vindu.getSenterpanel().getEditorPane();
        Kontrakt kontrakt = (Kontrakt) valgtObjekt;
        StringBuilder html = new StringBuilder();

        html.append("<h1>");
        html.append("Kontrakt");
        html.append("</h1>");
        html.append("<table id= 'kontraktinfo'>");

        html.append("<tr>");
        html.append("<td class='kontraktText'><b>Boligadresse</b>");
        html.append("</td>");
        html.append("<td class='kontraktData'>");
        html.append(kontrakt.getAnnonse().getBolig().getAdresse());
        html.append("</td>");
        html.append("<td class='kontraktText'><b>Postnummer</b>");
        html.append("</td>");
        html.append("<td class='kontraktData'>");
        html.append(kontrakt.getAnnonse().getBolig().getPostnummer());
        html.append("</td>");
        html.append("<td class='kontraktText'><b>Poststed</b>");
        html.append("</td>");
        html.append("<td class='kontraktData'>");
        html.append(kontrakt.getAnnonse().getBolig().getPoststed());
        html.append("</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<td><b>Utleiepris</b>");
        html.append("</td>");
        html.append("<td>");
        html.append(Konstanter.nf.format(kontrakt.getUtleiePris())).append(" ,-");
        html.append("</td>");
        html.append("<td><b>Depositum</b>");
        html.append("</td>");
        html.append("<td>");
        html.append(Konstanter.nf.format(kontrakt.getDepositum())).append(" ,-");
        html.append("</td>");
        html.append("<td><b>Kontraktvarighet</b>");
        html.append("</td>");
        html.append("<td>");
        html.append(kontrakt.getLeietidIMnd()).append(" mnd.");
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

        html.append("<tr>");
        html.append("<td><b><u>Leietaker</u></b>");
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

        html.append("<tr>");
        html.append("<td>");
        html.append("<b>Navn</b>");
        html.append("</td>");
        html.append("<td>");
        html.append(kontrakt.getLeietaker().getFornavn()).append(" ").append(kontrakt.getLeietaker().getEtternavn());
        html.append("</td>");
        html.append("<td>");
        html.append("<b>Epost</b>");
        html.append("</td>");
        html.append("<td>");
        html.append(kontrakt.getLeietaker().getEpost());
        html.append("</td>");
        html.append("<td>");
        html.append("<b>TelefonNr</b>");
        html.append("</td>");
        html.append("<td>");
        html.append(kontrakt.getLeietaker().getTelefon());
        html.append("</td>");
        html.append("</tr>");

        html.append("</table>");

        html.append("<br>");
        html.append("<b><u>Utleievilkår:</u></b><br>");
        html.append("<div id='vilkaartekst'>");
        html.append(kontrakt.getAnnonse().getEiersKrav());
        html.append("</div>");
        html.append("<br><br><br><br>");
        
        html.append("<table>");
        html.append("<tr>");
        html.append("<td class='kontraktText'>");
        html.append("</td>");
        html.append("<td class='kontraktData'>").append("________________________________");
        html.append("</td>");
        html.append("<td class='kontraktText'>");
        html.append("</td>");
        html.append("<td class='kontraktData'>").append("________________________________");
        html.append("</td>");
        html.append("</tr>");
        
        html.append("<tr>");
        html.append("<td>");
        html.append("</td>");
        html.append("<td>").append("Leietakers underskrift");
        html.append("</td>");
        html.append("<td>");
        html.append("</td>");
        html.append("<td>").append("Meglers underskrift");
        html.append("</td>");
        html.append("</tr>");
        html.append("</table>");

        
        output.setText(html.toString());
    }

    public static void visSoknadObjektHTMLOutput(Object valgtObjekt, JEditorPane output, ArkfaneTemplate vindu, HashSet<Soknad> soknadsliste, HashSet<Person> personliste) {
        output = vindu.getSenterpanel().getEditorPane();
        Soknad skalVises = (Soknad) valgtObjekt;
        Leietaker leietaker = skalVises.getLeietakerObjekt();
        Annonse annonse = skalVises.getAnnonseObjekt();
        Bolig bolig = annonse.getBolig();
        Person utleier = finnPersonInformasjon(personliste, bolig.getPersonID());

        StringBuilder html = new StringBuilder();

        html.append("<h1><b><u>").append("Søknad for annonse ").append(annonse.getAnnonseID()).append("</u></b></h1>");
        html.append("<table id='soknadinfo'>");

        html.append("<tr>");
        html.append("<td class='soknadText'><b><u>Bolig</u></b>");
        html.append("</td>");
        html.append("<td class='soknadData'>");
        html.append("</td>");
        html.append("<td class='soknadText'><b></b>");
        html.append("</td>");
        html.append("<td class='soknadData'>");
        html.append("</td>");
        html.append("<td class='soknadText'><b></b>");
        html.append("</td>");
        html.append("<td class='soknadData'>");
        html.append("</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<td><b>Adresse:</b>");
        html.append("</td>");
        html.append("<td>").append(bolig.getAdresse());
        html.append("</td>");
        html.append("<td><b>Postnr:</b>");
        html.append("</td>");
        html.append("<td>").append(bolig.getPostnummer());
        html.append("</td>");
        html.append("<td><b>Poststed</b>");
        html.append("</td>");
        html.append("<td>").append(bolig.getPoststed());
        html.append("</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<td><b>Boligeier:</b>");
        html.append("</td>");
        html.append("<td>").append(utleier.getFornavn()).append(" ").append(utleier.getEtternavn());
        html.append("</td>");
        html.append("<td><b>Epost:</b>");
        html.append("</td>");
        html.append("<td>").append(utleier.getEpost());
        html.append("</td>");
        html.append("<td><b>Telefonnr:</b>");
        html.append("</td>");
        html.append("<td>").append(utleier.getTelefon());
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

        html.append("<tr>");
        html.append("<td><b><u>Leietaker</u></b>");
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

        html.append("<tr>");
        html.append("<td><b>Navn:</b>");
        html.append("</td>");
        html.append("<td>").append(leietaker.getFornavn()).append(" ").append(leietaker.getEtternavn());
        html.append("</td>");
        html.append("<td><b>Epost:</b>");
        html.append("</td>");
        html.append("<td>").append(leietaker.getEpost());
        html.append("</td>");
        html.append("<td><b>Telefonnr:</b>");
        html.append("</td>");
        html.append("<td>").append(leietaker.getTelefon());
        html.append("</td>");
        html.append("</tr>");
        html.append("</tr>");
        html.append("<td><b>Sivilstatus:</b>");
        html.append("</td>");
        html.append("<td>").append(leietaker.getSivilistatus());
        html.append("</td>");
        html.append("<td><b>Yrke:</b>");
        html.append("</td>");
        html.append("<td>").append(leietaker.getYrke());
        html.append("</td>");
        html.append("<td><b>Arbeidsforhold:</b>");
        html.append("</td>");
        html.append("<td>").append(leietaker.getArbeidsforhold());
        html.append("</td>");
        html.append("</tr>");

        html.append("</table>");
        html.append("<br>");
        html.append("<b><u>Søknadstekst:</u></b><br>");
        html.append("<div id='soknadstekst'>");
        html.append(leietaker.getSoknadsTekst());
        html.append("</div>");
//        html.append(leietaker.getSoknadsTekst());

        output.setText(html.toString());
    }

    /**
     * Definerer CSS-oppsettet for HTML-utskriftene.
     * @param css 
     */
    public static void setStyleSheet(StyleSheet css) {

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

        //CSS for Søknader
        css.addRule(".soknadData {width: 170px}");
        css.addRule(".soknadText {width: 80px}");
        css.addRule("#soknadinfo {font-size: 12");
        css.addRule("#soknadinfo {border-spacing: 0}");
        css.addRule("#soknadinfo {border: 1px solid #cecece}");
        css.addRule("#soknadstekst {width: 300px}");
        css.addRule("#soknadstekst {height: 100px}");
        css.addRule("#soknadstekst {border: 1px solid #cecece}");

        //CSS for Søknader
        css.addRule(".kontraktData {width: 170px}");
        css.addRule(".kontraktText {width: 80px}");
        css.addRule("#kontraktinfo {font-size: 12");
        css.addRule("#kontraktinfo {border-spacing: 0}");
        css.addRule("#kontraktinfo {border: 1px solid #cecece}");
        css.addRule("#vilkaartekst {width: 300px}");
        css.addRule("#vilkaartekst {height: 100px}");
        css.addRule("#vilkaartekst {border: 1px solid #cecece}");

        //CSS for boliger registrert på person
        css.addRule("#boligerPrPerson {border-spacing: 0}");
        css.addRule("#boligerPrPersonKol1 {width: 100px}");
        css.addRule("#boligerPrPersonKol2 {width: 120px}");
        css.addRule("#boligerPrPersonKol3 {width: 200px}");
        css.addRule("#boligerPrPersonKol4 {width: 100px}");

        //CSS for annonser en person har vist interesse for
        css.addRule("#annonserAvInteresse {border-spacing: 0}");
        css.addRule("#annonserAvInteresseKol1 {width: 100px}");
        css.addRule("#annonserAvInteresseKol2 {width: 120px}");
        css.addRule("#annonserAvInteresseKol3 {width: 200px}");

    }    
}

