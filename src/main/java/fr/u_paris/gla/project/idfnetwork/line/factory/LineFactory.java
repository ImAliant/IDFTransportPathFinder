package fr.u_paris.gla.project.idfnetwork.line.factory;

import fr.u_paris.gla.project.idfnetwork.line.BusLine;
import fr.u_paris.gla.project.idfnetwork.line.FunicularLine;
import fr.u_paris.gla.project.idfnetwork.line.Line;
import fr.u_paris.gla.project.idfnetwork.line.LineType;
import fr.u_paris.gla.project.idfnetwork.line.MetroLine;
import fr.u_paris.gla.project.idfnetwork.line.RERLine;
import fr.u_paris.gla.project.idfnetwork.line.TramwayLine;

/**
 * Factory class to create lines.
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

