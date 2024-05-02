package fr.u_paris.gla.project.view.button.research_panel_button;

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

    protected void notifyObservers(Itinerary itinerary) {
        observers.forEach(observer -> observer.showItinerary(itinerary));
    }

    protected void onClick() {
        String departName = departureField.getText();
        String arriveName = arrivalField.getText();

        if (departName.isEmpty() || arriveName.isEmpty()) {
            return;
        }

        Stop startStop = null;
        Stop destinationStop = null;

        startStop = findStopByNameOrGeoPosition(departName);
        destinationStop = findStopByNameOrGeoPosition(arriveName);

        if (startStop == null || destinationStop == null) {
            System.out.println("Un des arrêts n'est pas trouvé.");
            return;
        }

        Itinerary route = launchResearch(startStop, destinationStop);

        notifyObservers(route);
    }

    protected Stop findStopByNameOrGeoPosition(String name) {
        Stop stop;

        if (isGeoPosition(name)) {
            double[] pos = parseGeoPosition(name);
            stop = instance.findClosestStopByGeoPosition(pos[0], pos[1]);
        } else {
            stop = instance.findStopByName(name);
        }

        return stop;
    }

    protected double[] parseGeoPosition(String position) {
        String[] pos = position.split(",");

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

    protected boolean isGeoPosition(String position) {
        String geoPositionPattern = "^\\d+\\.\\d+, \\d+\\.\\d+$";

        return position.matches(geoPositionPattern);
    }

    public int countObservers() {
        return observers.size();
    }
}
