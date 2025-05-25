package com.diamant.idftransportpathfinder.model.line;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The type of a route
 */
public enum RouteType {
    RER, TER, BUS, TRAMWAY, METRO, FUNICULAR;
    
    @Override
    public String toString() {
        String res = "";
        switch (this) {
            case RER:
                res = "RER";
                break;
            case TER:
                res = "TER";
                break;
            case BUS:
                res = "Bus";
                break;
            case TRAMWAY:
                res = "Tram";
                break;
            case METRO:
                res = "Subway";
                break;
            case FUNICULAR:
                res = "Funicular";
                break;
        }

        return res;
    }

    /**
     * Get the RouteType from a string
     * 
     * @param s the string
     * @return the RouteType
     */
    public static RouteType fromString(String s) {
        switch (s) {
            case "Rail": return RER;
            case "TER": return TER;
            case "Bus": return BUS;
            case "Tram": return TRAMWAY;
            case "Subway": return METRO;
            case "Funicular": return FUNICULAR;
            default: throw new IllegalArgumentException("The string given is not a correct route type: " + s);
        }
    }

    @JsonValue
    public String toValue() {
        return toString();
    }
}
