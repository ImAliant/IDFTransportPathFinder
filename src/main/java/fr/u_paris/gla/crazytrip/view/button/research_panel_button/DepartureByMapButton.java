package fr.u_paris.gla.crazytrip.view.button.research_panel_button;

import java.util.ArrayList;
import java.util.List;

import fr.u_paris.gla.crazytrip.observer.DepartureMapButtonObserver;

public class DepartureByMapButton extends ItineraryByMapButton {
    protected transient List<DepartureMapButtonObserver> departureObservers = new ArrayList<>();

    public DepartureByMapButton() {
        super();
    }

    public void addObserver(DepartureMapButtonObserver observer) {
        departureObservers.add(observer);
    }


    public void removeObserver(DepartureMapButtonObserver observer) {
        departureObservers.remove(observer);
    }


    private void notifyObservers() {
        for (DepartureMapButtonObserver observer: departureObservers) {
            observer.onChangeDeparture(true);
        }
    }

    @Override
    public void onClick() {
        notifyObservers();
    }

    public int countObservers() {
        return departureObservers.size();
    }
}
