package model;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.util.Collection;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;


public class TabellModellBolig extends TabellModell {

    private Bolig bolig;
    private Collection annonseliste;
    
    public TabellModellBolig(Collection annonseliste){
        super.overskrift = new String[]{"BoligID", "Adresse", "Annonsert","Utleid"};
        this.annonseliste = annonseliste;
        
    }

    @Override
    public Object getValueAt(int rad, int kolonne) {
        bolig = (Bolig) super.mottattArray.get(rad);
        switch (kolonne) {
            case 0:
                return bolig.getBoligID();
            case 1:
                return bolig.getAdresse();
            case 2:
                return erBoligenAnnonsert(bolig) ? "Ja" : "Nei";
            case 3:
                if (bolig.isErUtleid()) {
                    return "Ja";
                }
                return "Nei";
        }
        return null;
    }

    /**
     * Sjekker om boligen er annonsert og returnerer svaret.
     * @param bolig Boligen det gjelder
     * @return 
     */
    public boolean erBoligenAnnonsert(Bolig bolig){
        
        Iterator<Annonse> iter = annonseliste.iterator();
        Annonse temp;
        
        while(iter.hasNext()){
            temp = iter.next();
            if(temp.getBoligID() == bolig.getBoligID()){
                return temp.isErSynlig();
            }
        }
        return false;
    }
}
