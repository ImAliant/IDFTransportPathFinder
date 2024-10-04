package fr.u_paris.gla.crazytrip.gui.button;

import java.util.ArrayList;
import java.util.List;

import fr.u_paris.gla.crazytrip.algorithm.AstarPathFinder;
import fr.u_paris.gla.crazytrip.algorithm.ItineraryResult;
import fr.u_paris.gla.crazytrip.gui.loadingscreen.LoadingScreen;
import fr.u_paris.gla.crazytrip.gui.observer.ErrorOnResearchObserver;
import fr.u_paris.gla.crazytrip.gui.observer.PathResultObserver;
import fr.u_paris.gla.crazytrip.gui.panel.ArrivalField;
import fr.u_paris.gla.crazytrip.gui.panel.DepartureField;
import fr.u_paris.gla.crazytrip.model.Node;

public class ResearchButton extends StyleButton {
    private static final String TEXT = "Rechercher";

    private transient List<PathResultObserver> observers = new ArrayList<>();
    private transient List<ErrorOnResearchObserver> errorObservers = new ArrayList<>();

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
        
        if (departure == null || arrival == null) {
            errorOnResearch("Un des champs est vide");
            return;
        }

        LoadingScreen.getInstance().start();
        
        AstarPathFinder pathFinder = new AstarPathFinder(departure, arrival);
        ItineraryResult result = pathFinder.findPath();

        if (result == null) {
            errorOnResearch("Aucun itinéraire trouvé");
            return;
        }

        showResult(result);

        LoadingScreen.getInstance().stop();
    }

    public void addObserver(PathResultObserver observer) {
        observers.add(observer);
    }

    public void addErrorObserver(ErrorOnResearchObserver observer) {
        errorObservers.add(observer);
    }

    public void showResult(ItineraryResult result) {
        observers.forEach(observer -> observer.showResult(result));
    }

    public void errorOnResearch(String message) {
        errorObservers.forEach(observer -> observer.errorOnResearch(message));
    }
}
