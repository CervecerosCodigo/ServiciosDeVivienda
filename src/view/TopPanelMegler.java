package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lib.BildeFilSti;
import lib.Ikoner;
import lib.RegexTester;
import view.registrer.CustomSubPanel;

public class TopPanelMegler extends AbstractPanel {

    private CustomSubPanel sokePanel, knappePanel, fyllPanel;
    private StatistikkPanel statistikkPanel;
    private MeglerRadioKnapper meglerRadioKnapper;
    private CustomJTextField sokeFelt;
    private CustomJButton sokeKnapp, nyKontraktItem, nyUtleierItem, nyBoligItem, nyAnnonseItem;
    private ImageIcon bilde;
    private JLabel bildeLabel;
    
    private final static int BREDDE = 128, HOYDE = 30;

    TopPanelMegler(String borderTitle, int dimHeight, int dimWidth) {

        super(borderTitle, dimHeight, dimWidth);
        
        meglerRadioKnapper = new MeglerRadioKnapper();
        statistikkPanel = new StatistikkPanel();
        sokePanel = new CustomSubPanel(new FlowLayout());
        knappePanel = new CustomSubPanel(new FlowLayout());
        fyllPanel = new CustomSubPanel(new FlowLayout());
        sokeFelt = new CustomJTextField("Søk", RegexTester.KUN_BOKSTAVER_ELLER_TALL, 20);
        sokeKnapp = new CustomJButton(Ikoner.SEARCH);
        nyKontraktItem = new CustomJButton("Ny kontrakt", BREDDE, HOYDE);
        nyUtleierItem = new CustomJButton("Ny utleier", BREDDE, HOYDE);
        nyBoligItem = new CustomJButton("Ny bolig", BREDDE, HOYDE);
        nyAnnonseItem = new CustomJButton("Ny annonse", BREDDE, HOYDE);
        bilde = new ImageIcon(new BildeFilSti().getAbsoluteGalleryPath()+"/default/boligLogo.png");
        bildeLabel = new JLabel(bilde);
        
        add(fyllPanel);
        add(meglerRadioKnapper);
        add(sokePanel);
        add(knappePanel);
        add(statistikkPanel);
        add(bildeLabel);
        
        sokePanel.add(sokeFelt);
        sokePanel.add(sokeKnapp);
        knappePanel.add(nyUtleierItem);
        knappePanel.add(nyBoligItem);
        knappePanel.add(nyAnnonseItem);
        knappePanel.add(nyKontraktItem);

        setLayout(new FlowLayout(FlowLayout.LEADING));
        setVisible(true);
        
        sokePanel.setPreferredSize(new Dimension(350,35));
        knappePanel.setPreferredSize(new Dimension(270,70));
        fyllPanel.setPreferredSize(new Dimension(30,50));
        bildeLabel.setPreferredSize(new Dimension(bilde.getIconWidth(), bilde.getIconHeight()));

    }
    
    public StatistikkPanel getStatistikkPanel() {
    	return statistikkPanel;
    }

    public MeglerRadioKnapper getMeglerRadioKnapper() {
        return meglerRadioKnapper;
    }

    public CustomJTextField getSokeFelt() {
        return sokeFelt;
    }

    public CustomJButton getSokeKnapp() {
        return sokeKnapp;
    }

    public CustomJButton getNyKontraktItem() {
        return nyKontraktItem;
    }

    public CustomJButton getNyUtleierItem() {
        return nyUtleierItem;
    }

    public CustomJButton getNyBoligItem() {
        return nyBoligItem;
    }

    public CustomJButton getNyAnnonseItem() {
        return nyAnnonseItem;
    }

    public CustomJRadioButton getSoknaderRadio() {
        return meglerRadioKnapper.getSoknaderRadio();
    }

    public CustomJRadioButton getAnnonserRadio() {
        return meglerRadioKnapper.getAnnonserRadio();
    }

    public CustomJRadioButton getBoligerRadio() {
        return meglerRadioKnapper.getBoligerRadio();
    }

    public CustomJRadioButton getUtleiereRadio() {
        return meglerRadioKnapper.getUtleiereRadio();
    }

    public CustomJRadioButton getLeietakereRadio() {
        return meglerRadioKnapper.getLeietakereRadio();
    }
    
    public CustomJRadioButton getKontraktRadio() {
        return meglerRadioKnapper.getKontraktRadio();
    }

    public void leggTilRadioLytter(ActionListener lytter) {
        meglerRadioKnapper.addRadioListener(lytter);
    }

