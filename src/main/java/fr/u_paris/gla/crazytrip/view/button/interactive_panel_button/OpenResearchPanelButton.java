package fr.u_paris.gla.crazytrip.view.button.interactive_panel_button;

import java.util.List;
import java.util.ArrayList;

import javax.swing.Icon;

import fr.u_paris.gla.crazytrip.observer.ResearchPanelObserver;
import fr.u_paris.gla.crazytrip.utils.IconUtils;
import fr.u_paris.gla.crazytrip.view.button.MiniButton;

/**
 * Button to open the research panel.
 *
 * @see MiniButton
 * @see ResearchPanelObserver
 * @see ResearchPanel
 */
public class OpenResearchPanelButton extends MiniButton {
    /** Path to the icon. */
    private static final String PATH = "src/main/resources/fr/u_paris/gla/crazytrip/button_icon/loupe_icon.png";
    /** Observers of the button. */
    private transient List<ResearchPanelObserver> observers = new ArrayList<>();

    /**
     * Create a new button at the given position.
     *
     * @param x The x position of the button.
     * @param y The y position of the button.
     */
    public OpenResearchPanelButton() {
        super("Ouvrir le panneau de recherche");
        
        Icon icon = IconUtils.createIcon(PATH, WIDTH, HEIGHT);
        setIcon(icon);
    }

    /**
     * Add an observer to the button.
     *
     * @param observer The observer to add.
     */
    public void addObserver(ResearchPanelObserver observer) {
        observers.add(observer);
    }
    /**
     * Notify all observers.
     */
    private void notifyObservers() {
        observers.forEach(ResearchPanelObserver::updateVisibility);
    }
    /**
     * Notify all observers when button clicked
     */
    @Override
    public void onClick() {
        notifyObservers();
    }
}
