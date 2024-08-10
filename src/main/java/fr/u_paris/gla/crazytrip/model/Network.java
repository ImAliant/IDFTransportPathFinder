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
import fr.u_paris.gla.crazytrip.model.key.LineKey;
import fr.u_paris.gla.crazytrip.model.key.NodeKey;
import fr.u_paris.gla.crazytrip.model.line.RouteType;
import fr.u_paris.gla.crazytrip.parser.Parser;

public class Network {
    private static Network instance = null;

    private final Map<Node, Set<Segment>> graph;
    private final Map<LineKey, Line> lines;
    private final Map<NodeKey, Station> stations;

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

    private void initializeFields() {
        try {
            Parser.getInstance().parse(IDFMNetworkExtractor.PATH_TO_DATA);
        } catch (IOException e) {
            System.out.println("IOError while parsing the data file" + e);
            return;
        }

        Map<NodeKey, NodeDTO> stationsDTO = Parser.getStations();
        Map<LineKey, NodeKey> linesTerminus = Parser.getLines();
        convertStationsDTOtoStations(stationsDTO);

        Set<SegmentTransportDTO> segmentsDTO = Parser.getSegments();
        Map<LineKey, Set<Station>> transportLines = new HashMap<>();

        addSegmentToLinesAndGraph(segmentsDTO, transportLines);
        addLines(transportLines, linesTerminus);
    }

    private void addSegmentToLinesAndGraph(Set<SegmentTransportDTO> segmentsTransportDTO,
            Map<LineKey, Set<Station>> transportLines) {
        segmentsTransportDTO.forEach(segment -> {
            NodeKey startKey = segment.getStart().generateKey();
            NodeKey endKey = segment.getEnd().generateKey();

            Station start = this.stations.get(startKey);
            Station end = this.stations.get(endKey);
            if (start == null || end == null)
                throw new NullPointerException("The start or end can't be null");

            LineKey key = segment.getLineKey();

            if (transportLines.containsKey(key)) {
                transportLines.get(key).add(start);
                transportLines.get(key).add(end);
            } else {
                Set<Station> set = new HashSet<>();
                set.add(start);
                set.add(end);
                transportLines.put(key, set);
            }
            this.addSegmentLine(start, end, segment.getDistance(), segment.getDuration(), key);
        });
    }

    private void addLines(Map<LineKey, Set<Station>> lines, Map<LineKey, NodeKey> linesTerminus) {
        lines.forEach((key, value) -> {
            Station terminus = this.stations.get(linesTerminus.get(key));
            if (terminus == null)
                throw new NullPointerException("The terminus can't be null");

            Line line = LineFactory.createLine(key.getName(), value, terminus, key.getRouteType(), key.getColor());

            this.lines.putIfAbsent(key, line);
        });
    }

    public void addSegmentLine(Node start, Node end, double distance, double duration, LineKey lineKey) {
        addSegment(new SegmentTransport(start, end, distance, duration, lineKey));
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

    private void convertStationsDTOtoStations(Map<NodeKey, NodeDTO> stationsDTO) {
        stationsDTO.forEach((key, stationDTO) -> {
            Station station = this.stationDTOtoStation(stationDTO);
            this.stations.put(key, station);
        });
    }

    private Station stationDTOtoStation(NodeDTO stationDTO) {
        return new Station(stationDTO.getName(), stationDTO.getLatitude(), stationDTO.getLongitude(),
                stationDTO.getLineKey());
    }

    public Set<Station> findCloseStations(Node node) {
        final double distance = 0.5;
        return this.stations.values()
            .stream()
            .filter(station -> station.distanceTo(node) <= distance
                && !station.equals(node))
            .collect(Collectors.toSet());
    }

    public Station getNearestStation(Coordinates coordinates) {
        return this.stations.values().stream().min((station1, station2) -> {
            double distance1 = station1.getCoordinates().distanceTo(coordinates);
            double distance2 = station2.getCoordinates().distanceTo(coordinates);
            return Double.compare(distance1, distance2);
        }).orElse(null);
    }

    public Line getLine(String name, RouteType routeType, String color) {
        return lines.get(new LineKey(name, routeType, color));
    }

    public Set<Segment> getSegments(Node node) {
        return graph.getOrDefault(node, Collections.emptySet());
    }

    public Set<Segment> getSegmentsLineOfANode(Node node) {
        return graph.get(node).stream().filter(SegmentTransport.class::isInstance).collect(Collectors.toSet());
    }

    public Segment getSegment(Node start, Node end) {
        return graph.get(start).stream().filter(segment -> segment.getEndPoint().equals(end)).findFirst().orElse(null);
    }

    public List<Line> getAllLinesSpecificType(RouteType routeType) {
        ArrayList<Line> res = new ArrayList<>();
        lines.forEach((key, value) -> {
            if (value.getLineType().equals(routeType))
                res.add(value);
        });
        return res;
    }

    public List<Station> getStationsByName(String name) {
        return stations.values().stream().filter(station -> station.getName().contains(name))
                .collect(Collectors.toList());
    }

    public List<Line> getLinesFromStation(Station station) {
        return lines.values().stream().filter(line -> line.getStations().contains(station))
                .collect(Collectors.toList());
    }

    public Map<NodeKey, Station> getStations() {
        return Collections.unmodifiableMap(stations);
    }

    public Map<LineKey, Line> getLines() {
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

    public Set<Node> getNodes() {
        return graph.keySet();
    }

    public Line getLineFromSegment(Segment segment) {
        if (!(segment instanceof SegmentTransport))
            return null;

        LineKey key = ((SegmentTransport) segment).getLineKey();
        return lines.get(key);
    }
}
