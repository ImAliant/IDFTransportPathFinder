package com.diamant.idftransportpathfinder.model.factory;

import java.util.Set;

import com.diamant.idftransportpathfinder.model.Line;
import com.diamant.idftransportpathfinder.model.Station;
import com.diamant.idftransportpathfinder.model.line.BusLine;
import com.diamant.idftransportpathfinder.model.line.FunicularLine;
import com.diamant.idftransportpathfinder.model.line.MetroLine;
import com.diamant.idftransportpathfinder.model.line.RailwayLine;
import com.diamant.idftransportpathfinder.model.line.RouteType;
import com.diamant.idftransportpathfinder.model.line.TramwayLine;

/**
 * Factory class to create lines
 */
public class LineFactory {
    /** Prevent instantiation */
    private LineFactory() {}

    /**
     * Create a line
     * @param name the name of the line
     * @param stations the stations of the line
     * @param terminus the terminus of the line
     * @param routetype the type of the line
     * @param color the color of the line
     * @return the created line
     */
    public static Line createLine(String name, Set<Station> stations, Station terminus, RouteType routetype, String color) {
        Line line = null;

        switch (routetype) {
            case METRO:
                line = new MetroLine(name, stations, terminus, color);
                break;
            case BUS:
                line = new BusLine(name, stations, terminus, color);
                break;
            case RAIL:
                line = new RailwayLine(name, stations, terminus, color);
                break;
            case FUNICULAR:
                line = new FunicularLine(name, stations, terminus, color);
                break;
            case TRAMWAY:
                line = new TramwayLine(name, stations, terminus, color);
                break;
            default:
                throw new IllegalArgumentException("This route type doesn't exist");
        }

        return line;
    } 
}
