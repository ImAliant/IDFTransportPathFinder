package fr.u_paris.gla.crazytrip.model;

import java.util.Objects;

import fr.u_paris.gla.crazytrip.model.key.LineKey;

/**
 * A transport segment represent a segment of a trip that is done using a transport line.
 * 
 * A transport segment has a start point, an end point, a distance, a duration and a line key.
 * 
 * @see Segment
 * @see LineKey
 */
public final class SegmentTransport extends Segment {
    /** The key of the line */
    private final LineKey lineKey;

    /**
     * Creates a new transport segment with the given start point, end point, distance, duration and line key.
     * 
     * @param n1 the start point of the segment
     * @param n2 the end point of the segment
     * @param distance the distance of the segment
     * @param duration the duration of the segment
     * @param lineKey the key of the line
     * @throws IllegalArgumentException if the line key is null or blank
     */
    public SegmentTransport(Node n1, Node n2, double distance, double duration, LineKey lineKey) {
        super(n1, n2, distance, duration);
        if (lineKey == null)
            throw new IllegalArgumentException("Line cannot be null or blank");
        this.lineKey = lineKey;
    }

    /**
     * Getter for the key of the line.
     * @return the key of the line
     */
    public LineKey getLineKey() {
        return lineKey;
    }

    @Override
    public String toString() {
        return String.format("SegmentTransport{%s, %s, %s, %s}", 
            startPoint, endPoint, distance, duration);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SegmentTransport segment = (SegmentTransport) o;
        return super.equals(o) && lineKey.equals(segment.lineKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lineKey);
    }
}
