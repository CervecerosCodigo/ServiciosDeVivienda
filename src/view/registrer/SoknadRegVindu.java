package view.registrer;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import controller.VisMeldingInterface;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import lib.Melding;
import view.CustomJButton;

public class SoknadRegVindu extends AbstractRegistreringsPanel implements VisMeldingInterface{

    private JLabel kravLabel;
    private JTextArea eiersKrav;
    private CustomJButton aksepter, avbryt;

    public SoknadRegVindu(int bredde, int hoyde, String tittel) {
        super(bredde, hoyde, tittel);

        senterPanel = new CustomSubPanel("", 0, 0, new FlowLayout());
        bunnPanel = new CustomSubPanel("", 35, 0, new FlowLayout());

        kravLabel = new JLabel("Vennligst aksepter vilkårene:");
        eiersKrav = new JTextArea(10, 30);
        aksepter = new CustomJButton("Aksepter");
        avbryt = new CustomJButton("Avbryt");

        eiersKrav.setEditable(false);
        add(senterPanel, BorderLayout.CENTER);
        add(bunnPanel, BorderLayout.SOUTH);
        senterPanel.add(kravLabel);
        senterPanel.add(eiersKrav);
        bunnPanel.add(avbryt);
        bunnPanel.add(aksepter);

    }

    public void setEiersKrav(String eiersKrav) {
        this.eiersKrav.setText(eiersKrav);
    }

    
    public JTextArea getEiersKrav() {
        return eiersKrav;
    }

    public CustomJButton getAksepter() {
        return aksepter;
    }

    public CustomJButton getAvbryt() {
        return avbryt;
    }

    public void addSoknadListener(ActionListener lytter) {
        avbryt.addActionListener(lytter);
        aksepter.addActionListener(lytter);
    }

    @Override
    public void visMelding(String overskrift, String melding) {
        Melding.visMelding(overskrift, melding);
    }

}
