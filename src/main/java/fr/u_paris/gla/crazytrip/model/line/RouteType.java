package fr.u_paris.gla.crazytrip.model.line;

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
