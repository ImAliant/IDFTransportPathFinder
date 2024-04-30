package fr.u_paris.gla.project.idfnetwork.view.button.transport_button;

import fr.u_paris.gla.project.idfnetwork.Line;
import fr.u_paris.gla.project.observer.DisplayLineObserver;
/* import fr.u_paris.gla.project.idfnetwork.view.button.MiniButton; */
import fr.u_paris.gla.project.utils.IconUtils;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.ToolTipManager;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class TransportButton extends /* MiniButton */ JButton {
    protected transient List<DisplayLineObserver> observers = new ArrayList<>();

    protected TransportButton(String iconPath, String tooltipText) {
        /* super(); */

        // 
        setPreferredSize(new Dimension(25, 25));
        //

        Icon icon = IconUtils.createIcon(iconPath, 25, 25);
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

            @Override
            public void mouseClicked(MouseEvent e) {
                onClick();
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

    //
    protected abstract void onClick();
    //
}
