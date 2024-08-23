package fr.u_paris.gla.crazytrip.model;

import java.awt.Color;

import org.jxmapviewer.viewer.GeoPosition;

public class Route {
    private GeoPosition start;
    private GeoPosition end;
    private Color color;

    public Route(GeoPosition start, GeoPosition end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }

    public GeoPosition getStart() {
        return start;
    }

    public GeoPosition getEnd() {
        return end;
    }

    public Color getColor() {
        return color;
    }
}
