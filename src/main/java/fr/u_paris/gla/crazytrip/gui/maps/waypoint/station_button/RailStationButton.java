package fr.u_paris.gla.crazytrip.gui.maps.waypoint.station_button;

import fr.u_paris.gla.crazytrip.model.Station;

public class RailStationButton extends StationButton {
    private static final String RAIL_ICON = "src/main/resources/stop_logo/rer_logo.png";
    private static final int ZOOM_THRESHOLD = 7;

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
