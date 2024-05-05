package fr.u_paris.gla.crazytrip.view.button.waypoint.button;

import javax.swing.Icon;

import fr.u_paris.gla.crazytrip.idfnetwork.Stop;
import fr.u_paris.gla.crazytrip.utils.IconUtils;

/**
 * Button for a funicular stop waypoint.
 * 
 * @see StopButtonWaypoint
 */
public class FunicularStopButtonWaypoint extends StopButtonWaypoint {
    /** Path to the icon. */
    private static final String FUNICULAR_ICON_PATH = "src/main/resources/fr/u_paris/gla/crazytrip/stop_logo/funicular_stop_logo.png";
    /** The maximum zoom level to display the waypoint. */
    protected static final int ZOOM_THRESHOLD = 4;

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
