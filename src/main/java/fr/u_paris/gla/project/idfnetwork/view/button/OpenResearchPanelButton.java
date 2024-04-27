package fr.u_paris.gla.project.idfnetwork.view.button;

import java.util.List;
import java.util.ArrayList;

import javax.swing.Icon;

import fr.u_paris.gla.project.utils.IconUtils;
import fr.u_paris.gla.project.observer.ResearchPanelObserver;

public class OpenResearchPanelButton extends MiniButton {
    private static final String PATH = "src/main/resources/fr/u_paris/gla/project/button_icon/loupe_icon.png";

    private transient List<ResearchPanelObserver> observers = new ArrayList<>();

    public OpenResearchPanelButton(int x, int y) {
        super(x, y);

        Icon icon = IconUtils.createIcon(PATH, WIDTH, HEIGHT);
        setIcon(icon);
    }

    public void addObserver(ResearchPanelObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        observers.forEach(ResearchPanelObserver::updateVisibility);
    }

    @Override
    public void onClick() {
        notifyObservers();
    }
}
