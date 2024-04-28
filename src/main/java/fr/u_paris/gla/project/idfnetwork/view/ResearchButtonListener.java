package fr.u_paris.gla.project.idfnetwork.view;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import fr.u_paris.gla.project.idfnetwork.Itinerary;
import fr.u_paris.gla.project.idfnetwork.ItineraryCalculator;
import fr.u_paris.gla.project.idfnetwork.Network;
import fr.u_paris.gla.project.idfnetwork.stop.Stop;

public class ResearchButtonListener implements ActionListener {
    private CustomTextField departureField;
    private CustomTextField arrivalField;

    private Network instance = Network.getInstance();

    public ResearchButtonListener(CustomTextField departureField, CustomTextField arrivalField) {
        this.departureField = departureField;
        this.arrivalField = arrivalField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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

        launchResearch(startStop, destinationStop);
    }

    private double[] parseGeoPosition(String position) {
        String[] pos = position.split(",");
        double[] result = new double[2];

        result[0] = Double.parseDouble(pos[0]);
        result[1] = Double.parseDouble(pos[1]);

        return result;
    }

    private void launchResearch(Stop start, Stop destination) {
        Itinerary route = ItineraryCalculator.CalculateRoad(start, destination);

        if (route == null || route.getStops().isEmpty() || route.getLines().isEmpty()) {
            System.out.println(
                    "Aucun trajet trouvé entre : " + start + " et " + destination + "ou trajet incomplet.");
            return;
        }

        System.out.println("Trajet trouvé :");
        System.out.println("Départ : " + route.getStops().get(0).getStopName());

        for (int i = 0; i < route.getLines().size(); i++) {
            System.out.println("Prendre : " + route.getLines().get(i) + " -  Arret : "
                    + route.getStops().get(i + 1).getStopName());
        }

        System.out.println(
                "Arrivé à destination : " + route.getStops().get(route.getStops().size() - 1).getStopName());
        System.out.println("Distance totale : " + route.getTotalDistance() + " km");
        System.out.println("Durée totale : " + route.getTotalDuration() + " secondes");
    }

    private boolean isGeoPosition(String position) {
        String geoPositionPattern = "^\\d+\\.\\d+,\\d+\\.\\d+$";

        return position.matches(geoPositionPattern);
    }
}
