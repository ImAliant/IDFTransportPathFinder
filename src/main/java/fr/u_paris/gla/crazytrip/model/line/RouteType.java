package fr.u_paris.gla.crazytrip.model.line;

/**
 * The type of a route
 */
public enum RouteType {
    RAIL, BUS, TRAMWAY, METRO, FUNICULAR;
    
    @Override
    public String toString() {
        String res = "";
        switch (this) {
            case RAIL:
                res = "Rail";
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
            case "Rail": return RAIL;
            case "Bus": return BUS;
            case "Tram": return TRAMWAY;
            case "Subway": return METRO;
            case "Funicular": return FUNICULAR;
            default: throw new IllegalArgumentException("The string given is not a correct route type");
        }
    }
}
