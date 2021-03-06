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
 */
public class ComboDatoVelger extends CustomSubPanel {

    private final JComboBox dagCombo, manedCombo, arCombo;

    /**
     * En tom konstruktør som setter opp comboboksen med årstall 10 år
     * fremover. Skal brukes når man initiliserer klassen.
     */
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
         * Dersom man endrer år eller måned endres antall dager i comboboksen for
         * dager
         */
        arCombo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setAntallDager();
            }
        });
        manedCombo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setAntallDager();
            }
        });
        ///SLUTT PÅ VALG AV DATO///
    }

    /**
     * Brukes internt med hensikt for å få aktuelt år.
     * @return 
     */
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
            if (i < 10) 
                mnd = "0" + mnd;
            
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
            if (i < 10) 
                dag = "0" + dag;
            
            dagCombo.addItem(dag);
        }
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

    /**
     * Returnerer valgt dag.
     *
     * @return
     */
    public int getDag() {
        return Integer.parseInt(getDagCombo().getSelectedItem().toString());
    }

    /**
     * Returnerer valgt måned.
     *
     * @return
     */
    public int getManed() {
        return Integer.parseInt(getManedCombo().getSelectedItem().toString());
    }

    /**
     * Returnerer valgt år.
     *
     * @return
     */
    public int getAr() {
        return Integer.parseInt(getArCombo().getSelectedItem().toString());
    }

    /**
     * Brukse til å sette opp eget dato i comboksene. Dette er brukbart i
     * samband med endring av registrerte data i registeret.
     *
     * @param ar int
     * @param mnd int
     * @param dag int
     */
    public void setDato(int ar, int mnd, int dag) {
        getArCombo().addItem(ar);
        getArCombo().setSelectedItem(ar);

        getManedCombo().setSelectedItem(leggTilNullForran(mnd));
        getDagCombo().setSelectedItem(leggTilNullForran(dag));
    }
    
    /**
     * Returnere et ferdig kalenderobjekt utefra valgt dato.
     * @return 
     */
    public Calendar opprettKalenderobjekt() {
        return new GregorianCalendar(getAr(), getManed(), getManed());
    }

    /**
     * Bruker denne for å få en pen formatering i combokser for måneder og
     * dager. Vi il gjerne har en null forran slik at det blir jevnt satt opp.
     *
     * @param tall
     * @return String
     */
    private String leggTilNullForran(int tall) {
        if (tall >= 10)
            return String.valueOf(tall);
        
        return "0" + String.valueOf(tall);
    }

}
