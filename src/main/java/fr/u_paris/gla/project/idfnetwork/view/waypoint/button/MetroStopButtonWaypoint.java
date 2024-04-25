package fr.u_paris.gla.project.idfnetwork.view.waypoint.button;

import fr.u_paris.gla.project.idfnetwork.stop.Stop;

public class MetroStopButtonWaypoint extends StopButtonWaypoint {
    private static final String METRO_ICON_PATH = "src/main/resources/fr/u_paris/gla/project/stop_logo/metro_stop_logo.png";
    private static final int ZOOM_THRESHOLD = 6;

    protected MetroStopButtonWaypoint(Stop stop) {
        super(stop);

        this.iconPath = METRO_ICON_PATH;

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
