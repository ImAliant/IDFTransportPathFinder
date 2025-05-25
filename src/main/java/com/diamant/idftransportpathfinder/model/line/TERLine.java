package com.diamant.idftransportpathfinder.model.line;

import java.util.Set;

import com.diamant.idftransportpathfinder.model.Line;
import com.diamant.idftransportpathfinder.model.Station;

/**
 * A TER (Transport Express Régional) line
 * Represents a regional train line in the Île-de-France region.
 */
public class TERLine extends Line {
    /**
     * Constructor
     * @param name the name of the line
     * @param stations the stations of the line
     * @param terminusStation the terminus station of the line
     * @param color the color of the line
     */
    public TERLine(String name, Set<Station> stations, Station terminusStation, String color) {
        super(name, stations, terminusStation, color);
    }

    @Override
    public RouteType getLineType() {
        return RouteType.TER;
    }
}