    public void leggTilKnappeLytter(ActionListener lytter) {
        nyKontraktItem.addActionListener(lytter);
        sokeKnapp.addActionListener(lytter);
        nyAnnonseItem.addActionListener(lytter);
        nyBoligItem.addActionListener(lytter);
        nyUtleierItem.addActionListener(lytter);
    }
    
    private class MeglerRadioKnapper extends AbstractPanel {

        private CustomJRadioButton soknaderRadio, annonserRadio, boligerRadio, utleiereRadio, leietakereRadio, kontraktRadio;
        private ButtonGroup radioGroup;

        public MeglerRadioKnapper() {
            soknaderRadio = new CustomJRadioButton("Søknader");
            annonserRadio = new CustomJRadioButton("Annonser");
            boligerRadio = new CustomJRadioButton("Boliger");
            utleiereRadio = new CustomJRadioButton("Utleiere");
            leietakereRadio = new CustomJRadioButton("Leietakere");
            kontraktRadio = new CustomJRadioButton("Kontrakt");
            radioGroup = new ButtonGroup();

            radioGroup.add(soknaderRadio);
            radioGroup.add(annonserRadio);
            radioGroup.add(boligerRadio);
            radioGroup.add(utleiereRadio);
            radioGroup.add(leietakereRadio);
            radioGroup.add(kontraktRadio);

            setVisible(true);
            setPreferredSize(new Dimension(90, 100));
            setLayout(new FlowLayout(FlowLayout.LEFT, 0, -2));
            
            add(soknaderRadio);
            add(annonserRadio);
            add(boligerRadio);
            add(utleiereRadio);
            add(leietakereRadio);
            add(kontraktRadio);

//            soknaderRadio.setSelected(true);
        }

        public CustomJRadioButton getSoknaderRadio() {
            return soknaderRadio;
        }

        public CustomJRadioButton getAnnonserRadio() {
            return annonserRadio;
        }

        public CustomJRadioButton getBoligerRadio() {
            return boligerRadio;
        }

        public CustomJRadioButton getUtleiereRadio() {
            return utleiereRadio;
        }

        public CustomJRadioButton getLeietakereRadio() {
            return leietakereRadio;
        }
        
        public CustomJRadioButton getKontraktRadio() {
            return kontraktRadio;
        }

        public void addRadioListener(ActionListener lytter) {
            boligerRadio.addActionListener(lytter);
            utleiereRadio.addActionListener(lytter);
            leietakereRadio.addActionListener(lytter);
            soknaderRadio.addActionListener(lytter);
            annonserRadio.addActionListener(lytter);
            kontraktRadio.addActionListener(lytter);

        }
    }
    
    public class StatistikkPanel extends CustomSubPanel {
    	
    	private JLabel ledigeBoligerAntall, ledigeBoliger, kontrakterAntall, kontrakter;
    	private CustomSubPanel Panel1, Panel2;
    	
    	public StatistikkPanel() {
    		super(new GridLayout(2,1));
    		ledigeBoligerAntall = new JLabel("0"); // Startverdi
    		ledigeBoliger = new JLabel("Ledige boliger:");
    		kontrakterAntall = new JLabel("0"); // Startverdi
    		kontrakter = new JLabel("Kontrakter hittil i år:");
    		Panel1 = new CustomSubPanel(new FlowLayout(FlowLayout.LEFT));
    		Panel2 = new CustomSubPanel(new FlowLayout(FlowLayout.LEFT));
    		
    		Panel1.add(ledigeBoliger);
    		Panel1.add(ledigeBoligerAntall);
    		Panel2.add(kontrakter);
    		Panel2.add(kontrakterAntall);
    		add(Panel1);
    		add(Panel2);
    		
    		Panel1.setPreferredSize(new Dimension(230,30));
    		Panel2.setPreferredSize(new Dimension(230,30));
    		
    		ledigeBoligerAntall.setForeground(Color.GRAY);
    		ledigeBoliger.setForeground(Color.GRAY);
    		kontrakterAntall.setForeground(Color.GRAY);
    		kontrakter.setForeground(Color.GRAY);
    		
    		setPreferredSize(new Dimension(230,60));
    		setVisible(true);
    	}
    	
    	public void OppdaterStatistikk(int ledigeBoligerAntall, int kontrakterAntall) {
    		this.ledigeBoligerAntall.setText(Integer.toString(ledigeBoligerAntall));
    		this.kontrakterAntall.setText(Integer.toString(kontrakterAntall));
    	}
    }
}

