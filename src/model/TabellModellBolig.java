package model;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

public class TabellModellBolig extends TabellModell {

    
    public TabellModellBolig(){
        super.overskrift = new String[]{"BoligID", "EierID", "Adresse", "Utleid"};
        
    }

    @Override
    public Object getValueAt(int rad, int kolonne) {
        Bolig bolig = (Bolig) super.mottattArray[rad];
        switch (kolonne) {
            case 0:
                return bolig.getBoligID();
            case 1:
                return bolig.getPersonID();
            case 2:
                return bolig.getAdresse();
            case 3:
                if (bolig.isErUtleid()) {
                    return "Ja";
                }
                return "Nei";
        }
        return null;
    }

}
