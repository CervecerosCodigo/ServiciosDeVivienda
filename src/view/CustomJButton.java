package view;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

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
     *
     * @param navn
     */
    public CustomJButton(String navn) {
        this.navn = navn;
        //setBackground(Konstanter.BAKGRUNNSFARGEPANEL);
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
        //setBackground(Konstanter.BAKGRUNNSFARGEPANEL);
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
        //setBackground(Konstanter.BAKGRUNNSFARGEPANEL);
        setText(this.navn);
        setIcon(this.ikone);
    }

    /**
     * En knapp med ikone.
     *
     * @param ikone
     */
    public CustomJButton(Icon ikone) {
        this.ikone = ikone;
        //setBackground(Konstanter.BAKGRUNNSFARGEPANEL);
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
        //setBackground(Konstanter.BAKGRUNNSFARGEPANEL);
        setIcon(this.ikone);
        setPreferredSize(new Dimension(bredde, hoyde));
    }

}
