package controller.registrer;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.


import controller.TabellFireDataChangedInterface;
import controller.VisMeldingInterface;
import java.awt.event.*;
import java.util.*;
import lib.Ikoner;
import lib.Konstanter;
import lib.RegexTester;
import model.*;
import view.registrer.AnnonseRegVindu;

/**
 * Controller for logikken til registreringsvindu for annonseobjektene. 
 * Her er det Konstruktører for både nyregistreringer og å endre eksisterende objekter.
 * @author espen
 */
public class ControllerRegistrerAnnonse implements VisMeldingInterface{

    private AnnonseRegVindu vindu;
    private HashSet<Annonse> annonseliste;
    private HashSet<Person> personliste;
    private Bolig bolig;
    private Annonse annonseSomEndres;
    private String fornavn, etternavn, epost, tlf;

    private int depositum, utleiepris;
    private Calendar utlopsDato, tilgjengligFraDato, today;
    private String eiersKrav;
    private boolean erSynligsomAnnonse;
    
    private boolean erNyregistrering;
    TabellFireDataChangedInterface tabellOppdateringLytter;

    /**
     * Konstruktør for registrering av nye annonseobjekter.
     * @param annonseliste
     * @param personliste
     * @param bolig 
     */
    public ControllerRegistrerAnnonse(HashSet<Annonse> annonseliste, HashSet<Person> personliste, Bolig bolig) {
        this.annonseliste = annonseliste;
        this.personliste = personliste;
        this.bolig = bolig;
        vindu = new AnnonseRegVindu("Registrering av annonser");
        vindu.addAnnonsePanelListener(new KnappLytter());
        
        vindu.setIconImage(Ikoner.NY_BOLIG.getImage());
        
        fyllUtBoliginfo();
        erNyregistrering = true;
        
        today = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_WEEK));    

        vindu.getUtlopsDato().setDato(
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH)+1,
                today.get(Calendar.DAY_OF_WEEK));

        vindu.getTilgjengligFraDato().setDato(
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH)+1,
                today.get(Calendar.DAY_OF_WEEK));        
    }

    /**
     * Kontroller for endringer av eksisterende annonseobjekter.
     * @param annonseliste
     * @param personliste
     * @param annonseSomEndres 
     */
    public ControllerRegistrerAnnonse(HashSet<Annonse> annonseliste, HashSet<Person> personliste, Annonse annonseSomEndres) {
        this.annonseliste = annonseliste;
        this.personliste = personliste;
        this.bolig = annonseSomEndres.getBolig();
        this.annonseSomEndres = annonseSomEndres;
        vindu = new AnnonseRegVindu("Registrering av annonser");
        
        vindu.setIconImage(Ikoner.EDIT.getImage());
        
        vindu.addAnnonsePanelListener(new KnappLytter());
        fyllUtBoliginfo();
        erNyregistrering = false;
        today = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_WEEK));                

        //Setter inn data fra eksisterende annonse.
        vindu.getDepositum().setText(String.valueOf(this.annonseSomEndres.getDepositum()));
        vindu.getUtleiepris().setText(String.valueOf(this.annonseSomEndres.getUtleiepris()));

        vindu.getUtlopsDato().setDato(
                annonseSomEndres.getUtlopsDato().get(Calendar.YEAR),
                annonseSomEndres.getUtlopsDato().get(Calendar.MONTH),
                annonseSomEndres.getUtlopsDato().get(Calendar.DAY_OF_WEEK));

        vindu.getTilgjengligFraDato().setDato(
                annonseSomEndres.getTilgjengeligFraDato().get(Calendar.YEAR),
                annonseSomEndres.getTilgjengeligFraDato().get(Calendar.MONTH),
                annonseSomEndres.getTilgjengeligFraDato().get(Calendar.DAY_OF_WEEK));
        vindu.getEiersKrav().setText(annonseSomEndres.getEiersKrav());
        vindu.getErSynligSomAnnonse().setSelected(this.annonseSomEndres.isErSynlig());

    }

    /**
     * Sjekker om innskrevede data er OK for å oppdatere tilsendt annnonse. 
     * @param annonse
     * @return 
     */
    private boolean kontrollerDataForSletting(Annonse annonse) {
        getAnnonseDataFraGUI();
        if(kontrollerDataAnnonse()){
            return true;
        }
        return false;
    }

    /**
     * Om annonsen skal oppdateres må eksisterende kopi av annonsen slettes fra annonselisten før redigert annonse kan settes inn.
     * @param annonse
     * @return 
     */
    private boolean slettAnnonseFraSet(Annonse annonse) {
        if (kontrollerDataForSletting(annonse)) {
            if(annonseliste.remove(annonse)){
                return true;
            }
        }
        visMelding("", "Annonsen kunne ikke slettes!");
        return false;
    }

    /**
     * Fyller ut info om boligen annonsen gjelder for
     */
    private void fyllUtBoliginfo() {

        if (bolig != null) {
            if (finnEierInfoOmBolig()) {
                vindu.getBoligIDInfo().setText(String.valueOf(bolig.getBoligID()));
                vindu.getBoligAdresseInfo().setText(bolig.getAdresse());
                vindu.getBoligPostNrInfo().setText(bolig.getPostnummer());
                vindu.getBoligPostStedInfo().setText(bolig.getPoststed());
                vindu.getBoligEierFornavnInfo().setText(fornavn);
                vindu.getBoligEierEtternavnInfo().setText(etternavn);
                vindu.getBoligEierEpostInfo().setText(epost);
                vindu.getBoligEierTlfInfo().setText(tlf);
            } else {
                visMelding("", "Person finnes ikke!");
            }
        } else {
            visMelding("", "Boligenobjektet finnes ikke!");
        }
    }

    /**
     * Henter data fra Annonseregistreringsfeltene og legger inn i tilsendt annonse.
     * @param annonse
     * @return 
     */
    private Annonse oppdaterAnnonseObjekt(Annonse annonse) {
        
        getAnnonseDataFraGUI();
        annonse.setErSynlig(erSynligsomAnnonse);
        annonse.setTilgjengligFraDato(tilgjengligFraDato);
        annonse.setUtlopsDato(utlopsDato);
        annonse.setDepositum(depositum);
        annonse.setUtleiepris(utleiepris);
        annonse.setEiersKrav(eiersKrav);
        
        return annonse;
    }

    /**
     * Tar den endrede annonsen og setter den inn i annonselisten igjen.
     *
     * @param annonse
     * @return
     */
    private boolean skrivOppdateringtilAnnonseSet(Annonse annonse) {
        if (annonseliste.add(annonse)) {
            visMelding("Registrering fullført", "Annonsen er oppdatert!");
            return true;
        }
        visMelding("Registrering feilet", "Annonsen ble ikke oppdatert!");
        return false;
    }

    /**
     * Kjører Regexsjekk på alle feltene.
     * @return 
     */
    private boolean kontrollerDataAnnonse() {

        

        

        boolean[] annonseOK = new boolean[4];
        
        if ((today.compareTo(utlopsDato)) <= 0) {
            
            annonseOK[0] = true;
        } else {
            annonseOK[0] = false;
        }
        if ((today.compareTo(tilgjengligFraDato)) <= 0) {
            
            annonseOK[1] = true;
        } else {
            annonseOK[1] = false;
        }
        annonseOK[2] = RegexTester.testPris(String.valueOf(depositum));
        annonseOK[3] = RegexTester.testPris(String.valueOf(utleiepris));

        for (int i = 0; i < annonseOK.length; i++) {
            if (!annonseOK[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Henter feltene fra annonseregistreringsvinduet
     */
    private void getAnnonseDataFraGUI() {
        try {
            depositum = Integer.parseInt(vindu.getDepositum().getText());
            utleiepris = Integer.parseInt(vindu.getUtleiepris().getText());
            utlopsDato = vindu.getUtlopsDato().opprettKalenderobjekt();
            tilgjengligFraDato = vindu.getTilgjengligFraDato().opprettKalenderobjekt();
            erSynligsomAnnonse = vindu.getErSynligSomAnnonse().isSelected();
            eiersKrav = vindu.getEiersKrav().getText();
        } catch (Exception ex) {
            visMelding("", "Greier ikke hente data fra alle feltene!");
        }
    }

    /**
     * Finner infor om eier av bolig. Brukes for informasjon.
     * @return 
     */
    private boolean finnEierInfoOmBolig() {
        Person temp = null;
        Iterator<Person> iter = personliste.iterator();
        while (iter.hasNext()) {
            temp = iter.next();
            if (temp.getPersonID() == bolig.getPersonID()) {
                fornavn = temp.getFornavn();
                etternavn = temp.getEtternavn();
                epost = temp.getEpost();
                tlf = temp.getTelefon();
                return true;
            }
        }
        return false;
    }

    /**
     * Registrerer ny annonse
     * @return 
     */
    private boolean registrerNyAnnonse(){
        getAnnonseDataFraGUI();
        if(!kontrollerDataAnnonse()){
            visMelding("", "Vennligst sjekk alle feltene.");
            return false;
        }else{
            Annonse annonse = new Annonse(depositum, utleiepris, tilgjengligFraDato, utlopsDato, bolig, eiersKrav);
            annonseSomEndres = annonse;
            if(annonseliste.add(annonse)){
                visMelding("Registrering fullført!", "Annonsen er registrert!");
                
                return true;
            }else{
                visMelding("Registrering feilet!","Greide ikke å legge annonsen inn i registeret!");
            }
            
        }
        return false;
    }


    
    public void settTabellOppdateringsLytter(TabellFireDataChangedInterface lytter){
        tabellOppdateringLytter = lytter;
    }

    @Override
    public void visMelding(String overskrift, String melding) {
        vindu.visMelding(overskrift, melding);
    }
    
    
    class KnappLytter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(vindu.getLagreButton())) {
                if(erNyregistrering){
                    
                    registrerNyAnnonse();
                    if(tabellOppdateringLytter != null)
                        tabellOppdateringLytter.oppdaterTabellEtterEndring();
                    
                    annonseSomEndres.setErSynlig(true);
                    vindu.dispose();
                }else{
                    if(slettAnnonseFraSet(annonseSomEndres)){
                        annonseSomEndres.setErSynlig(erSynligsomAnnonse);
                        skrivOppdateringtilAnnonseSet(annonseSomEndres);
                        vindu.dispose();
                    }
                }
            }
            if (e.getSource().equals(vindu.getAvbrytButton())) {
                vindu.dispose();
            }
        }

    }
}
