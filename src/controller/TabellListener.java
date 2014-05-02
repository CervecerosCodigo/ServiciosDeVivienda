package controller;

import java.util.ArrayList;
import model.TabellModell;

/**
 *
 * @author espen
 */
public interface TabellListener<E> {
    
    public void tabellOppdatert(ArrayList<E> tabellData, TabellModell modell);
    

}
