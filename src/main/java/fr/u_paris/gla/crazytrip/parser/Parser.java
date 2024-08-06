package fr.u_paris.gla.crazytrip.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.u_paris.gla.crazytrip.dtos.NodeDTO;
import fr.u_paris.gla.crazytrip.dtos.SegmentTransportDTO;
import fr.u_paris.gla.crazytrip.io.TimeFormat;

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

    private static final Set<NodeDTO> stations = new HashSet<>();
    private static final Set<SegmentTransportDTO> segments = new HashSet<>();
    private static final Map<String, String> lines = new HashMap<>();
    
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

        int compteur = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String csvDelimiter = ";";
            while ((line = reader.readLine()) != null) {
                String[] fields = parseCSVLine(line, csvDelimiter);

                processFields(fields);

                System.out.println(compteur++);
            }
        }
    }

    private void processFields(String[] fields) {
        NodeDTO start;
        NodeDTO end;

        String lineName = fields[LNAME_INDEX].trim();
        String routetype = fields[ROUTETYPE_INDEX].trim();
        double duration = TimeFormat.convertToSeconds(fields[DURATION_INDEX]);
        double distance = Double.parseDouble(fields[DISTANCE_INDEX]);
        String color = fields[COLOR_INDEX].trim();

        start = processNode(fields, routetype, STOP_NAME_INDEX, LONGLAT_INDEX);
        end = processNode(fields, routetype, NEXT_STOP_NAME_INDEX, NEXT_LONGLAT_INDEX);

        processSegment(start, end, duration, distance, lineName, routetype, color);

        String lineKey = generateLineKey(lineName, routetype, color);
        lines.putIfAbsent(lineKey, start.getName());
    }

    private NodeDTO processNode(String[] fields, final String routetype, final int indexStop, final int indexLonglat) {
        String stopName = fields[indexStop].trim();
        String longLat = fields[indexLonglat].trim();
        double latitude = Double.parseDouble(longLat.split(",")[0]);
        double longitude = Double.parseDouble(longLat.split(",")[1]);

        NodeDTO node = findNodeInSet(stopName, latitude, longitude, routetype);
        if (node != null) return node;

        node = new NodeDTO(stopName, latitude, longitude, routetype);
        stations.add(node);

        return node;
    }

    private NodeDTO findNodeInSet(final String name, final double latitude, final double longitude, final String routetype) {
        for (NodeDTO node: stations) {
            if (node.getName().equals(name) && node.getLatitude() == latitude 
            && node.getLongitude() == longitude && node.getRouteType().equals(routetype)) {
                return node;
            }
        }
        return null;
    }

    private void processSegment(final NodeDTO start, final NodeDTO end, final double duration, 
    final double distance, final String lineName, final String routetype, final String color) {
        SegmentTransportDTO segment = new SegmentTransportDTO(start, end, duration, distance, lineName, routetype, color);
        segments.add(segment);
    }

    public static String generateLineKey(final String name, final String routetype, final String color) {
        return String.format("%s@%s@%s", name, routetype, color);
    }

    public static Set<NodeDTO> getStations() {
        return stations;
    }

    public static Set<SegmentTransportDTO> getSegments() {
        return segments;
    }

    public static Map<String, String> getLines() {
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
}