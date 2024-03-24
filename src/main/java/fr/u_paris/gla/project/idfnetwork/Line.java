package fr.u_paris.gla.project.idfnetwork;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import java.util.Collections;

/**
 * Represents a transport line in the idf network
 */
public abstract class Line {
    /** Name of the line */
    protected String lname;
    /** Color of the line */
    protected String color;
    /** Speed of the line */
    protected double speed;
    /** All the stops of the line */
    protected Map<String, Stop> stops = new HashMap<>();
    /** Paths between stops*/
    protected List<TravelPath> paths = new ArrayList<>();

    /** Magic number: default speed of a line in km/h */
    private static final double DEFAULT_SPEED = 1.0;

    /**
     * Create a new line with the given name and color
     * @param name
     * @param color
     */
    protected Line(String name, String color) {
        this.lname = name;
        this.color = color;
        this.speed = DEFAULT_SPEED;
    }
    
    /**
     * Add a stop to the line if it is not already in the line
     * @param stop
     */
    protected void addStop(Stop stop) {
        for (Stop s: stops.values()) {
            if (s.equals(stop)) {
                return;
            }
        }
        stops.put(stop.getStopName(), stop);
    }

    /**
     * Add a path between two stops
     * @param s1
     * @param s2
     * @param distance
     * @param duration
     */
    protected void addPath(Stop s1, Stop s2, double distance, Integer duration) {
        s1.addPath(s2, distance, duration);

        // We ensure that the path is not already in the list
        for (TravelPath path: paths) {
            if (path.getStart().equals(s1) && path.getEnd().equals(s2)) {
                return;
            }
        }
        paths.add(new TravelPath(s1, s2, distance, duration));
    }

    /**
     * Find a stop by its name
     * @param name
     * @return the stop with the given name, or null if it does not exist
     */
    protected Stop findStop(String name) {
        return stops.get(name);
    }

    public String pathsToString() {
        StringBuilder result = new StringBuilder();
        for (TravelPath path: paths) {
            result.append(path.getStart().getStopName()).append(" -> ").append(path.getEnd().getStopName()).append("\n");
        }
        return result.toString();
    }

    public abstract LineType getType();

    public String getLineName() {
        return lname;
    }

    public String getColor() {
        return color;
    }

    public List<Stop> getStops() {
        return Collections.unmodifiableList(new ArrayList<>(stops.values()));
    }

    public List<TravelPath> getPaths() {
        return Collections.unmodifiableList(new ArrayList<>(paths));
    }
}
