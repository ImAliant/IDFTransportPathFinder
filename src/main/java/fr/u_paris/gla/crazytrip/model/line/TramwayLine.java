package fr.u_paris.gla.crazytrip.model.line;

import java.util.Set;

import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.model.Station;

/**
 * A tramway type line
 */
public class TramwayLine extends Line {
    /**
     * Constructor
     * @param name the name of the line
     * @param stations the stations of the line
     * @param terminusStation the terminus station of the line
     * @param color the color of the line
     */
    public TramwayLine(String name, Set<Station> stations, Station terminusStation, String color) {
        super(name, stations, terminusStation, color);
    }

    @Override
    public RouteType getLineType() {
        return RouteType.TRAMWAY;
    }
}
