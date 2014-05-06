
package lib;

import java.awt.Dimension;
import view.CustomJTextField;

/**
 * En klasse med konstanter som bestemmer størrelse på GUI komponenter.
 * File: GuiSizes.java
 Package: lib
 Project: ServiciosDeVivienda
 May 2, 2014
 * @author Lukas David Larsed, s198569@stud.hioa.no
 */
public class GuiSizes {

    /**
     * Dette er vår mediumstørrelse for en textfield.
     */
    public static final int FIELD_MEDIUM = 15;
    
    public static final int TEXTAREA_ROW_MEDIUM = 6;
    public static final int TEXTAREA_COL_MEDIUM = 16;
    
    public static final Dimension  COMBOBOX_MEDIUM = new Dimension(175, CustomJTextField.HEIGHT*12);
   
}
