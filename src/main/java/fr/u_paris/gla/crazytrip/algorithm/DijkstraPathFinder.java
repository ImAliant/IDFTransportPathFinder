package fr.u_paris.gla.crazytrip.algorithm;

import fr.u_paris.gla.crazytrip.model.Node;
import fr.u_paris.gla.crazytrip.model.Segment;

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

    private DijkstraPathFinder() {}

    public static Itinerary dijkstra(Node start, Node end) {
        if (start == null) throw new IllegalArgumentException("Start node is null");
        if (end == null) throw new IllegalArgumentException("End node is null");

        Map<Node, Boolean> visited = new HashMap<>();
        PriorityQueue<DijkstraInfo> queue = new PriorityQueue<>(Comparator.comparingDouble(DijkstraInfo::getWeight)
        .thenComparing(n -> n.getLineChanges()));
        
        initialize(start, visited, queue);

        return runDijkstra(start, end, visited, queue);
    }

    public static List<Segment> getSegmentsFromItinerary(Node start, Node end) {
        Itinerary itinerary = dijkstra(start, end);

        LinkedList<Segment> segments = new LinkedList<>();
        Node current = end;

        while (!current.equals(start) && itinerary.contains(current)) {
            Node next = itinerary.get(current).getNode();
            segments.addFirst(network.getSegment(next, current));
            current = next;
        }

        return segments;
    }

    private static Itinerary runDijkstra(Node start, Node end, Map<Node, Boolean> visited, PriorityQueue<DijkstraInfo> queue) {
        Itinerary itinerary = new Itinerary();
        itinerary.add(start, new BestWeight(start, 0, null));

        while (!queue.isEmpty()) {
            System.out.println("DEBUG");
            DijkstraInfo current = queue.poll();
            Node currentNode = current.getNode();
            
            if (Boolean.TRUE.equals(visited.get(currentNode))) {
                continue;
            }

            visited.replace(currentNode, true);

            if (currentNode.equals(end)) return itinerary;

            Set<Segment> neighbors = new HashSet<>(network.getSegments(currentNode));
            
            for (Segment segment: neighbors) {
                Node neighbor = segment.getEndPoint();
                Line neighborLine = network.getLineFromSegment(segment);
                double weight = current.getWeight() + segment.getDuration();
                int lineChanges = current.getLineChanges() + (neighborLine.equals(current.getLine()) ? 0 : 1);

                addInfoInQueue(itinerary, currentNode, neighbor, neighborLine, weight, lineChanges, queue);
            }
        }

        return null;
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
