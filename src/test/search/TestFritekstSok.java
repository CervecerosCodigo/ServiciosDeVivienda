package test.search;

import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.JOptionPane;
import model.Annonse;
import model.Bolig;
import search.FreeTextSearch;

/**
 * Klassen er for å teste fritekstsøk som brukes for megler. File:
 * TestFritekstSok.java Package: test.search Project: ServiciosDeVivienda Apr
 * 22, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class TestFritekstSok {

    public TestFritekstSok() {
    }

    /**
     * Søker igjennom boligliste for et resultat og skriver ut det i en medling.
     * @param boligliste
     * @param soketekst 
     */
    public void testSokBoligListe(HashSet<Bolig> boligliste, String soketekst) {
        FreeTextSearch freeTextSearch = new FreeTextSearch();
        StringBuilder utskrift = new StringBuilder();
        ArrayList<Bolig> testList = freeTextSearch.searchForPattern(boligliste, soketekst);
        utskrift.append("Søkeresultat for " + soketekst + "\n");
        for (Bolig b : testList) {
            utskrift.append(b.toString()).append("\n");
        }
        visMelding("testSokBoligListe(HashSet<Bolig> boligliste, String soketekst)", utskrift.toString());
    }

    /**
     * Søker igjennom liste med annonser og skriver ut resultat i en melding.
     * @param annonseliste
     * @param soketekst 
     */
    public void testSokAnnonseListe(HashSet<Annonse> annonseliste, String soketekst) {
        FreeTextSearch freeTextSearch = new FreeTextSearch();
        StringBuilder utskrift = new StringBuilder();
        ArrayList<Annonse> testList = freeTextSearch.searchForPattern(annonseliste, soketekst);
        utskrift.append("Søkeresultat for " + soketekst + "\n");
        for (Annonse b : testList) {
            utskrift.append(b.toString()).append("\n");
        }
        visMelding("testSokAnnonseListe(HashSet<Annonse> annonseliste, String soketekst)", utskrift.toString());
    }

    /**
     * Brukes sammen med testing ettersom det ble litt for rotede å søke etter
     * tekst i terminal.
     *
     * @param metode
     * @param melding
     */
    private void visMelding(String metode, String melding) {
        JOptionPane.showMessageDialog(null, melding, metode, JOptionPane.INFORMATION_MESSAGE);
    }
}
