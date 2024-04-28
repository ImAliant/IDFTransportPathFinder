package fr.u_paris.gla.project.idfnetwork.view.waypoint.button;

import fr.u_paris.gla.project.idfnetwork.stop.Stop;
import fr.u_paris.gla.project.utils.IconUtils;

import javax.swing.Icon;

/**
 * Button for a tram stop waypoint.
 *
 * @see StopButtonWaypoint
 */
public class TramStopButtonWaypoint extends StopButtonWaypoint {
    /** Path to the icon. */
    private static final String TRAM_ICON_PATH = "src/main/resources/fr/u_paris/gla/project/stop_logo/tram_stop_logo.png";
    /** The maximum zoom level to display the waypoint. */
    private static final int ZOOM_THRESHOLD = 5;

    /**
     * Create a new tram stop button at the given position.
     *
     * @param stop The stop to display.
     */
    protected TramStopButtonWaypoint(Stop stop) {
        super(stop);

        this.iconPath = TRAM_ICON_PATH;

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
