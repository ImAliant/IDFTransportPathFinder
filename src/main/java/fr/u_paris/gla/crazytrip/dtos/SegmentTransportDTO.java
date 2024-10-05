package fr.u_paris.gla.crazytrip.dtos;

import java.util.Objects;

import fr.u_paris.gla.crazytrip.model.key.LineKey;

/**
 * This class represents a segment transport data transfer object.
 * 
 * It is used in the parser to store the data of a segment transport.
 * 
 * @see NodeDTO
 * @see LineKey
 */
public class SegmentTransportDTO {
    /** The start node of the segment */
    private final NodeDTO start;
    /** The end node of the segment */
    private final NodeDTO end;
    /** The duration of the segment */
    private final double duration;
    /** The distance of the segment */
    private final double distance;
    /** The line key of the segment */
    private final LineKey linekey;

    /**
     * Constructor of the segment transport data transfer object.
     * 
     * @param start The start node of the segment.
     * @param end The end node of the segment.
     * @param duration The duration of the segment.
     * @param distance The distance of the segment.
     * @param linekey The line key of the segment.
     * 
     * @see NodeDTO
     * @see LineKey
     */
    public SegmentTransportDTO(NodeDTO start, NodeDTO end, double duration, double distance, LineKey linekey) {
        this.start = start;
        this.end = end;
        this.duration = duration;
        this.distance = distance;
        this.linekey = linekey;
    }

    public NodeDTO getStart() {
        return start;
    }

    public NodeDTO getEnd() {
        return end;
    }

    public double getDuration() {
        return duration;
    }

    public double getDistance() {
        return distance;
    }

    public LineKey getLineKey() {
        return linekey;
    }

    @Override
    public String toString() {
        return String.format("SegmentTransportDTO{start=%s, end=%s, duration=%f, distance=%f, line=%s}", start, end, duration, distance, linekey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SegmentTransportDTO that = (SegmentTransportDTO) obj;
        return start.equals(that.start) && end.equals(that.end) && linekey.equals(that.linekey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, linekey);
    }
}
