package fr.u_paris.gla.crazytrip.model;

import java.util.Objects;
import java.util.Set;

import fr.u_paris.gla.crazytrip.model.line.RouteType;

/**
 * An abstract class representing a line
 * 
 * <p>A line is a set of stations with a name, a terminus station and a color.</p>
 * <p>A line can be of different types (bus, metro, funicular, railway).</p>
 * 
 * @see Station
 */
public abstract class Line {
    /** The stations of the line */
    private final Set<Station> stations;
    /** The name of the line */
    private final String name;
    /** The terminus station of the line */
    private final Station terminusStation;
    /** The color of the line */
    private final String color;

    /**
     * Constructor
     * @param name the name of the line
     * @param stations the stations of the line
     * @param terminusStation the terminus station of the line
     * @param color the color of the line
     */
    protected Line(String name, Set<Station> stations, Station terminusStation, String color) {
        this.name = name;
        this.stations = Set.copyOf(stations);
        this.terminusStation = terminusStation;
        this.color = color;
    }

    /**
     * Get a station by its name
     * @param name the name of the station
     * @return the station with the given name, or null if not found
     */
    public Station getStationByName(String name) {
        for (Station station: stations) {
            if (station.getName().equals(name)) return station;
        }
        return null;
    }

    /**
     * Get the stations of the line
     * @return the stations of the line
     */
    public Set<Station> getStations() {
        return stations;
    }

    /**
     * Get the name of the line
     * @return the name of the line
     */
    public String getName() {
        return name;
    }

    /**
     * Get the terminus station of the line
     * @return the terminus station of the line
     */
    public Station getTerminusStation() {
        return terminusStation;
    }

    /**
     * Get the type of the line
     * @return the type of the line
     */
    public abstract RouteType getLineType();

    /**
     * Get the color of the line
     * @return the color of the line
     */
    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return String.format("Line{%s, %s, %s}", name, terminusStation.getName(), getLineType());
    }

    public void printLine() {
        System.out.println("Line " + name + " :");
        for (Station station : stations) {
            System.out.println(station.getName());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return name.equals(line.name) && terminusStation.equals(line.terminusStation) && color.equals(line.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, terminusStation, color);
    }
}
