package fr.u_paris.gla.crazytrip.gui.maps.waypoint;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import fr.u_paris.gla.crazytrip.gui.maps.waypoint.station_button.StationButton;
import fr.u_paris.gla.crazytrip.model.Station;

/**
 * Class representing a station waypoint on the map.
 * 
 * <p>It is used to display a station on the map.</p>
 * 
 * @see DefaultWaypoint
 */
public class StationWaypoint extends DefaultWaypoint {
    /** The station to represent. */
    private final Station station;
    /** The button representing the station. */
    private final StationButton button;

    /**
     * Constructor for the station waypoint.
     * 
     * @param station The station to represent.
     * 
     * @see Station
     */
    public StationWaypoint(Station station) {
        super(new GeoPosition(station.getCoordinates().getLatitude(), station.getCoordinates().getLongitude()));
        
        this.station = station;
        this.button = StationButton.create(station, station.getLineKey().getRouteType());
    }

    /**
     * Updates the visibility of the station waypoint.
     * 
     * @param zoom The current zoom level.
     */
    public void updateVisibility(int zoom) {
        button.updateVisibility(zoom);
    }

    /**
     * Updates the visibility of the station waypoint.
     * 
     * @param visible Turn on if true, off if false.
     */
    public void updateVisibility(boolean visible) {
        button.setVisible(visible);
    }

    public Station getStation() {
        return station;
    }

    public StationButton getButton() {
        return button;
    }
}
