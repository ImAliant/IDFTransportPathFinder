package fr.u_paris.gla.crazytrip.gui.maps.waypoint.station_button;

import fr.u_paris.gla.crazytrip.model.Station;

public class TramwayStationButton extends StationButton {
    private static final String TRAMWAY_ICON = "src/main/resources/stop_logo/tram_logo.png";
    private static final int ZOOM_THRESHOLD = 5;

    protected TramwayStationButton(Station station) {
        super(station, TRAMWAY_ICON);
    }

    @Override
    public void updateVisibility(int zoom) {
        setVisible(zoom <= ZOOM_THRESHOLD);
    }
}
