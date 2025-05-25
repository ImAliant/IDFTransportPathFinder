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

/**
 * <p>Class representing a button to launch a research of an itinerary.</p>
 * 
 * <p>When the button is clicked, it launches a research of an itinerary between 
 * the departure and arrival nodes selected in the departure and arrival fields.</p>
 * <p>If the departure or arrival field is empty, it notifies observers that an error
 * occurred during the research.</p>
 * <p>When the research is done, it notifies observers that the result should be displayed.</p>
 * 
 * @see StyleButton
 * @see PathResultObserver
 * @see ErrorOnResearchObserver
 */
public class ResearchButton extends StyleButton {
    /** Text displayed by the button */
    private static final String TEXT = "Rechercher";
    /** Observers to notify the result of the research */
    private transient List<PathResultObserver> observers = new ArrayList<>();
    /** Observers to notify an error during the research */
    private transient List<ErrorOnResearchObserver> errorObservers = new ArrayList<>();

    /** Departure field to get the selected departure node */
    private DepartureField departureField;
    /** Arrival field to get the selected arrival node */
    private ArrivalField arrivalField;

    /**
     * Constructor.
     * 
     * @param departureField the departure field
     * @param arrivalField the arrival field
     * 
     * @see DepartureField
     * @see ArrivalField
     */
    public ResearchButton(DepartureField departureField, ArrivalField arrivalField) {
        super(TEXT);

        this.departureField = departureField;
        this.arrivalField = arrivalField;
    }

    /**
     * Launch the research of an itinerary between the departure and arrival nodes selected in the departure and arrival fields.
     * 
     * <p>If the departure or arrival field is empty, it notifies observers that an error
     * occurred during the research.</p>
     * <p>When the research is done, it notifies observers that the result should be displayed.</p>
     */
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

    /**
     * Add an observer to the list of observers for the result of the research.
     * 
     * @param observer the observer to add
     */
    public void addObserver(PathResultObserver observer) {
        observers.add(observer);
    }

    /**
     * Add an observer to the list of observers for an error during the research.
     * 
     * @param observer the observer to add
     */
    public void addErrorObserver(ErrorOnResearchObserver observer) {
        errorObservers.add(observer);
    }

    /**
     * Notify all observers that the result of the research should be displayed.
     * 
     * @param result the result of the research
     */
    public void showResult(ItineraryResult result) {
        observers.forEach(observer -> observer.showResult(result));
    }

    /**
     * Notify all observers that an error occurred during the research.
     * 
     * @param message the error message
     */
    public void errorOnResearch(String message) {
        errorObservers.forEach(observer -> observer.errorOnResearch(message));
    }
}
