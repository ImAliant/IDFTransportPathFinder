package fr.u_paris.gla.crazytrip.model.key;

import java.util.Objects;

import fr.u_paris.gla.crazytrip.model.line.RouteType;

/**
 * Key class to identify a line
 * 
 * This class is used to identify a line in a map. It is used as a key in a map
 * to identify a line. It contains the name of the line, the type of the line
 * and the color of the line.
 * 
 * @see RouteType
 */
public class LineKey {
    /** The name of the line */
    private final String name;
    /** The type of the line */
    private final RouteType routetype;
    /** The color of the line */
    private final String color;

    /**
     * Create a line key
     * 
     * @param name the name of the line
     * @param routetype the type of the line
     * @param color the color of the line
     */
    public LineKey(String name, RouteType routetype, String color) {
        this.name = name;
        this.routetype = routetype;
        this.color = color;
    }

    /**
     * Getter for the name of the line
     * @return the name of the line
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the type of the line
     * @return the type of the line
     */
    public RouteType getRouteType() {
        return routetype;
    }

    /**
     * Getter for the color of the line
     * @return the color of the line
     */
    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LineKey lineKey = (LineKey) obj;

        return name.equalsIgnoreCase(lineKey.name)
            && routetype.equals(lineKey.routetype)
            && color.equalsIgnoreCase(lineKey.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, routetype, color);
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, routetype);
    }
}
