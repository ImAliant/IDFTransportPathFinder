package fr.u_paris.gla.crazytrip.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import fr.u_paris.gla.crazytrip.model.Coordinates;
import fr.u_paris.gla.crazytrip.model.Network;
import fr.u_paris.gla.crazytrip.model.Node;
import fr.u_paris.gla.crazytrip.model.Station;

/**
 * This class represents a station data access object.
 * 
 * It is used to find stations in the network.
 * 
 * @see Station
 * @see Network
 */
public class StationDAO {
    /** Instance of the network used in the application */
    private static Network network = Network.getInstance();
    /** Private constructor to prevent instantiation */
    private StationDAO() {}

    /**
     * Finds a station by its name.
     * 
     * @param name The name of the station to find.
     * @return A list of stations with the given name or an empty list if no station is found.
     * 
     * @see Station
     */
    public static List<Station> findStation(String name) {
        return network.getStations().values().stream()
            .filter(station -> station.getName().contains(name))
            .collect(Collectors.toList());
    }

    /**
     * Finds all stations within a given distance from a node.
     * 
     * @param node The node from which to search for stations.
     * @param distance The maximum distance from the node.
     * @return A set of stations within the given distance from the node, excluding the node itself.
     * 
     * @see Station
     * @see Node
     */
    public static Set<Station> findCloseStations(Node node, double distance) {
        return network.getStations().values().stream()
            .filter(station -> station.distanceTo(node) <= distance
                && !station.equals(node))
            .collect(Collectors.toSet());
    }

    /**
     * Get the nearest station to the given coordinates.
     * 
     * @param coordinates The coordinates from which to search for the nearest station.
     * @return The nearest station to the given coordinates.
     * 
     * @see Station
     * @see Coordinates
     */
    public static Station getNearestStation(Coordinates coordinates) {
        return network.getStations().values().stream().min((station1, station2) -> {
            double distance1 = station1.getCoordinates().distanceTo(coordinates);
            double distance2 = station2.getCoordinates().distanceTo(coordinates);
            return Double.compare(distance1, distance2);
        }).orElse(null);
    }

    /**
     * Get all stations in the network.
     * 
     * @return A set of all stations in the network.
     * 
     * @see Station
     */
    public static Set<Station> getAllStations() {
        return network.getStations().values().stream().collect(HashSet::new, HashSet::add, HashSet::addAll);
    }
}
