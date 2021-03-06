package controller.registrer;

import java.util.HashSet;
import javax.swing.JFrame;

/**
 * Abstrakt klasse for registrering og endring av oppførte data i registrene.
 * @param <E>
 */
public abstract class AbstractControllerRegister<E> {

    final HashSet<E> set;
    
    // Referanse til eksisterende objekt som skal endres i settet.
    Object obj;

    /**
     * Konstruktør for registrering av et nytt objekt f.eks bolig.
     *
     * @param set
     */
    public AbstractControllerRegister(HashSet<E> set) {
        this.set = set;
    }

    /**
     * Konstruktør for endring av et eksissterende objekt, f.eks bolig.
     *
     * @param obj
     * @param set
     */
    public AbstractControllerRegister(HashSet<E> set, Object obj) {
        this.set = set;
        this.obj = obj;
    }

    /**
     * Sletter et objekt i registeret som brukes hos kontrolleren.
     *
     * @param e
     * @return
     */
    public boolean slettObjekt(E e) {
        return set.remove(e);
    }
    
    /**
     * Legger til et objekt i registeret av den type som brukes i kontrolleren.
     * @param e
     * @return 
     */
    public boolean registrerObjekt(E e) {
        return set.add(e);
    }
}
