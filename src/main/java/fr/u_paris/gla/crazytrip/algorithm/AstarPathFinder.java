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
     * @see Node
     * @see AstarInfo
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
     * 
     * @see Itinerary
     * @see AstarInfo
     * @see Node
     * @see Segment
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

    /**
     * Find the closest stations to the current node and create walk segments to them.
     * 
     * @param currentNode The current node.
     * @param neighbors The set of neighbors of the current node.
     * 
     * @see Segment
     * @see Station
     * @see Node
     */
    private void createWalkSegmentsToCloseStation(Node currentNode, Set<Segment> neighbors) {
        Set<Station> closeStations = StationDAO.findCloseStations(currentNode, 0.2);
        createWalkSegments(currentNode, closeStations, neighbors);
    }

    /**
     * Create walk segments for each station in the set of close stations.
     * 
     * @param currentNode The current node.
     * @param closeStations The set of close stations.
     * @param neighbors Set who will be filled with the walk segments.
     * 
     * @see Station
     * @see Segment
     * @see SegmentWalk
     * @see Node
     */
    private void createWalkSegments(Node currentNode, Set<Station> closeStations, Set<Segment> neighbors) {
        for (Station station : closeStations) {
            SegmentWalk walkSegment = new SegmentWalk(currentNode, station);
            neighbors.add(walkSegment);
        }
    }

    /**
     * Add new astar info in the queue.
     * 
     * @param itinerary The itinerary object containing the best path between the start and end nodes.
     * @param currentNode The current node.
     * @param neighborNode The neighbor node.
     * @param neighborLine The neighbor line.
     * @param weight The weight of the neighbor node.
     * @param lineChanges The number of line changes.
     * @param queue The priority queue used to store the nodes to visit.
     * 
     * @see Itinerary
     * @see Node
     * @see Line
     */
    private void addInfoInQueue(Itinerary itinerary, Node currentNode, Node neighborNode, Line neighborLine,
            double weight, int lineChanges, PriorityQueue<AstarInfo> queue) {
        double heuristic = heuristic(neighborNode);
        double estimatedCost = weight + heuristic;
        BestWeight neighborWeight = itinerary.get(neighborNode);

        if (isCostBetter(neighborWeight, estimatedCost, heuristic)) {
            itinerary.add(neighborNode, new BestWeight(currentNode, weight, neighborLine));
            queue.add(new AstarInfo(neighborNode, estimatedCost, lineChanges, neighborLine));
        }
    }

    /**
     * Initialize the priority queue.
     * 
     * @param queue The priority queue used to store the nodes to visit.
     */
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

    /**
     * Check if the node is a personalized node.
     * 
     * @param node The node to check.
     * @return True if the node is a personalized node, false otherwise.
     * 
     * @see PersonalizedNode
     */
    private boolean isPersonalizedNode(Node node) {
        return node instanceof PersonalizedNode;
    }

    /**
     * Check if the cost of the neighbor node is better than the current cost.
     * 
     * @param neighborWeight The weight of the neighbor node.
     * @param estimatedCost The estimated cost of the neighbor node.
     * @param heuristic The heuristic of the neighbor node.
     * 
     * @return True if the cost of the neighbor node is better than the current cost, false otherwise.
     * 
     * @see BestWeight
     */
    private boolean isCostBetter(BestWeight neighborWeight, double estimatedCost, double heuristic) {
        return neighborWeight == null || estimatedCost < neighborWeight.getWeight() + heuristic;
    }

    /**
     * Calculate the heuristic of a node.
     * 
     * @param node The node.
     * @return The heuristic of the node.
     * 
     * @see Node
     */
    private double heuristic(Node node) {
        return node.distanceTo(end);
    }
}
