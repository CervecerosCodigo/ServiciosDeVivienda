package model;

import lib.Konstanter;

public class TabellModellAnnonse extends TabellModell {
    
    public TabellModellAnnonse() {
        super.overskrift = new String[]{"AnnonseID", "Adresse", "Pris pr mnd", "Depositum"};

    }

    @Override
    public Object getValueAt(int rad, int kolonne) {
        Annonse annonse = (Annonse) super.mottattArray.get(rad);
        switch (kolonne) {
            case 0:
                return annonse.getAnnonseID();
            case 1:
                return annonse.getBolig().getAdresse();
            case 2:
                String utleie = Konstanter.nf.format(annonse.getUtleiepris());
                return utleie = utleie.substring(0, utleie.length()-3);
            case 3:
                String depo = Konstanter.nf.format(annonse.getDepositum());
                return depo = depo.substring(0, depo.length()-3);
        }
        return null;
    }
}
