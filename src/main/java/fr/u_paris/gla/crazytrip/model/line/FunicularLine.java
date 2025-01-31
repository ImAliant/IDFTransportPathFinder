package fr.u_paris.gla.crazytrip.model.line;

import java.util.Set;

import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.model.Station;

/**
 * A funicular type line
 */
public class FunicularLine extends Line {
    /**
     * Constructor
     * @param name the name of the line
     * @param stations the stations of the line
     * @param terminusStation the terminus station of the line
     * @param color the color of the line
     */
    public FunicularLine(String name, Set<Station> stations, Station terminusStation, String color) {
        super(name, stations, terminusStation, color);
    }

    @Override
    public RouteType getLineType() {
        return RouteType.FUNICULAR;
    }
}
