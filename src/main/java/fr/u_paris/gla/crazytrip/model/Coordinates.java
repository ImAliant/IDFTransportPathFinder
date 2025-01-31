package fr.u_paris.gla.crazytrip.model;

import fr.u_paris.gla.crazytrip.utils.GPS;

/**
 * A class representing coordinates
 * 
 * @param latitude the latitude of the coordinates
 * @param longitude the longitude of the coordinates
 */
public class Coordinates {
    /** The latitude of the coordinates */
    private final double latitude;
    /** The longitude of the coordinates */
    private final double longitude;
    
    /**
     * Constructor
     * @param latitude the latitude of the coordinates
     * @param longitude the longitude of the coordinates
     */
    public Coordinates(double latitude, double longitude) {
        if (latitude < -90 || latitude > 90)
            throw new IllegalArgumentException("Latitude must be between -90 and 90");
        if (longitude < -180 || longitude > 180)
            throw new IllegalArgumentException("Longitude must be between -180 and 180");
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Get the distance between two coordinates
     * @param co the other coordinates
     * @return the distance between the two coordinates
     */
    public double distanceTo(Coordinates co) {
        return GPS.distance(latitude, longitude, co.latitude, co.longitude);
    }

    /**
     * Get the latitude of the coordinates
     * @return the latitude of the coordinates
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Get the longitude of the coordinates
     * @return the longitude of the coordinates
     */
    public double getLongitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Coordinates
            && ((Coordinates) o).latitude == latitude
            && ((Coordinates) o).longitude == longitude;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(latitude) + Double.hashCode(longitude);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
