package fr.u_paris.gla.project.idfnetwork.view.waypoint.button;

import fr.u_paris.gla.project.idfnetwork.stop.Stop;

public class BusStopButtonWaypoint extends StopButtonWaypoint {
    private static final String BUS_ICON_PATH = "src/main/resources/fr/u_paris/gla/project/stop_logo/bus_stop_logo.png";
    private static final int ZOOM_THRESHOLD = 4;

    protected BusStopButtonWaypoint(Stop stop) {
        super(stop);

        this.iconPath = BUS_ICON_PATH;

        try {
            setupIcon();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateVisibility(int zoom) {
        setVisible(zoom <= ZOOM_THRESHOLD);
    }
}
