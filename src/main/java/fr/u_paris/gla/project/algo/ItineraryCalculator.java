package fr.u_paris.gla.project.algo;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import fr.u_paris.gla.project.idfnetwork.line.Line;
import fr.u_paris.gla.project.idfnetwork.line.WalkingLine;
import fr.u_paris.gla.project.idfnetwork.network.Network;
import fr.u_paris.gla.project.utils.GPS;
import fr.u_paris.gla.project.idfnetwork.TravelPath;
import fr.u_paris.gla.project.idfnetwork.Stop;
/**
 * This class provides methods to calculate the shortest route between two stops in a transportation network. 
 * It considers multiple factors including walking distances, and transfers between different lines. 
 * It uses Dijkstra's algorithm to find the shortest path.
 * 
 * @author Diamant Alexandre
 * @author Diamant Yanis
 */
public class ItineraryCalculator {
    /** Duration between two different stops, average walking speed and near stop distance */
    private static final int DURATION_BETWEEN_TWO_DIFFERENT_STOPS = 300;
    /** Average walking speed in km/h */
    private static final double AVERAGE_WALKING_SPEED = 5.0*3600;
    /** Near stop distance in km */
    private static final double NEAR_STOP_DISTANCE = 0.3; // 300m

    /** Network instance */
    private static Network network = Network.getInstance();

    /** List of stops near the destination */
    private static List<Stop> nearDest = new ArrayList<>();
    /** Walking line */
    private static Line walk = new WalkingLine("Marche", "Bleu");
    /** Temporary start */
    private static Stop tmpStart;
    /** Temporary destination */
    private static Stop tmpDest;

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
        tmpStart = addWalkingPath(start);
        tmpDest = addWalkingPath(destination);

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
    private static Itinerary dijsktraAlgorithm(Stop start, Stop destination) {
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
    private static Itinerary runDijsktraAlgorithm(Stop start,
            Stop destination, Map<Stop, Double> duration,
            Map<Stop, Stop> previousStops, PriorityQueue<Stop> queue) {
        
        while (!queue.isEmpty()) {
            Stop currentStop = queue.poll();
            if (isSameStop(currentStop, destination)) {
                break;
            }

            for (TravelPath path: currentStop.getPaths()) {
                Stop neighbor = path.getEnd();
                double newDistance = calculateNewDistance(currentStop, path, previousStops, duration);

                if (newDistance < duration.get(neighbor)) {
                    duration.put(neighbor, newDistance);
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
    private static Itinerary constructItinerary(Stop start, Stop destination, 
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
            Stop p = stops.get(i - 1);
            current = stops.get(i);
            next = stops.get(i + 1);
            calculateTotalDistanceAndDuration(p, current, next);
        }

        cleanUpNetwork();

        return new Itinerary(stops, lines, totalDistance, totalDuration);
    }

    /**
     * Clean up the network.
     */
    private static void cleanUpNetwork() {
        if (!nearDest.isEmpty()) {
            for (Stop stop : nearDest) {
                stop.removeLine(walk);
            }
        }
        walk.removePaths();
        network.removeStop(tmpDest);
        network.removeStop(tmpStart);
    }

    /**
     * Calculate the total distance and duration.
     * 
     * @param previous the previous stop
     * @param current the current stop
     * @param next the next stop
     */
    private static void calculateTotalDistanceAndDuration(Stop previous, Stop current, Stop next) {
        for (TravelPath path : current.getPaths()) {
            if (path.getEnd().equals(next)) {
                totalDuration = addFromTravelPath(previous.getPaths(), path, totalDuration);
                totalDuration += path.getDuration();
                totalDistance += path.getDistance();
                break;
            }
        }
    }

    /**
     * Calculate the new distance.
     * 
     * @param currentStop the current stop
     * @param path the travel path
     * @param previousStops the previous stops map
     * @param duration the duration map
     * 
     * @return the new distance
     */
    private static double calculateNewDistance(Stop currentStop, TravelPath path, 
        Map<Stop, Stop> previousStops, Map<Stop, Double> duration) {
        double newDistance = duration.get(currentStop) + path.getDuration();
        
        if (!isStopNull(previousStops.get(currentStop))) {
            newDistance = addFromTravelPath(currentStop.getPaths(), path, newDistance);
        }

        return newDistance;
    }

    /**
     * Add the duration from the travel path.
     * 
     * @param paths the list of travel paths
     * @param path the travel path
     * @param value the value
     * 
     * @return the new value
     */
    private static double addFromTravelPath(List<TravelPath> paths, TravelPath path, double value) {
        double newValue = value;
        for (TravelPath p: paths) {
            if (!isSameLine(path, p) && isSameStop(path.getStart(), p.getEnd())) {
                newValue += DURATION_BETWEEN_TWO_DIFFERENT_STOPS;
            }
        }

        return newValue;
    }

    /**
     * Initialize the stop distances to infinity.
     * 
     * @param start the start stop
     * @param duration the duration map
     * @param previousStops the previous stops map
     * @param queue the priority queue
     */
    private static void initializeStopDistances(
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
     * Add a walking path.
     * 
     * @param stop the stop
     * 
     * @return the stop
     */
    private static Stop addWalkingPath(Stop stop) {
        Stop tmpStop = null;

        if (checkIfStopExistsInNetwork(stop)) {
            tmpStop = stop;
            network.addStop(stop);

            addWalkingPathToNearDest(stop);
        }

        return tmpStop;
    }

    /**
     * Add a walking path to the nearest destination.
     * 
     * @param stop the stop
     */
    private static void addWalkingPathToNearDest(Stop stop) {
        nearDest = network.findStopFromGeoPosition(stop.getLatitude(), stop.getLongitude(), NEAR_STOP_DISTANCE);
        if (!nearDest.isEmpty()) {
            for (Stop nearStop : nearDest) {
                double distance = GPS.distance(stop.getLatitude(), stop.getLongitude(), nearStop.getLatitude(), nearStop.getLongitude())/* Math.sqrt(Math.pow(stop.getLatitude() - nearStop.getLatitude(), 2) + Math.pow(stop.getLongitude() - nearStop.getLongitude(), 2)) */;
                int duration = (int) (distance / AVERAGE_WALKING_SPEED);

                walk.addPath(stop, nearStop, distance, duration);
                walk.addStop(stop);
                stop.addLine(walk);
            }
        }
    }

    /**
     * Check if a stop exists in the network.
     * 
     * @param stop the stop
     * 
     * @return true if the stop exists in the network, false otherwise
     */
    private static boolean checkIfStopExistsInNetwork(Stop stop) {
        return network.findSameStop(stop.getStopName(), stop.getLongitude(), stop.getLatitude()) == null;
    }

    /**
     * Check if a stop is null.
     * 
     * @param stop the stop
     * 
     * @return true if the stop is null, false otherwise
     */
    private static boolean isStopNull(Stop stop) {
        return stop == null;
    }

    /**
     * Check if two travel paths are on the same line.
     * 
     * @param p1 the first travel path
     * @param p2 the second travel path
     * 
     * @return true if the two travel paths are on the same line, false otherwise
     */
    private static boolean isSameLine(TravelPath p1, TravelPath p2) {
        return p1.getLine().equals(p2.getLine());
    }

    /**
     * Check if two stops are the same.
     * 
     * @param s1 the first stop
     * @param s2 the second stop
     * 
     * @return true if the two stops are the same, false otherwise
     */
    private static boolean isSameStop(Stop s1, Stop s2) {
        return s1.equals(s2);
    }
}
