package controller;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi



import controller.registrer.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import model.*;
import view.ArkfaneTemplate;

/**
 * Denne klassen er kontrolleren til bunnpanelet i både meglerVindu og annonseVindu.
 * 
 * @author espen
 */
public class ControllerBunnPanel {

    private KnappeLytter lytter;
    private HashSet<Person> personliste;
    private HashSet<Bolig> boligliste;
    private HashSet<Annonse> annonseliste;
    private ArrayList<Object> tabellData;
    private TabellModell modell;

    public ControllerBunnPanel(HashSet<Bolig> boligliste, HashSet<Person> personliste, HashSet<Annonse> annonseliste) {

        this.boligliste = boligliste;
        this.personliste = personliste;
        this.annonseliste = annonseliste;
        
    }

    /**
     * Setter knappelytter, én for hvert av meglerVindu og annonseVindu.
     * @param vindu 
     */
    public void settKnappeLytter(ArkfaneTemplate vindu) {
        vindu.getBunnpanel().addKnappeLytter(lytter = new KnappeLytter(vindu));
    }

    /**
     * Tar i mot tabellData fra Tabellen.
     * @param tabellData
     * @param modell 
     */
    public void settOppTabellData(ArrayList<Object> tabellData, TabellModell modell){
        this.tabellData = tabellData;
        this.modell = modell;
    }
    
    
    /**
     * private lytteklasse for knappene i bunnpanelet.
     */
    class KnappeLytter implements ActionListener {

        ArkfaneTemplate vindu;
        int raderITabell;
        JTable tabell;

        public KnappeLytter(ArkfaneTemplate vindu) {
            this.vindu = vindu;
            tabell = vindu.getVenstrepanel().getTable();
        }


        
        public int finnValgtRadITabell() {
            try {
                int rad = tabell.getSelectedRow();
                rad = tabell.convertRowIndexToModel(rad);
                return rad;
            } catch (ArrayIndexOutOfBoundsException aiobe) {

            }
            return 0;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            raderITabell = tabell.getModel().getRowCount();


            if (e.getSource().equals(vindu.getBunnpanel().getEndreKnapp())) {
                try {
                    int valgtRad = finnValgtRadITabell();
                    if (tabell.getModel() instanceof TabellModellPerson) {
                        new ControllerRegistrerPerson((HashSet<Person>) personliste);
                    }
                    if (tabell.getModel() instanceof TabellModellBolig) {
                        new ControllerRegistrerBolig(boligliste, (Bolig)tabellData.get(valgtRad));
                    }
                    if (tabell.getModel() instanceof TabellModellAnnonse) {
                        
                    }
                } catch (ArrayIndexOutOfBoundsException aoibe) {
                    System.out.println("Feil");
                }

            } else if (e.getSource().equals(vindu.getBunnpanel().getTilbakeKnapp())) {
                vindu.getVenstrepanel().getTable().changeSelection(tabell.getSelectedRow() - 1, 0, false, false);

            } else if (e.getSource().equals(vindu.getBunnpanel().getFremKnapp())) {
                int valgtRad = vindu.getVenstrepanel().getTable().getSelectedRow();

                if (valgtRad + 1 < vindu.getVenstrepanel().getTable().getRowCount()) {
                    vindu.getVenstrepanel().getTable().changeSelection(tabell.getSelectedRow() + 1, 0, false, false);
                }
            }
        }

    }
}
