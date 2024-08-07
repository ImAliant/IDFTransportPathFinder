package fr.u_paris.gla.crazytrip.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;

import fr.u_paris.gla.crazytrip.dtos.NodeDTO;
import fr.u_paris.gla.crazytrip.dtos.SegmentTransportDTO;
import fr.u_paris.gla.crazytrip.idfm.IDFMNetworkExtractor;
import fr.u_paris.gla.crazytrip.model.factory.LineFactory;
import fr.u_paris.gla.crazytrip.model.line.RouteType;
import fr.u_paris.gla.crazytrip.parser.Parser;

public class Network {
    private static Network instance = null;
    
    private final Map<Node, Set<Segment>> graph;
    private final Map<String, Line> lines;
    private final Map<String, Station> stations;

    private Network() {
        this.graph = new HashMap<>();
        this.lines = new HashMap<>();
        this.stations = new HashMap<>();

        initializeFields();
    }

    public static Network getInstance() {
        if (instance == null) {
            instance = new Network();
        }
        return instance;
    }

    public Line getLine(String name, RouteType routetype) {
        String key = generateLineKey(name, routetype);
        return lines.get(key);
    }

    public SegmentTransport getSegmentLine(Node start, Node end) {
        Set<Segment> segments = this.graph.get(start);
        if (segments == null) {
            return null;
        }
        for (Segment segment : segments) {
            if (segment instanceof SegmentTransport && segment.getEndPoint().equals(end)) {
                return (SegmentTransport) segment;
            }
        }
        return null;
    }

    public Set<Segment> getSegmentsLineOfANode(Node node) {
        return graph.get(node).stream().filter(SegmentTransport.class::isInstance).collect(Collectors.toSet());
    }

    public List<Line> getAllLinesSpecificType(RouteType routeType) {
        ArrayList<Line> res = new ArrayList<>();
        lines.forEach((key, value) -> {
            if (value.getLineType().equals(routeType)) res.add(value);
        });
        return res;
    }

    private void initializeFields() {
        try {
            Parser.getInstance().parse(IDFMNetworkExtractor.PATH_TO_DATA);
        } catch (IOException e) {
            System.out.println("IOError while parsing the data file" + e);
            return;
        }

        Set<NodeDTO> stationsDTO = Parser.getStations();
        Map<String, String> linesTerminus = Parser.getLines();
        convertStationsDTOtoStations(stationsDTO);

        Set<SegmentTransportDTO> segmentsDTO = Parser.getSegments();
        Map<String, Set<Station>> transportLines = new HashMap<>();

        addSegmentToLinesAndGraph(segmentsDTO, transportLines);
        addLines(transportLines, linesTerminus);
    }

    private void addSegmentToLinesAndGraph(Set<SegmentTransportDTO> segmentsTransportDTO, Map<String, Set<Station>> transportLines) {
        segmentsTransportDTO.forEach(segment -> {
            String startKey = segment.getStart().generateKey();
            String endKey = segment.getEnd().generateKey();

            Station start = this.stations.get(startKey);
            Station end = this.stations.get(endKey);
            if (start == null || end == null) throw new NullPointerException("The start or end can't be null");

            String key = Parser.generateLineKey(segment.getLine(), segment.getRouteType(), segment.getColor());
            
            if (transportLines.containsKey(key)) {
                transportLines.get(key).add(start);
                transportLines.get(key).add(end);
            } else {
                Set<Station> set = new HashSet<>();
                set.add(start);
                set.add(end);
                transportLines.put(key, set);
            }
            this.addSegmentLine(start, end, segment.getDistance(), segment.getDuration(), segment.getLine());
        });
    }

    private void addLines(Map<String, Set<Station>> lines, Map<String, String> linesTerminus) {
        lines.forEach((key, value) -> {
            Station terminus = this.stations.get(linesTerminus.get(key));
            if (terminus == null) throw new NullPointerException("The terminus can't be null");
            
            String[] parts = key.split("@");
            String lineName = parts[0];
            String routetype = parts[1];
            String color = parts[2];

            Line line = LineFactory.createLine(lineName, value, terminus, RouteType.fromString(routetype), color);

            String newKey = generateLineKey(line.getName(), line.getLineType());
            this.lines.putIfAbsent(newKey, line);
        });
    }

    /* private void addAllWalkSegments(Set<Station> stations) {
        stations.forEach(station -> {
            Station start = this.stations.get(station.getName());
            stations.forEach(station2 -> {
                Station end = this.stations.get(station2.getName());
                if (start != end) {
                    this.addSegmentWalk(start, end, start.distanceTo(end));
                } 
            });
        });
    } */

    public Map<String, Station> getStations() {
        return Collections.unmodifiableMap(stations);
    }

    public Map<String, Line> getLines() {
        return Collections.unmodifiableMap(lines);
    }

    public Map<Node, Set<Segment>> getGraph() {
        return Collections.unmodifiableMap(graph);
    }
 
    public Set<Station> getAllStations() {
        return getStations().values().stream().collect(HashSet::new, HashSet::add, HashSet::addAll);
    }

    public Set<Line> getAllLines() {
        return getLines().values().stream().collect(HashSet::new, HashSet::add, HashSet::addAll);
    }
 
    public void addSegmentLine(Node start, Node end, double distance, double duration, String line) {
        addSegment(new SegmentTransport(start, end, distance, duration, line));
    }

    public void addSegmentWalk(Node start, Node end, double distance) {
        addSegment(new SegmentWalk(start, end, distance));
    }

    private void addSegment(Segment segment) {
        if (graph.containsKey(segment.getStartPoint())) {
            graph.get(segment.getStartPoint()).add(segment);
        } else {
            Set<Segment> set = new HashSet<>();
            set.add(segment);
            graph.put(segment.getStartPoint(), set);
        }
    }

    private void convertStationsDTOtoStations(Set<NodeDTO> stationsDTO) {
        stationsDTO.forEach(stationDTO -> {
            Station station = this.stationDTOtoStation(stationDTO);
            String key = stationDTO.generateKey();
            this.stations.put(key, station);
        });
    }

    private Station stationDTOtoStation(NodeDTO stationDTO) {
        return new Station(stationDTO.getName(), stationDTO.getLatitude(), stationDTO.getLongitude(), stationDTO.getRouteType());
    }

    public String generateLineKey(String name, RouteType routetype) {
        return String.format("%s-%s", name, routetype);
    }
}
