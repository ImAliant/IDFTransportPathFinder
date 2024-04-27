package fr.u_paris.gla.project.idfnetwork.view;

import java.awt.Color;

import javax.swing.JLabel;

/**
 * Custom label for the application.
 */
public class CustomLabel extends JLabel {
    /** Background color of the label. */
    private static final Color BACKGROUND_COLOR = new Color(104, 157, 113);
    /** Foreground color of the label. */
    private static final Color LABEL_FOREGROUND = Color.WHITE;
    /** Opaque property of the label. */
    private static final boolean OPAQUE = true;

    /**
     * Create a new custom label with the given text.
     * 
     * @param text The text of the label.
     */
    public CustomLabel(String text) {
        super(text);
        setBackground(BACKGROUND_COLOR);
        setForeground(LABEL_FOREGROUND);
        setOpaque(OPAQUE);
    }
}
