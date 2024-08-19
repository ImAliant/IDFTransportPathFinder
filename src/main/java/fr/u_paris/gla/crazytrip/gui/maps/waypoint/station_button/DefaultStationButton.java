package fr.u_paris.gla.crazytrip.gui.maps.waypoint.station_button;

import fr.u_paris.gla.crazytrip.model.Station;

public class DefaultStationButton extends StationButton {
    /** Default path to the icon. */
    private static final String DEFAULT_PATH = "src/main/resources/stop_logo/stop_logo.png";

    private static final int ZOOM_THRESHOLD = 0;

    public DefaultStationButton(Station station) {
        super(station, DEFAULT_PATH);
    }
    
    @Override
    public void updateVisibility(int zoom) {
        setVisible(zoom >= ZOOM_THRESHOLD);
    }
}
