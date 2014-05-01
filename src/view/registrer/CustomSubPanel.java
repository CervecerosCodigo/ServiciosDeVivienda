package view.registrer;
//Laget av Espen Zaal, studentnummer 198599 i klasse Informasjonsteknologi.

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import view.AbstractPanel;

public class CustomSubPanel extends AbstractPanel {

    private LayoutManager layout;
    private GridBagConstraints gc;

    public CustomSubPanel(String borderNavn, int hoyde, int bredde) {
        super(borderNavn, hoyde, bredde);
        setLayout(new GridLayout(1, 1));
        setVisible(true);
    }

    public CustomSubPanel(LayoutManager layout) {
        setLayout(layout);
        setVisible(true);
    }

}
