package fr.u_paris.gla.project.idfnetwork.line;

public enum LineType {
    BUS, TRAMWAY, RER, METRO, FUNICULAIRE, WALKING;

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

    public static LineType fromLine(Line line) {
        if (line instanceof BusLine) {
            return BUS;
        } else if (line instanceof TramwayLine) {
            return TRAMWAY;
        } else if (line instanceof RERLine) {
            return RER;
        } else if (line instanceof MetroLine) {
            return METRO;
        } else if (line instanceof FunicularLine) {
            return FUNICULAIRE;
        } else {
            throw new IllegalArgumentException("Unknown line type: " + line.getClass().getName());
        }
    }
}
