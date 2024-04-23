package fr.u_paris.gla.project.idfnetwork.view;

import java.awt.Color;

import javax.swing.JButton;

public class ResearchButton extends JButton {
    private static final Color BUTTON_FOREGROUND = Color.WHITE;
    private static final Color BUTTON_BACKGROUND = new Color(1, 121, 111);

    public ResearchButton(String text) {
        super(text);
        setForeground(BUTTON_FOREGROUND);
        setBackground(BUTTON_BACKGROUND);
    }
}
