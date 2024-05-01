package fr.u_paris.gla.project.view.button.waypoint.button;

import javax.swing.JLabel;

import fr.u_paris.gla.project.idfnetwork.line.LineType;
import fr.u_paris.gla.project.view.listener.StopButtonListener;
import fr.u_paris.gla.project.idfnetwork.Stop;

import java.awt.Cursor;

/**
 * Abstract class for a stop button.
 */
public abstract class StopButtonWaypoint extends JLabel {
    private static final long serialVersionUID = 1L;

    /** Width of the button. */
    protected static final int WIDTH = 20;
    /** Height of the button. */
    protected static final int HEIGHT = 20;
    /** Default path to the icon. */
    private static final String DEFAULT_PATH = "src/main/resources/fr/u_paris/gla/project/stop_logo/stop_logo.png";

    /** Path to the icon. */
    protected String iconPath = DEFAULT_PATH;
    /** The stop to display. */
    protected transient Stop stop;

    /**
     * Factory method to create a stop button given a stop and a line type.
     * 
     * @param stop The stop to display.
     * @param lineType The type of the line.
     * 
     * @return The stop button.
     */
    public static StopButtonWaypoint createButton(Stop stop, LineType lineType) {
        if (lineType == null) {
            return new DefaultStopButtonWaypoint(stop);
        }
        
        StopButtonWaypoint button;
        switch (lineType) {
            case BUS:
                button = new BusStopButtonWaypoint(stop);
                break;
            case RER:
                button = new RERStopButtonWaypoint(stop);
                break;
            case TRAMWAY:
                button = new TramStopButtonWaypoint(stop);
                break;
            case FUNICULAIRE:
                button = new FunicularStopButtonWaypoint(stop);
                break;
            case METRO:
                button = new MetroStopButtonWaypoint(stop);
                break;
            default:
                button = new DefaultStopButtonWaypoint(stop);
                break;
        }

        return button;
    }

    /**
     * Create a new stop button at the given position.
     *
     * @param stop The stop to display.
     */
    protected StopButtonWaypoint(Stop stop) {
        this.stop = stop;
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addListener();
    }

    /**
     * Update the visibility of the button.
     * 
     * @param zoom The current zoom level.
     */
    public abstract void updateVisibility(int zoom);

    /**
     * Add a listener to the button.
     */
    protected void addListener() {
        addMouseListener(new StopButtonListener(stop));
    }
}
