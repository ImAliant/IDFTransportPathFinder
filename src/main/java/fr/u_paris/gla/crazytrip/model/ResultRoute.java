package fr.u_paris.gla.crazytrip.model;

import java.awt.Color;

import fr.u_paris.gla.crazytrip.model.wrapper.RouteWrapper;

public class ResultRoute extends RouteWrapper<String>{
    private String lineName;

    public ResultRoute(String start, String end, Color color, String lineName) {
        super(start, end, color);
        this.lineName = lineName;
    }

    public String getLineName() {
        return lineName;
    }
}
