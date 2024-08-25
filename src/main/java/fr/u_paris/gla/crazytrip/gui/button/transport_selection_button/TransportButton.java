package fr.u_paris.gla.crazytrip.gui.button.transport_selection_button;

import java.util.List;

import javax.swing.Icon;

import java.util.ArrayList;

import fr.u_paris.gla.crazytrip.gui.button.MiniButton;
import fr.u_paris.gla.crazytrip.gui.observer.DisplayLineObserver;
import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.utils.IconUtils;

public abstract class TransportButton extends MiniButton {
    protected transient List<DisplayLineObserver> observers = new ArrayList<>();

    protected TransportButton(String iconPath, String tooltipText) {
        super(tooltipText);

        Icon icon = IconUtils.createIcon(iconPath, SIZE, SIZE);
        if (icon != null) setIcon(icon);
    }

    public void addObserver(DisplayLineObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(List<Line> lines) {
        observers.forEach(observer -> observer.update(lines));
    }
}
