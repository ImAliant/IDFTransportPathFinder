package fr.u_paris.gla.crazytrip.gui.button;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;

import fr.u_paris.gla.crazytrip.gui.observer.ZoomInObserver;
import fr.u_paris.gla.crazytrip.utils.IconUtils;

/**
 * <p>Class representing a button to zoom in the map.
 * It is used in the InteractiveButtonPanel class.</p>
 * 
 * <p>When the button is clicked, it notifies observers that the map should be zoomed in.</p>
 * 
 * @see MiniButton
 * @see ZoomInObserver
 */
public class ZoomInButton extends MiniButton {
    /** Path to the icon */
    private static final String PATH = "src/main/resources/icons/plus_icon.png";
    /** Tooltip text */
    private static final String TOOLTIP = "Zoomer";
    /** Observers of the button */
    private transient List<ZoomInObserver> observers = new ArrayList<>();

    /**
     * Constructor.
     * 
     * Create a button with the icon and the tooltip.
     */
    public ZoomInButton() {
        super(TOOLTIP);

        Icon icon = IconUtils.createIcon(PATH, SIZE, SIZE);
        if (icon != null) setIcon(icon);
    }

    /**
     * Add an observer to the list of observers.
     * 
     * @param observer the observer to add
     */
    public void addObserver(ZoomInObserver observer) {
        observers.add(observer);
    }

    /**
     * Notify all observers that the button has been clicked.
     */
    @Override
    public void action() {
        observers.forEach(ZoomInObserver::zoomIn);
    }
}
