package fr.u_paris.gla.crazytrip.idfnetwork.line;

import java.util.Objects;
/**
 * Represents a funicular line in a transportation network. This class extends {@link Line}
 * and specifies additional behaviors and properties for funicular lines.
 * <p>
 * A funicular line is defined by a name and a color, and it is characterized by a predefined
 * average speed of 10.0 km/h.
 * </p>
 *
 */
public class FunicularLine extends Line {
    private static final double AVERAGE_SPEED = 10.0;
    /**
     * Constructs a new FunicularLine with the specified name and color.
     *
     * @param name  the name of the funicular line
     * @param color the color associated with the funicular line
     */
    public FunicularLine(String name, String color) {
        super(name, color);
        this.speed = AVERAGE_SPEED;
    }
    /**
     * Returns the hash code value for this funicular line.
     * The hash code is calculated from the line's name, color, speed, stops, and paths.
     *
     * @return the hash code value for this funicular line
     */
    @Override
    public int hashCode() {
        return Objects.hash(lname, color, speed, stops, paths);
    }
    /**
     * Compares this funicular line with the specified object for equality.
     * The result is true if and only if the argument is not null and is a FunicularLine object that
     * has the same name, color, speed, stops, and paths as this object.
     *
     * @param o the object to be compared for equality with this funicular line
     * @return true if the specified object is equal to this funicular line, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FunicularLine)) return false;
        FunicularLine busLine = (FunicularLine) o;
        return Double.compare(busLine.speed, speed) == 0 &&
                lname.equals(busLine.lname) &&
                color.equals(busLine.color) &&
                stops.equals(busLine.stops) &&
                paths.equals(busLine.paths);
    }
    /**
     * Returns a string representation of this funicular line, which includes the line's name.
     *
     * @return a string representation of this funicular line
     */
    @Override
    public String toString() {
        return "F " + lname;
    }
    /**
     * Returns the type of this line as {@link LineType#FUNICULAIRE}.
     *
     * @return the type of this line
     */
    @Override
    public LineType getType() {
        return LineType.FUNICULAIRE;
    }
}
