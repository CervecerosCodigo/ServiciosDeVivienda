package controller;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi

import controller.registrer.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import model.*;
import view.AbstraktArkfane;
import view.ArkfaneAnnonse;
import view.ArkfaneMegler;
import view.MainPanel;

/**
 * Denne klassen er kontrolleren til bunnpanelet i både meglerVindu og
 * annonseVindu.
 *
 * @author espen
 */
public class ControllerBunnPanel {

    private KnappeLytter lytter;
    private HashSet<Person> personliste;
    private HashSet<Bolig> boligliste;
    private HashSet<Annonse> annonseliste;
    private HashSet<Soknad> soknadliste;
    private ArrayList<Object> tabellData;
    private TabellModell modell;

    public ControllerBunnPanel(HashSet<Bolig> boligliste, HashSet<Person> personliste, HashSet<Annonse> annonseliste, HashSet<Soknad> soknadliste) {

        this.boligliste = boligliste;
        this.personliste = personliste;
        this.annonseliste = annonseliste;
        this.soknadliste = soknadliste;

    }

    /**
     * Setter knappelytter, én for hvert av meglerVindu og annonseVindu.
     *
     * @param vindu
     */
    public void settKnappeLytter(AbstraktArkfane vindu) {
        vindu.getBunnpanel().addKnappeLytter(lytter = new KnappeLytter(vindu));
    }

    /**
     * Tar i mot tabellData fra Tabellen.
     *
     * @param tabellData
     * @param modell
     */
    public void settOppTabellData(ArrayList<Object> tabellData, TabellModell modell) {
        this.tabellData = tabellData;
        this.modell = modell;
    }


    /**
     * private lytteklasse for knappene i bunnpanelet.
     */
    class KnappeLytter implements ActionListener {

        AbstraktArkfane vindu;
        int raderITabell;
        JTable tabell;

        public KnappeLytter(AbstraktArkfane vindu) {
            this.vindu = vindu;
            tabell = vindu.getVenstrepanel().getTable();
            if(vindu instanceof ArkfaneAnnonse){
                vindu.getBunnpanel().getMultiKnapp().setText("Send forespørsel");
            }
        }

        /**
         * Finner hvilken rad som er valgt i tabellen og hvilken rad det representerer i datagrunnlaget.
         * @return 
         */
        public int finnValgtRadITabell() {
            try {
                int rad = tabell.getSelectedRow();
                rad = tabell.convertRowIndexToModel(rad);
                return rad;
            } catch (ArrayIndexOutOfBoundsException aiobe) {

            }
            return 0;
        }

        /**
         * Definerer funksjonen til knappen nede til venstre i vinduet.
         * Denne knappen har forskjellig funksjonalitet avhengig av hvilke objekt som
         * finnes i tabellen, og hvilke vindu man befinner seg i.
         * @param e 
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            raderITabell = tabell.getModel().getRowCount();

            if (e.getSource().equals(vindu.getBunnpanel().getMultiKnapp())) {
                try {
                    int valgtRad = finnValgtRadITabell();

                    if (tabell.getModel() instanceof TabellModellPerson) {
//                        new ControllerRegistrerPerson((HashSet<Person>) personliste, (Person) tabellData.get(valgtRad));
                        new ControllerRegistrerUtleier((HashSet<Person>) personliste, (Utleier) tabellData.get(valgtRad));
                    }
                    if (tabell.getModel() instanceof TabellModellBolig) {
                        new ControllerRegistrerBolig(boligliste, (Bolig) tabellData.get(valgtRad));
                    }
                    if (tabell.getModel() instanceof TabellModellAnnonse) {
                        if (vindu instanceof ArkfaneMegler) {
                            new ControllerRegistrerAnnonse(annonseliste, personliste, (Annonse) tabellData.get(valgtRad));
                        }else{
                            new ControllerRegistrerSoknad(personliste, annonseliste, soknadliste, (Annonse) tabellData.get(valgtRad));
                        }
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
