package fr.u_paris.gla.crazytrip.view.button.research_panel_button;

import java.util.ArrayList;
import java.util.List;

import fr.u_paris.gla.crazytrip.observer.ArrivalMapButtonObserver;

public class ArrivalByMapButton extends ItineraryByMapButton{
    protected transient List<ArrivalMapButtonObserver> arrivalObservers = new ArrayList<>();
    
    public ArrivalByMapButton() {
        super();
    }

    public void addObserver(ArrivalMapButtonObserver observer) {
        arrivalObservers.add(observer);
    }


    public void removeObserver(ArrivalMapButtonObserver observer) {
        arrivalObservers.remove(observer);
    }

    private void notifyObservers() {
        for (ArrivalMapButtonObserver observer: arrivalObservers) {
            observer.onChangeArrival(true);
        }
    }

    @Override
    public void onClick() {
        notifyObservers();
    }

    public int countObservers() {
        return arrivalObservers.size();
    }
}
