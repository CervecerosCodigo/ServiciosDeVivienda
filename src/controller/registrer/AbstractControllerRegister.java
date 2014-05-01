
package controller.registrer;

import java.util.HashSet;
import javax.swing.JFrame;

/**
 * 
 * File: AbstractControllerRegister.java
 * Project: ServiciosDeVivienda
 * Apr 29, 2014
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public abstract class AbstractControllerRegister<E> {
    /*
    Her skal jeg har to kontruktører. 
    1. Som tar i mot et set og en Jpanel slik at jeg kan ta imot data fra gui for å registrere denne.
    2. En som tar imot et objekt og Jpanel for å populere denne med data fra registeret med hensikt å endre registrerte data.
    */
    
    private JFrame frame;
    private HashSet<E> set;
    private Object obj;
    
    /**
     * Brukes for registrering.
     * @param frame
     * @param set 
     */
    public AbstractControllerRegister(HashSet<E> set, JFrame frame){
        
    }

    /**
     * Brukes for endring eller sletting av et alerede eksisterende objekt.
     * @param obj
     * @param set 
     */
    public AbstractControllerRegister(HashSet<E> set, Object obj) {
        this.set = set;
        this.obj = obj;
    }

    

    
}
