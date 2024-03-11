package fr.u_paris.gla.project.idfnetwork;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
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

    /**
     * Private constructor to prevent instantiation from other classes
     */ 
    private Network() {}

    /**
     * Private static inner class to hold the instance of the singleton class
     */ 
    private static class NetworkHolder {
        private static final Network INSTANCE = new Network();
    }
    /**
     * Returns the instance of the singleton class
     * @return the instance of the singleton class
     */
    public static Network getInstance() {
        return NetworkHolder.INSTANCE;
    }
    /**
     * Returns the line with the given name and route type
     * @param lname
     * @param routetype
     * @return
     */
    protected Line findLine(String lname, String routetype) {
        String key = generateLineKey(lname, routetype);
        return lines.get(key);
    }
    /**
     * Adds the given line to the network
     * @param line
     */
    protected void addLine(Line line) {
        String key = generateLineKey(line.getLineName(), line.getRouteType());
        lines.putIfAbsent(key, line);
    }
    /**
     * Returns the stop with the given name, longitude and latitude
     * @param name
     * @param longitude
     * @param latitude
     * @return
     */
    protected Stop findStop(String name, double longitude, double latitude) {
        String key = generateStopKey(name, longitude, latitude);
        return stops.get(key);
    }
    /**
     * Adds the given stop to the network
     * @param stop
     */ 
    protected void addStop(Stop stop) {
        String key = generateStopKey(stop.getStopName(), stop.getLongitude(), stop.getLatitude());
        stops.putIfAbsent(key, stop);
    }
    /**
     * Generates a key for a line
     * @param lname
     * @param routetype
     * @return
     */
    private String generateLineKey(String lname, String routetype) {
        return lname + "-" + routetype;
    }
    /**
     * Generates a key for a stop
     * @param name
     * @param longitude
     * @param latitude
     * @return
     */
    private String generateStopKey(String name, double longitude, double latitude) {
        return name + "-" + longitude + "-" + latitude;
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
}
