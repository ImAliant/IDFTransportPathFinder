package fr.u_paris.gla.crazytrip.gui.button.transport_selection_button;

import java.util.List;

import javax.swing.Icon;

import java.util.ArrayList;

import fr.u_paris.gla.crazytrip.gui.button.MiniButton;
import fr.u_paris.gla.crazytrip.gui.observer.DisplayLineObserver;
import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.utils.IconUtils;

/**
 * Abstract class representing a transport button.
 * 
 * It is used to create a button that will be used to select a transport type.
 * 
 * @see MiniButton
 * @see DisplayLineObserver
 */
public abstract class TransportButton extends MiniButton {
    /** Observers of the transport button */
    protected transient List<DisplayLineObserver> observers = new ArrayList<>();
    /**
     * Constructor of the transport button.
     * 
     * @param iconPath The path of the icon of the button.
     * @param tooltipText The text of the tooltip of the button.
     */
    protected TransportButton(String iconPath, String tooltipText) {
        super(tooltipText);

        Icon icon = IconUtils.createIcon(iconPath, SIZE, SIZE);
        if (icon != null) setIcon(icon);
    }

    /**
     * Adds an observer to the transport button.
     * 
     * @param observer The observer to add.
     */
    public void addObserver(DisplayLineObserver observer) {
        observers.add(observer);
    }

    /**
     * Notifies the observers the list of lines to add to a combo box.
     * 
     * @param lines The lines to notify to the observers.
     */
    public void notifyObservers(List<Line> lines) {
        observers.forEach(observer -> observer.update(lines));
    }
}
