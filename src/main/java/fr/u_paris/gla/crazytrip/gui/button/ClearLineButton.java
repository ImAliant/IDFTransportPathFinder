package fr.u_paris.gla.crazytrip.gui.button;

import java.util.ArrayList;
import java.util.List;

import fr.u_paris.gla.crazytrip.gui.observer.ClearLineObserver;

/**
 * Class representing a button to clear the content of the combo box in the LineSelectionPanel.
 * 
 * @see StyleButton
 * @see ClearLineObserver
 * @see fr.u_paris.gla.crazytrip.gui.panel.LineSelectionPanel
 */
public class ClearLineButton extends StyleButton { 
    /** Text displayed by the button */
    private static final String TEXT = "Effacer";
    /** List of observers to notify when the button is clicked */
    private transient List<ClearLineObserver> observers = new ArrayList<>();

    /**
     * Constructor.
     */
    public ClearLineButton() {
        super(TEXT);
    }

    /**
     * Add an observer to the list of observers.
     * 
     * @param observer the observer to add
     */
    public void addObserver(ClearLineObserver observer) {
        observers.add(observer);
    }

    /**
     * Notify all observers that the button has been clicked.
     */
    @Override
    public void action() {
        observers.forEach(ClearLineObserver::clearLine);
    }
}
