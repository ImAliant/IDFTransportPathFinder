package fr.u_paris.gla.project.idfnetwork;

import java.util.Objects;

public class TramwayLine extends Line {
    /** Magic number: average speed of a tramway line in km/h */
    private static final double AVERAGE_SPEED = 25.0;

    public TramwayLine(String name, String color) {
        super(name, color);
        this.speed = AVERAGE_SPEED;
    }

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

    @Override
    public String toString() {
        return "TRAMWAY " + lname;
    }

    @Override
    public LineType getType() {
        return LineType.TRAMWAY;
    }
}
