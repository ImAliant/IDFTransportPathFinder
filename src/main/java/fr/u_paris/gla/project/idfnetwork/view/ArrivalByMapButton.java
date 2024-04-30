package fr.u_paris.gla.project.idfnetwork.view;

import java.util.ArrayList;
import java.util.List;

import fr.u_paris.gla.project.observer.ArrivalMapButtonObserver;

public class ArrivalByMapButton extends ItineraryByMapButton{
    
    public ArrivalByMapButton(int x, int y) {
        super(x, y);
    }

    private List<ArrivalMapButtonObserver> arrivalObservers = new ArrayList<>();

    public void addObserver(ArrivalMapButtonObserver observer) {
        arrivalObservers.add(observer);
    }


    public void removeObserver(ArrivalMapButtonObserver observer) {
        arrivalObservers.remove(observer);
    }

    private void notifyObservers() {
        for (ArrivalMapButtonObserver observer: arrivalObservers) {
            observer.onChangeArrival(isEnabled);
        }
    }
    @Override

    public void onClick() {
        isEnabled = true;

        notifyObservers();

        
    }

    
}
