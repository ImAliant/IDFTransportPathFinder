package fr.u_paris.gla.project.idfnetwork.view.button;

import javax.swing.JButton;

/**
 * Abstract class for a mini button.
 */
public abstract class MiniButton extends JButton {
    /** Width of the button. */
    public static final int WIDTH = 25;
    /** Height of the button. */
    public static final int HEIGHT = 25;

    /**
     * Create a new mini button at the given position.
     * 
     * @param x The x position of the button.
     * @param y The y position of the button.
     */
    protected MiniButton(int x, int y) {
        super();
        
        setBounds(x, y, WIDTH, HEIGHT);

        addActionListener(e -> onClick());
    }

    /**
     * Method called when the button is clicked.
     */
    public abstract void onClick();
}
