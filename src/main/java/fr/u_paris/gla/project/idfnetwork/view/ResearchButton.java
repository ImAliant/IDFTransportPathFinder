package fr.u_paris.gla.project.idfnetwork.view;

import java.awt.Color;

import javax.swing.JButton;

import fr.u_paris.gla.project.idfnetwork.Itinerary;
import fr.u_paris.gla.project.idfnetwork.ItineraryCalculator;
import fr.u_paris.gla.project.idfnetwork.Network;
import fr.u_paris.gla.project.idfnetwork.stop.Stop;

public class ResearchButton extends JButton {
    private static final Color BUTTON_FOREGROUND = Color.WHITE;
    private static final Color BUTTON_BACKGROUND = new Color(1, 121, 111);

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
            startStop = Network.findClosestStopByGeoPosition(departPos[0], departPos[1]);
        } else {
            startStop = instance.findStopByName(departName);
        }

        if (isGeoPosition(arriveName)) {
            double[] arrivePos = parseGeoPosition(arriveName);
            destinationStop = Network.findClosestStopByGeoPosition(arrivePos[0], arrivePos[1]);
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
        String[] pos = position.split(",");
        double[] result = new double[2];

        result[0] = Double.parseDouble(pos[0]);
        result[1] = Double.parseDouble(pos[1]);

        return result;
    }

    private Itinerary launchResearch(Stop start, Stop destination) {
        return ItineraryCalculator.CalculateRoad(start, destination);
    }

    private boolean isGeoPosition(String position) {
        String geoPositionPattern = "^\\d+\\.\\d+,\\d+\\.\\d+$";

        return position.matches(geoPositionPattern);
    }
}
