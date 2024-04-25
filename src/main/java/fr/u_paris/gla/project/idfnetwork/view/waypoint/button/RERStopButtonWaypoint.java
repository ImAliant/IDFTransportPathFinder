package fr.u_paris.gla.project.idfnetwork.view.waypoint.button;

import fr.u_paris.gla.project.idfnetwork.stop.Stop;

public class RERStopButtonWaypoint extends StopButtonWaypoint {
    private static final String RER_ICON_PATH = "src/main/resources/fr/u_paris/gla/project/stop_logo/rer_stop_logo.png";
    private static final int ZOOM_THRESHOLD = 7;

    protected RERStopButtonWaypoint(Stop stop) {
        super(stop);

        this.iconPath = RER_ICON_PATH;

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
