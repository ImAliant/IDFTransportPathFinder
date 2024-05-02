package fr.u_paris.gla.project.view.button.waypoint.button;

import javax.swing.Icon;

import fr.u_paris.gla.project.utils.IconUtils;
import fr.u_paris.gla.project.idfnetwork.Stop;

/**
 * Button for a RER stop waypoint.
 * 
 * @see StopButtonWaypoint
 */
public class RERStopButtonWaypoint extends StopButtonWaypoint {
    /** Path to the icon. */
    private static final String RER_ICON_PATH = "src/main/resources/fr/u_paris/gla/project/button_icon/paris_transit_icons/20221022173330!Paris_transit_icons_-_RER.svg.png";
    /** The maximum zoom level to display the waypoint. */
    protected static final int ZOOM_THRESHOLD = 7;

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
