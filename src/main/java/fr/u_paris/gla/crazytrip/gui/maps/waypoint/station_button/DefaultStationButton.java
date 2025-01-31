package fr.u_paris.gla.crazytrip.gui.maps.waypoint.station_button;

import fr.u_paris.gla.crazytrip.model.Station;

/**
 * Class representing a default station button.
 * 
 * <p>It is used to display a station on the map.</p>
 * 
 * @see StationButton
 */
public class DefaultStationButton extends StationButton {
    /** Default path to the icon. */
    private static final String DEFAULT_PATH = "src/main/resources/stop_logo/stop_logo.png";
    /** Zoom threshold for the default station button. */
    private static final int ZOOM_THRESHOLD = 0;

    /**
     * Constructor for the default station button.
     * 
     * @param station The station to represent.
     * 
     * @see Station
     */
    public DefaultStationButton(Station station) {
        super(station, DEFAULT_PATH);
    }

    @Override
    public boolean isVisible(int zoom) {
        return zoom >= ZOOM_THRESHOLD;
    }
    
    @Override
    public void updateVisibility(int zoom) {
        setVisible(isVisible(zoom));
    }
}
