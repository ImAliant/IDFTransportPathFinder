package fr.u_paris.gla.project.view.button.transport_button;

import javax.swing.Icon;
import javax.swing.ToolTipManager;

import fr.u_paris.gla.project.idfnetwork.line.Line;
import fr.u_paris.gla.project.idfnetwork.network.Network;
import fr.u_paris.gla.project.observer.DisplayLineObserver;
import fr.u_paris.gla.project.utils.IconUtils;
import fr.u_paris.gla.project.view.button.MiniButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class TransportButton extends MiniButton {
    protected transient List<DisplayLineObserver> observers = new ArrayList<>();

    protected Network network = Network.getInstance();

    protected TransportButton(String iconPath, String tooltipText) {
        super();

        Icon icon = IconUtils.createIcon(iconPath, WIDTH, HEIGHT/* 25, 25 */);
        if (icon != null) {
            setIcon(icon);
        }
        setToolTipText(tooltipText);

        addMouseListener(new MouseAdapter() {
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

    public void addObserver(DisplayLineObserver observer) {
        observers.add(observer);
    }

    protected void notifyObservers(List<Line> lines) {
        for (DisplayLineObserver observer : observers) {
            observer.update(lines);
        }
    }
}
