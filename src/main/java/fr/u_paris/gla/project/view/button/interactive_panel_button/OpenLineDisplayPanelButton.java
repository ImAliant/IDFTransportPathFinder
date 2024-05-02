package fr.u_paris.gla.project.view.button.interactive_panel_button;

import java.util.List;

import fr.u_paris.gla.project.observer.LineDisplayPanelObserver;
import fr.u_paris.gla.project.view.button.MiniButton;
import fr.u_paris.gla.project.view.panel.LineDisplayPanel;

import java.util.ArrayList;

/**
 * Button to open the line display panel.
 *
 * @see MiniButton
 * @see LineDisplayPanelObserver
 * @see LineDisplayPanel
 */
public class OpenLineDisplayPanelButton extends MiniButton {
    /** Observers of the button. */
    private List<LineDisplayPanelObserver> observers = new ArrayList<>();
    /** Path to the icon. */
    private static final String PATH = "";

    /**
     * Create a new button at the given position.
     *
     * @param x The x position of the button.
     * @param y The y position of the button.
     */
    public OpenLineDisplayPanelButton() {
        super();

        /* Icon icon = IconUtils.createIcon(PATH, WIDTH, HEIGHT);
        setIcon(icon); */
    }

    /**
     * Add an observer to the button.
     *
     * @param observer The observer to add.
     */
    public void addObserver(LineDisplayPanelObserver observer) {
        observers.add(observer);
    }
    /**
     * Notify all observers.
     */
    private void notifyObservers() {
        observers.forEach(LineDisplayPanelObserver::updateVisibility);
    }

    @Override
    public void onClick() {
        notifyObservers();
    }
}
