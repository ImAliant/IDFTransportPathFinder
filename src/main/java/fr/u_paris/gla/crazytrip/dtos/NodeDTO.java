package fr.u_paris.gla.crazytrip.dtos;

public class NodeDTO {
    private final String name;
    private final double latitude;
    private final double longitude;

    public NodeDTO(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NodeDTO nodeDTO = (NodeDTO) obj;
        // FOR NOW WE CONSIDER ONLY THE SUBWAY NETWORK
        return name.equalsIgnoreCase(nodeDTO.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "StationDTO{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
