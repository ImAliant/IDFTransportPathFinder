package fr.u_paris.gla.project.view.button.waypoint;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import fr.u_paris.gla.project.idfnetwork.line.LineType;
import fr.u_paris.gla.project.view.button.waypoint.button.StopButtonWaypoint;
import fr.u_paris.gla.project.idfnetwork.Stop;

import java.io.Serializable;

/**
 * Class to represent a stop waypoint.
 * 
 * @see DefaultWaypoint
 * @see Stop
 */
public class StopWaypoint extends DefaultWaypoint implements Serializable {
    private static final long serialVersionUID = 1L;

    /** The stop to display. */
    private final transient Stop stop;
    /** The button associated with the stop. */
    private final StopButtonWaypoint button;

    /**
     * Create a new stop waypoint at the given position.
     *
     * @param stop The stop to display.
     */
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
