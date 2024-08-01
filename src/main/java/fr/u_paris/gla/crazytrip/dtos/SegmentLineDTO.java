package fr.u_paris.gla.crazytrip.dtos;

import java.util.Objects;

public class SegmentLineDTO {
    private final NodeDTO start;
    private final NodeDTO end;
    private final double duration;
    private final double distance;
    private final String line;

    public SegmentLineDTO(NodeDTO start, NodeDTO end, double duration, double distance, String line) {
        this.start = start;
        this.end = end;
        this.duration = duration;
        this.distance = distance;
        this.line = line;
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

    public String getLine() {
        return line;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SegmentLineDTO that = (SegmentLineDTO) obj;
        return start.equals(that.start) && end.equals(that.end) && line.equals(that.line);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, line);
    }
}
