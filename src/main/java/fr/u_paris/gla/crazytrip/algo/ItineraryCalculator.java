package fr.u_paris.gla.crazytrip.algo;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import fr.u_paris.gla.crazytrip.idfnetwork.Stop;
import fr.u_paris.gla.crazytrip.idfnetwork.TravelPath;
import fr.u_paris.gla.crazytrip.idfnetwork.line.Line;
import fr.u_paris.gla.crazytrip.idfnetwork.network.Network;
/**
 * This class provides methods to calculate the shortest route between two stops in a transportation network. 
 * It considers multiple factors including walking distances, and transfers between different lines. 
 * It uses Dijkstra's algorithm to find the shortest path.
 * 
 * @author Diamant Alexandre
 * @author Diamant Yanis
 */
public class ItineraryCalculator {
    /** Network instance */
    private static Network network = Network.getInstance();

    /** Total distance */
    private static double totalDistance = 0;
    /** Total duration */
    private static double totalDuration = 0;

    private ItineraryCalculator() {}

    /**
     * Calculates the most efficient road from the start stop to the destination stop.
     * This method dynamically adds stops if they are not already present in the network, calculates walking paths if necessary,
     * and applies Dijkstra's algorithm to determine the shortest travel path considering line transfers.
     * 
     * @param start The starting stop of the itinerary.
     * @param destination The destination stop of the itinerary.
     * @return An Itinerary object containing a list of stops, lines between them, total distance, and total duration of the trip.
     */
    public static Itinerary calculatePath(Stop start, Stop destination) {
        return dijsktraAlgorithm(start, destination);
    }

    /**
     * Initialize the disjkstra algorithm.
     * 
     * @param start the start stop
     * @param destination the destination stop
     * 
     * @return the best path between the two stops
     */ 
    protected static Itinerary dijsktraAlgorithm(Stop start, Stop destination) {
        Map<Stop, Double> duration = new HashMap<>();
        Map<Stop, Stop> previousStops = new HashMap<>();
        PriorityQueue<Stop> queue = new PriorityQueue<>(Comparator.comparingDouble(duration::get));

        initializeStopDistances(start, duration, previousStops, queue);

        return runDijsktraAlgorithm(start, destination, duration, previousStops, queue);
    }

    /**
     * Run the dijsktra algorithm.
     * 
     * @param start the start stop
     * @param destination the destination stop
     * @param duration the duration map
     * @param previousStops the previous stops map
     * @param queue the priority queue
     * 
     * @return the best path between the two stops
     */
    protected static Itinerary runDijsktraAlgorithm(Stop start,
            Stop destination, Map<Stop, Double> duration,
            Map<Stop, Stop> previousStops, PriorityQueue<Stop> queue) {
        
        while (!queue.isEmpty()) {
            Stop currentStop = queue.poll();
            if (isSameStop(currentStop, destination)) {
                break;
            }

            for (TravelPath path: currentStop.getPaths()) {
                Stop neighbor = path.getEnd();
                double newDuration = duration.get(currentStop) + 1;

                if (newDuration < duration.get(neighbor)) {
                    duration.put(neighbor, newDuration);
                    previousStops.put(neighbor, currentStop);
                    queue.add(neighbor);
                }
            }
        }

        return constructItinerary(start, destination, previousStops);
    }

    /**
     * Construct the itinerary.
     * 
     * @param start the start stop
     * @param destination the destination stop
     * @param previousStops the previous stops map
     * 
     * @return the best path between the two stops
     */
    protected static Itinerary constructItinerary(Stop start, Stop destination, 
        Map<Stop, Stop> previousStops) {
        List<Stop> stops = new ArrayList<>();
        List<Line> lines = new ArrayList<>();

        Stop currentStop = destination;
        Stop previous = previousStops.get(currentStop);

        while (!isStopNull(previous)) {
            stops.add(currentStop);
            for (TravelPath path : previous.getPaths()) {
                if (path.getEnd().equals(currentStop)) {
                    lines.add(path.getLine());
                    break;
                }
            }
            currentStop = previous;
            previous = previousStops.get(currentStop);
        }

        stops.add(start);
        Collections.reverse(stops);
        Collections.reverse(lines);

        Stop current = stops.get(0);
        Stop next = null;

        totalDistance += current.getPaths().get(0).getDistance();
        totalDuration += current.getPaths().get(0).getDuration();

        for (int i = 1; i < stops.size() - 1; i++) {
            current = stops.get(i);
            next = stops.get(i + 1);
            calculateTotalDistanceAndDuration(current, next);
        }

        return new Itinerary(stops, lines, totalDistance, totalDuration);
    }

    /**
     * Calculate the total distance and duration.
     * 
     * @param previous the previous stop
     * @param current the current stop
     * @param next the next stop
     */
    protected static void calculateTotalDistanceAndDuration(Stop current, Stop next) {
        for (TravelPath path : current.getPaths()) {
            if (path.getEnd().equals(next)) {
                totalDuration += path.getDuration();
                totalDistance += path.getDistance();
                break;
            }
        }
    }

    /**
     * Initialize the stop distances to infinity.
     * 
     * @param start the start stop
     * @param duration the duration map
     * @param previousStops the previous stops map
     * @param queue the priority queue
     */
    protected static void initializeStopDistances(
            Stop start, Map<Stop, Double> duration, 
            Map<Stop, Stop> previousStops, PriorityQueue<Stop> queue) {
        for (Stop stop : network.getStops()) {
            duration.put(stop, Double.MAX_VALUE);
            previousStops.put(stop, null);
        }
        duration.put(start, 0.0);
        queue.add(start);
    }

    /**
     * Check if a stop is null.
     * 
     * @param stop the stop
     * 
     * @return true if the stop is null, false otherwise
     */
    protected static boolean isStopNull(Stop stop) {
        return stop == null;
    }

    /**
     * Check if two stops are the same.
     * 
     * @param s1 the first stop
     * @param s2 the second stop
     * 
     * @return true if the two stops are the same, false otherwise
     */
    protected static boolean isSameStop(Stop s1, Stop s2) {
        return s1.equals(s2);
    }

    public static double getTotalDistance() {
        return totalDistance;
    }

    public static double getTotalDuration() {
        return totalDuration;
    }
}
