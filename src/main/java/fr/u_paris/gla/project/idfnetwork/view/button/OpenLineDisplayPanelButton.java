package fr.u_paris.gla.project.idfnetwork.view.button;

import java.util.List;
import java.util.ArrayList;

import fr.u_paris.gla.project.observer.LineDisplayPanelObserver;


public class OpenLineDisplayPanelButton extends MiniButton {
    private List<LineDisplayPanelObserver> observers = new ArrayList<>();

    private static final String PATH = "";

    public OpenLineDisplayPanelButton(int x, int y) {
        super(x, y);

        /* Icon icon = IconUtils.createIcon(PATH, WIDTH, HEIGHT);
        setIcon(icon); */
    }

    public void addObserver(LineDisplayPanelObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        observers.forEach(LineDisplayPanelObserver::updateVisibility);
    }

    @Override
    public void onClick() {
        System.out.println("OpenLineDisplayPanelButton.onClick");

        /* notifyObservers(); */
    }
}
