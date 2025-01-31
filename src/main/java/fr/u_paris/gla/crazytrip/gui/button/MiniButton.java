package fr.u_paris.gla.crazytrip.gui.button;

import java.awt.Dimension;

import javax.swing.JButton;

import fr.u_paris.gla.crazytrip.gui.listener.MiniButtonListener;

/**
 * Abstract class representing a mini button.
 * 
 * @see MiniButtonListener
 */
public abstract class MiniButton extends JButton{
    /** Size of the button */
    protected static final int SIZE = 25;
    /** Text displayed by the button when the mouse is over it */
    private final String tooltipText;

    /**
     * Constructor.
     * 
     * @param text the text displayed by the button when the mouse is over it
     */
    protected MiniButton(String text) {
        super();
        setToolTipText(text);
        setPreferredSize(new Dimension(SIZE, SIZE));

        this.tooltipText = text;

        addMouseListener(new MiniButtonListener(this));
    }

    /**
     * Action to perform when the button is clicked.
     */
    public abstract void action();

    public String getTooltipText() {
        return tooltipText;
    }
}
