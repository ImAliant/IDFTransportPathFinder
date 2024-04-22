package fr.u_paris.gla.project;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import javax.swing.event.MouseInputListener;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;

import com.graphhopper.util.shapes.GHPoint3D;

import fr.u_paris.gla.project.idfnetwork.Network;
import fr.u_paris.gla.project.idfnetwork.Stop;
import fr.u_paris.gla.project.idfnetwork.view.RoutingData;
import fr.u_paris.gla.project.idfnetwork.view.StopRender;
import fr.u_paris.gla.project.idfnetwork.view.StopWaypoint;

public class Maps extends JXMapViewer {
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
    public static final int MAX_ZOOM = 5;

    private transient Set<StopWaypoint> stopWaypoints = new HashSet<>();

    /**
     * Constructor of the maps.
     * 
     * @param width
     * @param height
     */
    public Maps(int width, int height) {
        super();

        init(width, height);
    }

    /**
     * Initialize the map.
     * 
     * @param width width of the panel
     * @param height height of the panel
     */
    private void init(int width, int height) {
        setSize(width, height);

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
        this.addMouseWheelListener(new ZoomMouseWheelListenerCursor(this));
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

    public void zoomIn() {
        adjustZoom(-1);
    }

    public void zoomOut() {
        adjustZoom(1);
    }

    private void adjustZoom(int factor) {
        setZoom(getZoom() + factor);
    }

    @Override
    public void setZoom(int zoom) {
        if (zoom > MAX_ZOOM) {
            return;
        }

        super.setZoom(zoom);
    }

    public void setRoutingData(List<RoutingData> routingData) {
        this.routingData = routingData;
        repaint();
    }

    private List<RoutingData> routingData;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (routingData != null && !routingData.isEmpty()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Path2D p2 = new Path2D.Double();
            first = true;
            for (RoutingData d : routingData) {
                draw(p2, d);
            }
            g2.setColor(new Color(28, 23, 255));
            g2.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.draw(p2);
            g2.dispose();
        }
    }

    private boolean first = true;

    private void draw(Path2D p2, RoutingData d) {
        d.getPointList().forEach(new Consumer<GHPoint3D>() {
            @Override
            public void accept(GHPoint3D t) {
                Point2D point = convertGeoPositionToPoint(new GeoPosition(t.getLat(), t.getLon()));
                if (first) {
                    first = false;
                    p2.moveTo(point.getX(), point.getY());
                } else {
                    p2.lineTo(point.getX(), point.getY());
                }
            }
        });
    }

}
