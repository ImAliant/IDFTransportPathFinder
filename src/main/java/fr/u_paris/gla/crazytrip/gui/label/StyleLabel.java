package fr.u_paris.gla.crazytrip.gui.label;

import java.awt.Color;

import javax.swing.JLabel;

/**
 * Label with a specific style.
 */
public class StyleLabel extends JLabel {
    /** Background color of the label. */
    private static final Color BACKGROUND_COLOR = new Color(104, 157, 113);
    /** Text color of the label. */
    private static final Color FOREGROUND_COLOR = Color.WHITE;
    /** Opaque property of the label. */
    private static final boolean OPAQUE = true;

    /**
     * Constructor.
     * 
     * @param text the text displayed by the label
     */
    public StyleLabel(String text) {
        super(text);

        setOpaque(OPAQUE);
        setBackground(BACKGROUND_COLOR);
        setForeground(FOREGROUND_COLOR);
    }
}
