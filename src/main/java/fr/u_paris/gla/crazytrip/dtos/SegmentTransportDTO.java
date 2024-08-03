package fr.u_paris.gla.crazytrip.dtos;

import java.util.Objects;

public class SegmentTransportDTO {
    private final NodeDTO start;
    private final NodeDTO end;
    private final double duration;
    private final double distance;
    private final String line;

    private final String routetype;
    private final String color;

    public SegmentTransportDTO(NodeDTO start, NodeDTO end, double duration, double distance, String line, String routetype, String color) {
        this.start = start;
        this.end = end;
        this.duration = duration;
        this.distance = distance;
        this.line = line;
        this.routetype = routetype;
        this.color = color;
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

    public String getRouteType() {
        return routetype;
    }

    public String getColor() {
        return color;
    }

    public String getLine() {
        return line;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SegmentTransportDTO that = (SegmentTransportDTO) obj;
        return start.equals(that.start) && end.equals(that.end) && line.equals(that.line);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, line);
    }
}
