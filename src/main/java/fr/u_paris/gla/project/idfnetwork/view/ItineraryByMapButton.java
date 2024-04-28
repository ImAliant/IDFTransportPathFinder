
package fr.u_paris.gla.project.idfnetwork.view;

import java.util.List;
import java.util.ArrayList;

import fr.u_paris.gla.project.idfnetwork.view.button.MiniButton;
import fr.u_paris.gla.project.observer.ItineraryMapButtonObserver;

public class ItineraryByMapButton extends MiniButton {
    private List<ItineraryMapButtonObserver> observers = new ArrayList<>();

    public ItineraryByMapButton(int x, int y) {
        super(x, y);
    }

    private boolean isEnabled = false;

    @Override
    public void onClick() {
        isEnabled = true;

        notifyObservers();
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void addObserver(ItineraryMapButtonObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ItineraryMapButtonObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (ItineraryMapButtonObserver observer: observers) {
            observer.onChange(isEnabled);
        }
    }
}
