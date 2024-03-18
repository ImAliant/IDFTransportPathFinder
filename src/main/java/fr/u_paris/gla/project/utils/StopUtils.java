/**
 * 
 */
package fr.u_paris.gla.project.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.u_paris.gla.project.idfnetwork.Stop;

public class StopUtils {

    public static Map<String, List<Stop>> associateStations(List<Stop> stops) {
        Map<String, List<Stop>> associatedStops = new HashMap<>();

        for (Stop stop : stops) {
            if (!associatedStops.containsKey(stop.getStopName())) {
                // Si la station n'existe pas encore, on l'ajoute à la carte associée
                List<Stop> stopList = new ArrayList<>();
                stopList.add(stop);
                associatedStops.put(stop.getStopName(), stopList);
            } else {
                // Si la station existe déjà, on vérifie s'il y a une station avec des coordonnées différentes
                List<Stop> stopList = associatedStops.get(stop.getStopName());
                boolean isNewStation = true;
                for (Stop existingStation : stopList) {
                    if (existingStation.getLongitude() == stop.getLongitude() &&
                            existingStation.getLatitude() == stop.getLatitude()) {
                        isNewStation = false;
                        break;
                    }
                }
                // Si la station est nouvelle, on l'ajoute à la liste
                if (isNewStation) {
                    stopList.add(stop);
                }
            }
        }

        return associatedStops;
    }
}

