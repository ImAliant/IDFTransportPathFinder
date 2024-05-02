package fr.u_paris.gla.project.view.button.interactive_panel_button;

import java.util.List;

import javax.swing.Icon;

import fr.u_paris.gla.project.observer.ZoomInObserver;
import fr.u_paris.gla.project.utils.IconUtils;
import fr.u_paris.gla.project.view.button.MiniButton;

import java.util.ArrayList;

/**
 * Button to zoom in the map.
 *
 * @see MiniButton
 * @see ZoomInObserver
 */
public class ZoomInButton extends MiniButton {
    /** Path to the icon. */
    private static final String PATH = "src/main/resources/fr/u_paris/gla/project/button_icon/plus_icon.png";   
    /** Observers of the button. */
    private transient List<ZoomInObserver> observers = new ArrayList<>();

    /**
     * Create a new button at the given position.
     *
     * @param x The x position of the button.
     * @param y The y position of the button.
     */
    public ZoomInButton() {
        super("Zoomer sur la carte");

        Icon icon = IconUtils.createIcon(PATH, WIDTH, HEIGHT);
        setIcon(icon);
    }

    /**
     * Add an observer to the button.
     *
     * @param observer The observer to add.
     */
    public void addObserver(ZoomInObserver observer) {
        observers.add(observer);
    }
    /**
     * Notify all observers.
     */
    public void notifyObservers() {
        observers.forEach(ZoomInObserver::zoomIn);
    }

    @Override
    public void onClick() {
        notifyObservers();
    }
    
}
