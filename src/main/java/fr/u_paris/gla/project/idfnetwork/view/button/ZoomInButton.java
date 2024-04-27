package fr.u_paris.gla.project.idfnetwork.view.button;

import java.util.List;

import javax.swing.Icon;

import fr.u_paris.gla.project.observer.ZoomInObserver;
import fr.u_paris.gla.project.utils.IconUtils;

import java.util.ArrayList;

public class ZoomInButton extends MiniButton {
    private static final String PATH = "src/main/resources/fr/u_paris/gla/project/button_icon/plus_icon.png";

    private transient List<ZoomInObserver> observers = new ArrayList<>();

    public ZoomInButton(int x, int y) {
        super(x, y);

        Icon icon = IconUtils.createIcon(PATH, WIDTH, HEIGHT);
        setIcon(icon);
    }

    public void addObserver(ZoomInObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        observers.forEach(ZoomInObserver::zoomIn);
    }

    @Override
    public void onClick() {
        notifyObservers();
    }
    
}
