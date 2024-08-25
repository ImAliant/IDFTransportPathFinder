package fr.u_paris.gla.crazytrip.gui.button;

import java.util.ArrayList;
import java.util.List;

import fr.u_paris.gla.crazytrip.algorithm.AstarPathFinder;
import fr.u_paris.gla.crazytrip.algorithm.ItineraryResult;
import fr.u_paris.gla.crazytrip.gui.observer.PathResultObserver;
import fr.u_paris.gla.crazytrip.gui.panel.ArrivalField;
import fr.u_paris.gla.crazytrip.gui.panel.DepartureField;
import fr.u_paris.gla.crazytrip.model.Node;

public class ResearchButton extends StyleButton {
    private static final String TEXT = "Rechercher";

    private transient List<PathResultObserver> observers = new ArrayList<>();

    private DepartureField departureField;
    private ArrivalField arrivalField;

    public ResearchButton(DepartureField departureField, ArrivalField arrivalField) {
        super(TEXT);

        this.departureField = departureField;
        this.arrivalField = arrivalField;
    }

    @Override
    public void action() {
        Node departure = departureField.getSelectedNode();
        Node arrival = arrivalField.getSelectedNode();
        
        if (departure == null || arrival == null) return;
        
        AstarPathFinder pathFinder = new AstarPathFinder(departure, arrival);
        ItineraryResult result = pathFinder.findPath();

        showResult(result);
    }

    public void addObserver(PathResultObserver observer) {
        observers.add(observer);
    }

    public void showResult(ItineraryResult result) {
        observers.forEach(observer -> observer.showResult(result));
    }
}
