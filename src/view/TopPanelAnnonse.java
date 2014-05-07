package view;

//Laget av Petter.
//Editert av Lukas. Lagt til getters og lyttere for komponenter 26.04
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.registrer.CustomSubPanel;
import lib.BildeFilSti;
import lib.RegexTester;

public class TopPanelAnnonse extends AbstractPanel {

    private KnappeGruppeHoyre knappeGruppeHoyre;
    private CheckBoxKnappeGruppe checkBoxKnappeGruppe;
    private CustomJButton sokeKnapp;
    private ImageIcon bilde;
    private JLabel bildeLabel;

    public TopPanelAnnonse(String borderTitle, int dimHeight, int dimWidth) {
        super(borderTitle, dimHeight, dimWidth);
        knappeGruppeHoyre = new KnappeGruppeHoyre();
        checkBoxKnappeGruppe = new CheckBoxKnappeGruppe();
        sokeKnapp = new CustomJButton("Søk");
        bilde = new ImageIcon(new BildeFilSti().getAbsoluteGalleryPath()+"/default/boligLogo.png");
        bildeLabel = new JLabel(bilde);
        
        setVisible(true);
        setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0));
        
        add(checkBoxKnappeGruppe);
        add(knappeGruppeHoyre);
        add(sokeKnapp);
        add(bildeLabel);
        
        bildeLabel.setPreferredSize(new Dimension(bilde.getIconWidth(), bilde.getIconHeight()));
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
    
	public JComboBox getPostStedBox() {
		return knappeGruppeHoyre.getPostStedBox();
	}
	
	public JComboBox getBoligTypeBox() {
		return knappeGruppeHoyre.getBoligTypeBox();
	}
    ////////////SLUTTT PÅ GETTERS////////////
	
	public void addItemPoststedBox(Object item) {
		knappeGruppeHoyre.addItemPoststedBox(item);
	}
	
	public void addItemBoligTypeBox(Object item) {
		knappeGruppeHoyre.addItemBoligTypeBox(item);
	}
    
    public void addKnappLytter(ActionListener lytter) {
        sokeKnapp.addActionListener(lytter);
    }

    private class KnappeGruppeHoyre extends AbstractPanel {

        private JLabel prisLabel, arealLabel;
        private CustomJTextField minPrisFelt, maksPrisFelt, minArealFelt, maksArealFelt;
        private JComboBox postStedBox, boligTypeBox;
        private CustomSubPanel postStedBoxPanel, boligTypeBoxPanel;

        public KnappeGruppeHoyre() {
            prisLabel = new JLabel("Pris:");
            arealLabel = new JLabel("Areal:");
            minPrisFelt = new CustomJTextField("min", RegexTester.PRIS, 5);
            maksPrisFelt = new CustomJTextField("maks", RegexTester.PRIS, 5);
            minArealFelt = new CustomJTextField("min", RegexTester.KVM_BOLIG, 5);
            maksArealFelt = new CustomJTextField("maks", RegexTester.KVM_BOLIG, 5);
            postStedBox = new JComboBox<>();
            boligTypeBox = new JComboBox<>();
            postStedBoxPanel = new CustomSubPanel(new FlowLayout());
            boligTypeBoxPanel = new CustomSubPanel(new FlowLayout());
            
            setVisible(true);
            setLayout(new GridLayout(2,4,-7,-5));
            prisLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            arealLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            
            postStedBoxPanel.add(postStedBox);
            boligTypeBoxPanel.add(boligTypeBox);

            add(prisLabel);
            add(minPrisFelt);
            add(maksPrisFelt);
            add(postStedBoxPanel);
            add(arealLabel);
            add(minArealFelt);
            add(maksArealFelt);
            add(boligTypeBoxPanel);
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
        
    	public JComboBox getPostStedBox() {
    		return postStedBox;
    	}
    	
    	public JComboBox getBoligTypeBox() {
    		return boligTypeBox;
    	}
    	
    	public void addItemPoststedBox(Object item) {
            postStedBox.addItem(item);
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

    private class CheckBoxKnappeGruppe extends AbstractPanel {

        private CustomJCheckBox balkongCheckBox, fellesvaskCheckBox, hageCheckBox, kjellerCheckBox;

        public CheckBoxKnappeGruppe() {
            balkongCheckBox = new CustomJCheckBox("Balkong");
            fellesvaskCheckBox = new CustomJCheckBox("Fellesvask");
            hageCheckBox = new CustomJCheckBox("Hage");
            kjellerCheckBox = new CustomJCheckBox("Kjeller");

            setVisible(true);
            setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5));
            setPreferredSize(new Dimension(100, 100));
            
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
