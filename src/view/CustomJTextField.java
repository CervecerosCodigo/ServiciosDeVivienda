package view;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class CustomJTextField extends JPanel {

    private JTextField textField;
    private String fieldName;
    private String pattern;

    public CustomJTextField(String initText, String pattern, int size ) {
        fieldName = initText;
        this.pattern = pattern;

        textField = new JTextField(size);
        textField.setText(initText);
        textField.setForeground(Color.GRAY);

        setLayout(new FlowLayout());
        add(textField);
        
        textField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if( textField.getText().equals(fieldName) ){
                    textField.setText("");
                } else {
                    if( !testInput() )
                        textField.setBackground(Color.red);
                }
                
            }

            @Override
            public void focusLost(FocusEvent e) {
                textField.setBackground(Color.WHITE);
                if (textField.getText().equals("")) {
                    textField.setText(fieldName);
                    textField.setForeground(Color.GRAY);
                    return;
                }
                if( !textField.getText().equals("") ){
                    if( !testInput() )
                        textField.setBackground(Color.red);
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
}
