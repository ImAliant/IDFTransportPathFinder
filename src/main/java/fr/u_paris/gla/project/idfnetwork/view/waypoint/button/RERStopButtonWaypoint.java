package fr.u_paris.gla.project.idfnetwork.view.waypoint.button;

import javax.swing.Icon;

import fr.u_paris.gla.project.idfnetwork.stop.Stop;
import fr.u_paris.gla.project.utils.IconUtils;

/**
 * Button for a RER stop waypoint.
 * 
 * @see StopButtonWaypoint
 */
public class RERStopButtonWaypoint extends StopButtonWaypoint {
    /** Path to the icon. */
    private static final String RER_ICON_PATH = "src/main/resources/fr/u_paris/gla/project/stop_logo/rer_stop_logo.png";
    /** The maximum zoom level to display the waypoint. */
    private static final int ZOOM_THRESHOLD = 7;

    /**
     * Create a new RER stop button at the given position.
     *
     * @param stop The stop to display.
     */
    protected RERStopButtonWaypoint(Stop stop) {
        super(stop);

        this.iconPath = RER_ICON_PATH;

        try {
            Icon icon = IconUtils.createIcon(iconPath, WIDTH, HEIGHT);
            setIcon(icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateVisibility(int zoom) {
        setVisible(zoom <= ZOOM_THRESHOLD);
    }
}
