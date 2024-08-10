package fr.u_paris.gla.crazytrip.model;

public final class SegmentWalk extends Segment {
    private static final double SPEED = 4.4;
    private static final int HTOS = 3600;

    public SegmentWalk(Node n1, Node n2, double distance) {
        super(n1, n2, distance, (int) (distance * HTOS / SPEED));
    }

    public SegmentWalk(Node n1, Node n2) {
        this(n1, n2, n1.distanceTo(n2));
    }

    @Override
    public String toString() {
        return String.format("SegmentWalk{%s, %s, %s, %s}", 
            startPoint, endPoint, distance, duration);
    }
}
