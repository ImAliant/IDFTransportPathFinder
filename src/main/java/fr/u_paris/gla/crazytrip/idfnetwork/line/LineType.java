package fr.u_paris.gla.crazytrip.idfnetwork.line;

/**
 * Defines the types of lines available in a transportation network.
 * Each constant in this enum represents a different type of transportation line,
 * such as bus, tramway, RER (regional express train), metro, funicular, and walking paths.
 * <p>
 * This enumeration also provides methods to convert from a string or a specific {@link Line} instance
 * to a corresponding {@link LineType}.
 * </p>
 *
 * @author Diamant Alexandre
 */
public enum LineType {
    BUS, TRAMWAY, RER, METRO, FUNICULAIRE, WALKING;

    /**
     * Converts a string representation of a line type into its corresponding {@link LineType} enum constant.
     * 
     * @param type the string representation of the line type
     * @return the {@link LineType} enum constant that matches the input string
     * @throws IllegalArgumentException if the specified string does not match any known line type
     */
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
    /**
     * Determines the {@link LineType} of a given {@link Line} instance.
     *
     * @param line the {@link Line} instance whose type is to be determined
     * @return the {@link LineType} that corresponds to the given line
     * @throws IllegalArgumentException if the line does not match any known type
     */
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
