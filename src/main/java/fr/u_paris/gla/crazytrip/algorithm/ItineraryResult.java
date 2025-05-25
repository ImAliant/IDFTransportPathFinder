package fr.u_paris.gla.crazytrip.algorithm;

import java.util.List;

/**
 * This class represents the itinerary found by the algorithm.
 * 
 * @see Path
 */
public class ItineraryResult {
    /** The paths of the itinerary */
    private List<Path> paths;
    /* private double duration; */

    /**
     * Constructor of the class.
     * 
     * @param paths The paths of the itinerary
     * @param duration The duration of the itinerary
     * 
     * @see Path
     */
    public ItineraryResult(List<Path> paths/* , double duration */) {
        this.paths = paths;
        /* this.duration = duration / 60; */
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        /* sb.append("Duration: ").append(duration).append(" min\n"); */
        sb.append("Path:\n");
        for (Path path : paths) {
            sb.append(path).append("\n");
        }
        return sb.toString();
    }

    public List<Path> getPaths() {
        return paths;
    }
}
