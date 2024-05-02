package fr.u_paris.gla.project.view.button.waypoint.button;

import javax.swing.Icon;

import fr.u_paris.gla.project.utils.IconUtils;
import fr.u_paris.gla.project.idfnetwork.Stop;

/**
 * Button for a bus stop waypoint.
 * 
 * @see StopButtonWaypoint
 */
public class BusStopButtonWaypoint extends StopButtonWaypoint {
    /** Path to the icon. */
    private static final String BUS_ICON_PATH = "src/main/resources/fr/u_paris/gla/project/button_icon/paris_transit_icons/Paris_transit_icons_-_Bus.svg.png";
    /** The maximum zoom level to display the waypoint. */
    private static final int ZOOM_THRESHOLD = 4;

    /**
     * Create a new bus stop button at the given position.
     *
     * @param stop The stop to display.
     */
    protected BusStopButtonWaypoint(Stop stop) {
        super(stop);

        this.iconPath = BUS_ICON_PATH;

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
