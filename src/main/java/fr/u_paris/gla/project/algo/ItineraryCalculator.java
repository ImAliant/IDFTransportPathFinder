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
    private static final int DURATION_BETWEEN_TWO_DIFFERENT_STOPS = 300;
    private static final double AVERAGE_WALKING_SPEED = 5.0*3600;
    private static final double NEAR_STOP_DISTANCE = 0.3; // 300m

    private static Network network = Network.getInstance();

    /**
     * Calculates the most efficient road from the start stop to the destination stop.
     * This method dynamically adds stops if they are not already present in the network, calculates walking paths if necessary,
     * and applies Dijkstra's algorithm to determine the shortest travel path considering line transfers.
     * 
     * @param start The starting stop of the itinerary.
     * @param destination The destination stop of the itinerary.
     * @return An Itinerary object containing a list of stops, lines between them, total distance, and total duration of the trip.
     */

    public static Itinerary CalculateRoad(Stop start, Stop destination) {
        Stop tmpStart = null;
        Stop tmpDest = null;
        List<Stop> nearDest = new ArrayList<>();

        Line walk = new WalkingLine("Marche", "Bleu");

        if(network.findSameStop(start.getStopName(), start.getLongitude(), start.getLatitude()) == null){
            tmpStart = start;
            network.addStop(start);
            List<Stop> nearStops = network.findStopFromGeoPosition(start.getLatitude(), start.getLongitude(), NEAR_STOP_DISTANCE);
            if(!nearStops.isEmpty()) {
                for (Stop stop : nearStops) {
                    double distance = Math.sqrt(Math.pow(start.getLatitude() - stop.getLatitude(), 2) + Math.pow(start.getLongitude() - stop.getLongitude(), 2));
                    int duration = (int) ((int) distance/AVERAGE_WALKING_SPEED);

                    walk.addPath(start, stop, distance, duration);
                    walk.addStop(start);
                    start.addLine(walk);
                }
            }
        }

        if(network.findSameStop(destination.getStopName(), destination.getLongitude(), destination.getLatitude()) == null){
            tmpDest = destination;
            network.addStop(destination);
            nearDest = network.findStopFromGeoPosition(destination.getLatitude(), destination.getLongitude(), NEAR_STOP_DISTANCE);
            if(!nearDest.isEmpty()) {
                for (Stop stop : nearDest) {
                    double distance = Math.sqrt(Math.pow(destination.getLatitude() - stop.getLatitude(), 2) + Math.pow(destination.getLongitude() - stop.getLongitude(), 2));
                    int duration = (int) ((int) distance/AVERAGE_WALKING_SPEED);

                    walk.addPath( stop, destination, distance, duration);
                    walk.addStop(stop);
                    stop.addLine(walk);
                }
            }
        }

        // Initialize Dijskstra algorithm
        Map<Stop, Double> duration = new HashMap<>();
        Map<Stop, Stop> previousStops = new HashMap<>();
        PriorityQueue<Stop> queue = new PriorityQueue<>(Comparator.comparingDouble(duration::get));

        // Initialize distances to infinity for all stops except start
        for (Stop stop : Network.getInstance().getStops()) {
            duration.put(stop, Double.MAX_VALUE);
            previousStops.put(stop, null);
        }
        duration.put(start, 0.0);
        queue.add(start);

        // Run Dijskstra algorithm
        while (!queue.isEmpty()) {
            Stop currentStop = queue.poll();
            if (currentStop.equals(destination)) {
                break;
            }

            for (TravelPath path : currentStop.getPaths()) {
                Stop neighbor = path.getEnd();
                double newDistance = duration.get(currentStop) + path.getDuration();
                if(previousStops.get(currentStop) != null) {
                    for (TravelPath p : previousStops.get(currentStop).getPaths()) {
                        if (!path.getLine().equals(p.getLine()) && (path.getStart().equals(p.getEnd()))) {
                            newDistance += DURATION_BETWEEN_TWO_DIFFERENT_STOPS;

                        }
                    }
                }
                if (newDistance < duration.get(neighbor)) {
                    duration.put(neighbor, newDistance);
                    previousStops.put(neighbor, currentStop);
                    queue.add(neighbor);
                }
            }
        }

        // Construct road from previousStops
        List<Stop> stops = new ArrayList<>();
        List<Line> lines = new ArrayList<>();
        Stop currentStop = destination;
        Stop previous = previousStops.get(currentStop);
        while (previous != null) {
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

        // Calculate total distance and duration
        double totalDistance = 0.0;
        double totalDuration = 0.0;
        Stop current = stops.get(0);
        Stop next = null;
        for (TravelPath path : current.getPaths()){
            totalDistance += path.getDistance();
            totalDuration += path.getDuration();
            break;
        }

        for (int i = 1; i < stops.size() - 1; i++){
            Stop previouss = stops.get(i-1);
            current = stops.get(i);
            next = stops.get(i+1);
            for (TravelPath path : current.getPaths()){
                if (path.getEnd().equals(next)){
                    for (TravelPath p : previouss.getPaths()) {
                        if (!path.getLine().equals(p.getLine()) && (path.getStart().equals(p.getEnd()) )) {
                            totalDuration += DURATION_BETWEEN_TWO_DIFFERENT_STOPS;
                        }
                    }
                    totalDistance += path.getDistance();
                    totalDuration += path.getDuration();
                    break;

                }
            }
        }
        if(!nearDest.isEmpty()) {
            for (Stop stop : nearDest) {
                stop.removeLine(walk);
            }
        }
        walk.removePaths();
        network.removeStop(tmpDest);
        network.removeStop(tmpStart);

        return new Itinerary(stops,lines,totalDistance,totalDuration);
    }
}
