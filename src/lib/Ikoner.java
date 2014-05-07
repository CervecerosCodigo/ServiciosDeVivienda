
package lib;

import javax.swing.ImageIcon;

/**
 * 
 * File: Ikoner.java
 * Package: lib
 * Project: ServiciosDeVivienda
 * May 7, 2014
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class Ikoner {
    private final static String ikonerSti = new BildeFilSti().getAbsoluteGalleryPath()+"/default/ico/";
    
    //Ikoner er hentet fra: http://flaticons.net/
    //Valg farve er 606060
    //Størrelse 36px med alfakanal
    
    //Følgende har 6px padding
    public final static ImageIcon PIL_VENSTRE = new ImageIcon(ikonerSti+"Arrowhead-Left-32.png");
    public final static ImageIcon PIL_HOYRE = new ImageIcon(ikonerSti+"Arrowhead-Right-32.png");
    //Følgende har 8px padding
    public final static ImageIcon SEARCH = new ImageIcon(ikonerSti+"Search-32.png");
    public final static ImageIcon ATTACH_IMAGE = new ImageIcon(ikonerSti+"Attach-Image-32.png");
    public final static ImageIcon IMAGES = new ImageIcon(ikonerSti+"Images-32.png");
    
    
    //Applikasjonsikone
    public final static ImageIcon APP_ICON = new ImageIcon(ikonerSti+"boligLogo.png");
    
}
