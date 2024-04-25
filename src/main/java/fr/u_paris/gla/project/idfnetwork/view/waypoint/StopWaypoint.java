package fr.u_paris.gla.project.idfnetwork.view.waypoint;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import fr.u_paris.gla.project.idfnetwork.LineType;
import fr.u_paris.gla.project.idfnetwork.stop.Stop;
import fr.u_paris.gla.project.idfnetwork.view.waypoint.button.StopButtonWaypoint;

import java.io.Serializable;

public class StopWaypoint extends DefaultWaypoint implements Serializable {
    private static final long serialVersionUID = 1L;

    private final transient Stop stop;
    private final StopButtonWaypoint button;

    public StopWaypoint(Stop stop) {
        super(new GeoPosition(stop.getLatitude(), stop.getLongitude()));
        this.stop = stop;

        LineType type = stop.getLineType();
        this.button = StopButtonWaypoint.createButton(stop, type);
    }

    public Stop getStop() {
        return stop;
    }

    public StopButtonWaypoint getButton() {
        return button;
    }

    @Override
    public String toString() {
        return stop.getStopName();
    }
}
