package fr.u_paris.gla.project.view.button.waypoint.button;

import javax.swing.Icon;

import fr.u_paris.gla.project.utils.IconUtils;
import fr.u_paris.gla.project.idfnetwork.Stop;

/**
 * Button for a metro stop waypoint.
 * 
 * @see StopButtonWaypoint
 */
public class MetroStopButtonWaypoint extends StopButtonWaypoint {
    /** Path to the icon. */
    private static final String METRO_ICON_PATH = "src/main/resources/fr/u_paris/gla/project/stop_logo/metro_stop_logo.png";
    /** The maximum zoom level to display the waypoint. */
    protected static final int ZOOM_THRESHOLD = 6;

    /**
     * Create a new metro stop button at the given position.
     *
     * @param stop The stop to display.
     */
    protected MetroStopButtonWaypoint(Stop stop) {
        super(stop);

        this.iconPath = METRO_ICON_PATH;

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
