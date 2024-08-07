package fr.u_paris.gla.crazytrip.model;

import java.util.Objects;
import java.util.Set;

import fr.u_paris.gla.crazytrip.model.line.RouteType;

public abstract class Line {
    private final Set<Station> stations;
    private final String name;
    private final Station terminusStation;
    private final String color;

    protected Line(String name, Set<Station> stations, Station terminusStation, String color) {
        this.name = name;
        this.stations = Set.copyOf(stations);
        this.terminusStation = terminusStation;
        this.color = color;
    }

    public Station getStationByName(String name) {
        for (Station station: stations) {
            if (station.getName().equals(name)) return station;
        }
        return null;
    }

    public Set<Station> getStations() {
        return stations;
    }

    public String getName() {
        return name;
    }

    public Station getTerminusStation() {
        return terminusStation;
    }

    public abstract RouteType getLineType();

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
