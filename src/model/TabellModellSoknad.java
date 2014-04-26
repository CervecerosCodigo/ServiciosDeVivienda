package model;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

public class TabellModellSoknad extends TabellModell {

    public TabellModellSoknad() {
        super.overskrift = new String[]{"AnnonseID", "Adresse", "Søkers fornavn", "Søkers etternavn"};
    }

    @Override
    public Object getValueAt(int rad, int kolonne) {
        Soknad soknad = (Soknad) mottattArray[rad];
        switch (kolonne) {
            case 0:
                return soknad.getAnnonseObjekt().getAnnonseID();
            case 1:
                return soknad.getAnnonseObjekt().getBolig().getAdresse();
            case 2:
                return soknad.getLeietakerObjekt().getFornavn();
            case 3:
                return soknad.getLeietakerObjekt().getEtternavn();
        }
        return null;
    }

}
