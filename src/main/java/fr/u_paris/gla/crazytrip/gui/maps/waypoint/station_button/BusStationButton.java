package fr.u_paris.gla.crazytrip.gui.maps.waypoint.station_button;

import fr.u_paris.gla.crazytrip.model.Station;

/** 
 * Class representing a bus station button on the map.
 * 
 * <p>It is used to display a bus station on the map.</p>
 * 
 * @see StationButton
 */ 
public class BusStationButton extends StationButton {
    /** Path to the bus icon. */
    private static final String BUS_ICON = "src/main/resources/stop_logo/bus_logo.png";
    /** Zoom threshold for the bus station button. */
    private static final int ZOOM_THRESHOLD = 4;

    /**
     * Constructor for the bus station button.
     * 
     * @param station The station to represent.
     * 
     * @see Station
     */
    protected BusStationButton(Station station) {
        super(station, BUS_ICON);
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
