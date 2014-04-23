package view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TopPanelAnnonse extends AbstractPanel {
	
	private KnappeGruppeHoyre knappeGruppeHoyre;
	private CheckBoxKnappeGruppe checkBoxKnappeGruppe;

    public TopPanelAnnonse(String borderTitle, int dimHeight, int dimWidth) {
        super(borderTitle, dimHeight, dimWidth);
        knappeGruppeHoyre = new KnappeGruppeHoyre();
        checkBoxKnappeGruppe = new CheckBoxKnappeGruppe();
        
        setVisible(true);
        setLayout(new FlowLayout(FlowLayout.LEFT, 40, 0));
        
        add(checkBoxKnappeGruppe);
        add(knappeGruppeHoyre);
    }
    
    private class KnappeGruppeHoyre extends JPanel {
    	
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
    		minPrisFelt = new CustomJTextField("min", null, 5);
    		maksPrisFelt = new CustomJTextField("maks", null, 5);
    		minArealFelt = new CustomJTextField("min", null, 5);
    		maksArealFelt = new CustomJTextField("maks", null, 5);
            fyllPanel = new JPanel();
            
            fyllPanel.setPreferredSize(new Dimension(120, 110));
    		
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
    }
    
    private class CheckBoxKnappeGruppe extends JPanel {
    	
    	private JCheckBox balkongCheckBox, fellesvaskCheckBox, hageCheckBox, kjellerCheckBox;
    	
    	public CheckBoxKnappeGruppe() {
    		balkongCheckBox = new JCheckBox("Balkong");
    		fellesvaskCheckBox = new JCheckBox("Fellesvask");
    		hageCheckBox = new JCheckBox("Hage");
    		kjellerCheckBox = new JCheckBox("Kjeller");
    		
    		setVisible(true);
    		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    		setPreferredSize(new Dimension(90, 110));
    		
    		add(balkongCheckBox);
    		add(fellesvaskCheckBox);
    		add(hageCheckBox);
    		add(kjellerCheckBox);
    	}
    	
    }
}
