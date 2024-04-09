package fr.u_paris.gla.project.idfnetwork;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

/**
 * Represents a stop in the idf network
 */
public class Stop {
    /** Name of the stop */
    private String lname;
    /** Longitude of the stop */
    private double longitude;
    /** Latitude of the stop */
    private double latitude;

    /** All the paths from this stop to another stop */
    private Map<String, TravelPath> paths = new HashMap<>();
    /** All the lines that pass through this stop */
    private Map<String, Line> lines = new HashMap<>();

    /**
     * Create a new stop with the given name, longitude and latitude
     * @param lname
     * @param longitude
     * @param latitude
     */
    protected Stop(String lname, double longitude, double latitude) {
        this.lname = lname;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    /**
     * Add a path to the stop if it is not already in the stop
     * @param end
     * @param distance
     * @param duration
     * @return
     */
    protected boolean addPath(Stop end, TravelPath path) {
        String key = generatePathKey(this, end);
        return paths.putIfAbsent(key, path) == null;
    }
    /**
     * Add a line to the stop if it is not already in the stop
     * @param line
     * @return
     */
    protected boolean addLine(Line line) {
        String key = generateLineKey(line.getLineName(), line.getType());
        return lines.putIfAbsent(key, line) == null;
    }
    /**
     * Generate a key for a path
     * @param start
     * @param end
     * @param distance
     * @param duration
     * @return the key
     */
    private String generatePathKey(Stop start, Stop end/*, double distance, Integer duration*/) {
        return start.getStopName() + "-" + end.getStopName()/* + "-" + distance + "-" + duration*/;
    }
    /**
     * Generate a key for a line
     * @param lname
     * @param routetype
     * @return the key
     */
    private String generateLineKey(String lname, LineType type) {
        return lname + "-" + type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.lname);
        sb.append(" [" + this.longitude + ", " + this.latitude + "]\n");
        sb.append("Ligne(s): \n");
        lines.forEach((k, line) -> sb.append(line + "\n"));
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, lname, longitude);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stop)) return false;
        Stop stop = (Stop) o;
        return Double.compare(stop.getLongitude(), getLongitude()) == 0
                && Double.compare(stop.getLatitude(), getLatitude()) == 0
                && Objects.equals(getStopName(), stop.getStopName());
    }

    public String getStopName() {
        return lname;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public List<TravelPath> getPaths() {
        return Collections.unmodifiableList(new ArrayList<>(paths.values()));
    }

    public List<Line> getLines() {
        return Collections.unmodifiableList(new ArrayList<>(lines.values()));
    }
}
