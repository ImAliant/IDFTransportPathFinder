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

import fr.u_paris.gla.crazytrip.dao.StationDAO;
import fr.u_paris.gla.crazytrip.model.Line;

public class AstarPathFinder extends PathFinder {
    public AstarPathFinder(Node start, Node end) {
        super(start, end);
    }

    private Itinerary astar() {
        Map<Node, Boolean> visited = new HashMap<>();
        PriorityQueue<AstarInfo> queue = new PriorityQueue<>(Comparator.comparing(AstarInfo::getLineChanges)
                .thenComparingDouble(AstarInfo::getWeight));

        initialize(visited, queue);

        return run(visited, queue);
    }

    @Override
    public ItineraryResult findPath() {
        Itinerary itinerary = astar();

        LinkedList<Path> paths = new LinkedList<>();
        double duration = itinerary.get(end).getWeight();
        Node current = end;

        while (!current.equals(start) && itinerary.contains(current)) {
            Node next = itinerary.get(current).getNode();

            Segment segment = network.getSegment(next, current);
            if (segment == null) {
                paths.addFirst(new Path(next, current, itinerary.get(current).getWeight()));
            } else {
                SegmentTransport st = (SegmentTransport) segment;
                paths.addFirst(new Path(next, current, itinerary.get(current).getWeight(), st.getLineKey()));
            }
            duration += segment.getDuration();

            current = next;
        }

        return new ItineraryResult(paths, duration);
    }

    private Itinerary run(Map<Node, Boolean> visited, PriorityQueue<AstarInfo> queue) {
        Itinerary itinerary = new Itinerary();
        itinerary.add(start, new BestWeight(start, 0, null));

        boolean first = true;

        while (!queue.isEmpty()) {
            AstarInfo current = queue.poll();
            Node currentNode = current.getNode();

            if (Boolean.TRUE.equals(visited.get(currentNode))) {
                continue;
            }

            visited.replace(currentNode, true);

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

    private void createWalkSegmentsToCloseStation(Node currentNode, Set<Segment> neighbors) {
        Set<Station> closeStations = StationDAO.findCloseStations(currentNode);
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
        BestWeight neighborWeight = itinerary.get(neighborNode);
        if (neighborWeight == null || weight < neighborWeight.getWeight()) {
            itinerary.add(neighborNode, new BestWeight(currentNode, weight, neighborLine));
            queue.add(new AstarInfo(neighborNode, weight, lineChanges, neighborLine));
        }
    }

    private void initialize(Map<Node, Boolean> visited, PriorityQueue<AstarInfo> queue) {
        Set<Node> allNodes = network.getNodes();
        allNodes.forEach(node -> visited.put(node, false));

        queue.add(new AstarInfo(start, 0, 0, null));
    }
}
