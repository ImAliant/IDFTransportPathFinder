package fr.u_paris.gla.project.idfnetwork.view.button;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ToolTipManager;
import javax.swing.Icon;
import fr.u_paris.gla.project.utils.IconUtils;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.border.EmptyBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class TransportButton extends JButton {
    public TransportButton(String iconPath, int width, int height, String tooltipText) {
        Icon icon = IconUtils.createIcon(iconPath, width, height);
        this.setIcon(icon);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(width, height));
        this.setVisible(true);
        this.setBorder(new EmptyBorder(0, 10, 0, 10));
        this.setBackground(new Color(100, 181, 246));

        this.setToolTipText(tooltipText);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                showTooltip(tooltipText);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                hideTooltip();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                onClick();
            }
        });
    }

    private void showTooltip(String tooltipText) {
        ToolTipManager.sharedInstance().setInitialDelay(0); // Affichage instantané de l'info-bulle
        this.setToolTipText(tooltipText);
    }

    private void hideTooltip() {
        this.setToolTipText(null); // Supprimer l'info-bulle lorsque la souris quitte le bouton
    }

    protected abstract void onClick();
}
