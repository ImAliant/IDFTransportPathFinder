package fr.u_paris.gla.project.idfnetwork.view.button;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;

import fr.u_paris.gla.project.observer.ZoomOutObserver;
import fr.u_paris.gla.project.utils.IconUtils;

public class ZoomOutButton extends MiniButton {
    private static final String PATH = "src/main/resources/fr/u_paris/gla/project/button_icon/moins_icon.png";

    private transient List<ZoomOutObserver> observers = new ArrayList<>();

    public ZoomOutButton(int x, int y) {
        super(x, y);

        Icon icon = IconUtils.createIcon(PATH, WIDTH, HEIGHT);
        setIcon(icon);
    }

    public void addObserver(ZoomOutObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        observers.forEach(ZoomOutObserver::zoomOut);
    }

    @Override
    public void onClick() {
        notifyObservers();
    }
}
