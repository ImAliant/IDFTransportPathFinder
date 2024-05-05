package fr.u_paris.gla.crazytrip.idfnetwork.network;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

import fr.u_paris.gla.crazytrip.idfnetwork.Stop;
import fr.u_paris.gla.crazytrip.idfnetwork.line.Line;
import fr.u_paris.gla.crazytrip.idfnetwork.line.LineType;
import fr.u_paris.gla.crazytrip.utils.GPS;

import java.util.HashMap;

/**
 * Represents the network of the Ile-de-France region.
 */
public class Network {
    /**
     * Transport lines of the idf network
     */
    protected static Map<String, Line> lines = new HashMap<>();
    /**
     * Stops of the idf network
     */
    protected static Map<String, Stop> stops = new HashMap<>();

    protected static Map<String, HashMap<String, Stop>> sameStops = new HashMap<>();


    private static Network instance = null;

    /**
     * Private constructor to prevent instantiation from other classes
     */
    private Network() {
    }

    /**
     * Returns the instance of the singleton class
     * 
     * @return the instance of the singleton class
     */
    public static Network getInstance() {
        if (instance == null) {
            instance = new Network();
        }
        return instance;
    }

    /**
     * Returns the line with the given name and route type
     * 
     * @param lname
     * @param routetype
     * @return
     */
    public Line findLine(String lname, LineType type) {
        String key = generateLineKey(lname, type);
        return lines.get(key);
    }

    public List<Line> getLinesByType(LineType type) {
        return lines.values()
            .stream()
            .filter(line -> line.getType() == type)
            .sorted(Comparator.comparing(Line::toString))
            .collect(Collectors.toList()); 
    }

    /**
     * Adds the given line to the network
     * 
     * @param line
     */
    public void addLine(Line line) {
        String key = generateLineKey(line.getLineName(), line.getType());
        lines.putIfAbsent(key, line);
    }

    /**
     * Returns the stop with the given name, longitude and latitude
     * 
     * @param name
     * @param longitude
     * @param latitude
     * @return
     */
    public Stop findStop(String name, double longitude, double latitude) {
        String key = generateStopKey(name, longitude, latitude);
        return stops.get(key);
    }

    public Stop findSameStop(String name,double longitude, double latitude){
        final double DISTANCE_THRESHOLD = 0.5;

        HashMap<String,Stop> stopMap = sameStops.get(name);
        if (stopMap != null) {
            for (Stop stop : stopMap.values()) {
                double distance = GPS.distance(latitude,longitude,stop.getLatitude(),stop.getLongitude());
                if (distance < DISTANCE_THRESHOLD) {
                    return stop;
                }
            }
        }
        return null;
    }

    public Stop findStopByName(String name) {
        for (Stop stop : getStops()) {
            if (stop.getStopName().equalsIgnoreCase(name)) {
                return stop;
            }
        }
        return null; 
    }

    public List<Stop> findStopFromGeoPosition(double latitude, double longitude, double distance) {
        List<Stop> res = new ArrayList<>();
        
        for (Stop s: getStops()) {
            if (GPS.distance(latitude, longitude, s.getLatitude(), s.getLongitude()) < distance) {
                res.add(s);
            }
        }

        return res;
    }

    public Stop findClosestStopByGeoPosition(double latitude, double longitude) {
        double distance = 0.01; // begin with 10m distance
        List<Stop> closestStops = new ArrayList<>();

        while (closestStops.isEmpty()) {
            closestStops = findStopFromGeoPosition(latitude, longitude, distance);

            distance += 0.01;
        }
        
        return closestStops.get(0);
    }

    /**
     * Adds the given stop to the network
     * 
     * @param stop
     */
    public void addStop(Stop stop) {
        String key = generateStopKey(stop.getStopName(), stop.getLongitude(), stop.getLatitude());
        stops.putIfAbsent(key, stop);

        String namekey = generateStopNameKey(stop.getStopName());

        sameStops.computeIfAbsent(namekey, k -> new HashMap<>());

        sameStops.get(namekey).putIfAbsent(key, stop);
    }

    public void removeStop(Stop stop) {
        if(stop != null) {
            String key = generateStopKey(stop.getStopName(), stop.getLongitude(), stop.getLatitude());
            stops.remove(key, stop);

            String namekey = generateStopNameKey(stop.getStopName());
            sameStops.get(namekey).remove(key, stop);
        }
    }

    /**
     * Generates a key for a line
     * 
     * @param lname
     * @param routetype
     * @return
     */
    private String generateLineKey(String lname, LineType line) {
        return lname + "-" + line;
    }

    /**
     * Generates a key for a stop
     * 
     * @param name
     * @param longitude
     * @param latitude
     * @return
     */
    private static String generateStopKey(String name, double longitude, double latitude) {
        return name + "-" + longitude + "-" + latitude;
    }

    private static String generateStopNameKey(String name) {
        return name;
    }

    @Override
    public String toString() {
        return "Network [lines=" + lines + ", stops=" + stops + "]";
    }

    public List<Line> getLines() {
        return Collections.unmodifiableList(new ArrayList<>(lines.values()));
    }

    public List<Stop> getStops() {
        return Collections.unmodifiableList(new ArrayList<>(stops.values()));
    }

    //DEBUG function
    public void clear() {
        lines.clear();
        stops.clear();
    }
}
