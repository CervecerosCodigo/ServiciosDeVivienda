package controller.registrer;

import controller.ControllerBildeViser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import lib.BildeFilSti;
import lib.BoligBilde;
import lib.Boligtype;
import lib.Konstanter;
import lib.Melding;
import lib.RegexTester;
import model.Bolig;
import model.Enebolig;
import model.Leilighet;
import view.BildeViser;
import view.registrer.BoligRegVindu;

/**
 * Kontroller for boligregistreringsvindu. Bruekes både for registrering av en
 * ny bolig og endring av alerede lagrede oplysninger.
 *
 * <br><br>
 * File: ControllerRegistrerBolig.java Project: ServiciosDeVivienda Apr 29, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class ControllerRegistrerBolig extends AbstractControllerRegister {

    private BoligRegVindu bRegVindu;

    private boolean erNyregistrering;//Dersom boligen skal registreres som ny eller editeres
    private Bolig bolig;//Midlertidlig boligobjekt som brukes i samband med oppdatering

    ///START PÅ DATAFELT FOR BOLIG///
    private String adresse, postNr, postSted, beskrivelse;
    private boolean erUtleid;
    private int eierID, meglerID, boAreal, byggeAr, arTilgjengelig, manedTilgjengelig, dagTilgjengelig;
    private GregorianCalendar tilgjengeligForUtleie;
    ///SLUTT PÅ DATAFELT FOR BOLIG///
    //
    ///START PÅ DATAFELT FOR LEILIGHET///
    private int etasjeNr, balkongAreal, bodAreal;
    private boolean harHeis, harGarasje, harFellesVaskeri;
    ///SLUTT PÅ DATAFELT FOR LEILIGHET///
    //
    ///START PÅ DATAFELT FOR ENEBOLIG///
    private int antallEtasjer, tomtAreal;
    private boolean harKjeller;
    ///SLUTT PÅ DATAFELT FOR ENEBOLIG///
    //
    ///START PÅ BILDEBEHANDLING///
    private BoligBilde boligBilde;
    private JFileChooser fc;
    private FileFilter ff;
    ///STOPP PÅ BILDEBEHANDLING///

    /**
     * En kontruktør for registrering av en ny bolig.
     *
     * @param boligSet HashSet<Bolig>
     */
    public ControllerRegistrerBolig(HashSet<Bolig> boligSet) {
        super(boligSet);
        erNyregistrering = true;
        boligBilde = new BoligBilde();
        bRegVindu = new BoligRegVindu("Registrering av boliger");
        bRegVindu.setKnappeLytter(new KnappeLytter());
    }

    /**
     * En konstruktør som brukes for endring av en bolig.
     *
     * @param boligSet HashSet<Bolig>
     * @param bolig Bolig
     */
    public ControllerRegistrerBolig(HashSet<Bolig> boligSet, Bolig bolig) {
        super(boligSet, bolig);
        erNyregistrering = false;
        boligBilde = new BoligBilde();
        this.bolig = bolig;

        bRegVindu = new BoligRegVindu("Endre bolig");
        bRegVindu.setKnappeLytter(new KnappeLytter());

        //Fyller inn felt i bolig panelen med data
        //FIXME: Dette borde egentlig flyttes til en egen metode
        bRegVindu.getEierField().setText(String.valueOf(bolig.getPersonID()));
        bRegVindu.getAdresseField().setText(bolig.getAdresse());
        bRegVindu.getPostNrField().setText(bolig.getPostnummer());
        bRegVindu.getPostStedField().setText(bolig.getPoststed());
        bRegVindu.getBoArealField().setText(String.valueOf(bolig.getBoAreal()));
        bRegVindu.getByggeArField().setText(String.valueOf(bolig.getByggeAr()));
        bRegVindu.getArCombo().setSelectedItem(bolig.getTilgjengeligForUtleie().get(Calendar.YEAR));
        bRegVindu.getManedCombo().setSelectedItem(bolig.getTilgjengeligForUtleie().get(Calendar.MONTH));
        bRegVindu.getDagCombo().setSelectedItem(bolig.getTilgjengeligForUtleie().get(Calendar.DAY_OF_MONTH));
        if (bolig.isErUtleid()) {
            bRegVindu.getErUtleidCheckBox().setSelected(true);
        }
        bRegVindu.getBeskrivelseTextArea().setText(bolig.getBeskrivelse());
        //Setter opp teller for bilder
        bRegVindu.getBildeResultatLabel().setText(String.valueOf(boligBilde.antallBilder(bolig)));

        if (bolig instanceof Leilighet) {
            bRegVindu.getLeilighetRButton().setSelected(true);
            bRegVindu.aktiverBoligKomponenter();
            bRegVindu.aktiverLeilighetKomponenter();
            //Setter opp datafelt for leilighet
            bRegVindu.getEtasjeNrField().setText(String.valueOf(((Leilighet) bolig).getEtasjeNr()));
            bRegVindu.getBalkongArealField().setText(String.valueOf(((Leilighet) bolig).getBalkongAreal()));
            bRegVindu.getBoArealField().setText(String.valueOf(String.valueOf(((Leilighet) bolig).getBodAreal())));
            bRegVindu.getHarHeisCheckBox().setSelected(((Leilighet) bolig).isHarHeis());
            bRegVindu.getHarGarasjeCheckBox().setSelected(((Leilighet) bolig).isHarGarsje());
            bRegVindu.getHarFellesVaskeriCheckbox().setSelected(((Leilighet) bolig).isHarFellesvaskeri());
        } else if (bolig instanceof Enebolig) {
            bRegVindu.getEneboligRButton().setSelected(true);
            bRegVindu.aktiverBoligKomponenter();
            bRegVindu.aktiverEneboligKomponenter();
            //Setter opp datafelt for enebolig
            bRegVindu.getAntallEtasjerField().setText(String.valueOf(((Enebolig) bolig).getAntallEtasjer()));
            bRegVindu.getTomtArealField().setText(String.valueOf(((Enebolig) bolig).getTomtAreal()));
            bRegVindu.getHarKjellerCheckBox().setSelected(((Enebolig) bolig).isHarKjeller());
        }
        
  
    }

    /**
     * Brukes KUN i samband med oppdatering av eksisterende boligobjekt. Dersom
     * alle felt i GUI er ok tillater den til å gå videre.
     *
     * @return boolean
     */
    private boolean kontrollerDataForSletting(Bolig bolig) {
        getBoligData();
        if (bolig instanceof Leilighet) {
            getLeilighetData();
            if (kontrollerDataBolig() && kontrollerDataLeilighet()) {
                return true;
            }
        } else if (bolig instanceof Enebolig) {
            getEneboligData();
            if (kontrollerDataBolig() && kontrollerDataEnebolig()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Brukes i samband med oppdatering av registrert boligobjekt. Sletter
     * objektet fra settet for å nngå dobbellagring.
     *
     * @param bolig
     * @return
     */
    private boolean slettBoligFraSet(Bolig bolig) {

        if (kontrollerDataForSletting(bolig)) {
            if (super.set.remove(bolig)) {
                return true;
            }
        }
        Melding.visMelding("slettBoligFraSet", "Bolig ble IKKE slettet fra set");
        return false;
    }

    /**
     * Brukes i samband med oppdatering av en bolig. Overskriver alle datafelt i
     * et boligobjekt med ny informasjon hentet fra GUI.
     *
     * @param bolig
     * @return Bolig
     */
    private Bolig oppdaterBoligObjekt(Bolig bolig) {
        //Henter generelle boligparametre
        getBoligData();

        //Setter generelle datafelt for bolig
        bolig.setPersonID(eierID);
        bolig.setAdresse(adresse);
        bolig.setPostnummer(postNr);
        bolig.setPoststed(postSted);
        bolig.setBoAreal(boAreal);
        bolig.setByggeAr(byggeAr);
        bolig.setTilgjengeligForUtleie(tilgjengeligForUtleie);
        bolig.setErUtleid(erUtleid);
        bolig.setBeskrivelse(beskrivelse);

        if (bolig instanceof Leilighet) {

            getLeilighetData();

            ((Leilighet) bolig).setEtasjeNr(etasjeNr);
            ((Leilighet) bolig).setBalkongAreal(balkongAreal);
            ((Leilighet) bolig).setBodAreal(bodAreal);
            ((Leilighet) bolig).setHarHeis(harHeis);
            ((Leilighet) bolig).setHarGarsje(harGarasje);
            ((Leilighet) bolig).setHarFellesvaskeri(harFellesVaskeri);

        } else if (bolig instanceof Enebolig) {

            getEneboligData();

            ((Enebolig) bolig).setAntallEtasjer(antallEtasjer);
            ((Enebolig) bolig).setTomtAreal(tomtAreal);
            ((Enebolig) bolig).setHarKjeller(harKjeller);
        }

        return bolig;
    }

    /**
     * Legger tillbake boligobjektet til set etter at det er oppdatert.
     *
     * @param bolig
     * @return
     */
    private boolean skrivOppdateringTilBoligSet(Bolig bolig) {
        if (super.set.add(bolig)) {
            Melding.visMelding("skrivOppdateringTilBoligSet", "Boligen ble oppdatert i registret");
            return true;
        }
        Melding.visMelding("skrivOppdateringTilBoligSet", "Boligen ble IKKE oppdatert i registret");

        return false;
    }

    /**
     * Populerer alle datafelt fra bolig delen i GUI.
     */
    public void getBoligData() {
        eierID = Integer.parseInt(bRegVindu.getEierField().getText());
        meglerID = Integer.parseInt(bRegVindu.getMeglerField().getText());
        adresse = bRegVindu.getAdresseField().getText();
        postNr = bRegVindu.getPostNrField().getText();
        postSted = bRegVindu.getPostStedField().getText();
        beskrivelse = bRegVindu.getBeskrivelseTextArea().getText();
        erUtleid = bRegVindu.getErUtleidCheckBox().isSelected();
        boAreal = Integer.parseInt(bRegVindu.getBoArealField().getText());
        byggeAr = Integer.parseInt(bRegVindu.getByggeArField().getText());
        arTilgjengelig = Integer.parseInt(bRegVindu.getArCombo().getSelectedItem().toString());
        manedTilgjengelig = Integer.parseInt(bRegVindu.getManedCombo().getSelectedItem().toString());
        dagTilgjengelig = Integer.parseInt(bRegVindu.getDagCombo().getSelectedItem().toString());
        tilgjengeligForUtleie = new GregorianCalendar(arTilgjengelig, manedTilgjengelig, dagTilgjengelig);
    }

    public void getLeilighetData() {
        etasjeNr = Integer.parseInt(bRegVindu.getEtasjeNrField().getText());
        balkongAreal = Integer.parseInt(bRegVindu.getBalkongArealField().getText());
        bodAreal = Integer.parseInt(bRegVindu.getBoArealField().getText());
        harHeis = bRegVindu.getHarHeisCheckBox().isSelected();
        harGarasje = bRegVindu.getHarGarasjeCheckBox().isSelected();
        harFellesVaskeri = bRegVindu.getHarFellesVaskeriCheckbox().isSelected();
    }

    public void getEneboligData() {
        antallEtasjer = Integer.parseInt(bRegVindu.getAntallEtasjerField().getText());
        tomtAreal = Integer.parseInt(bRegVindu.getTomtArealField().getText());
        harKjeller = bRegVindu.getHarKjellerCheckBox().isSelected();
    }

    /**
     * Foretar en regex kontroll på alle datafelt som kan settes av bruker i
     * boligpanelen for registrereing av bolig.
     *
     * @return boolean
     */
    private boolean kontrollerDataBolig() {
        //TODO: En søkeklasse som man kan bruke for å finne ut at brukeren har lagt inn korrekt id på eier og megler
        boolean[] boligOK = new boolean[7];

        boligOK[0] = RegexTester.testID(String.valueOf(eierID));
        boligOK[1] = RegexTester.testID(String.valueOf(meglerID));
        boligOK[2] = RegexTester.testGateadresse(adresse);
        boligOK[3] = RegexTester.testPostNummer(postNr);
        boligOK[4] = RegexTester.testPostOrtNavn(postSted);
        boligOK[5] = RegexTester.testKVMbolig(String.valueOf(boAreal));
        boligOK[6] = RegexTester.testYearNummer(String.valueOf(byggeAr));
        for (int i = 0; i < boligOK.length; i++) {
            if (!boligOK[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Foretar en regex kontroll på alle felt som brukes for registrering av
     * leilighet som kan settes av bruker.
     *
     * @return boolean
     */
    private boolean kontrollerDataLeilighet() {
        boolean[] leilighetOK = new boolean[3];

        leilighetOK[0] = RegexTester.testEtasje(String.valueOf(etasjeNr));
        leilighetOK[1] = RegexTester.testKVMbolig(String.valueOf(balkongAreal));
        leilighetOK[2] = RegexTester.testKVMbolig(String.valueOf(bodAreal));

        for (int i = 0; i < leilighetOK.length; i++) {
            if (!leilighetOK[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Foretar en regex test på de to ekstra felt som brukeren kan sette ved
     * registrering av en ny enebolig.
     *
     * @return boolean
     */
    private boolean kontrollerDataEnebolig() {
        boolean[] eneboligOK = new boolean[2];

        eneboligOK[0] = RegexTester.testEtasje(String.valueOf(antallEtasjer));
        eneboligOK[1] = RegexTester.testKVMtomt(String.valueOf(tomtAreal));
        for (int i = 0; i < eneboligOK.length; i++) {
            if (!eneboligOK[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Foretar registrering av en ny leilighet.
     *
     * @return boolean Dersom registreringen var vellyket.
     */
    private boolean registrerNyLeilighet() {

        //Henter data fra GUI
        getBoligData();
        getLeilighetData();

        //Kontroller generelle datafelt for bolig
        if (!kontrollerDataBolig()) {
            Melding.visMelding("Boligregstrering", "Feil i skjema for bolig.");
            return false;
            //Kontrollerer leilighetsfelt
        } else if (!kontrollerDataLeilighet()) {
            Melding.visMelding("Boligregstrering", "Feil i skjema for leilighet.");
            return false;
        } else {
            Leilighet leilighet = new Leilighet(etasjeNr, balkongAreal, bodAreal, harHeis, harGarasje, harFellesVaskeri, eierID, adresse, postNr, postSted, boAreal, byggeAr, beskrivelse, erUtleid, tilgjengeligForUtleie);

            if (set.add(leilighet)) {
//                Melding.visMelding("Boligregstrering", "Ny leilighet er registrert");

                //Opretter en ny mappe for boligens bilder dersom den ikke finnes med fra før
                BildeFilSti gallerimappe = new BildeFilSti();
                gallerimappe.lagBildemappeForBolig(leilighet);

                //Setter lokal klasse referanse til det nye objektet for å lagre bilder
                bolig = leilighet;

                return true;
            } else {
                Melding.visMelding("Boligregstrering", "Leiligheten ble IKKE registrert");
            }
        }
        return false;
    }

    /**
     * Foretar registrering av en ny enebolig.
     *
     * @return boolean
     */
    private boolean registrerNyEnebolig() {

        //Henter data fra GUI
        getBoligData();
        getEneboligData();

        //Kontroller generelle datafelt for bolig
        if (!kontrollerDataBolig()) {
            Melding.visMelding("Boligregstrering", "Feil i skjema for bolig.");
            return false;
            //Kontrollerer leilighetsfelt
        } else if (!kontrollerDataEnebolig()) {
            Melding.visMelding("Boligregstrering", "Feil i skjema for enebolig.");
            return false;
        } else {
            Enebolig enebolig = new Enebolig(Boligtype.ENEBOLIG, antallEtasjer, harKjeller, tomtAreal, eierID, adresse, postNr, postSted, boAreal, byggeAr, beskrivelse, erUtleid, tilgjengeligForUtleie);

            if (set.add(enebolig)) {
//                Melding.visMelding("Boligregstrering", "Ny enebolig er registrert");

                //Opretter en ny mappe for boligens bilder dersom den ikke finnes med fra før
                BildeFilSti gallerimappe = new BildeFilSti();
                gallerimappe.lagBildemappeForBolig(enebolig);

                //Setter lokal klasse referanse til det nye objektet for å lagre bilder
                bolig = enebolig;

                return true;
            } else {
                Melding.visMelding("Boligregstrering", "Leiligheten ble IKKE registrert");
            }
        }
        return false;
    }

    /**
     * Laster opp første eller ekstra bilder for en alerede eksisterende
     * boligobjekt. Endrer størrelse på bilde og setter inkrementell filnavn
     * deretter lagrer bilde i galleriet for bildet.
     *
     * @param bolig
     */
    private void lastOppEkstraBilde(Bolig bolig) {
        fc = new JFileChooser();
        fc.setLocale(Locale.GERMAN);
        ff = new FileNameExtensionFilter("*.jpg", "jpg");
        fc.setFileFilter(ff);
        int valg = fc.showOpenDialog(null);
        if (valg == JFileChooser.APPROVE_OPTION) {
            File fil = fc.getSelectedFile();
            try {
                boligBilde.lagreNyttBildeForBolig(bolig, fil);

                //Spørsmål om flere bilder
                int valg2 = JOptionPane.showOptionDialog(null, "Bilde er nå lastet opp.\nØnsker du å laste opp flere bilder?", "Laste opp flere bilder?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, Konstanter.VALG_JA_NEI, Konstanter.VALG_JA_NEI[0]);
                if (valg2 == JOptionPane.YES_OPTION) {
                    lastOppEkstraBilde(bolig);
                } else {
                    bRegVindu.dispose();
                }

            } catch (IOException ex) {
                Melding.visMelding("Lagre ny bilde", "IO Exception\nve lagring av nytt bilde");
            }
        }
    }

    /**
     * Brukes for å laste opp bilder for en nyregistrert bolig. Ettersom vi ikke
     * kan laste opp bilder før boligen blitt registrert så gjør vi det på slik
     * måte.
     *
     * @param bolig
     */
    private void lastOppBildeForNyBolig(Bolig bolig) {
        int valg2 = JOptionPane.showOptionDialog(null, "Boligen er nå registrert.\nØnsker du å laste opp et eller flere bilder?\n(Du kan også gjøre det seinere)", "Bilder for ny bolig?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, Konstanter.VALG_JA_NEI, Konstanter.VALG_JA_NEI[0]);
        if (valg2 == JOptionPane.YES_OPTION) {
            lastOppEkstraBilde(bolig);
        }
    }

    /**
     * Kontrollerbasert knappelytter for boligregistreringsvindu.
     */
    private class KnappeLytter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(bRegVindu.getLagreButton())) {

                if (erNyregistrering) {
                    if (bRegVindu.getLeilighetRButton().isSelected()) {
                        if (registrerNyLeilighet()) {
                            lastOppBildeForNyBolig(bolig);
                            bRegVindu.dispose();
                        }
                    } else if (bRegVindu.getEneboligRButton().isSelected()) {
                        if (registrerNyEnebolig()) {
                            lastOppBildeForNyBolig(bolig);
                            bRegVindu.dispose();
                        }
                    }
                } else {
                    //Sletter eksisterende bolig i set, oppdaterer alle datafelt og skriver det tilbake til settet.
                    if (slettBoligFraSet(bolig)) {
                        skrivOppdateringTilBoligSet(oppdaterBoligObjekt(bolig));
                        bRegVindu.dispose();
                    }

                }
            } else if (e.getSource().equals(bRegVindu.getBildeButton())) {
                lastOppEkstraBilde(bolig);
            }else if(e.getSource().equals(bRegVindu.getVisFlereBilderButton())){
                ControllerBildeViser controllerBildeViser = new ControllerBildeViser(bolig, true);
            }
        }
    }

}
