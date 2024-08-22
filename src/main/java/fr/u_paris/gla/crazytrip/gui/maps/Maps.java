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

import fr.u_paris.gla.crazytrip.gui.maps.waypoint.StationRender;
import fr.u_paris.gla.crazytrip.gui.maps.waypoint.StationWaypoint;
import fr.u_paris.gla.crazytrip.gui.observer.ZoomInObserver;
import fr.u_paris.gla.crazytrip.gui.observer.ZoomOutObserver;
import fr.u_paris.gla.crazytrip.model.Station;

public class Maps extends JXMapViewer implements ZoomInObserver, ZoomOutObserver{
    /**
     * Default zoom of the map.
     */
    private static final int DEFAULT_ZOOM = 2;
    /**
     * Maximum zoom of the map.
     */
    public static final int MAX_ZOOM = 7;
    /**
     * Default latitude of the map. Latitude of Paris.
     */
    private static final double IDF_LATITUDE = 48.860031;
    /**
     * Default longitude of the map. Longitude of Paris.
     */
    private static final double IDF_LONGITUDE = 2.342877;

    private transient StationRender wayPointPainter;
    private transient Set<StationWaypoint> allStationWaypoints = new HashSet<>();

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
        wayPointPainter.setWaypoints(allStationWaypoints);
        this.setOverlayPainter(wayPointPainter);
    }

    public void addStationMarker(Station station) {
        StationWaypoint waypoint = new StationWaypoint(station);
        allStationWaypoints.add(waypoint);
        this.add(waypoint.getButton());
    }

    private void adjustZoom(int factor) {
        setZoom(getZoom() + factor);

        updateStationsVisibility();
    }

    private void updateStationsVisibility() {
        int zoom = getZoom();

        allStationWaypoints.parallelStream().forEach(waypoint -> waypoint.updateVisibility(zoom));
    }

    @Override
    public void zoomIn() {
        adjustZoom(-1);
    }

    @Override
    public void zoomOut() {
        adjustZoom(1);
    }

    @Override
    public void setZoom(int zoom) {
        if (zoom > MAX_ZOOM) return;

        super.setZoom(zoom);
    }

    public StationRender getWayPointPainter() {
        return wayPointPainter;
    }

    public Set<StationWaypoint> getAllStationWaypoints() {
        return allStationWaypoints;
    }
}
