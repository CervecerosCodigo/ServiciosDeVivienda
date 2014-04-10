package serviciosdevivienda;

import controller.*;
import javax.swing.SwingUtilities;


/**
 *
 * File: ServiciosDeVivienda.java Project: ServiciosDeVivienda Mar 26, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class ServiciosDeVivienda {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            MainController controller;

            @Override
            public void run() {

                
                controller = new MainController();

                //////Avsluttnings Hooks///////
                Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

                    @Override
                    public void run() {

                        System.out.println("Programmet avsluttes");
                        controller.lagreData();
                    }
                }));
            }
        });

    }
}
