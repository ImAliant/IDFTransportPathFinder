package fr.u_paris.gla.crazytrip.idfnetwork.line.factory;

import fr.u_paris.gla.crazytrip.idfnetwork.line.BusLine;
import fr.u_paris.gla.crazytrip.idfnetwork.line.FunicularLine;
import fr.u_paris.gla.crazytrip.idfnetwork.line.Line;
import fr.u_paris.gla.crazytrip.idfnetwork.line.LineType;
import fr.u_paris.gla.crazytrip.idfnetwork.line.MetroLine;
import fr.u_paris.gla.crazytrip.idfnetwork.line.RERLine;
import fr.u_paris.gla.crazytrip.idfnetwork.line.TramwayLine;

/**
 * Factory class to create lines.
 * @author Diamant Alexandre
 */
public class LineFactory {
    private LineFactory() {}

    /**
     * Create a line of the given type.
     * 
     * @param type The type of the line.
     * @param name The name of the line.
     * @param color The color of the line.
     * @return The created line.
     */
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
            default:
                throw new IllegalArgumentException("Unknown line type: " + type);
        }
    }
}

