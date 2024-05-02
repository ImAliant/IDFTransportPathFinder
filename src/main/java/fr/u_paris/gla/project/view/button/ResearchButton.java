package fr.u_paris.gla.project.view.button;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import fr.u_paris.gla.project.algo.Itinerary;
import fr.u_paris.gla.project.algo.ItineraryCalculator;
import fr.u_paris.gla.project.idfnetwork.network.Network;
import fr.u_paris.gla.project.observer.ItineraryObserver;
import fr.u_paris.gla.project.view.textfield.CustomTextField;
import fr.u_paris.gla.project.idfnetwork.Stop;
/**
 * The {@code ResearchButton} class extends {@code JButton} to create a specialized button that initiates
 * itinerary search between two specified locations in a GUI application. The button supports input as
 * either names of stops or geographical coordinates, and notifies observers with the calculated itinerary.
 * @author Diamant Alexandre
 * @author Dedeoglu Dilara
 */
public class ResearchButton extends JButton {
    private static final Color BUTTON_FOREGROUND = Color.WHITE;
    private static final Color BUTTON_BACKGROUND = new Color(1, 121, 111);

    private List<ItineraryObserver> observers = new ArrayList<>();
    private CustomTextField departureField;
    private CustomTextField arrivalField;

    private Network instance = Network.getInstance();

    /**
     * Constructs a new {@code ResearchButton} with specified text label and text fields for departure and arrival inputs.
     *
     * @param text the text to display on the button
     * @param departureTF the text field for inputting the departure stop or coordinates
     * @param arrivalTF the text field for inputting the arrival stop or coordinates
     */
    public ResearchButton(String text, CustomTextField departureTF, CustomTextField arrivalTF) {
        super(text);

        this.departureField = departureTF;
        this.arrivalField = arrivalTF;

        setForeground(BUTTON_FOREGROUND);
        setBackground(BUTTON_BACKGROUND);

        addActionListener(e -> onClick());
    }
    /**
     * Adds an {@code ItineraryObserver} to the list of observers to be notified when an itinerary is calculated.
     *
     * @param observer the observer to be added
     */
    public void addObserver(ItineraryObserver observer) {
        observers.add(observer);
    }

     /**
     * Notifies all observers with the provided itinerary.
     *
     * @param itinerary the itinerary to be shared with observers
     */
    private void notifyObservers(Itinerary itinerary) {
        observers.forEach(observer -> observer.showItinerary(itinerary));
    }

    /**
     * Handles the button click event by calculating an itinerary based on the inputs in the departure and arrival fields.
     * It retrieves stops by name or finds the nearest stop by geographic coordinates if provided.
     */
    private void onClick() {
        String departName = departureField.getText();
        String arriveName = arrivalField.getText();

        if (departName.isEmpty() || arriveName.isEmpty()) {
            return;
        }

        Stop startStop = null;
        Stop destinationStop = null;

        if (isGeoPosition(departName)) {
            double[] departPos = parseGeoPosition(departName);
            startStop = instance.findClosestStopByGeoPosition(departPos[0], departPos[1]);
        } else {
            startStop = instance.findStopByName(departName);
        }

        if (isGeoPosition(arriveName)) {
            double[] arrivePos = parseGeoPosition(arriveName);
            destinationStop = instance.findClosestStopByGeoPosition(arrivePos[0], arrivePos[1]);
        } else {
            destinationStop = instance.findStopByName(arriveName);
        }

        if (startStop == null || destinationStop == null) {
            System.out.println("Un des arrêts n'est pas trouvé.");
            return;
        }

        Itinerary route = launchResearch(startStop, destinationStop);

        notifyObservers(route);
    }

    /**
     * Parses a geographic position from a string input expected to be in the format "latitude, longitude".
     *
     * @param position the string containing the geographic coordinates
     * @return an array of two doubles representing the latitude and longitude
     */
    private double[] parseGeoPosition(String position) {
        String[] pos = position.split(", ");

        for (int i = 0; i < pos.length; i++) {
            pos[i] = pos[i].replace(",", ".");
        }

        double[] result = new double[2];

        for (int i = 0; i < pos.length; i++) {
            result[i] = Double.parseDouble(pos[i]);
        }

        return result;
    }
    /**
     * Initiates a search for an itinerary between two stops.
     *
     * @param start the starting stop
     * @param destination the destination stop
     * @return the calculated itinerary
     */
    private Itinerary launchResearch(Stop start, Stop destination) {
        return ItineraryCalculator.CalculateRoad(start, destination);
    }

    private boolean isGeoPosition(String position) {
        String geoPositionPattern = "^\\d+\\,\\d+, \\d+\\,\\d+$";

        return position.matches(geoPositionPattern);
    }
}
