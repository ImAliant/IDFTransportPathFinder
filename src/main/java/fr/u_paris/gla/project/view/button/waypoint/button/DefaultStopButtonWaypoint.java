package fr.u_paris.gla.project.view.button.waypoint.button;

import javax.swing.Icon;

import fr.u_paris.gla.project.utils.IconUtils;
import fr.u_paris.gla.project.idfnetwork.Stop;

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
