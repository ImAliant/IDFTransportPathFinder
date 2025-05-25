package fr.u_paris.gla.crazytrip.gui.maps.waypoint.station_button;

import fr.u_paris.gla.crazytrip.model.Station;

/**
 * Class representing a tramway station button on the map.
 * 
 * <p>It is used to display a tramway station on the map.</p>
 * 
 * @see StationButton
 */
public class TramwayStationButton extends StationButton {
    /** Path to the tramway icon. */
    private static final String TRAMWAY_ICON = "src/main/resources/stop_logo/tram_logo.png";
    /** Zoom threshold for the tramway station button. */
    private static final int ZOOM_THRESHOLD = 5;

    /**
     * Constructor for the tramway station button.
     * 
     * @param station The station to represent.
     * 
     * @see Station
     */
    protected TramwayStationButton(Station station) {
        super(station, TRAMWAY_ICON);
    }

    @Override
    public boolean isVisible(int zoom) {
        return zoom <= ZOOM_THRESHOLD;
    }

    @Override
    public void updateVisibility(int zoom) {
        setVisible(isVisible(zoom));
    }
}
