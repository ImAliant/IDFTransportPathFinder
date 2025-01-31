package fr.u_paris.gla.crazytrip.model;

/**
 * A segment in the graph representing the network of stations.
 * 
 * A segment has a start point, an end point, a distance and a duration.
 * A segment can be a transport segment or a walk segment.
 * 
 * @see SegmentTransport
 * @see SegmentWalk
 */
public abstract sealed class Segment permits SegmentTransport, SegmentWalk {
    /** The start point of the segment */
    protected final Node startPoint;
    /** The end point of the segment */
    protected final Node endPoint;
    /** The duration of the segment */
    protected double duration;
    /** The distance of the segment */
    protected double distance;

    /**
     * Creates a new segment with the given start point, end point, distance and duration.
     * 
     * @param n1 the start point of the segment
     * @param n2 the end point of the segment
     * @param distance the distance of the segment
     * @param duration the duration of the segment
     * @throws IllegalArgumentException if the nodes are null or the distance or duration is negative
     */
    protected Segment(final Node n1, final Node n2, double distance, double duration) {
        if (n1 == null || n2 == null)
            throw new IllegalArgumentException("Nodes cannot be null");
        if (distance < 0 || duration < 0)
            throw new IllegalArgumentException("Distance and duration must be positive");
        this.startPoint = n1;
        this.endPoint = n2;
        this.distance = distance;
        this.duration = duration;
    }

    /**
     * Getter for the start point of the segment.
     * @return the start point of the segment
     */
    public Node getStartPoint() {
        return startPoint;
    }

    /**
     * Getter for the end point of the segment.
     * @return the end point of the segment
     */
    public Node getEndPoint() {
        return endPoint;
    }

    /**
     * Getter for the duration of the segment.
     * @return the duration of the segment
     */
    public double getDuration() {
        return duration;
    }

    /**
     * Getter for the distance of the segment.
     * @return the distance of the segment
     */
    public double getDistance() {
        return distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Segment segment = (Segment) o;
        return startPoint.equals(segment.startPoint) && endPoint.equals(segment.endPoint);
    }

    @Override
    public int hashCode() {
        return startPoint.hashCode() + endPoint.hashCode();
    }
}
