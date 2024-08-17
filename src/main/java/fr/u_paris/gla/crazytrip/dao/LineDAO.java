package fr.u_paris.gla.crazytrip.dao;

import java.util.List;
import java.util.stream.Collectors;

import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.model.Network;
import fr.u_paris.gla.crazytrip.model.Station;
import fr.u_paris.gla.crazytrip.model.line.RouteType;

public class LineDAO {
    private static Network network = Network.getInstance();

    private LineDAO() {}

    public static List<Line> findLine(String name) {
        return network.getLines().values().stream()
            .filter(line -> line.getName().equals(name))
            .collect(Collectors.toList());
    }

    public static List<Line> findLineByType(RouteType type) {
        return network.getLines().values().stream()
            .filter(line -> line.getLineType().equals(type))
            .collect(Collectors.toList());
    }

    public static List<Line> findLineByStation(Station station) {
        return network.getLines().values().stream()
            .filter(line -> line.getStations().contains(station))
            .collect(Collectors.toList());
    }
}
