package fr.u_paris.gla.crazytrip.view.button.interactive_panel_button;

import java.util.List;
import java.util.ArrayList;

import javax.swing.Icon;

import fr.u_paris.gla.crazytrip.observer.LineDisplayPanelObserver;
import fr.u_paris.gla.crazytrip.utils.IconUtils;
import fr.u_paris.gla.crazytrip.view.button.MiniButton;
import fr.u_paris.gla.crazytrip.view.panel.LineDisplayPanel;

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
    private static final String PATH = "src/main/resources/fr/u_paris/gla/crazytrip/button_icon/paris_transit_icons/plan.png";

    /**
     * Create a new button at the given position.
     *
     * @param x The x position of the button.
     * @param y The y position of the button.
     */
    public OpenLineDisplayPanelButton() {
        super("Afficher les lignes sur le plan");

        Icon icon = IconUtils.createIcon(PATH, WIDTH, HEIGHT);
        setIcon(icon);
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
    /**
     * Notify all observers when button clicked
     */
    @Override
    public void onClick() {
        notifyObservers();
    }
}
