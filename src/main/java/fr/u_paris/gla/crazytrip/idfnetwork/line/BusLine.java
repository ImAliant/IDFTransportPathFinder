package fr.u_paris.gla.crazytrip.idfnetwork.line;

import java.util.Objects;
/**
 * Represents a bus line in a transportation network. This class extends {@link Line}
 * and specifies additional behaviors and properties for bus lines.
 * <p>
 * A bus line is defined by a name and a color, and it is characterized by a predefined
 * average speed of 20.0 km/h.
 * </p>
 *
 * @author Diamant A lexandre
 */
public class BusLine extends Line {
    /** Magic number: average speed of a bus line in km/h */
    private static final double AVERAGE_SPEED = 20.0;
     /**
     * Constructs a new BusLine with the specified name and color.
     *
     * @param name  the name of the bus line
     * @param color the color associated with the bus line
     */
    public BusLine(String name, String color) {
        super(name, color);
        this.speed = AVERAGE_SPEED;
    }
    /**
     * Returns the hash code value for this bus line.
     * The hash code is calculated from the line's name, color, speed, stops, and paths.
     *
     * @return the hash code value for this bus line
     */
    @Override
    public int hashCode() {
        return Objects.hash(lname, color, speed, stops, paths);
    }
    /**
     * Compares this bus line with the specified object for equality.
     * The result is true if and only if the argument is not null and is a BusLine object that
     * has the same name, color, speed, stops, and paths as this object.
     *
     * @param o the object to be compared for equality with this bus line
     * @return true if the specified object is equal to this bus line, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusLine)) return false;
        BusLine busLine = (BusLine) o;
        return Double.compare(busLine.speed, speed) == 0 &&
                lname.equals(busLine.lname) &&
                color.equals(busLine.color) &&
                stops.equals(busLine.stops) &&
                paths.equals(busLine.paths);
    }
     /**
     * Returns a string representation of this bus line, which includes the line's name.
     *
     * @return a string representation of this bus line
     */
    @Override
    public String toString() {
        return "B " + lname;
    }
    /**
     * Returns the type of this line as {@link LineType#BUS}.
     *
     * @return the type of this line
     */
    @Override
    public LineType getType() {
        return LineType.BUS;
    }
}
