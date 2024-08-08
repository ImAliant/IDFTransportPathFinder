package fr.u_paris.gla.crazytrip.model;

import java.util.Objects;

import fr.u_paris.gla.crazytrip.model.key.LineKey;

public final class SegmentTransport extends Segment {
    private final LineKey lineKey;

    public SegmentTransport(Node n1, Node n2, double distance, double duration, LineKey lineKey) {
        super(n1, n2, distance, duration);
        if (lineKey == null)
            throw new IllegalArgumentException("Line cannot be null or blank");
        this.lineKey = lineKey;
    }

    public LineKey getLineKey() {
        return lineKey;
    }

    @Override
    public String toString() {
        return String.format("SegmentLine{%s, %s, %s, %s}", 
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
