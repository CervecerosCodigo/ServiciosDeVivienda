package view;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JTextField;

public class CustomJTextField extends AbstractPanel {

    private JTextField textField;
    private String fieldName;
    private String pattern;

    public CustomJTextField(String initText, String pattern, int size ) {
        fieldName = initText;
        this.pattern = pattern;

        textField = new JTextField(size);
        textField.setText(initText);
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.GRAY);
        
        setLayout(new FlowLayout());
        add(textField);
        
        textField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if( textField.getText().equals(fieldName) ){
                    textField.setText("");
                    textField.setBackground(Color.WHITE);
                    textField.setForeground(Color.GRAY);                    
                } else {
                    if( !testInput() )
                        textField.setForeground(Color.white);
                        textField.setBackground(Color.red);
                }
                
            }

            @Override
            public void focusLost(FocusEvent e) {
                //textField.setBackground(Color.WHITE);
                if (textField.getText().equals("")) {
                    textField.setText(fieldName);
                    textField.setForeground(Color.GRAY);
                    textField.setBackground(Color.WHITE);                    
                    return;
                }else{
                    if( !testInput() ){
                        textField.setForeground(Color.white);
                        textField.setBackground(Color.RED);
                    }else{
                        textField.setForeground(Color.GRAY); 
                        textField.setBackground(Color.WHITE);
                       
                    }
                }
            }
        });

    }

    private boolean testInput() {
        return textField.getText().matches(pattern);
    }
    
    /**
     * Returnerer tekst fra custom panel. Brukse for å få tilbake tekst som skrives i søkepanelen.
     * @return String
     */
    public String getText(){
        return textField.getText();
    }
    

    /**
     * Gjør det mulig å deaktivere feltet etter behov.
     * @param isEnabled boolean
     */
    @Override
    public void setEnabled(boolean isEnabled){
        textField.setEnabled(isEnabled);
    }
    
    /**
     * Setter en ny tekst i feltet.
     * @param text String
     */
    public void setText(String text){
        textField.setText(text);
    }
    
}
