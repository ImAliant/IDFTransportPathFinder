package fr.u_paris.gla.crazytrip.gui.button;

import java.util.ArrayList;
import java.util.List;

import fr.u_paris.gla.crazytrip.gui.observer.ClearLineObserver;

public class ClearLineButton extends StyleButton { 
    private static final String TEXT = "Effacer";

    private transient List<ClearLineObserver> observers = new ArrayList<>();

    public ClearLineButton() {
        super(TEXT);
    }

    public void addObserver(ClearLineObserver observer) {
        observers.add(observer);
    }

    @Override
    public void action() {
        observers.forEach(ClearLineObserver::clearLine);
    }
}
