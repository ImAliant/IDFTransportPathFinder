package fr.u_paris.gla.crazytrip.idfnetwork.line;

import java.util.Objects;

public class TramwayLine extends Line {
    /** Magic number: average speed of a tramway line in km/h */
    private static final double AVERAGE_SPEED = 25.0;

    public TramwayLine(String name, String color) {
        super(name, color);
        this.speed = AVERAGE_SPEED;
    }
    /**
     * Returns the hash code value for this tramway line.
     * The hash code is calculated from the line's name, color, speed, stops, and paths.
     *
     * @return the hash code value for this tramway line
     */
    @Override
    public int hashCode() {
        return Objects.hash(lname, color, speed, stops, paths);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TramwayLine)) return false;
        TramwayLine busLine = (TramwayLine) o;
        return Double.compare(busLine.speed, speed) == 0 &&
                lname.equals(busLine.lname) &&
                color.equals(busLine.color) &&
                stops.equals(busLine.stops) &&
                paths.equals(busLine.paths);
    }
    /**
     * Returns a string representation of this tramway line, which includes the line's name.
     *
     * @return a string representation of this tramway line
     */
    @Override
    public String toString() {
        return "T " + lname;
    }

    @Override
    public LineType getType() {
        return LineType.TRAMWAY;
    }
}
