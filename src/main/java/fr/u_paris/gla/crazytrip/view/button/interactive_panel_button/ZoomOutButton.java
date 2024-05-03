package fr.u_paris.gla.crazytrip.view.button.interactive_panel_button;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;

import fr.u_paris.gla.crazytrip.observer.ZoomOutObserver;
import fr.u_paris.gla.crazytrip.utils.IconUtils;
import fr.u_paris.gla.crazytrip.view.button.MiniButton;

/**
 * Button to zoom out the map.
 *
 * @see MiniButton
 * @see ZoomOutObserver
 */
public class ZoomOutButton extends MiniButton {
    /** Path to the icon. */
    private static final String PATH = "src/main/resources/fr/u_paris/gla/crazytrip/button_icon/moins_icon.png";
    /** Observers of the button. */
    private transient List<ZoomOutObserver> observers = new ArrayList<>();

    /**
     * Create a new button at the given position.
     *
     * @param x The x position of the button.
     * @param y The y position of the button.
     */
    public ZoomOutButton() {
        super("Dézoomer sur la carte");

        Icon icon = IconUtils.createIcon(PATH, WIDTH, HEIGHT);
        setIcon(icon);
    }

    /**
     * Add an observer to the button.
     *
     * @param observer The observer to add.
     */
    public void addObserver(ZoomOutObserver observer) {
        observers.add(observer);
    }

    /**
     * Notify all observers.
     */
    private void notifyObservers() {
        observers.forEach(ZoomOutObserver::zoomOut);
    }

    @Override
    public void onClick() {
        notifyObservers();
    }
}
