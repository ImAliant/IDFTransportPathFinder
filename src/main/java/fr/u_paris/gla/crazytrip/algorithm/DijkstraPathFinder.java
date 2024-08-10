package fr.u_paris.gla.crazytrip.algorithm;

import fr.u_paris.gla.crazytrip.model.Node;
import fr.u_paris.gla.crazytrip.model.Segment;
import fr.u_paris.gla.crazytrip.model.SegmentTransport;
import fr.u_paris.gla.crazytrip.model.SegmentWalk;
import fr.u_paris.gla.crazytrip.model.Station;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.model.Network;

public class DijkstraPathFinder {
    private static final Network network = Network.getInstance();

    private DijkstraPathFinder() {
    }

    public static Itinerary dijkstra(Node start, Node end) {
        if (start == null)
            throw new IllegalArgumentException("Start node is null");
        if (end == null)
            throw new IllegalArgumentException("End node is null");

        Map<Node, Boolean> visited = new HashMap<>();
        PriorityQueue<DijkstraInfo> queue = new PriorityQueue<>(Comparator.comparing(DijkstraInfo::getLineChanges)
                .thenComparingDouble(DijkstraInfo::getWeight));

        initialize(start, visited, queue);

        return runDijkstra(start, end, visited, queue);
    }

    public static List<DijkstraPath> getPath(Node start, Node end) {
        Itinerary itinerary = dijkstra(start, end);

        LinkedList<DijkstraPath> paths = new LinkedList<>();
        Node current = end;

        while (!current.equals(start) && itinerary.contains(current)) {
            Node next = itinerary.get(current).getNode();

            Segment segment = network.getSegment(next, current);
            if (segment == null) {
                paths.addFirst(new DijkstraPath(next, current, itinerary.get(current).getWeight()));
            } else {
                SegmentTransport st = (SegmentTransport) segment;
                paths.addFirst(new DijkstraPath(next, current, itinerary.get(current).getWeight(), st.getLineKey()));
            }

            current = next;
        }

        return paths;
    }

    private static Itinerary runDijkstra(Node start, Node end, Map<Node, Boolean> visited,
            PriorityQueue<DijkstraInfo> queue) {
        Itinerary itinerary = new Itinerary();
        itinerary.add(start, new BestWeight(start, 0, null));

        boolean first = true;

        while (!queue.isEmpty()) {
            DijkstraInfo current = queue.poll();
            Node currentNode = current.getNode();

            if (Boolean.TRUE.equals(visited.get(currentNode))) {
                continue;
            }

            visited.replace(currentNode, true);

            if (currentNode.equals(end))
                return itinerary;

            Set<Segment> neighbors = new HashSet<>(network.getSegments(currentNode));

            if (!first) createWalkSegmentsToCloseStation(currentNode, neighbors);

            processNeighbors(queue, itinerary, current, currentNode, neighbors);

            if (first) first = false;
        }

        return null;
    }

    private static void processNeighbors(PriorityQueue<DijkstraInfo> queue, Itinerary itinerary, DijkstraInfo current,
            Node currentNode, Set<Segment> neighbors) {
        for (Segment segment : neighbors) {
            Node neighbor = segment.getEndPoint();
            double weight = current.getWeight() + segment.getDuration();
            int changes;
            Line neighborLine = null;

            if (segment instanceof SegmentWalk) {
                changes = current.getLineChanges() + 1;
            } else {
                neighborLine = network.getLineFromSegment(segment);
                changes = current.getLineChanges() + (neighborLine.equals(current.getLine()) ? 0 : 1);
            }

            addInfoInQueue(itinerary, currentNode, neighbor, neighborLine, weight, changes, queue);
        }
    }

    private static void createWalkSegmentsToCloseStation(Node currentNode, Set<Segment> neighbors) {
        Set<Station> closeStations = network.findCloseStations(currentNode);
        createWalkSegments(currentNode, closeStations, neighbors);
    }

    private static void createWalkSegments(Node currentNode, Set<Station> closeStations, Set<Segment> neighbors) {
        for (Station station : closeStations) {
            SegmentWalk walkSegment = new SegmentWalk(currentNode, station);
            neighbors.add(walkSegment);
        }
    }

    private static void addInfoInQueue(Itinerary itinerary, Node currentNode, Node neighborNode, Line neighborLine,
            double weight, int lineChanges, PriorityQueue<DijkstraInfo> queue) {
        BestWeight neighborWeight = itinerary.get(neighborNode);
        if (neighborWeight == null || weight < neighborWeight.getWeight()) {
            itinerary.add(neighborNode, new BestWeight(currentNode, weight, neighborLine));
            queue.add(new DijkstraInfo(neighborNode, weight, lineChanges, neighborLine));
        }
    }

    private static void initialize(Node start, Map<Node, Boolean> visited, PriorityQueue<DijkstraInfo> queue) {
        Set<Node> allNodes = network.getNodes();
        allNodes.forEach(node -> visited.put(node, false));

        queue.add(new DijkstraInfo(start, 0, 0, null));
    }
}
