
package serviciosdevivienda;

import controller.*;
import view.*;
import register.*;
import java.util.*;

/**
 * 
 * File: ServiciosDeVivienda.java
 * Project: ServiciosDeVivienda
 * Mar 26, 2014
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class ServiciosDeVivienda {

    public static void main(String[] args) {

        VelkomstMainFrame oppstartsvindu = new VelkomstMainFrame();
        
        Controller controller = new Controller(oppstartsvindu );

    }

}
