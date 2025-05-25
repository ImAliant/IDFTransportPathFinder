package com.diamant.idftransportpathfinder.model.line;

import java.util.Set;

import com.diamant.idftransportpathfinder.model.Line;
import com.diamant.idftransportpathfinder.model.Station;

/**
 * A bus type line
 */
public class BusLine extends Line{
    /**
     * Constructor
     * @param name the name of the line
     * @param stations the stations of the line
     * @param terminusStation the terminus station of the line
     * @param color the color of the line
     */
    public BusLine(String name, Set<Station> stations, Station terminusStation, String color) {
        super(name, stations, terminusStation, color);
    }

    @Override
    public RouteType getLineType() {
        return RouteType.BUS;
    }
}
