package fr.u_paris.gla.crazytrip.gui.button;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import fr.u_paris.gla.crazytrip.gui.listener.StyleButtonListener;

/**
 * Abstract class representing a styled button.
 * 
 * @see StyleButtonListener
 */
public abstract class StyleButton extends JButton {
    /** Color of the button. */
    private static final Color BUTTON_COLOR = new Color(1, 121, 111);
    /** Color of the button when the mouse is over it. */
    private static final Color BUTTON_HOVER_COLOR = new Color(1, 101, 91);
    /** Color of the text inside the button */
    private static final Color FOREGROUND_COLOR = Color.WHITE;

    /**
     * Constructor.
     * 
     * @param text the text displayed by the button
     */
    protected StyleButton(String text) {
        super(text);

        setBackground(BUTTON_COLOR);
        setForeground(FOREGROUND_COLOR);
        setBorder(BorderFactory.createRaisedBevelBorder());
        
        addMouseListener(new StyleButtonListener(this, BUTTON_COLOR, BUTTON_HOVER_COLOR));
    }

    /**
     * Action to perform when the button is clicked.
     */
    public abstract void action();
}
