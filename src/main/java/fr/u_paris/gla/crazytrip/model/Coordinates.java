package fr.u_paris.gla.crazytrip.model;

import fr.u_paris.gla.crazytrip.utils.GPS;

public class Coordinates {
    private final double latitude;
    private final double longitude;
    
    public Coordinates(double latitude, double longitude) {
        if (latitude < -90 || latitude > 90)
            throw new IllegalArgumentException("Latitude must be between -90 and 90");
        if (longitude < -180 || longitude > 180)
            throw new IllegalArgumentException("Longitude must be between -180 and 180");
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double distanceTo(Coordinates co) {
        return GPS.distance(latitude, longitude, co.latitude, co.longitude);
    }

    public double getLatitude() {
        return latitude;
    }

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
