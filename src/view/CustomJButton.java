package view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JButton;
import lib.Konstanter;

public class CustomJButton extends JButton {

    private String navn;
    private Icon ikone;

    /**
     * En knapp med tekst.
     * @param navn
     */
    public CustomJButton(String navn) {
        this.navn = navn;
        setText(this.navn);
    }

    /**
     * En knapp med tekst og størrelse.
     *
     * @param navn
     * @param bredde
     * @param hoyde
     */
    public CustomJButton(String navn, int bredde, int hoyde) {
        this.navn = navn;
        setText(this.navn);
        setPreferredSize(new Dimension(bredde, hoyde));
    }

    /**
     * En knapp med tekst og icone.
     *
     * @param navn
     * @param ikone
     */
    public CustomJButton(String navn, Icon ikone) {
        this.navn = navn;
        this.ikone = ikone;
        setText(this.navn);
        setIcon(this.ikone);
    }

    /**
     * En knapp med ikone.
     * @param ikon
     */
    public CustomJButton(Icon ikone) {
        this.ikone = ikone;
        setIcon(this.ikone);
    }

    /**
     * En knapp med ikone og størrelse.
     * @param ikone
     * @param bredde
     * @param hoyde 
     */
    public CustomJButton(Icon ikone, int bredde, int hoyde) {
        this.ikone = ikone;
        setIcon(this.ikone);
        setPreferredSize(new Dimension(bredde, hoyde));
    }
}
