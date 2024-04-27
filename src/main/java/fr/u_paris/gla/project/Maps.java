package fr.u_paris.gla.project;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.event.MouseInputListener;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;

import fr.u_paris.gla.project.idfnetwork.Network;
import fr.u_paris.gla.project.idfnetwork.stop.Stop;
import fr.u_paris.gla.project.idfnetwork.view.waypoint.StopRender;
import fr.u_paris.gla.project.idfnetwork.view.waypoint.StopWaypoint;
import fr.u_paris.gla.project.observer.ZoomInObserver;
import fr.u_paris.gla.project.observer.ZoomOutObserver;

public class Maps extends JXMapViewer implements ZoomInObserver, ZoomOutObserver {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Default latitude of the map. Latitude of Paris.
     */
    private static final double IDF_LATITUDE = 48.85656189753147;
    /**
     * Default longitude of the map. Longitude of Paris.
     */
    private static final double IDF_LONGITUDE = 2.4002576758282745;
    /**
     * Default zoom of the map.
     */
    public static final int DEFAULT_ZOOM = 2;
    /**
     * Minimum zoom of the map.
     */
    public static final int MIN_ZOOM = 0;
    /**
     * Maximum zoom of the map.
     */
    public static final int MAX_ZOOM = 7;

    private transient Set<StopWaypoint> stopWaypoints = new HashSet<>();

    /**
     * Constructor of the maps.
     */
    public Maps() {
        super();

        init();
    }

    /**
     * Initialize the map components.
     */
    private void init() {
        // Tile factory to get the map
        createTiles();

        // Define the default position and zoom
        setDefaultLocation();

        configureMapMouseListeners();

        displayNetwork();
    }

    private void displayNetwork() {
        Network network = Network.getInstance();

        List<Stop> stops = network.getStops();
        stops.forEach(this::addStopWaypoint);

        initWaypoint();
    }

    private void addStopWaypoint(Stop stop) {
        stopWaypoints.add(
            new StopWaypoint(stop)
        );
    }

    private void initWaypoint() {
        WaypointPainter<StopWaypoint> wp = new StopRender();
        wp.setWaypoints(stopWaypoints);
        this.setOverlayPainter(wp);
        for (StopWaypoint stopWaypoint : stopWaypoints) {
            this.add(stopWaypoint.getButton());
        }
    }

    private void configureMapMouseListeners() {
        MouseInputListener listener = new PanMouseInputListener(this);
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
    }

    private void setDefaultLocation() {
        GeoPosition idf = new GeoPosition(IDF_LATITUDE, IDF_LONGITUDE);
        setZoom(DEFAULT_ZOOM);
        setCenterPosition(idf);
    }

    private void createTiles() {
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        setTileFactory(tileFactory);
    }

    @Override
    public void zoomIn() {
        adjustZoom(-1);
    }

    private void updateVisibleStop() {
        int zoom = getZoom();
        for (StopWaypoint stopWaypoint : stopWaypoints) {
            stopWaypoint.getButton().updateVisibility(zoom);
        }
    }

    @Override
    public void zoomOut() {
        adjustZoom(1);
    }

    private void adjustZoom(int factor) {
        setZoom(getZoom() + factor);

        updateVisibleStop();
    }

    @Override
    public void setZoom(int zoom) {
        if (zoom > MAX_ZOOM) {
            return;
        }

        super.setZoom(zoom);
    }

    public Set<StopWaypoint> getWaypoints() {
        return stopWaypoints;
    }
}
