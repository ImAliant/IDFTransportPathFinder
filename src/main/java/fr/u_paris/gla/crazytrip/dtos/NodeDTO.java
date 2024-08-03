package fr.u_paris.gla.crazytrip.dtos;

public class NodeDTO {
    private final String name;
    private final double latitude;
    private final double longitude;
    private final String routetype;

    public NodeDTO(String name, double latitude, double longitude, String routetype) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.routetype = routetype;
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

    public String getRouteType() {
        return routetype;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NodeDTO nodeDTO = (NodeDTO) obj;

        return name.equalsIgnoreCase(nodeDTO.name) && routetype.equalsIgnoreCase(routetype);
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
