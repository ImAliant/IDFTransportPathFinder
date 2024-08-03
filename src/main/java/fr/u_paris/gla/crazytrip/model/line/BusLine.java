package fr.u_paris.gla.crazytrip.model.line;

import java.util.Set;

import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.model.Station;

public class BusLine extends Line{
    public BusLine(String name, Set<Station> stations, Station terminusStation, String color) {
        super(name, stations, terminusStation, color);
    }

    @Override
    public String getLineType() {
        return "Bus";
    }
}
