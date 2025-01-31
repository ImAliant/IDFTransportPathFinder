package fr.u_paris.gla.crazytrip.gui.maps.waypoint.station_button;

import fr.u_paris.gla.crazytrip.model.Station;

/**
 * Class representing a rail station button on the map.
 * 
 * <p>It is used to display a rail station on the map.</p>
 * 
 * @see StationButton
 */
public class RailStationButton extends StationButton {
    /** Path to the rail icon. */
    private static final String RAIL_ICON = "src/main/resources/stop_logo/rer_logo.png";
    /** Zoom threshold for the rail station button. */
    private static final int ZOOM_THRESHOLD = 7;

    /**
     * Constructor for the rail station button.
     * 
     * @param station The station to represent.
     * 
     * @see Station
     */
    protected RailStationButton(Station station) {
        super(station, RAIL_ICON);
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
