
package serviciosdevivienda;

import controller.*;
import model.*;
import view.*;

/**
 * 
 * File: ServiciosDeVivienda.java
 * Project: ServiciosDeVivienda
 * Mar 26, 2014
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class ServiciosDeVivienda {

    public static void main(String[] args) {

        TopLevelGUI oppstartsvindu = new TopLevelGUI();
        Register register = new Register();
        Controller controller = new Controller( register, oppstartsvindu );
    }

}
