package fr.u_paris.gla.project.view.button;

import java.util.List;
import java.util.ArrayList;

import javax.swing.Icon;

import fr.u_paris.gla.project.observer.ResearchPanelObserver;
import fr.u_paris.gla.project.utils.IconUtils;

/**
 * Button to open the research panel.
 *
 * @see MiniButton
 * @see ResearchPanelObserver
 * @see ResearchPanel
 */
public class OpenResearchPanelButton extends MiniButton {
    /** Path to the icon. */
    private static final String PATH = "src/main/resources/fr/u_paris/gla/project/button_icon/loupe_icon.png";
    /** Observers of the button. */
    private transient List<ResearchPanelObserver> observers = new ArrayList<>();

    /**
     * Create a new button at the given position.
     *
     * @param x The x position of the button.
     * @param y The y position of the button.
     */
    public OpenResearchPanelButton(/* int x, int y */) {
        super(/* x, y */);

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

    @Override
    public void onClick() {
        notifyObservers();
    }
}
