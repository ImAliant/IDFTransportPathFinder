package fr.u_paris.gla.crazytrip.model;

import java.awt.Color;

import org.jxmapviewer.viewer.GeoPosition;

import fr.u_paris.gla.crazytrip.model.wrapper.RouteWrapper;

public class MapsRoute extends RouteWrapper<GeoPosition>{
    public MapsRoute(GeoPosition start, GeoPosition end, Color color) {
        super(start, end, color);
    }
}
