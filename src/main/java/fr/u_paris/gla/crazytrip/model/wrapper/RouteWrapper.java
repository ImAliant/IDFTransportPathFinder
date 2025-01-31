package fr.u_paris.gla.crazytrip.model.wrapper;

import java.awt.Color;

/**
 * A wrapper for a route
 * 
 * @param <T> the type of the route
 */
public abstract class RouteWrapper<T> {
    /** The start of the route */
    private T start;
    /** The end of the route */
    private T end;
    /** The color of the route */
    private Color color;
    
    /**
     * Constructor
     * @param start the start of the route
     * @param end the end of the route
     * @param color the color of the route
     */
    protected RouteWrapper(T start, T end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }

    /**
     * Getter for the start of the route
     * @return the start of the route
     */
    public T getStart() {
        return start;
    }

    /**
     * Getter for the end of the route
     * @return the end of the route
     */
    public T getEnd() {
        return end;
    }

    /**
     * Getter for the color of the route
     * @return the color of the route
     */
    public Color getColor() {
        return color;
    }
}
