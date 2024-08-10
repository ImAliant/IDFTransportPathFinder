package fr.u_paris.gla.crazytrip.dtos;

import java.util.Objects;

import fr.u_paris.gla.crazytrip.model.key.LineKey;

public class SegmentTransportDTO {
    private final NodeDTO start;
    private final NodeDTO end;
    private final double duration;
    private final double distance;
    
    private final LineKey linekey;

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
