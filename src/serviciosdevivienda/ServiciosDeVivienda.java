package serviciosdevivienda;

import controller.*;
import javax.swing.SwingUtilities;
import lib.FilSkriver;
import model.Bolig;

/**
 *
 * File: ServiciosDeVivienda.java Project: ServiciosDeVivienda Mar 26, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class ServiciosDeVivienda {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

//             MainController controller;
            FilSkriver filSkriver;
            String file = "programdata/data.iso";

            @Override
            public void run() {

                final MainController controller = new MainController();

//                FileInputStream fis = null;
//                ObjectInputStream in = null;
//                
//                try{
//                    fis = new FileInputStream(file);
//                    in = new ObjectInputStream(fis);
//                    controller = (MainController) in.readObject();
//                    in.close();
//                    
//                }catch(ClassNotFoundException e){
//                    System.out.println("Klassen ikke funnet");
//                }catch(IOException e){
//                    System.out.println("IO feil");
//                }
                //////Avsluttnings Hooks///////
                Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

                    @Override
                    public void run() {

                        System.out.println("Programmet avsluttes");
                        filSkriver = new FilSkriver(file);
                        filSkriver.skrivTilFil(controller);

                    }
                }));
            }
        });

    }
}
