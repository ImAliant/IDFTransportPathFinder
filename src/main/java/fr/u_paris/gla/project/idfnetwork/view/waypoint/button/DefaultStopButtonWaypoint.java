package fr.u_paris.gla.project.idfnetwork.view.waypoint.button;

import javax.swing.Icon;

import fr.u_paris.gla.project.idfnetwork.stop.Stop;
import fr.u_paris.gla.project.utils.IconUtils;

public class DefaultStopButtonWaypoint extends StopButtonWaypoint {
    private static final int ZOOM_THRESHOLD = 0;

    protected DefaultStopButtonWaypoint(Stop stop) {
        super(stop);

        this.zoom = ZOOM_THRESHOLD;

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
