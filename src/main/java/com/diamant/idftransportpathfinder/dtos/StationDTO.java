package com.diamant.idftransportpathfinder.dtos;

import java.util.Objects;

import com.diamant.idftransportpathfinder.model.Station;
import com.diamant.idftransportpathfinder.model.key.LineKey;
import com.diamant.idftransportpathfinder.model.key.NodeKey;
import com.diamant.idftransportpathfinder.model.line.RouteType;

/**
 * This class represents a node data transfer object.
 * 
 * It is used in the parser to store the data of a node.
 * 
 * @see NodeKey
 * @see LineKey
 */
public class StationDTO {
    private static final int DEFAULT_ZOOM_THRESHOLD = 10;
    private static final int RER_ZOOM_THRESHOLD = 6;
    private static final int TER_ZOOM_THRESHOLD = 3;
    private static final int METRO_ZOOM_THRESHOLD = 13;
    private static final int TRAMWAY_ZOOM_THRESHOLD = 13;
    private static final int BUS_ZOOM_THRESHOLD = 15;
    private static final int FUNICULAR_ZOOM_THRESHOLD = 16;

    /** The name of the node */
    private final String name;
    /** The latitude of the node */
    private final double latitude;
    /** The longitude of the node */
    private final double longitude;
    /** The line key of the node */
    private final LineKey lineKey;
    /** The zoom threshold for the node */
    private final int zoomThreshold;

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
    public StationDTO(String name, double latitude, double longitude, LineKey lineKey) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.lineKey = lineKey;
        this.zoomThreshold = getThresholdFromLineKey(lineKey.getRouteType());
    }

    /**
     * Constructor of the node data transfer object from a station.
     * 
     * @param station The station to convert to a node data transfer object.
     * 
     * @see Station
     */
    public StationDTO(Station station) {
        this(station.getName(), 
              station.getCoordinates().getLatitude(), 
              station.getCoordinates().getLongitude(), 
              station.getLineKey());
    }

    private int getThresholdFromLineKey(RouteType type) {
        // Define zoom thresholds based on the route type
        switch (type) {
            case METRO:
                return METRO_ZOOM_THRESHOLD;
            case RER:
                return RER_ZOOM_THRESHOLD;
            case TER:
                return TER_ZOOM_THRESHOLD;
            case TRAMWAY:
                return TRAMWAY_ZOOM_THRESHOLD;
            case BUS:
                return BUS_ZOOM_THRESHOLD;
            case FUNICULAR:
                return FUNICULAR_ZOOM_THRESHOLD;
            default:
                return DEFAULT_ZOOM_THRESHOLD; // Default threshold for unknown types
        }
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

    public int getZoomThreshold() {
        return zoomThreshold;
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
        StationDTO nodeDTO = (StationDTO) obj;

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
