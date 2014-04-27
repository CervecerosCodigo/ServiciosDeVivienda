package model;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import lib.Konstanter;

public class TabellModellAnnonse extends TabellModell {


    
    public TabellModellAnnonse() {
        super.overskrift = new String[]{"AnnonseID", "Adresse", "Depositum", "Pris pr mnd"};

    }

    @Override
    public Object getValueAt(int rad, int kolonne) {
        Annonse annonse = (Annonse) super.mottattArray[rad];
        switch (kolonne) {
            case 0:
                return annonse.getAnnonseID();
            case 1:
                return annonse.getBolig().getAdresse();
            case 2:
                return Konstanter.nf.format(annonse.getDepositum());
            case 3:
                return Konstanter.nf.format(annonse.getUtleiepris());
        }
        return null;
    }
}
