package fr.u_paris.gla.crazytrip.gui.maps.waypoint.station_button;

import java.awt.Cursor;

import javax.swing.Icon;
import javax.swing.JLabel;

import fr.u_paris.gla.crazytrip.model.Station;
import fr.u_paris.gla.crazytrip.model.line.RouteType;
import fr.u_paris.gla.crazytrip.utils.IconUtils;

public abstract class StationButton extends JLabel {
    /** Width of the button. */
    protected static final int WIDTH = 20;
    /** Height of the button. */
    protected static final int HEIGHT = 20;

    protected final transient Station station;

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

    public abstract void updateVisibility(int zoom);
}
