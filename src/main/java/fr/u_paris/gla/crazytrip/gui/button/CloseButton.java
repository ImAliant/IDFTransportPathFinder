package fr.u_paris.gla.crazytrip.gui.button;

import java.util.ArrayList;
import java.util.List;

import fr.u_paris.gla.crazytrip.gui.observer.PanelObserver;

public class CloseButton extends StyleButton {
    private static final String TEXT = "Fermer";

    private transient List<PanelObserver> observers = new ArrayList<>();

    public CloseButton() {
        super(TEXT);
    }

    public void addObserver(PanelObserver observer) {
        observers.add(observer);
    }

    @Override
    public void action() {
        observers.forEach(PanelObserver::updateVisibility);
    }
}
