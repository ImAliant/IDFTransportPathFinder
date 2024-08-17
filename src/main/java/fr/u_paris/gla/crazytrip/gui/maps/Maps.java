package fr.u_paris.gla.crazytrip.gui.maps;

import java.util.HashSet;
import java.util.Set;

import javax.swing.event.MouseInputListener;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;

import fr.u_paris.gla.crazytrip.model.Station;

public class Maps extends JXMapViewer {
    /**
     * Default zoom of the map.
     */
    private static final int DEFAULT_ZOOM = 2;
    /**
     * Default latitude of the map. Latitude of Paris.
     */
    private static final double IDF_LATITUDE = 48.860031;
    /**
     * Default longitude of the map. Longitude of Paris.
     */
    private static final double IDF_LONGITUDE = 2.342877;

    private StationRender wayPointPainter;
    private Set<StationWaypoint> stopWaypoints = new HashSet<>();

    public Maps() {
        super();

        init();
    }

    private void init() {
        // Tile factory to get the map
        createTiles();

        // Define the default position and zoom
        setDefaultLocation();

        configureMapMouseListeners();
    }

    private void createTiles() {
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        setTileFactory(tileFactory);
    }

    private void setDefaultLocation() {
        GeoPosition idf = new GeoPosition(IDF_LATITUDE, IDF_LONGITUDE);
        setZoom(DEFAULT_ZOOM);
        setCenterPosition(idf);
    }

    private void configureMapMouseListeners() {
        MouseInputListener listener = new PanMouseInputListener(this);
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }

    public void initPainter() {
        this.wayPointPainter = new StationRender();
        wayPointPainter.setWaypoints(stopWaypoints);
        this.setOverlayPainter(wayPointPainter);
    }

    public void addStationMarker(Station station) {
        StationWaypoint waypoint = new StationWaypoint(station);
        stopWaypoints.add(waypoint);
        this.add(waypoint.getButton());
    }
}
