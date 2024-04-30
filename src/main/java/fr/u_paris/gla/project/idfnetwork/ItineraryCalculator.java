package fr.u_paris.gla.project.idfnetwork;

import fr.u_paris.gla.project.idfnetwork.stop.Stop;

import java.util.*;

public class ItineraryCalculator {
    public static Itinerary CalculateRoad(Stop start, Stop destination) {
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
        for (int i = 0; i < stops.size() - 1; i++) {
            Stop current = stops.get(i);
            Stop next = stops.get(i + 1);
            for (TravelPath path : current.getPaths()) {
                if (path.getEnd().equals(next)) {
                    totalDistance += path.getDistance();
                    totalDuration += path.getDuration();
                    break;
                }
            }
        }
        return new Itinerary(stops, lines, totalDistance, totalDuration);
    }
}
