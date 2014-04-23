package test.searchklasser;

import java.text.ParseException;
import java.util.HashSet;
import java.util.SortedSet;
import javax.swing.JOptionPane;
import lib.Boligtype;
import model.Annonse;
import search.AnnonseFilter;

/**
 * Brukes til testing av annonsefilter for boligsøker. File:
 * TestAnnonseFilter.java Package: test.search Project: ServiciosDeVivienda Apr
 * 22, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class TestAnnonseFilter {

    HashSet<Annonse> annonseliste;

    public TestAnnonseFilter(HashSet<Annonse> annonseliste) {
        this.annonseliste = annonseliste;
    }

    /**
     * Brukes til å teste filtrering for boligsøker etter angiven parametre.
     */
    public void testBoligSokerEtterParametre() {
        AnnonseFilter search = new AnnonseFilter(annonseliste);
        StringBuilder utskrift = new StringBuilder();

        //Parameter
        String poststed = "Oslo";
        Boligtype boligtype = Boligtype.LEILIGHET;
        int prisMin = 8000;
        int prisMaks = 22000;
        int arealMin = 60;
        int arealMaks = 200;
        boolean harBalkong = false;
        boolean harFellesvask = false;
        boolean harHage = false;
        boolean harKjeller = false;

        HashSet<Annonse> a = search.filtrerEtterParametre(poststed, boligtype, prisMin, prisMaks, arealMin, arealMaks, harBalkong, harFellesvask, harHage, harKjeller);
        for (Annonse ad : a) {
            utskrift.append(ad.toString());
        }
        visMelding("testBoligSokerEtterParametre()", utskrift.toString());
    }
    
    
        /**
     * Denne er til å teste søkeklasser som finnes for boligsøkere
     */
//    private void testBoligSoker() {//Viktig, får ikke kjørt denne lengre etters alle indre metoder i klassen er nå endret til private så som det skal være. Bruk: testBoligSokerEtterParametre() istenden.
//        AnnonseFilter search = new AnnonseFilter(annonseliste);
//        StringBuilder utskrift = new StringBuilder();
//        utskrift.append("Kommenter bort testBoligSøker() i konstruktør for å få bort den testmeldingen.\n\n");
//
//        //Test for antall tilgjengelige annonser
//        utskrift.append("Antall annonser: " + search.getAntallAnnonser());
//
//        //Test for utskrift av alle poststeder med annonser
//        try {
//            SortedSet<String> p = search.getPoststederIAnnonser();
//            utskrift.append("\n\nAlle poststeder med annonser:\n");
//            for (String s : p) {
//                utskrift.append(s).append(", ");
//            }
//            utskrift.append("\n");
//        } catch (ParseException ex) {
//            System.out.println("Parseexception i getPoststederIAnnonser()");
//        }
//
//        //Test for utskrift av alle boligtyper som finnes i annonseregisteret.
//        try {
//            SortedSet<Boligtype> bt = search.getBoligtyperIAnnonser();
//            utskrift.append("\nAlle boligtyper i annonser:\n");
//            for(Boligtype btt : bt ){
//                utskrift.append(btt.toString()).append(", ");
//            }
//            utskrift.append("\n");
//        }catch(ParseException ex){
//            System.out.println("Parsexception i getBoligTyperIAnnonser()");
//        }
//
//        //Test for annonseintervall
////        try {
////            ArrayList<Annonse> annonseIntervall = search.getAnnonseIntervall(2, 4);
////            for(Annonse a : annonseIntervall){
////                System.out.println("Annonseintervall: "+a.toString());
////            }
////        } catch (SearchException ex) {
////            System.out.println("En Search exception har intreffet");
////        }
//        //Test for filtrering etter poststed
//        String poststed = "Lørenskog";
//        search.filtrerEtterPostSted(poststed);
//        HashSet<Annonse> e = search.getFilteredResults();
//        utskrift.append("\nFiltrere etter poststed <<" + poststed + ">>:\n");
//        for (Annonse a : e) {
//            utskrift.append(a.toString()).append("\n");
//        }
//
//        //Test for filtrering etter boligtype
//        search.filtrerEtterBoligType(Boligtype.LEILIGHET);
//        HashSet<Annonse> f = search.getFilteredResults();
//        utskrift.append("\nFiltrere etter boligtype <<" + Boligtype.ENEBOLIG.toString() + ">>:\n");
//        for (Annonse b : f) {
//            utskrift.append(b.toString()).append("\n");
//        }
//
//        //Test for sortering etter pris
//        int prisMin = 8000;
//        int prisMaks = 12000;
//        search.filtrerEtterPrisRange(prisMin, prisMaks);
//        HashSet<Annonse> g = search.getFilteredResults();
//        utskrift.append("\nFiltrere etter prisrange <<" + prisMin + " - " + prisMaks + ">>:\n");
//        for (Annonse c : g) {
//            utskrift.append(c.toString()).append("\n");
//        }
//
//        //Test for sortering etter boareal
//        int kvmMin = 60;
//        int kvmMaks = 80;
//        search.filtrerEtterBoArealRange(kvmMin, kvmMaks);
//        HashSet<Annonse> h = search.getFilteredResults();
//        utskrift.append("\nFiltrere etter boareal range <<" + kvmMin + " - " + kvmMaks + ">>:\n");
//        for (Annonse d : h) {
//            utskrift.append(d.toString()).append("\n");
//        }
//
//        //Test for fellesvaskeri
//        //Filtre over andre checkbokser skal implementeres i like måte. Har foreløpig ikke testet dem alle.
//        search.filtrerEtterFellesvaskeri();
//        HashSet<Annonse> i = search.getFilteredResults();
//        utskrift.append("\nFiltrere etter fellevaskeri\n");
//        for (Annonse fellesvask : i) {
//            utskrift.append(fellesvask.toString()).append("\n");
//        }
//
//        //Viser resultat av filtreringer
//        visMelding("testBoligSoker()", utskrift.toString());
//    }
    

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
