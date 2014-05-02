package view;

//Laget av Petter.
//Editert av Lukas. Lagt til getters og lyttere for komponenter 26.04
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import view.CustomJButton;
import view.CustomJCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import lib.Konstanter;
import lib.RegexTester;

public class TopPanelAnnonse extends AbstractPanel {

    private KnappeGruppeHoyre knappeGruppeHoyre;
    private CheckBoxKnappeGruppe checkBoxKnappeGruppe;
    private CustomJButton sokeKnapp;

    public TopPanelAnnonse(String borderTitle, int dimHeight, int dimWidth) {
        super(borderTitle, dimHeight, dimWidth);
        knappeGruppeHoyre = new KnappeGruppeHoyre();
        checkBoxKnappeGruppe = new CheckBoxKnappeGruppe();
        sokeKnapp = new CustomJButton("Søk");

        setVisible(true);
        setLayout(new FlowLayout(FlowLayout.LEFT, 40, 0));
        
        add(checkBoxKnappeGruppe);
        add(knappeGruppeHoyre);
        add(sokeKnapp);
    }

    ////////////GETTERS////////////
    public KnappeGruppeHoyre getKnappeGruppeHoyre() {
        return knappeGruppeHoyre;
    }

    public CheckBoxKnappeGruppe getCheckBoxKnappeGruppe() {
        return checkBoxKnappeGruppe;
    }

    public CustomJButton getSokeKnapp() {
        return sokeKnapp;
    }

    public JComboBox getPostStedBox() {
        return knappeGruppeHoyre.getPostStedBox();
    }

    public JComboBox getBoligTypeBox() {
        return knappeGruppeHoyre.getBoligTypeBox();
    }

    public CustomJTextField getMinPrisFelt() {
        return knappeGruppeHoyre.getMinPrisFelt();
    }

    public CustomJTextField getMaksPrisFelt() {
        return knappeGruppeHoyre.getMaksPrisFelt();
    }

    public CustomJTextField getMinArealFelt() {
        return knappeGruppeHoyre.getMinArealFelt();
    }

    public CustomJTextField getMaksArealFelt() {
        return knappeGruppeHoyre.getMaksArealFelt();
    }

    public CustomJCheckBox getBalkongCheckBox() {
        return checkBoxKnappeGruppe.getBalkongCheckBox();
    }

    public CustomJCheckBox getFellesvaskCheckBox() {
        return checkBoxKnappeGruppe.getFellesvaskCheckBox();
    }

    public CustomJCheckBox getHageCheckBox() {
        return checkBoxKnappeGruppe.getHageCheckBox();
    }

    public CustomJCheckBox getKjellerCheckBox() {
        return checkBoxKnappeGruppe.getKjellerCheckBox();
    }
    ////////////SLUTTT PÅ GETTERS////////////

    ////////////SETTERS////////////
    public void setPostStedBox(JComboBox postStedBox) {
        knappeGruppeHoyre.setPostStedBox(postStedBox);
    }

    public void setBoligTypeBox(JComboBox boligTypeBox) {
        knappeGruppeHoyre.setBoligTypeBox(boligTypeBox);
    }
    ////////////SLUTT PÅ SETTERS////////////

    public void addKnappLytter(ActionListener lytter) {
        sokeKnapp.addActionListener(lytter);
    }

    /**
     * Legger til innhold i comboboksen for poststeder. Trenger denne ettersom
     * det gikk ikke å sette en hel comboboks element.
     *
     * @param item
     */
    public void addItemPoststedBox(Object item) {
        knappeGruppeHoyre.addItemPoststedBox(item);
    }

    /**
     * Legger til innhold i combobokse for poststeder.
     *
     * @param item
     */
    public void addItemBoligTypeBox(Object item) {
        knappeGruppeHoyre.addItemBoligTypeBox(item);
    }

    /**
     * Privat klasse for kombobokser og tekstfelter
     */
    private class KnappeGruppeHoyre extends AbstractPanel {

        private JComboBox postStedBox, boligTypeBox;
        private JLabel prisLabel, bindestrekLabel, arealLabel, bindestrekLabel2;
        private CustomJTextField minPrisFelt, maksPrisFelt, minArealFelt, maksArealFelt;
        private JPanel fyllPanel;

