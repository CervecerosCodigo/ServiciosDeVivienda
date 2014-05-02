package controller;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.event.*;
import javax.swing.JTable;
import lib.ObjektType;
import view.ArkfaneTemplate;

public class ControllerBunnPanel {

    private KnappeLytter lytter;

    public ControllerBunnPanel() {

    }

    public void settKnappeLytter(ArkfaneTemplate vindu) {
        vindu.getBunnpanel().addKnappeLytter(lytter = new KnappeLytter(vindu));
    }

    /**
     * private lytteklasse for Endre-knappen i bunnpanelet.
     */
    class KnappeLytter implements ActionListener {

        ArkfaneTemplate vindu;
        int raderITabell;
        JTable tabell;

        public KnappeLytter(ArkfaneTemplate vindu) {
            this.vindu = vindu;
            tabell = vindu.getVenstrepanel().getTable();
        }

        public int finnValgtRadITabell() {
            try {
                int rad = tabell.getSelectedRow();
                rad = tabell.convertRowIndexToModel(rad);
                return rad;
            } catch (ArrayIndexOutOfBoundsException aiobe) {

            }
            return 0;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            raderITabell = tabell.getModel().getRowCount();

            /**
             * FixME Kan ikke lage ferdig før vi har vinduer for
             * oppretting/endring av objekter.
             */
            if (e.getSource().equals(vindu.getBunnpanel().getEndreKnapp())) {
                try {
                    int valgtRad = finnValgtRadITabell();
                    if (tabell.getModel().getClass().getSimpleName().equals("tabellModellPerson")) {
                        
                    }
                    if (tabell.getModel().getClass().getSimpleName().equals("tabellModellBolig")) {

                    }
                    if (tabell.getModel().getClass().getSimpleName().equals("tabellModellAnnonse")) {

                    }
                } catch (Exception ex) {

                }

            } else if (e.getSource().equals(vindu.getBunnpanel().getTilbakeKnapp())) {
                vindu.getVenstrepanel().getTable().changeSelection(tabell.getSelectedRow() - 1, 0, false, false);

            } else if (e.getSource().equals(vindu.getBunnpanel().getFremKnapp())) {
                int valgtRad = vindu.getVenstrepanel().getTable().getSelectedRow();

                if (valgtRad + 1 < vindu.getVenstrepanel().getTable().getRowCount()) {
                    vindu.getVenstrepanel().getTable().changeSelection(tabell.getSelectedRow() + 1, 0, false, false);
                }
            }
        }

    }
}
