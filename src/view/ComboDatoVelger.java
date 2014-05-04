package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JComboBox;
import view.registrer.CustomSubPanel;

/**
 * Gir en CustomSubPanel med comboboks som består av en datovelger. Datovelgeren
 * tar hensyn til hvilket år og måned som er valgt og populerer velgeren for
 * dagsnummer med korrekt antall dager. Som standard blir datovelgeren fyllt opp
 * med datoer 10 år fremover.
 *
 * File: ComboDatoVelger.java Package: view Project: ServiciosDeVivienda May 4,
 * 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class ComboDatoVelger extends CustomSubPanel {

    private final JComboBox dagCombo, manedCombo, arCombo;
    private CustomSubPanel datoPickPanel;

    public ComboDatoVelger() {
        super(new FlowLayout());

        dagCombo = new JComboBox<>();
        manedCombo = new JComboBox<>();
        arCombo = new JComboBox<>();

        add(arCombo);
        add(manedCombo);
        add(dagCombo);

        //Fyller opp combobokser med data ved oppstart
        fyllDatoComboBoxerMndAr(getGjeldendeAr());
        setAntallDager();

        ///START VALG AV DATO///
        /**
         * Dersom man endrer år elle måned endres antall dager i comboboksen for
         * dager
         */
        arCombo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setAntallDager();
//                visValgtDato();
            }
        });
        manedCombo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setAntallDager();
//                visValgtDato();
            }
        });
        ///SLUTT PÅ VALG AV DATO///

    }

    private int getGjeldendeAr() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * Fyller comboboksen med data for 10 år fremover.
     *
     * @param ar Startår med hvilket man skal starte med fyllingen med. For å
     * fylle boksen med aktuelt år send inn getGjeldendeAr som parameter.
     */
    private void fyllDatoComboBoxerMndAr(int ar) {

        for (int i = ar; i <= (ar + 10); i++) {
            arCombo.addItem(i);
        }
        for (int i = 1; i <= 12; i++) {
            String mnd = String.valueOf(i);
            if (i < 10) {
                mnd = "0" + mnd;
            }
            manedCombo.addItem(mnd);
        }

    }

    /**
     * Setter antall dager for comboboxen etter at man har valgt år og måned.
     */
    private void setAntallDager() {
        dagCombo.removeAllItems();
        int ar = Integer.parseInt(arCombo.getSelectedItem().toString());
        int mnd = Integer.parseInt(manedCombo.getSelectedItem().toString());
        int dager = getAntallDager(ar, mnd);

        for (int i = 1; i <= dager; i++) {
            String dag = String.valueOf(i);
            if (i < 10) {
                dag = "0" + dag;
            }
            dagCombo.addItem(dag);
        }

//        Melding.visMelding("", "År: "+ar+"\nMåned:"+mnd+"\nAntall dager:"+getAntallDager(ar, mnd));
    }

    /**
     * Returnerer antall dager i en måned girr år og måned.
     *
     * @param ar
     * @param mnd
     * @return int antall dager mellom 1 og 31
     */
    private int getAntallDager(int ar, int mnd) {
        Calendar cal = new GregorianCalendar(ar, mnd - 1, 1);
        int antallDager = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return antallDager;
    }

    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
    //--//                                                                      //--//
    //--//                      GETTERS OG SETTERS                              //--//
    //--//                                                                      //--//
    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//
    public JComboBox getDagCombo() {
        return dagCombo;
    }

    public JComboBox getManedCombo() {
        return manedCombo;
    }

    public JComboBox getArCombo() {
        return arCombo;
    }

    public int getDag() {
        return Integer.parseInt(getDagCombo().getSelectedItem().toString());
    }

    public int getManed() {
        return Integer.parseInt(getManedCombo().getSelectedItem().toString());
    }

    public int getAr() {
        return Integer.parseInt(getArCombo().getSelectedItem().toString());
    }

    public void setDato(int ar, int mnd, int dag) {
        getArCombo().addItem(ar);
        getArCombo().setSelectedItem(ar);

        getManedCombo().setSelectedItem(leggTilNullForran(mnd));
        getDagCombo().setSelectedItem(leggTilNullForran(dag));
    }

    /**
     * Bruker denne for å få en pen formatering i combokser for måneder og
     * dager. Vi il gjerne har en null forran slik at det blir jevnt satt opp.
     *
     * @param tall
     * @return String
     */
    private String leggTilNullForran(int tall) {
        if (tall >= 10) {
            return String.valueOf(tall);
        }
        return "0" + String.valueOf(tall);
    }

}
