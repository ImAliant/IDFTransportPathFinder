package fr.u_paris.gla.project.idfnetwork;

public enum LineType {
    BUS, TRAMWAY, RER, METRO, FUNICULAIRE;

    public static LineType fromString(String type) {
        switch (type) {
            case "Bus":
                return BUS;
            case "Tram":
                return TRAMWAY;
            case "Rail":
                return RER;
            case "Subway":
                return METRO;
            case "Funicular":
                return FUNICULAIRE;
            default:
                throw new IllegalArgumentException("Unknown line type: " + type);
        }
    }
}
