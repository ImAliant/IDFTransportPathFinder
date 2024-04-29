package fr.u_paris.gla.project.idfnetwork;

import fr.u_paris.gla.project.App;
import fr.u_paris.gla.project.utils.GPS;

import java.util.*;

public class ItineraryCalculator {
    public static Itinerary CalculateRoad(Stop start, Stop destination) {
        int DURATION_BETWEEN_TWO_DIFFERENT_STOPS = 300;
        Stop tmpStart = null;
        Stop tmpDest = null;
        List<Stop> nearDest = new ArrayList<>();;
        double AVERAGE_WALKING_SPEED = 5.0*3600;
        Line walk = new WalkingLine("Marche", "Bleu");

        if(Network.findSameStop(start.getStopName(), start.getLongitude(), start.getLatitude()) == null){
            tmpStart = start;
            Network.addStop(start);
            List<Stop> nearStops = getStopsFromAdress(start.getLatitude(), start.getLongitude());
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

        if(Network.findSameStop(destination.getStopName(), destination.getLongitude(), destination.getLatitude()) == null){
            tmpDest = destination;
            Network.addStop(destination);
            nearDest = getStopsFromAdress(destination.getLatitude(), destination.getLongitude());
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
        for(Stop stop : Network.getInstance().getStops()){
            duration.put(stop, Double.MAX_VALUE);
            previousStops.put(stop, null);
        }
        duration.put(start, 0.0);
        queue.add(start);

        // Run Dijskstra algorithm
        while(!queue.isEmpty()){
            Stop currentStop = queue.poll();
            if(currentStop.equals(destination)){
                break;
            }

            for(TravelPath path : currentStop.getPaths()){

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
        while(previous != null){
            stops.add(currentStop);
            for (TravelPath path : previous.getPaths()) {
                if (path.getEnd().equals(currentStop)){
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
        Network.removeStop(tmpDest);
        Network.removeStop(tmpStart);

        return new Itinerary(stops,lines,totalDistance,totalDuration);
    }


    public static List<Stop> getStopsFromAdress(double latitude, double longitude){
        Network.getInstance().getStops();
        List<Stop> res= new ArrayList<>();
        for(Stop s: Network.getInstance().getStops()){
            //Périmètres défini à 100m
            if(GPS.distance(latitude,longitude,s.getLatitude(),s.getLongitude())<0.1){
                res.add(s);
            }

        }
        return res;
    }

}

