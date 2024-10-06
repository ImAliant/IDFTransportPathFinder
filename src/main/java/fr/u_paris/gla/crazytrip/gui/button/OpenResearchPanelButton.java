package fr.u_paris.gla.crazytrip.gui.button;

import java.util.List;
import java.util.ArrayList;

import javax.swing.Icon;

import fr.u_paris.gla.crazytrip.gui.observer.PanelObserver;
import fr.u_paris.gla.crazytrip.utils.IconUtils;

/**
 * <p>Class representing a button to open the research panel.
 * It is used in the InteractiveButtonPanel class.</p>
 * 
 * <p>When the button is clicked, it notifies observers that the visibility
 * of the panel should be updated.</p>
 * 
 * @see MiniButton
 * @see PanelObserver
 */ 
public class OpenResearchPanelButton extends MiniButton {
    /** Path to the icon of the button. */
    private static final String ICON_PATH = "src/main/resources/icons/loupe_icon.png";
    /** Text to display when the mouse is over the button. */
    private static final String TOOLTIP = "Ouvrir le panneau de recherche";
    /** Observers to notify when the button is clicked. */
    private transient List<PanelObserver> observers = new ArrayList<>();

    /**
     * Constructor.
     */
    public OpenResearchPanelButton() {
        super(TOOLTIP);

        Icon icon = IconUtils.createIcon(ICON_PATH, SIZE, SIZE);
        if (icon != null) setIcon(icon);
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
     * Notify all observers that the button has been clicked, and update the visibility of the panel.
     */
    @Override
    public void action() {
        observers.forEach(PanelObserver::updateVisibility);
    }
}
