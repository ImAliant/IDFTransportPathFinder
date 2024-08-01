package fr.u_paris.gla.crazytrip.model;

import java.util.Objects;

public final class SegmentLine extends Segment {
    private final String line;

    public SegmentLine(Node n1, Node n2, double distance, double duration, String line) {
        super(n1, n2, distance, duration);
        if (line == null || line.isBlank())
            throw new IllegalArgumentException("Line cannot be null or blank");
        this.line = line;
    }

    public String getLine() {
        return line;
    }

    @Override
    public String toString() {
        return String.format("SegmentLine{%s, %s, %s, %s, %s}", 
            startPoint, endPoint, distance, duration, line);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SegmentLine segment = (SegmentLine) o;
        return line.equals(segment.line);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), line);
    }
}
