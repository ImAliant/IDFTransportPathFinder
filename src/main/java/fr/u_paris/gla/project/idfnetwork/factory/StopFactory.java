package fr.u_paris.gla.project.idfnetwork.factory;

import fr.u_paris.gla.project.idfnetwork.stop.*;
import fr.u_paris.gla.project.idfnetwork.LineType;

public class StopFactory {
    private StopFactory() {}

    public static Stop createStop(LineType type, String lname, double longitude, double latitude) {
        Stop stop;
        switch (type) {
            case BUS:
                stop = new BusStop(lname, longitude, latitude);
                break;
            case RER:
                stop = new RERStop(lname, longitude, latitude);
                break;
            case TRAMWAY:
                stop = new TramwayStop(lname, longitude, latitude);
                break;
            case METRO:
                stop = new MetroStop(lname, longitude, latitude);
                break;
            case FUNICULAIRE:
                stop = new FunicularStop(lname, longitude, latitude);
                break;
            default:
                throw new IllegalArgumentException("Unknown line type: " + type);
        }
        return stop;
    }
}