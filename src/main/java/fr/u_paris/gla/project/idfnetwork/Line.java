package fr.u_paris.gla.project.idfnetwork;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import java.util.ArrayList;
import java.util.Collections;

import java.util.Objects;

/**
 * Represents a transport line in the idf network
 */
public class Line {
    /** Name of the line */
    private String lname;
    /** Type of transport (bus, tram, metro, etc.)*/
    private String routetype;
    /** Color of the line */
    private String color;
    /** All the stops of the line */
    private Map<String, Stop> stops = new HashMap<>();
    /** Paths between stops*/
    private List<TravelPath> paths = new ArrayList<>();

    /**
     * Create a new line with the given name, type and color
     * @param name
     * @param routetype
     * @param color
     */
    protected Line(String name, String routetype, String color) {
        this.lname = name;
        this.routetype = routetype;
        this.color = color;
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

    @Override
    public String toString() {
        // Affiche l'ensemble des trajets de la ligne
        StringBuilder result = new StringBuilder(lname + ":\n");
        for (TravelPath path: paths) {
            result.append(path.getStart().getStopName()).append(" -> ").append(path.getEnd().getStopName()).append("\n");
        }
        return result.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(lname, routetype, color);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Line other = (Line) obj;

        return lname.equals(other.lname) && routetype.equals(other.routetype) && color.equals(other.color);
    }

    public String getLineName() {
        return lname;
    }

    public String getRouteType() {
        return routetype;
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
