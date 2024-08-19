package fr.u_paris.gla.crazytrip.gui.maps.waypoint.station_button;

import fr.u_paris.gla.crazytrip.model.Station;

public class BusStationButton extends StationButton {
    private static final String BUS_ICON = "src/main/resources/stop_logo/bus_logo.png";
    private static final int ZOOM_THRESHOLD = 4;

    protected BusStationButton(Station station) {
        super(station, BUS_ICON);
    }

    @Override
    public void updateVisibility(int zoom) {
        setVisible(zoom <= ZOOM_THRESHOLD);
    }
}
