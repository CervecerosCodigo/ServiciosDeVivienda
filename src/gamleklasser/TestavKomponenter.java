package gamleklasser;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import javax.swing.*;


/**
 * Man oppretter her new Textcomponent når man skal opprette et nytt tekstfelt i GUI.
 * 
 * */

public abstract class TestavKomponenter extends JPanel{

    //AbstrACTE GET, SET-METODER OSV
    abstract void setValue();
    abstract boolean isEmpty();
    abstract String getString();
}
//////////////////////////////
class Textcomponent extends TestavKomponenter{
    protected JLabel label;
    protected JTextField tekstfelt;
    
    public Textcomponent(String labelNavn ){
        label.setText(labelNavn);
    }

    @Override
    void setValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    String getString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
