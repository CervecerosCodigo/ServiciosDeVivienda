package view;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.text.SimpleDateFormat;
import javax.swing.table.AbstractTableModel;
import lib.Konstanter;
import lib.ObjektType;
import model.*;

/**
 * Denne klassen opprettes fra VenstrePanel.java, og definerer tabellens funksjonalitet.
 * Under instansieringen opprettes bare en tom array, og standard kolonnenavn.
 * I det man skal legge inn data i tabellen blir disse overskrevet.
 * Tabellen fylles med data fra ControllerTabellOgOutput.settInnDataIModell()
 * @author espen
 */
public class TabellModell extends AbstractTableModel {

    private Object[] mottattArray;
    private String[] overskrift;
    private ObjektType objekttype;

    public TabellModell() {
        this.overskrift = new String[0];
        this.mottattArray = new Object[0];
    }

    /**
     * Tar i mot en array, kolonneNavnene og konstanten
     * som sier hvilke datasett som skal brukes.
     * @param liste
     * @param kolonneNavn
     */
    public void fyllTabellMedInnhold(Object[] liste, String[] kolonneNavn, ObjektType objekttype) {
        this.mottattArray = liste;
        this.overskrift = kolonneNavn;
        this.objekttype = objekttype;
    }

    /**
     * Returnerer antall rader i tabellen.
     * @return 
     */
    @Override
    public int getRowCount() {
        return mottattArray.length;
    }

    /**
     * Returnerer antall kolonner i tabellen.
     * @return 
     */
    @Override
    public int getColumnCount() {
        return overskrift.length;
    }


    /**
     * For hver rad/kolonne i mottatt Array returnerer metoden innholdet til 
     * den respektive cellen i tabellen som vises.
     * @param rad
     * @param kolonne
     * @return 
     */
    @Override
    public Object getValueAt(int rad, int kolonne) {
        
        if (objekttype == ObjektType.BOLIGOBJ) {
            Bolig bolig = null;
            bolig = (Bolig) mottattArray[rad];
            switch (kolonne) {
                case 0:
                    return bolig.getBoligID();
                case 1:
                    return bolig.getPersonID();
                case 2:
                    return bolig.getAdresse();
                case 3:
                    return bolig.isErUtleid();
            }
        } else if (objekttype == ObjektType.PERSONOBJ) {

            Person person = null;
            person = (Person) mottattArray[rad];
            switch (kolonne) {
                case 0:
                    return person.getPersonID();
                case 1:
                    return person.getFornavn();
                case 2:
                    return person.getEtternavn();
                case 3:
                    return person.getEpost();
            }
        } else if (objekttype == ObjektType.ANNONSEOBJ) {
            Annonse annonse = null;
            annonse = (Annonse) mottattArray[rad];
            switch (kolonne) {
                case 0:
                    return annonse.getAnnonseID();
                case 1:
                    return annonse.getBolig().getAdresse();
                case 2:
                    return "kr. " + Konstanter.nf.format(annonse.getDepositum());
                case 3:
                    return "kr. " + Konstanter.nf.format(annonse.getUtleiepris());
            }
        } else if (objekttype == ObjektType.KONTRAKTOBJ) {
            Kontrakt kontrakt = null;
            kontrakt = (Kontrakt) mottattArray[rad];
            switch (kolonne) {
                case 0:
                    return kontrakt.getAnnonseID();
                case 1:
                    return kontrakt.getBoligID().getBoligID();
                case 2:
                    return kontrakt.getLeietakerID();
                case 3:
                    return kontrakt.getLeietidIMnd();
            }
        } else if (objekttype == ObjektType.SOKNADSOBJ) {
            Soknad soknad = null;
            soknad = (Soknad) mottattArray[rad];
            switch (kolonne) {
                case 0:
                    return soknad.getAnnonseObjekt().getAnnonseID();
                case 1:
                    return soknad.getAnnonseObjekt().getBolig().getAdresse();
                case 2:
                    return soknad.getLeietakerObjekt().getFornavn();
                case 3:
                    return soknad.getLeietakerObjekt().getEtternavn();
            }
        }
        return null;
    }
    
    /**
     * Returnerer kolonnenavnet til den kolonnen indexen refererer til.
     * @param index
     * @return 
     */
    @Override
    public String getColumnName(int index) {
        return overskrift[index];
    }
    
}
