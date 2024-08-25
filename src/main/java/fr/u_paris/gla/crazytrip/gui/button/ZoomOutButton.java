package fr.u_paris.gla.crazytrip.gui.button;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;

import fr.u_paris.gla.crazytrip.gui.observer.ZoomOutObserver;
import fr.u_paris.gla.crazytrip.utils.IconUtils;

public class ZoomOutButton extends MiniButton {
    /** Path to the icon */
    private static final String PATH = "src/main/resources/icons/moins_icon.png";
    /** Tooltip text */
    private static final String TOOLTIP = "Dézoomer";
    /** Observers of the button */
    private transient List<ZoomOutObserver> observers = new ArrayList<>();

    public ZoomOutButton() {
        super(TOOLTIP);

        Icon icon = IconUtils.createIcon(PATH, SIZE, SIZE);
        if (icon != null) setIcon(icon);
    }

    public void addObserver(ZoomOutObserver observer) {
        observers.add(observer);
    }

    @Override
    public void action() {
        observers.forEach(ZoomOutObserver::zoomOut);
    }
}
