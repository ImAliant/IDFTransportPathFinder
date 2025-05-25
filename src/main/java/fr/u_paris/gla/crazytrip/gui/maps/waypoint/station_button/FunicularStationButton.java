package fr.u_paris.gla.crazytrip.gui.maps.waypoint.station_button;

import fr.u_paris.gla.crazytrip.model.Station;

/**
 * Class representing a funicular station button.
 * 
 * <p>It is used to display a funicular station on the map.</p>
 * 
 * @see StationButton
 */
public class FunicularStationButton extends StationButton {
    /** Path to the funicular icon. */
    private static final String FUNICULAR_ICON = "src/main/resources/stop_logo/funicular_stop_logo.png";
    /** Zoom threshold for the funicular station button. */
    private static final int ZOOM_THRESHOLD = 4;

    /**
     * Constructor for the funicular station button.
     * 
     * @param station The station to represent.
     * 
     * @see Station
     */
    protected FunicularStationButton(Station station) {
        super(station, FUNICULAR_ICON);
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
