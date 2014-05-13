package model;

public class TabellModellKontrakt extends TabellModell {

    public TabellModellKontrakt() {
        super.overskrift = new String[]{"KontraktID", "BoligID", "LeietakerID", "Varighet"};
    }

    @Override
    public Object getValueAt(int rad, int kolonne) {
        Kontrakt kontrakt = (Kontrakt) mottattArray.get(rad);
        switch (kolonne) {
            case 0:
                return kontrakt.getAnnonseID();
            case 1:
                return kontrakt.getBoligID().getBoligID();
            case 2:
                return kontrakt.getLeietakerID();
            case 3:
                return kontrakt.getLeietidIMnd();
        }
        return null;
    }

}
