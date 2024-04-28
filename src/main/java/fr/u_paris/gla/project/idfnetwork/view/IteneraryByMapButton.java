package fr.u_paris.gla.project.idfnetwork.view;


import java.awt.Color;

import javax.swing.JButton;


public class IteneraryByMapButton extends JButton {
   
    private static final Color BUTTON_FOREGROUND = Color.WHITE;
    private static final Color BUTTON_BACKGROUND = new Color(1, 121, 111);

    public IteneraryByMapButton(String text, CustomTextField departureTF, CustomTextField arrivalTF) {
        super(text);
        setForeground(BUTTON_FOREGROUND);
        setBackground(BUTTON_BACKGROUND);

        IteneraryByMapButtonListener listener = new IteneraryByMapButtonListener(departureTF, arrivalTF);
        addListener(listener);
    }

    private void addListener(ItenararyByMapButtonListener listener) {
        this.addActionListener(listener);
    }
}

}
