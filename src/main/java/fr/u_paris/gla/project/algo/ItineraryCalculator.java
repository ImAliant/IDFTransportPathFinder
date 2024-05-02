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

public class ItineraryCalculator {
    private static final int DURATION_BETWEEN_TWO_DIFFERENT_STOPS = 300;
    private static final double AVERAGE_WALKING_SPEED = 5.0*3600;
    private static final double NEAR_STOP_DISTANCE = 0.3; // 300m

    private static Network network = Network.getInstance();

    private static List<Stop> nearDest = new ArrayList<>();
    private static Line walk = new WalkingLine("Marche", "Bleu");
    private static Stop tmpStart;
    private static Stop tmpDest;

    private static double totalDistance = 0;
    private static double totalDuration = 0;

    private ItineraryCalculator() {}

    public static Itinerary calculatePath(Stop start, Stop destination) {
        tmpStart = addWalkingPath(start);
        tmpDest = addWalkingPath(destination);

        return dijsktraAlgorithm(start, destination);
    }

    private static Itinerary dijsktraAlgorithm(Stop start, Stop destination) {
        Map<Stop, Double> duration = new HashMap<>();
        Map<Stop, Stop> previousStops = new HashMap<>();
        PriorityQueue<Stop> queue = new PriorityQueue<>(Comparator.comparingDouble(duration::get));

        initializeStopDistances(start, duration, previousStops, queue);

        return runDijsktraAlgorithm(start, destination, duration, previousStops, queue);
    }

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

    private static double calculateNewDistance(Stop currentStop, TravelPath path, 
        Map<Stop, Stop> previousStops, Map<Stop, Double> duration) {
        double newDistance = duration.get(currentStop) + path.getDuration();
        
        if (!isStopNull(previousStops.get(currentStop))) {
            newDistance = addFromTravelPath(currentStop.getPaths(), path, newDistance);
        }

        return newDistance;
    }

    private static double addFromTravelPath(List<TravelPath> paths, TravelPath path, double value) {
        double newValue = value;
        for (TravelPath p: paths) {
            if (!isSameLine(path, p) && isSameStop(path.getStart(), p.getEnd())) {
                newValue += DURATION_BETWEEN_TWO_DIFFERENT_STOPS;
            }
        }

        return newValue;
    }

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

    private static Stop addWalkingPath(Stop stop) {
        Stop tmpStop = null;

        if (checkIfStopExistsInNetwork(stop)) {
            tmpStop = stop;
            network.addStop(stop);

            addWalkingPathToNearDest(stop);
        }

        return tmpStop;
    }

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

    private static boolean checkIfStopExistsInNetwork(Stop stop) {
        return network.findSameStop(stop.getStopName(), stop.getLongitude(), stop.getLatitude()) == null;
    }

    private static boolean isStopNull(Stop stop) {
        return stop == null;
    }

    private static boolean isSameLine(TravelPath p1, TravelPath p2) {
        return p1.getLine().equals(p2.getLine());
    }

    private static boolean isSameStop(Stop s1, Stop s2) {
        return s1.equals(s2);
    }
}
