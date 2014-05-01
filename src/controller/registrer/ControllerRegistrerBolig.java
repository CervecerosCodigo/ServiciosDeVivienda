
package controller.registrer;

import java.util.HashSet;
import javax.swing.JFrame;
import model.Bolig;
import view.registrer.BoligRegVindu;

/**
 * 
 * File: ControllerRegistrerBolig.java
 * Project: ServiciosDeVivienda
 * Apr 29, 2014
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class ControllerRegistrerBolig extends AbstractControllerRegister{
    
    private BoligRegVindu boligRegVindu;

    public ControllerRegistrerBolig(JFrame frame, HashSet<Bolig> boligSet) {
        super(boligSet, frame);
        
        boligRegVindu = new BoligRegVindu("Registrering av boliger");
    }

    
    public void getBoligData(){
        
    }

}
