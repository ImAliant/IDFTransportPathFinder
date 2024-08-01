package fr.u_paris.gla.crazytrip.model;

public abstract sealed class Segment permits SegmentLine, SegmentWalk {
    protected final Node startPoint;
    protected final Node endPoint;
    protected double duration;
    protected double distance;

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

    public Node getStartPoint() {
        return startPoint;
    }

    public Node getEndPoint() {
        return endPoint;
    }

    public double getDuration() {
        return duration;
    }

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
