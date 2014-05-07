package lib;

import controller.ControllerTabell;
import java.awt.Color;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.plaf.nimbus.NimbusStyle;

/**
 *
 * File: Konstanter.java Package: lib Project: ServiciosDeVivienda Apr 2, 2014
 *
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class Konstanter {

    /**
     * Kollator rekkefølge som brukes til sortering.
     */
    public static final String KOLLATOR_REKKEFOLGE = "<\0<0<1<2<3<4<5<6<7<8<9"
            + "<A,a<B,b<C,c<D,d<E,e<F,f<G,g<H,h<I,i<J,j"
            + "<K,k<L,l<M,m<N,n<O,o<P,p<Q,q<R,r<S,s<T,t"
            + "<U,u<V,v<W,w<X,x<Y,y<Z,z<Æ,æ<Ø,ø<Å=AA,å=aa;AA,aa";

    /**
     * Felles serialiseringsnummer som brukes til unik nummer ved lagring av
     * programmets datastruktur.
     */
    public static final long SERNUM = 1234L;

    /**
     * En relativ path til alle eksterne filer som brukes i programmet som
     * serialisert data, bilder osv.
     */
    public static final String PROGRAMDATA = "programdata";

    /**
     * Serialisert fil som brukes til lagring og innlesning av all data i
     * programmet.
     */
    public static final String FILNANV = Konstanter.PROGRAMDATA + "/data.iso";

    /**
     * En path til alle bildegallerier. I mappen finnes også undermapper med
     * samme mappenavn som vi har på boligId som kjennetegner til hvilken bolig
     * bildene tilhører.
     */
    public static final String BILDEGALLERIER = Konstanter.PROGRAMDATA + "/img";

    /**
     * Hver enkel bolig har et standardbilde som blir vist i annonsens fremside.
     * Ettersom bildene ligger en /programdata/img/boligID kommer altid å
     * fremsidebild å hete 1.jpg for hver enkel bolig.
     */
    public static final String STANDARDBILDE = "1.jpg";

    /**
     * Standard bredde for bilde.
     */
    public static final int BILDE_WIDTH = 405;
    
    /**
     * Standard høyde for bilde.
     */
    public static final int BILDE_HEIGHT = 270;
    
    /**
     * Brukes i feks JOptionPane for å sette lokalisert melding i knapper.
     */
    public static final Object[] VALG_JA_NEI = {"Ja", "Nei"};

    public static NumberFormat nf = NumberFormat.getIntegerInstance();
    public static SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    

    public static final Color BAKGRUNNSFARGEPANEL = new Color(200, 210, 240); //Lys blå
//    public static final Color BAKGRUNNSFARGEPANEL = new Color(255,255,255); //Hvit
//    public static final Color BAKGRUNNSFARGEPANEL = new Color(193, 205, 193); //Honey Dew
    
    
}
