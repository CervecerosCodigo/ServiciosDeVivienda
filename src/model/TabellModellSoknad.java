package model;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.Color;
import javax.swing.table.DefaultTableCellRenderer;

public class TabellModellSoknad extends TabellModell {

    public TabellModellSoknad() {
        super.overskrift = new String[]{"AnnonseID", "Søkers epost", "Behandlet", "Godkjent"};

    }

    @Override
    public Object getValueAt(int rad, int kolonne) {
        Soknad soknad = (Soknad) mottattArray.get(rad);
        
        switch (kolonne) {
            case 0:
                return soknad.getAnnonseObjekt().getAnnonseID();
            case 1:
                return soknad.getLeietakerObjekt().getEpost();
            case 2:
                return soknad.ErBehandlet() ? "Ja" : "Nei";
            case 3:
                return soknad.ErGodkjent() ? "Ja" : "Nei";
        }
        return null;
    }
    
}
