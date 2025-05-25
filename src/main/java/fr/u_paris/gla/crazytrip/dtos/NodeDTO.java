package fr.u_paris.gla.crazytrip.dtos;

import java.util.Objects;

import fr.u_paris.gla.crazytrip.model.key.LineKey;
import fr.u_paris.gla.crazytrip.model.key.NodeKey;

/**
 * This class represents a node data transfer object.
 * 
 * It is used in the parser to store the data of a node.
 * 
 * @see NodeKey
 * @see LineKey
 */
public class NodeDTO {
    /** The name of the node */
    private final String name;
    /** The latitude of the node */
    private final double latitude;
    /** The longitude of the node */
    private final double longitude;
    /** The line key of the node */
    private final LineKey lineKey;

    /**
     * Constructor of the node data transfer object.
     * 
     * @param name The name of the node.
     * @param latitude The latitude of the node.
     * @param longitude The longitude of the node.
     * @param lineKey The line key of the node.
     * 
     * @see LineKey
     */
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

    /**
     * Generates a node key from the node data transfer object.
     * 
     * @return A node key generated from the node data transfer object.
     * 
     * @see NodeKey
     */
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
