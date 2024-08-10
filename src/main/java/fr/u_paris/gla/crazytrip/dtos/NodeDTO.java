package fr.u_paris.gla.crazytrip.dtos;

import java.util.Objects;

import fr.u_paris.gla.crazytrip.model.key.LineKey;
import fr.u_paris.gla.crazytrip.model.key.NodeKey;

public class NodeDTO {
    private final String name;
    private final double latitude;
    private final double longitude;
    private final LineKey lineKey;

    public NodeDTO(String name, double latitude, double longitude, LineKey lineKey) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.lineKey = lineKey;
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

    public LineKey getLineKey() {
        return lineKey;
    }

    public NodeKey generateKey() {
        return new NodeKey(name, lineKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NodeDTO nodeDTO = (NodeDTO) obj;

        return name.equalsIgnoreCase(nodeDTO.name)
            && Double.compare(latitude, nodeDTO.latitude) == 0
            && Double.compare(longitude, nodeDTO.longitude) == 0
            && lineKey.equals(nodeDTO.lineKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, latitude, longitude, lineKey);
    }

    @Override
    public String toString() {
        return "StationDTO{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}' + " " + lineKey.getName() + " " + lineKey.getRouteType();
    } 
}
