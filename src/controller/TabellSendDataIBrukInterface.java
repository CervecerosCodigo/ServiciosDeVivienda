/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.util.ArrayList;
import model.TabellModell;

/**
 *
 * @author espen
 */
public interface TabellSendDataIBrukInterface<E> {
    
    public void sendTabellDataIBruk(ArrayList<E> tabellData);
    
}
