package fr.u_paris.gla.crazytrip.algorithm;

import fr.u_paris.gla.crazytrip.model.Node;
import fr.u_paris.gla.crazytrip.model.PersonalizedNode;
import fr.u_paris.gla.crazytrip.model.Segment;
import fr.u_paris.gla.crazytrip.model.SegmentTransport;
import fr.u_paris.gla.crazytrip.model.SegmentWalk;
import fr.u_paris.gla.crazytrip.model.Station;
import fr.u_paris.gla.crazytrip.model.line.RouteType;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import fr.u_paris.gla.crazytrip.dao.StationDAO;
import fr.u_paris.gla.crazytrip.model.Line;

/**
 * Class used to find the best path between two nodes using the A* algorithm.
 */
public class AstarPathFinder extends PathFinder {
    /** The preferences for each type of transport. The lower the value, the more the transport is preferred. */
    private static final Map<RouteType, Double> TRANSPORT_PREFERENCES = Map.of(
            RouteType.RAIL, 1.0,
            RouteType.METRO, 1.2,
            RouteType.TRAMWAY, 1.4,
            RouteType.BUS, 1.6);
    /** The segments used to walk to the closest station. */
    private Map<Node, SegmentWalk> walkSegments = new HashMap<>();

    /**
     * Constructor.
     * @param start The start node.
     * @param end The end node.
     */
    public AstarPathFinder(Node start, Node end) {
        super(start, end);
    }

    /**
     * Find the best path between the start and end nodes using the A* algorithm.
     * @return An Itinerary object containing the best path between the start and end nodes.
     * 
     * @see Itinerary
     */
    private Itinerary astar() {
        // The set of visited nodes.
        Set<Node> visited = new HashSet<>();
        // The priority queue used to store the nodes to visit.
        PriorityQueue<AstarInfo> queue = new PriorityQueue<>(Comparator.comparing(AstarInfo::getTransfers)
                .thenComparingDouble(AstarInfo::getWeight));
        initialize(queue);

        return run(visited, queue);
    }

    /**
     * Find the best path between the start and end nodes.
     * @return An ItineraryResult object containing the best path between the start and end nodes.
     * 
     * @see ItineraryResult
     */
    @Override
    public ItineraryResult findPath() {
        // We execute the A* algorithm to find the best path.
        Itinerary itinerary = astar();
        // If the itinerary is null, it means that there is no path between the start and end nodes.
        if (itinerary == null)
            return null;

        // We create the list of paths that compose the itinerary.
        LinkedList<Path> paths = new LinkedList<>();
        Node current = end;

        // We add the walk segment to the closest station if it exists.
        if (walkSegments.containsKey(current)) {
            Segment segment = walkSegments.get(end);
            paths.addFirst(new Path(segment.getStartPoint(), end, segment.getDuration()));
        }

        // We add the segments of the itinerary to the list of paths.
        while (!current.equals(start) && itinerary.contains(current)) { 
            Node next = itinerary.get(current).getNode();

            Segment segment = network.getSegment(next, current);
            Path path;

            // If the segment is null, it means that the segment is a walk segment.
            if (segment == null) {
                path = new Path(next, current, itinerary.get(current).getWeight());
            } else {
                SegmentTransport st = (SegmentTransport) segment;
                path = new Path(next, current, itinerary.get(current).getWeight(), st.getLineKey());
            }
            paths.addFirst(path);

            current = next;
        }

        if (walkSegments.containsKey(current)) {
            Segment segment = walkSegments.get(start);
            paths.addFirst(new Path(start, segment.getEndPoint(), segment.getDuration()));
        }

        return new ItineraryResult(paths);
    }

    /**
     * Run the A* algorithm.
     * @param visited The set of visited nodes.
     * @param queue The priority queue used to store the nodes to visit.
     * @return An Itinerary object containing the best path between the start and end nodes.
     * 
     * @see Itinerary
     */
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

    /**
     * Process the neighbors of the current node.
     * @param queue The priority queue used to store the nodes to visit.
     * @param itinerary The itinerary object.
     * @param current The current info of the node.
     * @param currentNode The current node.
     * @param neighbors The set of neighbors of the current node.
     */
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
        if (isPersonalizedNode(start)) {
            Node old = start;
            start = StationDAO.getNearestStation(old.getCoordinates());
            walkSegments.put(start, new SegmentWalk(old, start));
        }
        if (isPersonalizedNode(end)) {
            Node old = end;
            end = StationDAO.getNearestStation(old.getCoordinates());
            walkSegments.put(end, new SegmentWalk(old, end));
        }

        queue.add(new AstarInfo(start, 0, 0, null));
    }

    private boolean isPersonalizedNode(Node node) {
        return node instanceof PersonalizedNode;
    }

    private double heuristic(Node node) {
        return node.distanceTo(end);
    }
}
