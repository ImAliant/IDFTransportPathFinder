package fr.u_paris.gla.crazytrip.gui.maps.waypoint.station_button;

import fr.u_paris.gla.crazytrip.model.Station;

public class FunicularStationButton extends StationButton {
    private static final String FUNICULAR_ICON = "src/main/resources/stop_logo/funicular_stop_logo.png";
    private static final int ZOOM_THRESHOLD = 4;

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
