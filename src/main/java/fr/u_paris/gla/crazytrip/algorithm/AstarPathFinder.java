package fr.u_paris.gla.crazytrip.algorithm;

import fr.u_paris.gla.crazytrip.model.Node;
import fr.u_paris.gla.crazytrip.model.Segment;
import fr.u_paris.gla.crazytrip.model.SegmentTransport;
import fr.u_paris.gla.crazytrip.model.SegmentWalk;
import fr.u_paris.gla.crazytrip.model.Station;
import fr.u_paris.gla.crazytrip.model.line.RouteType;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import fr.u_paris.gla.crazytrip.dao.StationDAO;
import fr.u_paris.gla.crazytrip.model.Line;

public class AstarPathFinder extends PathFinder {
    private static final Map<RouteType, Double> TRANSPORT_PREFERENCES = Map.of(
            RouteType.RAIL, 1.0,
            RouteType.METRO, 1.2,
            RouteType.TRAMWAY, 1.4,
            RouteType.BUS, 1.6);

    public AstarPathFinder(Node start, Node end) {
        super(start, end);
    }

    private Itinerary astar() {
        Set<Node> visited = new HashSet<>();
        PriorityQueue<AstarInfo> queue = new PriorityQueue<>(Comparator.comparing(AstarInfo::getTransfers)
                .thenComparingDouble(AstarInfo::getWeight));
        initialize(queue);

        return run(visited, queue);
    }

    @Override
    public ItineraryResult findPath() {
        Itinerary itinerary = astar();
        if (itinerary == null)
            return null;

        LinkedList<Path> paths = new LinkedList<>();
        Node current = end;

        while (!current.equals(start) && itinerary.contains(current)) {
            Node next = itinerary.get(current).getNode();

            Segment segment = network.getSegment(next, current);
            Path path;
            if (segment == null) {
                path = new Path(next, current, itinerary.get(current).getWeight());
            } else {
                SegmentTransport st = (SegmentTransport) segment;
                path = new Path(next, current, itinerary.get(current).getWeight(), st.getLineKey());
            }
            paths.addFirst(path);

            current = next;
        }

        return new ItineraryResult(paths);
    }

    private Itinerary run(Set<Node> visited, PriorityQueue<AstarInfo> queue) {
        Itinerary itinerary = new Itinerary();
        itinerary.add(start, new BestWeight(start, 0, null));

        boolean first = true;

        while (!queue.isEmpty()) {
            AstarInfo current = queue.poll();
            Node currentNode = current.getNode();

            if (visited.contains(currentNode))
                continue;

            visited.add(currentNode);

            if (currentNode.equals(end))
                return itinerary;

            Set<Segment> neighbors = new HashSet<>(network.getSegments(currentNode));

            if (!first)
                createWalkSegmentsToCloseStation(currentNode, neighbors);

            processNeighbors(queue, itinerary, current, currentNode, neighbors);

            if (first)
                first = false;
        }

        return null;
    }

    private void processNeighbors(PriorityQueue<AstarInfo> queue, Itinerary itinerary, AstarInfo current,
            Node currentNode, Set<Segment> neighbors) {

        for (Segment segment : neighbors) {
            Node neighbor = segment.getEndPoint();
            double baseWeight = current.getWeight() + segment.getDuration();
            double adjustedWeight = baseWeight;

            int transfers;
            Line neighborLine = null;
            RouteType type = null;

            if (segment instanceof SegmentWalk) {
                transfers = current.getTransfers() + 1;
            } else {
                neighborLine = network.getLineFromSegment(segment);
                type = neighborLine.getLineType();
                double preference = TRANSPORT_PREFERENCES.getOrDefault(type, 1.0);

                adjustedWeight = baseWeight * preference;

                transfers = current.getTransfers() + (neighborLine.equals(current.getLine()) ? 0 : 1);
            }

            addInfoInQueue(itinerary, currentNode, neighbor, neighborLine, adjustedWeight, transfers, queue);
        }

    }

    private void createWalkSegmentsToCloseStation(Node currentNode, Set<Segment> neighbors) {
        Set<Station> closeStations = StationDAO.findCloseStations(currentNode, 0.2);
        createWalkSegments(currentNode, closeStations, neighbors);
    }

    private void createWalkSegments(Node currentNode, Set<Station> closeStations, Set<Segment> neighbors) {
        for (Station station : closeStations) {
            SegmentWalk walkSegment = new SegmentWalk(currentNode, station);
            neighbors.add(walkSegment);
        }
    }

    private void addInfoInQueue(Itinerary itinerary, Node currentNode, Node neighborNode, Line neighborLine,
            double weight, int lineChanges, PriorityQueue<AstarInfo> queue) {
        double heuristic = heuristic(neighborNode);
        double estimatedCost = weight + heuristic;
        BestWeight neighborWeight = itinerary.get(neighborNode);
        if (neighborWeight == null || estimatedCost < neighborWeight.getWeight() + heuristic) {
            itinerary.add(neighborNode, new BestWeight(currentNode, weight, neighborLine));
            queue.add(new AstarInfo(neighborNode, estimatedCost, lineChanges, neighborLine));
        }
    }

    private void initialize(PriorityQueue<AstarInfo> queue) {
        queue.add(new AstarInfo(start, 0, 0, null));
    }

    private double heuristic(Node node) {
        return node.distanceTo(end);
    }
}
