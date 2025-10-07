package com.diamant.idftransportpathfinder.dtos;

import java.util.Set;

import com.diamant.idftransportpathfinder.model.Line;
import com.diamant.idftransportpathfinder.model.Station;
import com.diamant.idftransportpathfinder.model.line.RouteType;

public class LineDTO {
    private final Set<Station> stations;
    private final String name;
    private final String color;
    private final RouteType routeType;

    public LineDTO(Set<Station> stations, String name, String color, RouteType routeType) {
        this.stations = stations;
        this.name = name;
        this.color = color;
        this.routeType = routeType;
    }

    public LineDTO(Line line) {
        this.stations = line.getStations();
        this.name = line.getName();
        this.color = line.getColor();
        this.routeType = line.getLineType();
    }

    public Set<Station> getStations() {
        return stations;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public RouteType getRouteType() {
        return routeType;
    }
}
