package fr.u_paris.gla.crazytrip.model;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import fr.u_paris.gla.crazytrip.dtos.NodeDTO;
import fr.u_paris.gla.crazytrip.dtos.SegmentTransportDTO;
import fr.u_paris.gla.crazytrip.idfm.IDFMNetworkExtractor;
import fr.u_paris.gla.crazytrip.model.factory.LineFactory;
import fr.u_paris.gla.crazytrip.model.key.LineKey;
import fr.u_paris.gla.crazytrip.model.key.NodeKey;
import fr.u_paris.gla.crazytrip.parser.Parser;

/**
 * <p>The network is the representation of the transport network.</p>
 * <p>It's a graph where the nodes are the stations and the segments are the transport segments.</p>
 * <p>We use the singleton pattern to ensure that there is only one instance of the network.</p>
 * 
 * @see Node
 * @see Segment
 * @see Line
 * @see Station
 * @see SegmentTransport
 * @see SegmentWalk
 * @see LineFactory
 * @see Parser
 * @see IDFMNetworkExtractor
 * @see LineKey
 * @see NodeKey
 */
public class Network {
    /** The unique instance of the network */
    private static Network instance = null;

    /** The graph of the network composed of nodes and segments linked to the nodes */
    private final Map<Node, Set<Segment>> graph;
    /** The lines of the network */
    private final Map<LineKey, Line> lines;
    /** The stations of the network */
    private final Map<NodeKey, Station> stations;

    /**
     * Creates a new network.
     * Initializes the graph, the lines and the stations.
     */
    private Network() {
        this.graph = new HashMap<>();
        this.lines = new HashMap<>();
        this.stations = new HashMap<>();

        initializeFields();
    }

    /**
     * Returns the unique instance of the network.
     * @return the unique instance of the network
     */
    public static Network getInstance() {
        if (instance == null) {
            instance = new Network();
        }
        return instance;
    }

    /**
     * Initializes the fields of the network.
     */
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

    /**
     * Adds the segments to the lines and the graph.
     * 
     * For each segment, we search the start and end stations in the stations map.
     * If the stations are not in the map, we throw a NullPointerException.
     * 
     * Then we search the line key in the transport lines map.
     * If the line key is in the map, we add the start and end stations to the set of stations of the line.
     * If the line key is not in the map, we create a new set of stations with the start and end stations and we add it to the map.
     * 
     * Finally, we add the segment to the line and the graph.
     * 
     * @param segmentsTransportDTO
     * @param transportLines
     */
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

    /**
     * Adds the lines to the network.
     * 
     * For each line, we search the terminus in the stations map.
     * If the terminus is not in the map, we throw a NullPointerException.
     * 
     * Finally, we create a new line with the name, the stations and the terminus and we add it to the lines map.
     * 
     * @param lines
     * @param linesTerminus
     */
    private void addLines(Map<LineKey, Set<Station>> lines, Map<LineKey, NodeKey> linesTerminus) {
        lines.forEach((key, value) -> {
            Station terminus = this.stations.get(linesTerminus.get(key));
            if (terminus == null)
                throw new NullPointerException("The terminus can't be null");

            Line line = LineFactory.createLine(key.getName(), value, terminus, key.getRouteType(), key.getColor());

            this.lines.putIfAbsent(key, line);
        });
    }

    /**
     * Adds a transport segment to the graph.
     * 
     * @param start the start station of the segment
     * @param end the end station of the segment
     * @param distance the distance of the segment
     * @param duration the duration of the segment
     * @param lineKey the key of the line
     */
    public void addSegmentLine(Node start, Node end, double distance, double duration, LineKey lineKey) {
        addSegment(new SegmentTransport(start, end, distance, duration, lineKey));
    }

    /**
     * Adds a walk segment to the graph.
     * 
     * @param start the start station of the segment
     * @param end the end station of the segment
     * @param distance the distance of the segment
     */
    public void addSegmentWalk(Node start, Node end, double distance) {
        addSegment(new SegmentWalk(start, end, distance));
    }

    /**
     * Adds a segment to the graph.
     * 
     * @param segment the segment to add
     */
    private void addSegment(Segment segment) {
        if (graph.containsKey(segment.getStartPoint())) {
            graph.get(segment.getStartPoint()).add(segment);
        } else {
            Set<Segment> set = new HashSet<>();
            set.add(segment);
            graph.put(segment.getStartPoint(), set);
        }
    }

    /**
     * Converts the stations DTO to stations.
     * 
     * For each station DTO, we create a new station and we add it to the stations map.
     * 
     * @param stationsDTO the stations DTO to convert
     */
    private void convertStationsDTOtoStations(Map<NodeKey, NodeDTO> stationsDTO) {
        stationsDTO.forEach((key, stationDTO) -> {
            Station station = this.stationDTOtoStation(stationDTO);
            this.stations.put(key, station);
        });
    }

    /**
     * Converts a station DTO to a station.
     * 
     * @param stationDTO the station DTO to convert
     * @return the station
     */
    private Station stationDTOtoStation(NodeDTO stationDTO) {
        return new Station(stationDTO.getName(), stationDTO.getLatitude(), stationDTO.getLongitude(),
                stationDTO.getLineKey());
    }

    /**
     * Returns the segments of a node or an empty set if the node is not in the graph.
     * 
     * @param node the node
     * @return the segments of the node
     */
    public Set<Segment> getSegments(Node node) {
        return graph.getOrDefault(node, Collections.emptySet());
    }

    /**
     * Returns the transport segments of a node.
     * 
     * @param node the node
     * @return the transport segments of the node
     */
    public Set<Segment> getSegmentsTransportOfANode(Node node) {
        return graph.get(node).stream().filter(SegmentTransport.class::isInstance).collect(Collectors.toSet());
    }

    /**
     * Returns the segment between two nodes
     * @param start the start node
     * @param end the end node
     * @return the segment between the two nodes or null if there is no segment between the two nodes
     */
    public Segment getSegment(Node start, Node end) {
        Set<Segment> segments = graph.get(start);
        if (segments == null) return null;
        
        return segments.stream().filter(segment -> segment.getEndPoint().equals(end)).findFirst().orElse(null);
    }

    /**
     * Returns an unmutable map of the stations.
     * @return an unmutable map of the stations
     */ 
    public Map<NodeKey, Station> getStations() {
        return Collections.unmodifiableMap(stations);
    }

    /**
     * Returns an unmutable map of the lines.
     * @return an unmutable map of the lines
     */
    public Map<LineKey, Line> getLines() {
        return Collections.unmodifiableMap(lines);
    }

    /**
     * Returns an unmutable map of the graph.
     * @return an unmutable map of the graph
     */
    public Map<Node, Set<Segment>> getGraph() {
        return Collections.unmodifiableMap(graph);
    }

    /**
     * Returns all the lines of the network.
     * @return all the lines of the network
     */
    public Set<Line> getAllLines() {
        return getLines().values().stream().collect(HashSet::new, HashSet::add, HashSet::addAll);
    }

    /**
     * Returns all the nodes of the network.
     * @return all the nodes of the network
     */
    public Set<Node> getNodes() {
        return graph.keySet();
    }

    /**
     * Returns the line of a segment.
     * 
     * @param segment the segment
     * @return the line of the segment or null if the segment is not a transport segment
     */
    public Line getLineFromSegment(Segment segment) {
        if (!(segment instanceof SegmentTransport))
            return null;

        LineKey key = ((SegmentTransport) segment).getLineKey();
        return lines.get(key);
    }
}
