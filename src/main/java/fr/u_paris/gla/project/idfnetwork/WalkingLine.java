package fr.u_paris.gla.project.idfnetwork;

import java.util.Objects;

public class WalkingLine extends Line {
    /** Magic number: average speed of a metro line in km/h */
    private static final double AVERAGE_SPEED = 5.0;

    public WalkingLine(String name, String color) {
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
        if (!(o instanceof MetroLine)) return false;
        WalkingLine walk = (WalkingLine) o;
        return Double.compare(walk.speed, speed) == 0 &&
                lname.equals(walk.lname) &&
                color.equals(walk.color) &&
                stops.equals(walk.stops) &&
                paths.equals(walk.paths);
    }

    @Override
    public String toString() {
        return "Marcher jusqu'à";
    }

    @Override
    public LineType getType() {
        return LineType.WALKING;
    }
}
