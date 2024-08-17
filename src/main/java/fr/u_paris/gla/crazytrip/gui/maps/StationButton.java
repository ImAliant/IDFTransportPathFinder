package fr.u_paris.gla.crazytrip.gui.maps;

import java.awt.Cursor;

import javax.swing.JLabel;

import fr.u_paris.gla.crazytrip.model.Station;

public abstract class StationButton extends JLabel {
    /** Width of the button. */
    protected static final int WIDTH = 20;
    /** Height of the button. */
    protected static final int HEIGHT = 20;
    /** Default path to the icon. */
    private static final String DEFAULT_PATH = "src/main/resources/stop_logo/stop_logo.png";

    private final transient Station station;
    protected String path = DEFAULT_PATH;

    protected StationButton(Station station) {
        this.station = station;
        this.setToolTipText(station.getName());
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        // TODO Add listener
    }

    public static StationButton create(Station station) {
        return new DefaultStationButton(station);
    }

    public abstract void updateVisibility(int zoom);

    public Station getStation() {
        return station;
    }
}
