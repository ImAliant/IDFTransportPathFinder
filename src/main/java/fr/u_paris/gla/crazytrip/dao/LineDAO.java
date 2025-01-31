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

/**
 * This class represents a line data access object.
 * 
 * It is used to find lines in the network.
 * 
 * @see Line
 * @see Network
 */
public class LineDAO {
    /** Instance of the network used in the application */
    private static Network network = Network.getInstance();
    /** Private constructor to prevent instantiation */
    private LineDAO() {}

    /**
     * Finds a line by its name.
     * 
     * @param name The name of the line to find.
     * @return A list of lines with the given name or an empty list if no line is found.
     * 
     * @see Line
     */
    public static List<Line> findLine(String name) {
        return network.getLines().values().stream()
            .filter(line -> line.getName().equals(name))
            .collect(Collectors.toList());
    }

    /**
     * Finds a line by its type.
     * 
     * @param type The type of the line to find.
     * @return A list of lines with the given type.
     * 
     * @see Line
     * @see RouteType
     */
    public static List<Line> findLineByType(RouteType type) {
        return network.getLines().values().stream()
            .filter(line -> line.getLineType().equals(type))
            .collect(Collectors.toList());
    }

    /**
     * Finds a line by a station.
     * 
     * @param station The station to find the line.
     * @return A list of lines that contain the given station.
     * 
     * @see Line
     * @see Station
     */
    public static List<Line> findLineByStation(Station station) {
        return network.getLines().values().stream()
            .filter(line -> line.getStations().contains(station))
            .collect(Collectors.toList());
    }

    /**
     * Finds all the segments of a line.
     * 
     * @param line The line to find the segments.
     * @return A set of segments of the given line, or an empty set if no segment is found.
     * 
     * @see Line
     * @see SegmentTransport
     */
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

    /**
     * Finds a line by its key.
     * 
     * @param key The key of the line to find.
     * @return The line with the given key or null if no line is found.
     * 
     * @see LineKey
     * @see Line
     */
    public static Line findLineByKey(LineKey key) {
        return network.getLines().get(key);
    }
}
