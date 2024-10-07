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

import fr.u_paris.gla.crazytrip.gui.listener.SelectPositionListener;
import fr.u_paris.gla.crazytrip.gui.maps.popup.SelectPositionPopupMenu;
import fr.u_paris.gla.crazytrip.gui.maps.waypoint.StationRender;
import fr.u_paris.gla.crazytrip.gui.maps.waypoint.StationWaypoint;
import fr.u_paris.gla.crazytrip.model.Station;

/**
 * <p>Class representing the map of the application.</p>
 * 
 * <p>This class extends the JXMapViewer class from the JXMapViewer2 library.</p>
 * 
 * <p>It is used to display the map of the application and to interact with it.</p>
 * 
 * @see JXMapViewer
 */
public class Maps extends JXMapViewer {
    /** Default latitude of the map. Latitude of Paris. */
    private static final double IDF_LATITUDE = 48.860031;
    /** Default longitude of the map. Longitude of Paris. */
    private static final double IDF_LONGITUDE = 2.342877;
    /** All the station waypoints on the map. */
    private transient Set<StationWaypoint> allStationWaypoints = new HashSet<>();
    /** The painter for the waypoints. */
    private transient StationRender wayPointPainter;
    /** The drawer for the path. */
    private transient PathDrawer drawer;
    /** The handler for the zoom. */
    private transient ZoomHandler zoomHandler;
    /** The popup menu for the map. */
    private transient SelectPositionPopupMenu popupMenu;

    /**
     * Constructor for the Maps class.
     * 
     * Initializes the map and its components.
     */
    public Maps() {
        super();

        init();
    }

    /**
     * Initializes the map and its components.
     * 
     * <p>Creates the tile factory, sets the default location and zoom, and configures the mouse listeners.</p>
     * <p>Also initializes the path drawer, the zoom handler, and the popup menu.</p>
     */
    private void init() {
        // Tile factory to get the map
        createTiles();

        // Define the default position and zoom
        setDefaultLocation();

        configureMapMouseListeners();

        this.drawer = new PathDrawer(this);
        this.zoomHandler = new ZoomHandler(this);
        this.popupMenu = new SelectPositionPopupMenu();
    }

    /**
     * Creates the tile factory for the map.
     * 
     * <p>Uses the OpenStreetMap tile factory info to create the tile factory.</p>
     * 
     * @see OSMTileFactoryInfo
     * @see DefaultTileFactory
     * @see TileFactoryInfo
     */
    private void createTiles() {
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        setTileFactory(tileFactory);
    }

    /**
     * Sets the default location of the map.
     * 
     * <p>Sets the center position of the map to the default location.</p>
     */
    private void setDefaultLocation() {
        GeoPosition idf = new GeoPosition(IDF_LATITUDE, IDF_LONGITUDE);
        setCenterPosition(idf);
    }

    /**
     * Configures the mouse listeners for the map.
     * 
     * <p>Creates the mouse listeners for the map and adds them to the map.</p>
     */
    private void configureMapMouseListeners() {
        MouseInputListener mouseInputlistener = new PanMouseInputListener(this);
        SelectPositionListener selectPositionListener = new SelectPositionListener(this);
        addMouseListener(mouseInputlistener);
        addMouseMotionListener(mouseInputlistener);

        addMouseListener(selectPositionListener);
    }

    /**
     * Initializes the painter for the waypoints.
     * 
     * <p>Creates the painter for the waypoints and sets the waypoints to display.</p>
     */
    public void initPainter() {
        this.wayPointPainter = new StationRender();
        wayPointPainter.setWaypoints(allStationWaypoints);
        this.setOverlayPainter(wayPointPainter);
    }

    /**
     * Adds a station marker to the map.
     * 
     * <p>Creates a station waypoint for the station and adds it to the map.</p>
     * 
     * @param station The station to add to the map.
     * 
     * @see Station
     */
    public void addStationMarker(Station station) {
        StationWaypoint waypoint = new StationWaypoint(station);
        allStationWaypoints.add(waypoint);
        this.add(waypoint.getButton());
    }

    /**
     * Updates the visibility of the stations on the map.
     * 
     * <p>Updates the visibility of the stations on the map according to the current zoom level.</p>
     */
    public void updateStationsVisibility() {
        int zoom = getZoom();

        allStationWaypoints.parallelStream().forEach(waypoint -> waypoint.updateVisibility(zoom));
    }

    public StationRender getWayPointPainter() {
        return wayPointPainter;
    }

    public Set<StationWaypoint> getAllStationWaypoints() {
        return allStationWaypoints;
    }

    public PathDrawer getPathPaiter() {
        return drawer;
    }

    public ZoomHandler getZoomHandler() {
        return zoomHandler;
    }

    public SelectPositionPopupMenu getPopupMenu() {
        return popupMenu;
    }
}
