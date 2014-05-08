package view;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.


import java.awt.GridLayout;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;



public class SenterPanel extends AbstractPanel{

    private JEditorPane output;
    private HTMLEditorKit kit;
    private StyleSheet css;
    private JScrollPane scroll;
    private Document dok;
    
    public SenterPanel(String borderTitle, int dimHeight, int dimWidth) {
        super(borderTitle, dimHeight, dimWidth);
        setLayout( new GridLayout(1, 1));
        output = new JEditorPane();
        kit = new HTMLEditorKit();
        dok = kit.createDefaultDocument();
        css = kit.getStyleSheet();
        
        output.setDocument(dok);
        output.setEditable( false );
        output.setEditorKit( kit );
        output.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        scroll = new JScrollPane( output );
        
        add(scroll);
        
    }

    public JEditorPane getEditorPane(){
        return output;
    }
    
    public HTMLEditorKit getHTMLEditorKit(){
        return kit;
    }
    
    public Document getHTMLDokument(){
        return dok;
    }
            
    public StyleSheet getStyleSheet(){
        return css;
    }
    
    @Override
    public void addMouseListener(MouseListener lytter){
        output.addMouseListener(lytter);
        
    }
}
