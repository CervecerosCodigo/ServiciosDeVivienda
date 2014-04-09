package view;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;

public class VenstrePanel extends AbstractPanel {

    private JList liste;
    private JScrollPane scroll;
    private DefaultListModel valgtModel;

    public VenstrePanel(String borderTitle, int dimHeight, int dimWidth) {
        super(borderTitle, dimHeight, dimWidth);
        setLayout(new GridLayout(1, 1));
        
        liste = new JList();
        //liste.setPreferredSize(new Dimension(300, 350));
        scroll = new JScrollPane(liste);

        add(liste);

    }

    //Returnerer listen
    public JList getListe() {
        return liste;
    }

    //Setter listemodel etter valgt radioknapp
    public void setlisteModel(DefaultListModel model) {
        valgtModel = model;
        liste.setModel(valgtModel);
    }

//    //Setter inn det valgte elementet i menyen
//    public void LeggTilElementerIDataModel(Object o) {
//        valgtModel.addElement(o);
//    }
//
//    //Sletter det valgte elementet fra modellen
//    public void SlettElementIDataModel(Object o) {
//        valgtModel.removeElement(o);
//    }
//
//    //Sletter innholdet valgte modellen med elementer
//    public void TomDataModel(Object o) {
//        valgtModel.clear();
//    }
    
    

    //Metoden tar i mot den private lytteklassen fra Controller
    public void addListeListener(ListSelectionListener event) {
        liste.addListSelectionListener(event);
    }

}
