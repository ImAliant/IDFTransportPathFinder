package fr.u_paris.gla.project.view.button.research_panel_button;

import java.util.ArrayList;
import java.util.List;

import fr.u_paris.gla.project.observer.ArrivalMapButtonObserver;

public class ArrivalByMapButton extends ItineraryByMapButton{
    protected transient List<ArrivalMapButtonObserver> arrivalObservers = new ArrayList<>();
    
    public ArrivalByMapButton() {
        super();
    }

    /**
     * Add an observer to the button.
     *
     * @param observer The observer to add.
     */
    public void addObserver(ArrivalMapButtonObserver observer) {
        arrivalObservers.add(observer);
    }

    /**
     * Remove an observer to the button.
     *
     * @param observer The observer to remove.
     */
    public void removeObserver(ArrivalMapButtonObserver observer) {
        arrivalObservers.remove(observer);
    }
    /**
     * Notify all observers.
     */
    private void notifyObservers() {
        for (ArrivalMapButtonObserver observer: arrivalObservers) {
            observer.onChangeArrival(true);
        }
    }
    /**
     * Notify all observers when button clicked.
     */
    @Override
    public void onClick() {
        notifyObservers();
    }

    public int countObservers() {
        return arrivalObservers.size();
    }
}
