package fr.u_paris.gla.crazytrip.gui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ToolTipManager;

import fr.u_paris.gla.crazytrip.gui.button.MiniButton;

public class MiniButtonListener extends MouseAdapter {
    private MiniButton button;
    private String tooltipText;

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

    private void showTooltip() {
        ToolTipManager.sharedInstance().setInitialDelay(0);
        button.setToolTipText(tooltipText);
    }

    private void hideTooltip() {
        button.setToolTipText(null);
    }
}
