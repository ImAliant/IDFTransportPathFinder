package fr.u_paris.gla.crazytrip.gui.maps.waypoint.station_button;

import fr.u_paris.gla.crazytrip.model.Station;

/**
 * Class representing a metro station button on the map.
 * 
 * <p>It is used to display a metro station on the map.</p>
 * 
 * @see StationButton
 */
public class MetroStationButton extends StationButton {
    /** Path to the metro icon. */
    private static final String METRO_ICON = "src/main/resources/stop_logo/metro_logo.png";
    /** Zoom threshold for the metro station button. */
    private static final int ZOOM_THRESHOLD = 6;

    /**
     * Constructor for the metro station button.
     * 
     * @param station The station to represent.
     * 
     * @see Station
     */
    protected MetroStationButton(Station station) {
        super(station, METRO_ICON);
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
