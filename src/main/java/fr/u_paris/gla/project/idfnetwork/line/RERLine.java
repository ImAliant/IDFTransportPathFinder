package fr.u_paris.gla.project.idfnetwork.line;

import java.util.Objects;

public class RERLine extends Line {
    /** Magic number: average speed of a RER line in km/h */
    private static final double AVERAGE_SPEED = 60.0;

    public RERLine(String name, String color) {
        super(name, color);
        this.speed = AVERAGE_SPEED;
    }
    /**
     * Returns the hash code value for this rer line.
     * The hash code is calculated from the line's name, color, speed, stops, and paths.
     *
     * @return the hash code value for this rer line
     */
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
    /**
     * Returns a string representation of this rer line, which includes the line's name.
     *
     * @return a string representation of this rer line
     */
    @Override
    public String toString() {
        return "R " + lname;
    }

    @Override
    public LineType getType() {
        return LineType.RER;
    }
}
