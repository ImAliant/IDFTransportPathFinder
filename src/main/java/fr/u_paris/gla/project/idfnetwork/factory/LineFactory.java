package fr.u_paris.gla.project.idfnetwork.factory;

import fr.u_paris.gla.project.idfnetwork.*;

public class LineFactory {
    private LineFactory() {}

    public static Line createLine(LineType type, String name, String color) {
        switch (type) {
            case BUS:
                return new BusLine(name, color);
            case TRAMWAY:
                return new TramwayLine(name, color);
            case RER:
                return new RERLine(name, color);
            case METRO:
                return new MetroLine(name, color);
            case FUNICULAIRE:
                return new FunicularLine(name, color);
            case WALKING:
                return new WalkingLine(name, color);
            default:
                throw new IllegalArgumentException("Unknown line type: " + type);
        }
    }
}

