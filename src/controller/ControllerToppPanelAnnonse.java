package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.HashSet;
import java.util.SortedSet;
import lib.Boligtype;
import lib.Melding;
import model.Annonse;
import search.AnnonseFilter;
import view.ArkfaneTemplate;

/**
 * Kontroller klasse for topp panel annonse søk. File:
 * ControllerToppPanelAnnonse.java Package: controller Project:
 * ServiciosDeVivienda Apr 26, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class ControllerToppPanelAnnonse {

    ArkfaneTemplate vindu;
    private HashSet<Annonse> annonseliste;
    private AnnonseFilter afilter;

    public ControllerToppPanelAnnonse(ArkfaneTemplate vindu, HashSet<Annonse> annonseliste) {
        this.vindu = vindu;
        this.annonseliste = annonseliste;
        afilter = new AnnonseFilter(annonseliste);

        try {
            addPoststederToComboBoxOnLoad(afilter.getPoststederIAnnonser());
            addBoligTyperToComboBoxOnLoad(afilter.getBoligtyperIAnnonser());
        } catch (ParseException e) {
            Melding.visMelding("ControllerToppPanelAnnonse", e.getMessage());
        }

        vindu.getToppanelAnnonse().addKnappLytter(new KnappeLytter());
    }

    /**
     * Later inn alle poststeder i comboboksen ved initialisering.
     *
     * @param poststedSet
     */
    private void addPoststederToComboBoxOnLoad(SortedSet<String> poststedSet) {
        for (String s : poststedSet) {
            vindu.getToppanelAnnonse().addItemPoststedBox(s);
        }
    }

    private void addBoligTyperToComboBoxOnLoad(SortedSet<Boligtype> boligtypeSet) {
        for (Boligtype b : boligtypeSet) {
            vindu.getToppanelAnnonse().addItemBoligTypeBox(b);
        }
    }

    class KnappeLytter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(vindu.getToppanelAnnonse().getSokeKnapp())) {
                Melding.visMelding("SøkeKnapp", "Søk");
            }
        }
    }
}
