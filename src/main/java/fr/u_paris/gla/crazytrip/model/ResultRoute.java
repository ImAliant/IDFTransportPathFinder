package fr.u_paris.gla.crazytrip.model;

import java.awt.Color;

import fr.u_paris.gla.crazytrip.model.wrapper.RouteWrapper;

/**
 * A route between two stations with a line name.
 * 
 * A result route has a start station, an end station, a color and a line name.
 * 
 * @see RouteWrapper
 */
public class ResultRoute extends RouteWrapper<String>{
    /** The name of the line */
    private String lineName;

    /**
     * Creates a new result route with the given start station, end station, color and line name.
     * 
     * @param start the name of the start station
     * @param end the name of the end station
     * @param color the color of the route
     * @param lineName the name of the line
     */
    public ResultRoute(String start, String end, Color color, String lineName) {
        super(start, end, color);
        this.lineName = lineName;
    }

    /**
     * Getter for the name of the line.
     * @return the name of the line
     */
    public String getLineName() {
        return lineName;
    }
}
