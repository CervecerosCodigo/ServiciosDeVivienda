package serviciosdevivienda;

import javax.swing.SwingUtilities;

import controller.SkrivTilLesFraFil;

/**
 *
 * File: ServiciosDeVivienda.java Project: ServiciosDeVivienda Mar 26, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class ServiciosDeVivienda {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            SkrivTilLesFraFil startProgram;

            @Override
            public void run() {

                startProgram = new SkrivTilLesFraFil();

                //////Avsluttnings Hooks///////
                Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

                    @Override
                    public void run() {

                        System.out.println("Programmet avsluttes");
                        startProgram.lagreData();
                    }
                }));
            }
        });

    }
}
