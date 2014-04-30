
package controller.registrer;

import java.util.HashSet;
import javax.swing.JFrame;
import view.registrer.BoligRegVindu;

/**
 * 
 * File: ControllerRegistrerBolig.java
 * Project: ServiciosDeVivienda
 * Apr 29, 2014
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class ControllerRegistrerBolig extends AbstractControllerRegister<Object>{
    
    private BoligRegVindu regVindu;

    public ControllerRegistrerBolig(HashSet<Object> set, JFrame frame) {
        super(set, frame);
        
        regVindu = new BoligRegVindu("Registrering av boliger");
    }

    
    

}
