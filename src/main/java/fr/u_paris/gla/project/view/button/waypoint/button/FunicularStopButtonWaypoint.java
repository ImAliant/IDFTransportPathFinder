package fr.u_paris.gla.project.view.button.waypoint.button;

import javax.swing.Icon;

import fr.u_paris.gla.project.utils.IconUtils;
import fr.u_paris.gla.project.idfnetwork.Stop;

/**
 * Button for a funicular stop waypoint.
 * 
 * @see StopButtonWaypoint
 */
public class FunicularStopButtonWaypoint extends StopButtonWaypoint {
    /** Path to the icon. */
    private static final String FUNICULAR_ICON_PATH = "src/main/resources/fr/u_paris/gla/project/stop_logo/funicular_stop_logo.png";
    /** The maximum zoom level to display the waypoint. */
    private static final int ZOOM_THRESHOLD = 4;

    /**
     * Create a new funicular stop button at the given position.
     *
     * @param stop The stop to display.
     */
    protected FunicularStopButtonWaypoint(Stop stop) {
        super(stop);

        this.iconPath = FUNICULAR_ICON_PATH;

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