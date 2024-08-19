package fr.u_paris.gla.crazytrip.gui.maps.waypoint.station_button;

import fr.u_paris.gla.crazytrip.model.Station;

public class MetroStationButton extends StationButton {
    private static final String METRO_ICON = "src/main/resources/stop_logo/metro_logo.png";
    private static final int ZOOM_THRESHOLD = 6;

    protected MetroStationButton(Station station) {
        super(station, METRO_ICON);
    }

    @Override
    public void updateVisibility(int zoom) {
        setVisible(zoom <= ZOOM_THRESHOLD);
    }
}
