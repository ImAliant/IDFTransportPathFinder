package fr.u_paris.gla.project.idfnetwork.view.button;

import javax.swing.JButton;
import javax.swing.Icon;
import fr.u_paris.gla.project.utils.IconUtils;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.border.EmptyBorder;

public abstract class TransportButton extends JButton {
    public TransportButton(String iconPath, int width, int height, String tooltipText) {
        Icon icon = IconUtils.createIcon(iconPath, width, height);
        this.setIcon(icon);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(width, height));
        this.setVisible(true);
        setBorder(new EmptyBorder(0, 10, 0, 10));
        setBackground(new Color(100, 181, 246));
        setToolTipText(tooltipText);
        addActionListener(e -> onClick());
    }

    protected abstract void onClick();
}
