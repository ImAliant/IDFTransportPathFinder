package fr.u_paris.gla.crazytrip.model;

/**
 * A walk segment represent a segment of a trip that is done by walking betweeen two nodes.
 * 
 * A walk segment has a start point, an end point, a distance and a duration.
 * 
 * @see Segment
 * @see Node
 */
public final class SegmentWalk extends Segment {
    /** The speed of walking in km/h */
    private static final double SPEED = 4.4;
    /** The time to go from hours to seconds */
    private static final int HTOS = 3600;

    /**
     * Creates a new walk segment with the given start point, end point and distance.
     * 
     * Calculates the duration of the segment based on the distance and the speed of walking.
     * 
     * @param n1 the start point of the segment
     * @param n2 the end point of the segment
     * @param distance the distance of the segment
     */
    public SegmentWalk(Node n1, Node n2, double distance) {
        super(n1, n2, distance, (int) (distance * HTOS / SPEED));
    }

    /**
     * Creates a new walk segment with the given start point and end point.
     * 
     * Calculates the distance of the segment based on the distance between the two nodes.
     * Calculates the duration of the segment based on the distance and the speed of walking.
     * 
     * @param n1 the start point of the segment
     * @param n2 the end point of the segment
     */
    public SegmentWalk(Node n1, Node n2) {
        this(n1, n2, n1.distanceTo(n2));
    }

    @Override
    public String toString() {
        return String.format("SegmentWalk{%s, %s, %s, %s}", 
            startPoint, endPoint, distance, duration);
    }
}
