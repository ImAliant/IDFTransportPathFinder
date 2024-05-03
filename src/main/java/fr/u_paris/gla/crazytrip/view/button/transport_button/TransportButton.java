package fr.u_paris.gla.crazytrip.view.button.transport_button;

import javax.swing.Icon;

import fr.u_paris.gla.crazytrip.idfnetwork.line.Line;
import fr.u_paris.gla.crazytrip.idfnetwork.network.Network;
import fr.u_paris.gla.crazytrip.observer.DisplayLineObserver;
import fr.u_paris.gla.crazytrip.utils.IconUtils;
import fr.u_paris.gla.crazytrip.view.button.MiniButton;

import java.util.ArrayList;
import java.util.List;

public abstract class TransportButton extends MiniButton {
    protected transient List<DisplayLineObserver> observers = new ArrayList<>();

    protected Network network = Network.getInstance();

    protected TransportButton(String iconPath, String tooltipText) {
        super(tooltipText);
        
        Icon icon = IconUtils.createIcon(iconPath, WIDTH, HEIGHT/* 25, 25 */);
        if (icon != null) {
            setIcon(icon);
        }
        
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
