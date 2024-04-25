package fr.u_paris.gla.project.idfnetwork;

import fr.u_paris.gla.project.utils.GPS;

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

    protected static Map<String, HashMap<String, Stop>> sameStops = new HashMap<>();

    private static Network instance = null;

    /**
     * Private constructor to prevent instantiation from other classes
     */ 
    private Network() {}

    /**
     * Returns the instance of the singleton class
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
     * @param lname
     * @param routetype
     * @return
     */
    public Line findLine(String lname, LineType type) {
        String key = generateLineKey(lname, type);
        return lines.get(key);
    }
    /**
     * Adds the given line to the network
     * @param line
     */
    protected void addLine(Line line) {
        String key = generateLineKey(line.getLineName(), line.getType());
        lines.putIfAbsent(key, line);
    }
    /**
     * Returns the stop with the given name, longitude and latitude
     * @param name
     * @param longitude
     * @param latitude
     * @return
     */
    public Stop findStop(String name, double longitude, double latitude) {
        String key = generateStopKey(name, longitude, latitude);
        return stops.get(key);
    }

    public static Stop findSameStop(String name,double longitude, double latitude){
        HashMap<String,Stop> stopMap = sameStops.get(name);
        if (stopMap != null) {
            for (Stop stop : stopMap.values()) {
                double distance = Math.sqrt(Math.pow(latitude - stop.getLatitude(), 2) + Math.pow(longitude - stop.getLongitude(), 2));
                //double distance = GPS.distance(latitude,longitude,stop.getLatitude(),stop.getLongitude());
                if (distance < 0.5) {
                    return stop;
                }
            }
        }
        return null;
    }
    /**
     * Adds the given stop to the network
     * @param stop
     */ 
    protected static void addStop(Stop stop) {
        String key = generateStopKey(stop.getStopName(), stop.getLongitude(), stop.getLatitude());
        stops.putIfAbsent(key, stop);

        String namekey = generateStopNameKey(stop.getStopName());

        if (!sameStops.containsKey(namekey)) {
            sameStops.put(namekey, new HashMap<>());
        }
        sameStops.get(namekey).putIfAbsent(key, stop);
    }

    protected static void removeStop(Stop stop) {
        if(stop != null) {
            String key = generateStopKey(stop.getStopName(), stop.getLongitude(), stop.getLatitude());
            stops.remove(key, stop);

            String namekey = generateStopNameKey(stop.getStopName());
            sameStops.get(namekey).remove(key, stop);
        }
    }
    /**
     * Generates a key for a line
     * @param lname
     * @param routetype
     * @return
     */
    private String generateLineKey(String lname, LineType line) {
        return lname + "-" + line;
    }
    /**
     * Generates a key for a stop
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
    protected void clear() {
        lines.clear();
        stops.clear();
    }
}
