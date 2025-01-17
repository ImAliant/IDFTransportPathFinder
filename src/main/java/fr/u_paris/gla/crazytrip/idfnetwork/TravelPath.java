package fr.u_paris.gla.crazytrip.idfnetwork;

import fr.u_paris.gla.crazytrip.idfnetwork.line.Line;

/**
 * Represents a path between two stops
 */
public class TravelPath {
    /** The start of the path */
    private Stop start;
    /** The end of the path */
    private Stop end;
    /** The distance of the path */
    private double distance;
    /** The duration of the path */
    private double duration;

    private Line line;

    /**
     * Create a new path with the given start, end, distance and duration
     * @param start
     * @param end
     * @param distance
     * @param duration
     */
    public TravelPath(Stop start, Stop end, double distance, Integer duration, Line line) {
        this.start = start;
        this.end = end;
        this.distance = distance;
        this.duration = duration;
        this.line = line;
    }

    public Stop getStart() {
        return start;
    }

    public Stop getEnd() {
        return end;
    }

    public double getDistance() {
        return distance;
    }

    public double getDuration() {
        return duration;
    }

    public Line getLine() {
        return line;
    }
}
