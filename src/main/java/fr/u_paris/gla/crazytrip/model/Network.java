package fr.u_paris.gla.crazytrip.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.u_paris.gla.crazytrip.dtos.NodeDTO;
import fr.u_paris.gla.crazytrip.dtos.SegmentLineDTO;
import fr.u_paris.gla.crazytrip.idfm.IDFMNetworkExtractor;
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

    public Line getLineByName(String name) {
        return this.lines.get(name);
    }

    public Station getStationByName(String name) {
        return this.stations.get(name);
    }

    public SegmentLine getSegmentLine(Node start, Node end) {
        Set<Segment> segments = this.graph.get(start);
        if (segments == null) {
            return null;
        }
        for (Segment segment : segments) {
            if (segment instanceof SegmentLine && segment.getEndPoint().equals(end)) {
                return (SegmentLine) segment;
            }
        }
        return null;
    }

    //
    public SegmentWalk getSegmentWalk(Node start, Node end) {
        Set<Segment> segments = this.graph.get(start);
        if (segments == null) {
            return null;
        }
        for (Segment segment : segments) {
            if (segment instanceof SegmentWalk && segment.getEndPoint().equals(end)) {
                return (SegmentWalk) segment;
            }
        }
        return null;
    }
    //

    public Set<Segment> getSegments(Node node) {
        return this.graph.get(node);
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

        Set<SegmentLineDTO> segmentsDTO = Parser.getSegments();
        Map<String, Set<Station>> transportLines = new HashMap<>();

        addSegmentToLinesAndGraph(segmentsDTO, transportLines);
        addLines(transportLines, linesTerminus);
        addAllWalkSegments(getAllStations());
    }

    private void addSegmentToLinesAndGraph(Set<SegmentLineDTO> segmentsLineDTO, Map<String, Set<Station>> transportLines) {
        segmentsLineDTO.forEach(segment -> {
            Station start = this.stations.get(segment.getStart().getName());
            Station end = this.stations.get(segment.getEnd().getName());
            
            if (transportLines.containsKey(segment.getLine())) {
                transportLines.get(segment.getLine()).add(start);
                transportLines.get(segment.getLine()).add(end);
            } else {
                Set<Station> set = new HashSet<>();
                set.add(start);
                set.add(end);
                transportLines.put(segment.getLine(), set);
            }
            this.addSegmentLine(start, end, segment.getDistance(), segment.getDuration(), segment.getLine());
        });
    }

    private void addLines(Map<String, Set<Station>> lines, Map<String, String> linesTerminus) {
        lines.forEach((key, value) -> {
            Station terminus = this.stations.get(linesTerminus.get(key));
            Line line = new Line(key, value, terminus);
            this.lines.put(key, line);
        });
    }

    private void addAllWalkSegments(Set<Station> stations) {
        stations.forEach(station -> {
            Station start = this.stations.get(station.getName());
            stations.forEach(station2 -> {
                Station end = this.stations.get(station2.getName());
                if (start != end) {
                    this.addSegmentWalk(start, end, start.distanceTo(end));
                } 
            });
        });
    }

    public Map<String, Station> getStations() {
        return stations;
    }

    public Set<Station> getAllStations() {
        return getStations().values().stream().collect(HashSet::new, HashSet::add, HashSet::addAll);
    }
 
    public void addSegmentLine(Node start, Node end, double distance, double duration, String line) {
        addSegment(new SegmentLine(start, end, distance, duration, line));
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
            this.stations.put(station.getName(), station);
        });
    }

    private Station stationDTOtoStation(NodeDTO stationDTO) {
        return new Station(stationDTO.getName(), stationDTO.getLatitude(), stationDTO.getLongitude());
    }
}
