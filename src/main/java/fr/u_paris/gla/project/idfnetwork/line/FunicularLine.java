package fr.u_paris.gla.project.idfnetwork.line;

import java.util.Objects;

public class FunicularLine extends Line {
    private static final double AVERAGE_SPEED = 10.0;

    public FunicularLine(String name, String color) {
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
        if (!(o instanceof FunicularLine)) return false;
        FunicularLine busLine = (FunicularLine) o;
        return Double.compare(busLine.speed, speed) == 0 &&
                lname.equals(busLine.lname) &&
                color.equals(busLine.color) &&
                stops.equals(busLine.stops) &&
                paths.equals(busLine.paths);
    }

    @Override
    public String toString() {
        return "F " + lname;
    }

    @Override
    public LineType getType() {
        return LineType.FUNICULAIRE;
    }
}
