package fr.u_paris.gla.crazytrip.dtos;

import fr.u_paris.gla.crazytrip.model.Coordinates;

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

        final double DISTANCE_THRESHOLD = 0.3;

        return name.equalsIgnoreCase(nodeDTO.name)
            && routetype.equalsIgnoreCase(routetype)
            && distanceTo(nodeDTO) <= DISTANCE_THRESHOLD;
    }

    private double distanceTo(NodeDTO nodeDTO) {
        Coordinates current = new Coordinates(latitude, longitude);
        Coordinates other = new Coordinates(nodeDTO.latitude, nodeDTO.longitude);

        return current.distanceTo(other);
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
