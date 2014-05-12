package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.SortedSet;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import lib.Boligtype;
import lib.Melding;
import lib.ObjektType;
import lib.RegexTester;
import model.Annonse;
import model.Bolig;
import model.Kontrakt;
import search.AnnonseFilter;
import view.AbstraktArkfane;

/**
 * Kontroller klasse for topp panel annonse søk. File:
 * ControllerToppPanelAnnonse.java Package: controller Project:
 * ServiciosDeVivienda Apr 26, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class ControllerToppPanelAnnonse {

    AbstraktArkfane vindu;
    private HashSet<Annonse> annonseliste;
    private AnnonseFilter afilter;
    private ListListenerInterface listListener;

    public ControllerToppPanelAnnonse(AbstraktArkfane vindu, HashSet<Annonse> annonseliste) {
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
        OppdaterStatistikk();
    }
    
    private void OppdaterStatistikk() {

        int antallLedigeBoliger = 0;
        int antallBoliger = 0;

        for (Annonse annonseIterator : annonseliste) {
            if (annonseIterator.getBolig().isErUtleid() == false) {
                antallLedigeBoliger++;
            }
        }

        antallBoliger = annonseliste.size();
        vindu.getToppanelAnnonse().getStatistikkPanel().OppdaterStatistikk(antallLedigeBoliger, antallBoliger);
    }
    

    /**
     * Laster inn alle poststeder i comboboksen ved initialisering.
     *
     * @param poststedSet
     */
    private void addPoststederToComboBoxOnLoad(SortedSet<String> poststedSet) {
        for (String s : poststedSet) {
            vindu.getToppanelAnnonse().addItemPoststedBox(s);
        }
    }

    /**
     * Laster inn alle boligtyper i comboboksen ved initialisering.
     *
     * @param boligtypeSet
     */
    private void addBoligTyperToComboBoxOnLoad(SortedSet<Boligtype> boligtypeSet) {
        for (Boligtype b : boligtypeSet) {
            vindu.getToppanelAnnonse().addItemBoligTypeBox(b);
        }
    }

    private HashSet<Annonse> filtrerAnnonser() {
        String postSted = (String) vindu.getToppanelAnnonse().getPostStedBox().getSelectedItem();
        Boligtype boligType = (Boligtype) vindu.getToppanelAnnonse().getBoligTypeBox().getSelectedItem();
        int prisMin = 0;
        int prisMaks = 0;
        int arealMin = 0;
        int arealMaks = 0;
        boolean harBalkong = vindu.getToppanelAnnonse().getBalkongCheckBox().isSelected();
        boolean harFellesvask = vindu.getToppanelAnnonse().getFellesvaskCheckBox().isSelected();
        boolean harHage = vindu.getToppanelAnnonse().getHageCheckBox().isSelected();
        boolean harKjeller = vindu.getToppanelAnnonse().getKjellerCheckBox().isSelected();

        String prisMinS = vindu.getToppanelAnnonse().getMinPrisFelt().getText();
        String prisMaksS = vindu.getToppanelAnnonse().getMaksPrisFelt().getText();
        String arealMinS = vindu.getToppanelAnnonse().getMinArealFelt().getText();
        String arealMaksS = vindu.getToppanelAnnonse().getMaksArealFelt().getText();

        //TODO: Her må vi ha noe slik at vi kan gi tilbakemelding til GUI slik at feltet skifter farve
        boolean erPrisMinOK = RegexTester.testPris(prisMinS);
        boolean erPrisMaksOK = RegexTester.testPris(prisMaksS);
        boolean erArealMinOK = RegexTester.testKVMbolig(arealMinS);
        boolean erArealMaksOK = RegexTester.testKVMbolig(arealMaksS);

//        Melding.visMelding("Regex", erPrisMinOK + "\n" + erPrisMaksOK + "\n" + erArealMinOK + "\n" + erArealMaksOK);

        if (prisMinS.equals("min") || !erPrisMinOK) {
            prisMin = 0;
        } else if (erPrisMinOK) {
            prisMin = Integer.parseInt(prisMinS);
        }

        if (prisMaksS.equals("maks") || !erPrisMaksOK) {
            prisMaks = 0;
        } else if (erPrisMaksOK) {
            prisMaks = Integer.parseInt(prisMaksS);
        }

        if (arealMinS.equals("min") || !erArealMinOK) {
            arealMin = 0;
        } else if (erArealMinOK) {
            arealMin = Integer.parseInt(arealMinS);
        }

        if (arealMaksS.equals("maks") || !erArealMaksOK) {
            arealMaks = 0;
        } else if (erArealMaksOK) {
            arealMaks = Integer.parseInt(arealMaksS);
        }

        HashSet<Annonse> annonser = afilter.filtrerEtterParametre(postSted, boligType, prisMin, prisMaks, arealMin, arealMaks, harBalkong, harFellesvask, harHage, harKjeller);

        return annonser;
    }

    /**
     * Tar imot ListListener interfacet med hensikt til å sende events og data
     * opp til overliggende controller (MainController).
     *
     * @param listListener
     */
    public void setListListener(ListListenerInterface listListener) {
        this.listListener = listListener;
    }
    
    public void sendSokeResultat() {
        HashSet<Annonse> filterResultat = filtrerAnnonser();
        
        if (listListener != null)
            listListener.listReady(filterResultat, ObjektType.ANNONSEOBJ);
    }

    class KnappeLytter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(vindu.getToppanelAnnonse().getSokeKnapp())) {
            	sendSokeResultat();
            }
        }
    }
}
