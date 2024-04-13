package fr.u_paris.gla.project.idfnetwork.view.waypoint;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import fr.u_paris.gla.project.idfnetwork.stop.Stop;
import fr.u_paris.gla.project.idfnetwork.view.waypoint.button.StopButtonWaypoint;

import java.io.Serializable;

import javax.swing.JLabel;

public class StopWaypoint extends DefaultWaypoint implements Serializable {
    private static final long serialVersionUID = 1L;

    private final transient Stop stop;
    private final JLabel button;

    public StopWaypoint(Stop stop) {
        super(new GeoPosition(stop.getLatitude(), stop.getLongitude()));
        this.stop = stop;

        this.button = new StopButtonWaypoint(stop);
    }

    public Stop getStop() {
        return stop;
    }

    public JLabel getButton() {
        return button;
    }
}
