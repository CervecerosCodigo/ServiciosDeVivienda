package controller;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.util.Collection;
import javax.swing.JEditorPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import lib.Konstanter;
import model.*;
import view.*;

public class ArrayTilHTMLMetoder {

    private static Object[] tabellData;
    private static int datasettIBruk;

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
     * Oppretter en array med lenge av mottatt datasett. Denne metoden er
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

    public static void sendObjektFraTabellTilOutput(int valgtRad, int datasettIBruk, Object[] tabellData, ArkfaneTemplate vindu) {
        Object valgtObjekt = null;

        switch (datasettIBruk) {
            case Konstanter.PERSONOBJ:
                valgtObjekt = (Person) tabellData[valgtRad];
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
        JEditorPane output = vindu.getSenterpanel().getEditorPane();
        //Document dok = meglerVindu.getSenterpanel().getHTMLDokument();

        output.setText(valgtObjekt.toString());

    }
}
