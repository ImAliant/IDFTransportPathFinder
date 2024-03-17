package fr.u_paris.gla.project.idfnetwork;

import java.util.Objects;

public class RERLine extends Line {
    /** Magic number: average speed of a RER line in km/h */
    private static final double AVERAGE_SPEED = 60.0;

    public RERLine(String name, String color) {
        super(name, color);
        this.speed = AVERAGE_SPEED;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lname, color, speed, stops, paths);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RERLine)) return false;
        RERLine busLine = (RERLine) o;
        return Double.compare(busLine.speed, speed) == 0 &&
                lname.equals(busLine.lname) &&
                color.equals(busLine.color) &&
                stops.equals(busLine.stops) &&
                paths.equals(busLine.paths);
    }

    @Override
    public LineType getType() {
        return LineType.RER;
    }
}
