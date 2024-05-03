package fr.u_paris.gla.project.view.button;

import java.util.ArrayList;
import java.util.List;

import fr.u_paris.gla.project.observer.DepartureMapButtonObserver;

public class DepartureByMapButton extends ItineraryByMapButton {
    private transient List<DepartureMapButtonObserver> departureObservers = new ArrayList<>();

    public DepartureByMapButton() {
        super();
    }
    /**
     * Add an observer to the button.
     *
     * @param observer The observer to add.
     */
    public void addObserver(DepartureMapButtonObserver observer) {
        departureObservers.add(observer);
    }

    /**
     * Remove an observer to the button.
     *
     * @param observer The observer to remove.
     */
    public void removeObserver(DepartureMapButtonObserver observer) {
        departureObservers.remove(observer);
    }

    /**
     * Notify all observers.
     */
    private void notifyObservers() {
        for (DepartureMapButtonObserver observer: departureObservers) {
            observer.onChangeDeparture(isEnabled);
        }
    }
    /**
     * Notify all observers when button clicked.
     */
    @Override
    public void onClick() {
        isEnabled = true;

        notifyObservers();
    }
}
