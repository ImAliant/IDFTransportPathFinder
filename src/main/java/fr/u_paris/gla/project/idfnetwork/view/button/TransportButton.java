package fr.u_paris.gla.project.idfnetwork.view.button;

import javax.swing.JButton;
import javax.swing.ToolTipManager;
import javax.swing.Icon;

import fr.u_paris.gla.project.idfnetwork.Line;
import fr.u_paris.gla.project.idfnetwork.LineType;
import fr.u_paris.gla.project.idfnetwork.Network;
import fr.u_paris.gla.project.utils.IconUtils;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.border.EmptyBorder;

import org.apache.commons.lang3.ObjectUtils.Null;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

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
        ToolTipManager.sharedInstance().setInitialDelay(0);
        this.setToolTipText(tooltipText);
    }

    private void hideTooltip() {
        this.setToolTipText(null);
    }

    protected void showLines(LineType lineType) {
        List<Line> lineTypeLines = Network.getInstance().getLinesByType(lineType);

        if (lineTypeLines != null && !lineTypeLines.isEmpty()) {
            System.out.println("Lignes " + lineType + ":");
            for (Line line : lineTypeLines) {
                System.out.println(line.getLineName());
            }
            showClickedLineType(lineTypeLines);
        } else {
            System.out.println("Aucune ligne " + lineType + " disponible.");
        }

    }

    protected abstract void onClick();
    protected abstract void showClickedLineType(List<Line> lineTypeLines);
}
