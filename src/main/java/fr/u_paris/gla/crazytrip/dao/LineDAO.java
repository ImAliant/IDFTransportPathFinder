package fr.u_paris.gla.crazytrip.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.model.Network;
import fr.u_paris.gla.crazytrip.model.SegmentTransport;
import fr.u_paris.gla.crazytrip.model.Station;
import fr.u_paris.gla.crazytrip.model.key.LineKey;
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

    public static Set<SegmentTransport> findAllSegmentsOfLine(Line line) {
        Map<LineKey, Line> lines = network.getLines();
        LineKey key = lines.entrySet().stream()
            .filter(entry -> entry.getValue().equals(line))
            .map(Map.Entry::getKey)
            .findFirst()
            .orElse(null);
        
        if (key == null) {
            return Set.of();
        }

        return network.getGraph().values().stream()
            .flatMap(Set::stream)
            .filter(SegmentTransport.class::isInstance)
            .map(SegmentTransport.class::cast)
            .filter(segment -> segment.getLineKey().equals(key))
            .collect(Collectors.toSet());
    }

    public static Line findLineByKey(LineKey key) {
        return network.getLines().get(key);
    }
}
