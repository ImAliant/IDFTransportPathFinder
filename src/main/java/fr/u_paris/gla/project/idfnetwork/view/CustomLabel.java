package fr.u_paris.gla.project.idfnetwork.view;

import java.awt.Color;

import javax.swing.JLabel;

public class CustomLabel extends JLabel {
    private static final Color BACKGROUND_COLOR = new Color(104, 157, 113);
    private static final Color LABEL_FOREGROUND = Color.WHITE;
    private static final boolean OPAQUE = true;

    public CustomLabel(String text) {
        super(text);
        setBackground(BACKGROUND_COLOR);
        setForeground(LABEL_FOREGROUND);
        setOpaque(OPAQUE);
    }
}
