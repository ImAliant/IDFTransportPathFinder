package fr.u_paris.gla.crazytrip.gui.listener;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import fr.u_paris.gla.crazytrip.gui.button.StyleButton;

/**
 * Listener for a style button.
 * 
 * @see StyleButton
 */
public class StyleButtonListener extends MouseAdapter {
    /** The button. */
    private final StyleButton button;
    /** The default color of the button. */
    private final Color defaultColor;
    /** The color of the button when the mouse is over it. */
    private final Color hoverColor;

    /**
     * Constructor.
     * 
     * @param button the button
     * @param defaultColor the default color of the button
     * @param hoverColor the color of the button when the mouse is over it
     * 
     * @see StyleButton
     */
    public StyleButtonListener(StyleButton button, Color defaultColor, Color hoverColor) {
        this.button = button;
        this.defaultColor = defaultColor;
        this.hoverColor = hoverColor;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        button.action();
    }

    /**
     * Change the background color of the button when the mouse enters it.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        button.setBackground(hoverColor);
    }

    /**
     * Change the background color of the button when the mouse exits it.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        button.setBackground(defaultColor);
    }
}
