/**
 * 
 */
package fr.u_paris.gla.project.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.u_paris.gla.project.idfnetwork.Stop;
import fr.u_paris.gla.project.idfnetwork.Network;
import fr.u_paris.gla.project.utils.GPS;

/**
 * Utility class for associating stops with identical names but different coordinates.
 */
public class StopUtils {

    /**
     * Associates stops with identical names but different coordinates.
     * 
     * @param stops List of stops to associate
     * @return A map where each key is a stop name and the value is a list of stops with that name
     */
  /*   public static Map<String, List<Stop>> associateStations() {
        // Map to store associated stops
       List <Stop> all_stops =   Network.getInstance().getStops();
        Map<String, List<Stop>> associatedStops = new HashMap<>();

        // Loop through all stops
        for (Stop stop : all_stops) {
            // Check if the stop name is already in the map
            if (!associatedStops.containsKey(stop.getStopName())) {
                // If the stop name doesn't exist yet, create a new list and add the stop to it
                List<Stop> stopList = new ArrayList<>();
                stopList.add(stop);
                associatedStops.put(stop.getStopName(), stopList);
            } else {
                // If the stop name already exists, check if there's a stop with different coordinates
                List<Stop> stopList = associatedStops.get(stop.getStopName());
                boolean isNewStation = true;
                for (Stop existingStation : stopList) {
                    // Compare the coordinates of the existing station with the current stop
                    if (existingStation.getLongitude() == stop.getLongitude() &&
                            existingStation.getLatitude() == stop.getLatitude()) {
                        // If coordinates match, it's not a new station
                        isNewStation = false;
                        break;
                    }
                }
                // If it's a new station, add it to the list
                if (isNewStation) {
                    stopList.add(stop);
                }
            }
        }

        return associatedStops;
}  */
    public static Map<String, List<Stop>> associateStations() {
        // Map to store associated stops
        Map<String, List<Stop>> associatedStops = new HashMap<>();

        // Loop through all stops
        for (Stop stop : Network.getInstance().getStops()) {
            List<Stop> stopList = new ArrayList<>();
            associatedStops.putIfAbsent(stop.getStopName(), stopList);
            for (Stop s : stopList) {
                // Compare the coordinates of the existing station with the current stop
                if (s.getStopName() == stop.getStopName() && GPS.distance(s.getLatitude(), s.getLongitude(), stop.getLatitude(), stop.getLongitude()) < 100){
                    stopList.add(stop);
                }
                 
                   
            }
        }
        
        return associatedStops;
    }
}
