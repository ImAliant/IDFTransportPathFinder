package fr.u_paris.gla.project.idfnetwork.line;

import java.util.Objects;

public class MetroLine extends Line {
    /** Magic number: average speed of a metro line in km/h */
    private static final double AVERAGE_SPEED = 40.0;

    public MetroLine(String name, String color) {
        super(name, color);
        this.speed = AVERAGE_SPEED;
    }
    /**
     * Returns the hash code value for this metro line.
     * The hash code is calculated from the line's name, color, speed, stops, and paths.
     *
     * @return the hash code value for this metro line
     */
    @Override
    public int hashCode() {
        return Objects.hash(lname, color, speed, stops, paths);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MetroLine)) return false;
        MetroLine busLine = (MetroLine) o;
        return Double.compare(busLine.speed, speed) == 0 &&
                lname.equals(busLine.lname) &&
                color.equals(busLine.color) &&
                stops.equals(busLine.stops) &&
                paths.equals(busLine.paths);
    }

    /**
     * Returns a string representation of this metro line, which includes the line's name.
     *
     * @return a string representation of this metro line
     */
    @Override
    public String toString() {
        return "M " + lname;
    }

    @Override
    public LineType getType() {
        return LineType.METRO;
    }
}
