package fr.u_paris.gla.crazytrip.model;

import fr.u_paris.gla.crazytrip.model.key.LineKey;

/**
 * A station is a node where a transport line stops.
 * 
 * A station has a name, coordinates and a line key.
 * 
 * @see Node
 * @see LineKey
 */
public final class Station extends Node{
    /** The key of the line */
    private final LineKey linekey;

    /**
     * Creates a new station with the given name, latitude, longitude and line key.
     * 
     * @param name the name of the station
     * @param latitude the latitude of the station
     * @param longitude the longitude of the station
     * @param linekey the key of the line
     * @throws IllegalArgumentException if the line key is null or blank
     */
    public Station(String name, double latitude, double longitude, LineKey linekey) {
        super(name, latitude, longitude);
        this.linekey = linekey;
    }

    /**
     * Getter for the key of the line.
     * @return the key of the line
     */
    public LineKey getLineKey() {
        return linekey;
    }

    @Override
    public String toString() {
        return getName() + " " + String.format("%s %s", linekey.getName(), linekey.getRouteType());
    }

    /**
     * Returns a string representation of the station for the toolkit.
     * @return a string representation of the station for the toolkit
     */
    public String toolkitToString() {
        StringBuilder sb = new StringBuilder("<html>");
        sb.append(getName()).append("<br>");
        sb.append("Line: ").append(linekey.getName()).append(" ").append(linekey.getRouteType());
        sb.append("</html>");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station node = (Station) o;

        return super.equals(o) && linekey.equals(node.linekey);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + linekey.hashCode();
    }
}
