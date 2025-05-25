package com.diamant.idftransportpathfinder.model.line;

import java.util.Set;

import com.diamant.idftransportpathfinder.model.Line;
import com.diamant.idftransportpathfinder.model.Station;

/**
 * A RER (Réseau Express Régional) line
 * Represents a regional express train line in the Île-de-France region.
 */
public class RERLine extends Line {
    /**
     * Constructor
     * @param name the name of the line
     * @param stations the stations of the line
     * @param terminusStation the terminus station of the line
     * @param color the color of the line
     */
    public RERLine(String name, Set<Station> stations, Station terminusStation, String color) {
        super(name, stations, terminusStation, color);
    }

    @Override
    public RouteType getLineType() {
        return RouteType.RER;
    }
}
