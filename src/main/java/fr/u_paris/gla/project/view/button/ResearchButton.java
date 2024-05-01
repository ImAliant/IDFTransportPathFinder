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

public class ResearchButton extends JButton {
    private static final Color BUTTON_FOREGROUND = Color.WHITE;
    private static final Color BUTTON_BACKGROUND = new Color(1, 121, 111);

    private List<ItineraryObserver> observers = new ArrayList<>();
    private CustomTextField departureField;
    private CustomTextField arrivalField;

    private Network instance = Network.getInstance();

    public ResearchButton(String text, CustomTextField departureTF, CustomTextField arrivalTF) {
        super(text);

        this.departureField = departureTF;
        this.arrivalField = arrivalTF;

        setForeground(BUTTON_FOREGROUND);
        setBackground(BUTTON_BACKGROUND);

        addActionListener(e -> onClick());
    }

    public void addObserver(ItineraryObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(Itinerary itinerary) {
        observers.forEach(observer -> observer.showItinerary(itinerary));
    }

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

    private Itinerary launchResearch(Stop start, Stop destination) {
        return ItineraryCalculator.CalculateRoad(start, destination);
    }

    private boolean isGeoPosition(String position) {
        String geoPositionPattern = "^\\d+\\,\\d+, \\d+\\,\\d+$";

        return position.matches(geoPositionPattern);
    }
}