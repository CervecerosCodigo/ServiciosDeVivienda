package controller.registrer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.HashSet;
import lib.BildeFilSti;
import lib.Boligtype;
import lib.Melding;
import lib.RegexTester;
import model.Bolig;
import model.Enebolig;
import model.Leilighet;
import view.CustomJTextField;
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

    /**
     * En kontruktør for registrering av en ny bolig.
     * @param boligSet HashSet<Bolig>
     */
    public ControllerRegistrerBolig(HashSet<Bolig> boligSet) {
        super(boligSet);

        bRegVindu = new BoligRegVindu("Registrering av boliger");
        bRegVindu.setKnappeLytter(new KnappeLytter());
    }
    
    /**
     * En konstruktør som brukes for endring av en bolig.
     * @param boligSet HashSet<Bolig>
     * @param bolig Bolig
     */
    public ControllerRegistrerBolig(HashSet<Bolig> boligSet, Bolig bolig){
        super(boligSet, bolig);
        /*
        Hente opp en bolig.
        Gjør alle endringer som skal gjøres.
        Slett den boligen fra registeret.
        Sett inn det boligobjektet inn i registeret igjenn.
        */
        bRegVindu = new BoligRegVindu("Endre bolig");
        
        
        
        if(bolig instanceof Leilighet){
            bRegVindu.getLeilighetRButton().setEnabled(true);
        }else if(bolig instanceof Enebolig){
            bRegVindu.getLeilighetRButton().setEnabled(true);
        }
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
                Melding.visMelding("Boligregstrering", "Ny leilighet er registrert");

                //Opretter en ny mappe for boligens bilder dersom den ikke finnes med fra før
                BildeFilSti gallerimappe = new BildeFilSti();
                gallerimappe.lagBildemappeForBolig(leilighet);

                return true;
            } else {
                Melding.visMelding("Boligregstrering", "Leiligheten ble IKKE registrert");
            }
        }
        return false;
    }

    
    /**
     * Foretar registrering av en ny enebolig.
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
                Melding.visMelding("Boligregstrering", "Ny enebolig er registrert");

                //Opretter en ny mappe for boligens bilder dersom den ikke finnes med fra før
                BildeFilSti gallerimappe = new BildeFilSti();
                gallerimappe.lagBildemappeForBolig(enebolig);

                return true;
            } else {
                Melding.visMelding("Boligregstrering", "Leiligheten ble IKKE registrert");
            }
        }
        return false;
    }

    /**
     * Kontrollerbasert knappelytter for boligregistreringsvindu.
     */
    private class KnappeLytter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(bRegVindu.getLagreButton())) {
//                Melding.visMelding("Lagre", "Lagre knapp for ny bolig er kliket\n og fanget opp o kontroller");
                if (bRegVindu.getLeilighetRButton().isSelected()) {
                    if(registrerNyLeilighet()){
                        bRegVindu.dispose();
                    }
                }else if(bRegVindu.getEneboligRButton().isSelected()){
                    if(registrerNyEnebolig()){
                        bRegVindu.dispose();
                    }
                }
            }
        }
    }

}
