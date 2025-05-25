package fr.u_paris.gla.crazytrip.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.u_paris.gla.crazytrip.dtos.NodeDTO;
import fr.u_paris.gla.crazytrip.dtos.SegmentTransportDTO;
import fr.u_paris.gla.crazytrip.io.TimeFormat;
import fr.u_paris.gla.crazytrip.model.key.LineKey;
import fr.u_paris.gla.crazytrip.model.key.NodeKey;
import fr.u_paris.gla.crazytrip.model.line.RouteType;

/**
 * A parser for the network data.
 * 
 * This class is used to parse the network data from a CSV file and create the
 * corresponding objects.
 * 
 * The network data is composed of the following fields:
 * <ul>
 * <li>Line name</li>
 * <li>Route type</li>
 * <li>Color</li>
 * <li>Stop name</li>
 * <li>Longitude and latitude</li>
 * <li>Next stop name</li>
 * <li>Next longitude and latitude</li>
 * <li>Duration</li>
 * <li>Distance</li>
 * </ul>
 * 
 * @see NodeDTO
 * @see SegmentTransportDTO
 * @see LineKey
 * @see NodeKey
 */
public class Parser {
    // CSV file format
    private static final int LNAME_INDEX = 0;
    private static final int ROUTETYPE_INDEX = 2;
    private static final int COLOR_INDEX = 3;
    private static final int STOP_NAME_INDEX = 4;
    private static final int LONGLAT_INDEX = 5;
    private static final int NEXT_STOP_NAME_INDEX = 6;
    private static final int NEXT_LONGLAT_INDEX = 7;
    private static final int DURATION_INDEX = 8;
    private static final int DISTANCE_INDEX = 9;

    private static final int FIELDS_SIZE = 10;

    /** Stores the stations. */
    private static final Map<NodeKey, NodeDTO> stations = new HashMap<>();
    /** Stores the segments. */
    private static final Set<SegmentTransportDTO> segments = new HashSet<>();
    /** Stores the lines. */
    private static final Map<LineKey, NodeKey> lines = new HashMap<>();
    
    /** The singleton instance of the parser. */
    private static Parser instance = null;

    /** Private constructor to prevent instantiation. */
    private Parser() {}

    /**
     * Get the singleton instance of the parser.
     * 
     * @return the singleton instance of the parser
     */
    public static Parser getInstance() {
        if (instance == null) {
            instance = new Parser();
        }
        return instance;
    }

    /**
     * Parse the network data from the given file.
     * 
     * @param dataFile the file containing the network data
     * @throws IOException if an I/O error occurs
     */
    public void parse(String dataFile) throws IOException {
        processNetwork(dataFile);
    }

    /**
     * Process the network data from the given file.
     * 
     * @param dataFile the file containing the network data
     * @throws IOException if an I/O error occurs
     */
    private void processNetwork(String dataFile) throws IOException {
        File file = new File(dataFile);
        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist: " + dataFile);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String csvDelimiter = ";";
            while ((line = reader.readLine()) != null) {
                String[] fields = parseCSVLine(line, csvDelimiter);

                processFields(fields);
            }
        }
    }

    /**
     * Process the fields of a line from the CSV file.
     * 
     * @param fields the fields of the line
     */
    public void processFields(String[] fields) {
        if (fields.length != FIELDS_SIZE) {
            throw new IllegalArgumentException("Invalid number of fields: " + fields.length);
        }

        NodeDTO start;
        NodeDTO end;

        String lineName = fields[LNAME_INDEX].trim();
        String routetype = fields[ROUTETYPE_INDEX].trim();
        double duration = TimeFormat.convertToSeconds(fields[DURATION_INDEX]);
        double distance = Double.parseDouble(fields[DISTANCE_INDEX]);
        String color = fields[COLOR_INDEX].trim();

        LineKey lineKey = new LineKey(lineName, RouteType.fromString(routetype), color);

        start = processNode(fields, lineKey, STOP_NAME_INDEX, LONGLAT_INDEX);
        end = processNode(fields, lineKey, NEXT_STOP_NAME_INDEX, NEXT_LONGLAT_INDEX);

        processSegment(start, end, duration, distance, lineKey);

        lines.putIfAbsent(lineKey, start.generateKey());
    }

    /**
     * Process a node from the fields of a line.
     * 
     * @param fields the fields of the line
     * @param key the key of the line
     * @param indexStop the index of the stop name
     * @param indexLonglat the index of the longitude and latitude
     * @return the node
     */
    private NodeDTO processNode(String[] fields, final LineKey key, final int indexStop, final int indexLonglat) {
        String stopName = fields[indexStop].trim();
        String longLat = fields[indexLonglat].trim();
        double latitude = Double.parseDouble(longLat.split(",")[0]);
        double longitude = Double.parseDouble(longLat.split(",")[1]);

        NodeKey nodeKey = new NodeKey(stopName, key);

        stations.putIfAbsent(nodeKey, new NodeDTO(stopName, latitude, longitude, key));

        return stations.get(nodeKey);
    }

    /**
     * Process a segment from the fields of a line.
     * 
     * @param start the start node
     * @param end the end node
     * @param duration the duration of the segment
     * @param distance the distance of the segment
     * @param linekey the key of the line
     */
    private void processSegment(final NodeDTO start, final NodeDTO end, final double duration, final double distance, final LineKey linekey) {
        SegmentTransportDTO segment = new SegmentTransportDTO(start, end, duration, distance, linekey);
        segments.add(segment);
    }

    /**
     * Getter for the stations.
     * @return the stations
     */
    public static Map<NodeKey, NodeDTO> getStations() {
        return stations;
    }

    /**
     * Getter for the segments.
     * @return the segments
     */
    public static Set<SegmentTransportDTO> getSegments() {
        return segments;
    }

    /**
     * Getter for the lines.
     * @return the lines
     */
    public static Map<LineKey, NodeKey> getLines() {
        return lines;
    }

    /**
     * Parse a line from the CSV file.
     * 
     * @param line the line to parse
     * @param delimiter the delimiter used to separate the fields
     * @return the fields of the line
     */
    private String[] parseCSVLine(String line, String delimiter) {
        // set the delimiter and the regex to remove quotes
        String regex = "\"([^\"]*)\"";
        // split the line and remove quotes
        String[] fields = line.split(delimiter);
        for (int i = 0; i < fields.length; i++) {
            fields[i] = fields[i].replaceAll(regex, "$1");
        }
        return fields;
    }

    //
    public List<NodeDTO> searchNode(String name) {
        return stations.values().stream().filter(node -> node.getName().contains(name)).toList();
    }
    //
}
