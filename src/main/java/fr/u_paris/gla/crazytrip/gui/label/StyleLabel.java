package fr.u_paris.gla.crazytrip.gui.label;

import java.awt.Color;

import javax.swing.JLabel;

public class StyleLabel extends JLabel {
    private static final Color BACKGROUND_COLOR = new Color(104, 157, 113);
    private static final Color FOREGROUND_COLOR = Color.WHITE;
    private static final boolean OPAQUE = true;

    public StyleLabel(String text) {
        super(text);

        setOpaque(OPAQUE);
        setBackground(BACKGROUND_COLOR);
        setForeground(FOREGROUND_COLOR);
    }
}
