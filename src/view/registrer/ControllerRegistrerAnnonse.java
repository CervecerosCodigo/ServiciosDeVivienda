package view.registrer;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import controller.registrer.ControllerRegistrerUtleier;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import model.Annonse;
import model.Bolig;



public class ControllerRegistrerAnnonse {

    private AnnonseRegVindu vindu;
    private HashSet<Bolig> boligliste;
    private HashSet<Annonse> annonseliste;
    private Annonse annonseSomEndres;
    
    public ControllerRegistrerAnnonse(HashSet<Bolig> boligliste, HashSet<Annonse> annonseliste, Bolig bolig){
        this.boligliste = boligliste;
        this.annonseliste = annonseliste;
        vindu = new AnnonseRegVindu("Registrering av annonser");
        vindu.addAnnonsePanelListener(new KnappLytter());
    }
    
    public ControllerRegistrerAnnonse(HashSet<Bolig> boligliste, HashSet<Annonse> annonseliste, Annonse annonseSomEndres){
        this.boligliste = boligliste;
        this.annonseliste = annonseliste;
        this.annonseSomEndres = annonseSomEndres;
        vindu = new AnnonseRegVindu("Registrering av annonser");        
        vindu.addAnnonsePanelListener(new KnappLytter());        
    }
    
    private void registrerAnnonse(){
        
    }
    
    class KnappLytter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(vindu.getLagreButton())) {
                
            }
        }

    }    
}
