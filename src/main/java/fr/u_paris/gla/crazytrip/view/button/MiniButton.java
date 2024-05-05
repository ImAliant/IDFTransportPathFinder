package fr.u_paris.gla.crazytrip.view.button;

import javax.swing.ToolTipManager;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

/**
 * Abstract class for a mini button.
 */
public abstract class MiniButton extends JButton {
    /** Width of the button. */
    public static final int WIDTH = 25;
    /** Height of the button. */
    public static final int HEIGHT = 25;

    String tooltipText;

    /**
     * Create a new mini button at the given position.
     * 
     * @param x The x position of the button.
     * @param y The y position of the button.
     */
    protected MiniButton(String tooltipText) {
        super();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        setToolTipText(tooltipText);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onClick();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                showTooltip(tooltipText);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hideTooltip();
            }
        });
    }

    private void showTooltip(String tooltipText) {
        ToolTipManager.sharedInstance().setInitialDelay(0);
        setToolTipText(tooltipText);
    }

    private void hideTooltip() {
        setToolTipText(null);
    }


    /**
     * Method called when the button is clicked.
     */
    public abstract void onClick();
}
