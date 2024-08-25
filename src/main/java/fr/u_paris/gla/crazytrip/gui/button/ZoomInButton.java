package fr.u_paris.gla.crazytrip.gui.button;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;

import fr.u_paris.gla.crazytrip.gui.observer.ZoomInObserver;
import fr.u_paris.gla.crazytrip.utils.IconUtils;

public class ZoomInButton extends MiniButton {
    /** Path to the icon */
    private static final String PATH = "src/main/resources/icons/plus_icon.png";
    /** Tooltip text */
    private static final String TOOLTIP = "Zoomer";
    /** Observers of the button */
    private transient List<ZoomInObserver> observers = new ArrayList<>();

    public ZoomInButton() {
        super(TOOLTIP);

        Icon icon = IconUtils.createIcon(PATH, SIZE, SIZE);
        if (icon != null) setIcon(icon);
    }

    public void addObserver(ZoomInObserver observer) {
        observers.add(observer);
    }

    @Override
    public void action() {
        observers.forEach(ZoomInObserver::zoomIn);
    }
}
