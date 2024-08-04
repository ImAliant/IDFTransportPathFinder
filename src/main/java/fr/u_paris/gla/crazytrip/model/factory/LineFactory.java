package fr.u_paris.gla.crazytrip.model.factory;

import java.util.Set;

import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.model.Station;
import fr.u_paris.gla.crazytrip.model.line.BusLine;
import fr.u_paris.gla.crazytrip.model.line.FunicularLine;
import fr.u_paris.gla.crazytrip.model.line.MetroLine;
import fr.u_paris.gla.crazytrip.model.line.RailwayLine;
import fr.u_paris.gla.crazytrip.model.line.RouteType;
import fr.u_paris.gla.crazytrip.model.line.TramwayLine;

public class LineFactory {
    private LineFactory() {}

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
