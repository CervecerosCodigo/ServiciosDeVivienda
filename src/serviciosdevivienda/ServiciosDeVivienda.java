package serviciosdevivienda;

import controller.SkrivTilLesFraFil;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import lib.Konstanter;

/**
 *
 * File: ServiciosDeVivienda.java Project: ServiciosDeVivienda Mar 26, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class ServiciosDeVivienda {

    public static void main(String[] args) {

        //Setter opp Nimbus GUI tema, krever Java 7
    	UIManager.put("nimbusBase", new Color(100,139,255));
    	UIManager.put("nimbusBlueGrey", Konstanter.BAKGRUNNSFARGEPANEL);
    	UIManager.put("control", Konstanter.BAKGRUNNSFARGEPANEL);
    	
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {

            //Hvis Numbus ikke finnes så går den over til "Metal" standard swing tema.
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServiciosDeVivienda.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(ServiciosDeVivienda.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ServiciosDeVivienda.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(ServiciosDeVivienda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

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
