package fr.u_paris.gla.crazytrip.view.button.waypoint.button;

import javax.swing.Icon;

import fr.u_paris.gla.crazytrip.idfnetwork.Stop;
import fr.u_paris.gla.crazytrip.utils.IconUtils;

/**
 * Button for a default stop waypoint.
 * 
 * @see StopButtonWaypoint
 */
public class DefaultStopButtonWaypoint extends StopButtonWaypoint {
    /** The default stop button is always visible. */
    protected static final int ZOOM_THRESHOLD = 0;

    protected DefaultStopButtonWaypoint(Stop stop) {
        super(stop);

        try {
            Icon icon = IconUtils.createIcon(iconPath, WIDTH, HEIGHT);
            setIcon(icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateVisibility(int zoom) {
        setVisible(zoom >= ZOOM_THRESHOLD);
    }
}
