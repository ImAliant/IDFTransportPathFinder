package fr.u_paris.gla.crazytrip.model;

import java.util.Set;

public final class Line {
    private final Set<Station> stations;
    private final String name;
    private final Station terminusStation;

    public Line(String name, Set<Station> stations, Station terminusStation) {
        this.name = name;
        this.stations = Set.copyOf(stations);
        this.terminusStation = terminusStation;
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

    @Override
    public String toString() {
        return String.format("Line{%s, %s}", name, terminusStation);
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
        return name.equals(line.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
