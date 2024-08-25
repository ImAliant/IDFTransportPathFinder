package fr.u_paris.gla.crazytrip.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import fr.u_paris.gla.crazytrip.model.Coordinates;
import fr.u_paris.gla.crazytrip.model.Network;
import fr.u_paris.gla.crazytrip.model.Node;
import fr.u_paris.gla.crazytrip.model.Station;

public class StationDAO {
    private static Network network = Network.getInstance();

    private StationDAO() {}

    public static List<Station> findStation(String name) {
        return network.getStations().values().stream()
            .filter(station -> station.getName().contains(name))
            .collect(Collectors.toList());
    }

    public static Set<Station> findCloseStations(Node node) {
        final double distance = 0.5; // 500 meters
        return network.getStations().values().stream()
            .filter(station -> station.distanceTo(node) <= distance
                && !station.equals(node))
            .collect(Collectors.toSet());
    }

    // TODO: Return a list of the nearest bus, tram and metro stations
    public static Station getNearestStation(Coordinates coordinates) {
        return network.getStations().values().stream().min((station1, station2) -> {
            double distance1 = station1.getCoordinates().distanceTo(coordinates);
            double distance2 = station2.getCoordinates().distanceTo(coordinates);
            return Double.compare(distance1, distance2);
        }).orElse(null);
    }

    public static Set<Station> getAllStations() {
        return network.getStations().values().stream().collect(HashSet::new, HashSet::add, HashSet::addAll);
    }
}