        public KnappeGruppeHoyre() {
            postStedBox = new JComboBox<>();
            boligTypeBox = new JComboBox<>();
            prisLabel = new JLabel("Pris");
            bindestrekLabel = new JLabel("-");
            arealLabel = new JLabel("Areal");
            bindestrekLabel2 = new JLabel("-");
            minPrisFelt = new CustomJTextField("min", RegexTester.PRIS, 5);
            maksPrisFelt = new CustomJTextField("maks", RegexTester.PRIS, 5);
            minArealFelt = new CustomJTextField("min", RegexTester.KVM_BOLIG, 5);
            maksArealFelt = new CustomJTextField("maks", RegexTester.KVM_BOLIG, 5);
            fyllPanel = new JPanel();

            fyllPanel.setPreferredSize(new Dimension(120, 110));
            fyllPanel.setBackground(Konstanter.BAKGRUNNSFARGEPANEL);
            setVisible(true);
            setLayout(new FlowLayout());

            add(postStedBox);
            add(boligTypeBox);
            add(fyllPanel);
            add(prisLabel);
            add(minPrisFelt);
            add(bindestrekLabel);
            add(maksPrisFelt);
            add(arealLabel);
            add(minArealFelt);
            add(bindestrekLabel2);
            add(maksArealFelt);

        }

        public JComboBox getPostStedBox() {
            return postStedBox;
        }

        public JComboBox getBoligTypeBox() {
            return boligTypeBox;
        }

        public CustomJTextField getMinPrisFelt() {
            return minPrisFelt;
        }

        public CustomJTextField getMaksPrisFelt() {
            return maksPrisFelt;
        }

        public CustomJTextField getMinArealFelt() {
            return minArealFelt;
        }

        public CustomJTextField getMaksArealFelt() {
            return maksArealFelt;
        }

        public void setPostStedBox(JComboBox postStedBox) {
            this.postStedBox = postStedBox;
        }

        public void addItemPoststedBox(Object item) {
            postStedBox.addItem(item);
        }

        public void setBoligTypeBox(JComboBox boligTypeBox) {
            this.boligTypeBox = boligTypeBox;
        }

        public void addItemBoligTypeBox(Object item) {
            boligTypeBox.addItem(item);
        }

        /**
         * Lytter til kombobkser. Med hjelp av lytteren lagd til her kan vi
         * sortere ummidelbart etter at brukeren har gjort sit valg.
         *
         * @param lytter
         */
        public void addAnnonseFieldsListener(ActionListener lytter) {
            postStedBox.addActionListener(lytter);
            boligTypeBox.addActionListener(lytter);
        }
    }

    /**
     * Privat klasse for sjekkbokser
     */
    private class CheckBoxKnappeGruppe extends AbstractPanel {

        private CustomJCheckBox balkongCheckBox, fellesvaskCheckBox, hageCheckBox, kjellerCheckBox;

        public CheckBoxKnappeGruppe() {
            balkongCheckBox = new CustomJCheckBox("Balkong");
            fellesvaskCheckBox = new CustomJCheckBox("Fellesvask");
            hageCheckBox = new CustomJCheckBox("Hage");
            kjellerCheckBox = new CustomJCheckBox("Kjeller");

            setVisible(true);
            setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
            setPreferredSize(new Dimension(90, 110));
            
            add(balkongCheckBox);
            add(fellesvaskCheckBox);
            add(hageCheckBox);
            add(kjellerCheckBox);
        }

        public CustomJCheckBox getBalkongCheckBox() {
            return balkongCheckBox;
        }

        public CustomJCheckBox getFellesvaskCheckBox() {
            return fellesvaskCheckBox;
        }

        public CustomJCheckBox getHageCheckBox() {
            return hageCheckBox;
        }

        public CustomJCheckBox getKjellerCheckBox() {
            return kjellerCheckBox;
        }

        /**
         * Lytter til sjekkbokser. Trenger egentlig ikke denne men da kan vi
         * filtrere all data "on the fly" slik at brukeren ikke trenger å klikke
         * på "Søk" eller liknande.
         *
         * @param lytter
         */
        public void addAnnonseChecboxListener(ActionListener lytter) {
            balkongCheckBox.addActionListener(lytter);
            fellesvaskCheckBox.addActionListener(lytter);
            hageCheckBox.addActionListener(lytter);
            kjellerCheckBox.addActionListener(lytter);
        }
    }
}
