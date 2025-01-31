package fr.u_paris.gla.crazytrip.gui.button;

import java.util.ArrayList;
import java.util.List;

import fr.u_paris.gla.crazytrip.gui.observer.PanelObserver;

/**
 * Class representing a button to close a panel.
 * 
 * @see StyleButton
 * @see PanelObserver
 */
public class CloseButton extends StyleButton {
    /** Text displayed by the button */
    private static final String TEXT = "Fermer";
    /** List of observers to notify when the button is clicked */
    private transient List<PanelObserver> observers = new ArrayList<>();

    /**
     * Constructor.
     */
    public CloseButton() {
        super(TEXT);
    }

    /**
     * Add an observer to the list of observers.
     * 
     * @param observer the observer to add
     */
    public void addObserver(PanelObserver observer) {
        observers.add(observer);
    }

    /**
     * Notify all observers that the button has been clicked.
     */
    @Override
    public void action() {
        observers.forEach(PanelObserver::updateVisibility);
    }
}
