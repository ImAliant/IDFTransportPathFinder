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

    private static final Map<NodeKey, NodeDTO> stations = new HashMap<>();
    private static final Set<SegmentTransportDTO> segments = new HashSet<>();
    private static final Map<LineKey, NodeKey> lines = new HashMap<>();
    
    private static Parser instance = null;

    private Parser() {}

    public static Parser getInstance() {
        if (instance == null) {
            instance = new Parser();
        }
        return instance;
    }

    public void parse(String dataFile) throws IOException {
        processNetwork(dataFile);
    }

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

    private NodeDTO processNode(String[] fields, final LineKey key, final int indexStop, final int indexLonglat) {
        String stopName = fields[indexStop].trim();
        String longLat = fields[indexLonglat].trim();
        double latitude = Double.parseDouble(longLat.split(",")[0]);
        double longitude = Double.parseDouble(longLat.split(",")[1]);

        NodeKey nodeKey = new NodeKey(stopName, key);

        stations.putIfAbsent(nodeKey, new NodeDTO(stopName, latitude, longitude, key));

        return stations.get(nodeKey);
    }

    private void processSegment(final NodeDTO start, final NodeDTO end, final double duration, final double distance, final LineKey linekey) {
        SegmentTransportDTO segment = new SegmentTransportDTO(start, end, duration, distance, linekey);
        segments.add(segment);
    }

    public static Map<NodeKey, NodeDTO> getStations() {
        return stations;
    }

    public static Set<SegmentTransportDTO> getSegments() {
        return segments;
    }

    public static Map<LineKey, NodeKey> getLines() {
        return lines;
    }

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
