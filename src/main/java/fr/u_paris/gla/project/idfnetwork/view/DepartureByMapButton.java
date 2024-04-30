package fr.u_paris.gla.project.idfnetwork.view;

import java.util.ArrayList;
import java.util.List;

import fr.u_paris.gla.project.observer.DepartureMapButtonObserver;

public class DepartureByMapButton extends ItineraryByMapButton {
    public DepartureByMapButton(int x, int y) {
        super(x, y);
    }

    private List<DepartureMapButtonObserver> departureObservers = new ArrayList<>();

    public void addObserver(DepartureMapButtonObserver observer) {
        departureObservers.add(observer);
    }


    public void removeObserver(DepartureMapButtonObserver observer) {
        departureObservers.remove(observer);
    }


    private void notifyObservers() {
        for (DepartureMapButtonObserver observer: departureObservers) {
            observer.onChangeDeparture(isEnabled);
        }
    }


    @Override
    public void onClick() {
        isEnabled = true;

        notifyObservers();
    }
    
    
}
