/**
 * 
 */
package fr.u_paris.gla.project.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.u_paris.gla.project.idfnetwork.network.Network;
import fr.u_paris.gla.project.idfnetwork.Stop;

/**
 * Utility class for associating stops with identical names but different coordinates.
 */
public class StopUtils {
    private static final double DISTANCE_THRESHOLD = 0.1;

    private StopUtils() {}

    /**
     * Associates stops with identical names but different coordinates.
     * 
     * @param stops List of stops to associate
     * @return A map where each key is a stop name and the value is a list of stops with that name
     */
    public static Map<String, List<Stop>> associateStations() {
        // Map to store associated stops
        Map<String, List<Stop>> associatedStops = new HashMap<>();

        // Loop through all stops
        for (Stop stop : Network.getInstance().getStops()) {
            List<Stop> stopList = new ArrayList<>();
            associatedStops.putIfAbsent(stop.getStopName(), stopList);
            for (Stop s : stopList) {
                // Compare the coordinates of the existing station with the current stop
                if (s.getStopName().equals(stop.getStopName()) && GPS.distance(s.getLatitude(), s.getLongitude(), stop.getLatitude(), stop.getLongitude()) < DISTANCE_THRESHOLD){
                    stopList.add(stop);
                }
            }
        }
        
        return associatedStops;
    }
}
