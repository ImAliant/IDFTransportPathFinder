package fr.u_paris.gla.crazytrip.gui.maps.waypoint.station_button;

import java.awt.Cursor;

import javax.swing.Icon;
import javax.swing.JLabel;

import fr.u_paris.gla.crazytrip.model.Station;
import fr.u_paris.gla.crazytrip.model.line.RouteType;
import fr.u_paris.gla.crazytrip.utils.IconUtils;

/**
 * Class representing a station button on the map.
 * 
 * <p>It is used to display a station on the map.</p>
 * 
 * @see JLabel
 */
public abstract class StationButton extends JLabel {
    /** Width of the button. */
    protected static final int WIDTH = 20;
    /** Height of the button. */
    protected static final int HEIGHT = 20;
    /** The station to represent. */
    protected final transient Station station;

    /**
     * Constructor for the station button.
     * 
     * <p>It creates a station button with the given station and icon path.</p>
     * 
     * @param station The station to represent.
     * 
     * @see Station
     */
    protected StationButton(Station station, String iconPath) {
        this.station = station;
        this.setToolTipText(station.toolkitToString());
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        try {
            Icon icon = IconUtils.createIcon(iconPath, WIDTH, HEIGHT);
            setIcon(icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Factory method to create a station button.
     * 
     * <p>It creates a station button with the given station and route type.</p>
     * 
     * @param station The station to represent.
     * @param type The route type of the station.
     * 
     * @return The created station button.
     * 
     * @see Station
     * @see RouteType
     */
    public static StationButton create(Station station, RouteType type) {
        switch (type) {
            case BUS:
                return new BusStationButton(station);
            case TRAMWAY:
                return new TramwayStationButton(station);
            case METRO:
                return new MetroStationButton(station);
            case RAIL:
                return new RailStationButton(station);
            case FUNICULAR:
                return new FunicularStationButton(station);
            default:
                return new DefaultStationButton(station);
        }
    }

    /**
     * Checks if the station button is visible.
     * 
     * <p>It checks if the station button is visible at the given zoom level.</p>
     * 
     * @param zoom The zoom level.
     * 
     * @return True if the station button is visible, false otherwise.
     */
    public abstract boolean isVisible(int zoom);
    /**
     * Updates the visibility of the station button.
     * 
     * <p>It updates the visibility of the station button at the given zoom level.</p>
     * 
     * @param zoom The zoom level.
     */
    public abstract void updateVisibility(int zoom);
}
