package fr.u_paris.gla.crazytrip.model.line;

import java.util.Set;

import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.model.Station;

public class TramwayLine extends Line {
    public TramwayLine(String name, Set<Station> stations, Station terminusStation, String color) {
        super(name, stations, terminusStation, color);
    }

    @Override
    public RouteType getLineType() {
        return RouteType.TRAMWAY;
    }
}
