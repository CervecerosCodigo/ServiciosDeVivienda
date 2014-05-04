package controller.registrer;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JTextArea;
import model.Annonse;
import model.Bolig;
import model.Person;
import view.ComboDatoVelger;
import view.CustomJCheckBox;
import view.CustomJTextField;
import view.registrer.AnnonseRegVindu;

public class ControllerRegistrerAnnonse {

    private AnnonseRegVindu vindu;
    private HashSet<Annonse> annonseliste;
    private HashSet<Person> personliste;
    private Bolig bolig;
    private Annonse annonseSomEndres;
    private String fornavn, etternavn, epost, tlf;
    
    private int depositum, utleiepris;
    private Calendar utlopsDato, tilgjengligFraDato;
    private String eiersKrav;    
    private Boolean erSynligsomAnnonse;
    

    public ControllerRegistrerAnnonse(HashSet<Annonse> annonseliste, HashSet<Person> personliste, Bolig bolig) {
        this.annonseliste = annonseliste;
        this.personliste = personliste;
        this.bolig = bolig;
        vindu = new AnnonseRegVindu("Registrering av annonser");
        vindu.addAnnonsePanelListener(new KnappLytter());
    }

    public ControllerRegistrerAnnonse(HashSet<Annonse> annonseliste, HashSet<Person> personliste, Annonse annonseSomEndres) {
        this.annonseliste = annonseliste;
        this.personliste = personliste;
        this.bolig = annonseSomEndres.getBolig();
        this.annonseSomEndres = annonseSomEndres;
        vindu = new AnnonseRegVindu("Registrering av annonser");
        vindu.addAnnonsePanelListener(new KnappLytter());
    }

    private void fyllUtBoliginfo() {
        if( bolig != null ){
            if (finnPersonInfo()) {        
                vindu.getBoligIDInfo().setText(String.valueOf(bolig.getBoligID()));
                vindu.getBoligAdresseInfo().setText(bolig.getAdresse());
                vindu.getBoligPostNrInfo().setText(bolig.getPostnummer());
                vindu.getBoligPostStedInfo().setText(bolig.getPoststed());
                vindu.getBoligEierFornavnInfo().setText(fornavn);
                vindu.getBoligEierEtternavnInfo().setText(etternavn);
                vindu.getBoligEierEpostInfo().setText(epost);
                vindu.getBoligEierTlfInfo().setText(tlf);
            }else{

            }
        }else{
            System.out.println("Boligenobjektet finnes ikke!");
        }
    }
    
    private void henteAnnonsefeltene(){
        try{
            depositum = Integer.parseInt(vindu.getDepositum().getText());
            utleiepris = Integer.parseInt(vindu.getUtleiepris().getText());
//            utlopsDato = vindu.getUtlopsDato().
            erSynligsomAnnonse = vindu.getErSynligSomAnnonse().isSelected();
            eiersKrav = vindu.getEiersKrav().getText();
        }catch(Exception ex){
            System.out.println("Fant ikke feltene.");
        }
    }

    private boolean finnPersonInfo() {
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

    class KnappLytter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(vindu.getLagreButton())) {

            }
        }

    }
}
