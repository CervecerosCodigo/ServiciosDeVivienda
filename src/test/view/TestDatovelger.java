package test.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import lib.Melding;
import view.ComboDatoVelger;

/**
 * Brukes kun til for å teste datovelger i comboboksen. File:
 * TestDatovelger.java Package: test.view Project: ServiciosDeVivienda May 4,
 * 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class TestDatovelger extends JFrame {
    
    private ComboDatoVelger datoVelger;
    private JButton knappEndre, knappVisSattDato;
    
    public TestDatovelger() {
        super("Test av datovelger");
        datoVelger = new ComboDatoVelger();
        knappEndre = new JButton("Endre dato");
        knappVisSattDato = new JButton("Vis satt dato");
        
        setLayout(new FlowLayout());
        this.add(datoVelger);
        this.add(knappEndre);
        this.add(knappVisSattDato);
        
        knappEndre.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(knappEndre)) {
//                    Melding.visMelding("Endre dato", "Dato skal endres til: 2030");
                    datoVelger.setDato(2030, 12, 15);
                }
            }
        });
        
        knappVisSattDato.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
         if(e.getSource().equals(knappVisSattDato)){
             Melding.visMelding("Dato", "Valg dato\n"+datoVelger.getAr()+"."+datoVelger.getManed()+"."+datoVelger.getDag());
         }
            }
        });
        
        setSize(400, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
