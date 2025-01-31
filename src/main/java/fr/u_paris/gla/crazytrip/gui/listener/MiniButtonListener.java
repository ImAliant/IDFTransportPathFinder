package fr.u_paris.gla.crazytrip.gui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ToolTipManager;

import fr.u_paris.gla.crazytrip.gui.button.MiniButton;

/**
 * Listener for a mini button.
 * 
 * @see MiniButton
 */
public class MiniButtonListener extends MouseAdapter {
    /** The button. */
    private MiniButton button;
    /** The text of the tooltip. */
    private String tooltipText;

    /**
     * Constructor.
     * 
     * @param button the button
     * 
     * @see MiniButton
     */
    public MiniButtonListener(MiniButton button) {
        this.button = button;
        this.tooltipText = button.getToolTipText();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        button.action();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        showTooltip();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        hideTooltip();
    }

    /**
     * Show the tooltip.
     */
    private void showTooltip() {
        ToolTipManager.sharedInstance().setInitialDelay(0);
        button.setToolTipText(tooltipText);
    }

    /**
     * Hide the tooltip.
     */
    private void hideTooltip() {
        button.setToolTipText(null);
    }
}
