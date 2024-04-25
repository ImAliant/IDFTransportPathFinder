package fr.u_paris.gla.project;


import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import javax.swing.event.MouseInputListener;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;

import fr.u_paris.gla.project.idfnetwork.Line;
import fr.u_paris.gla.project.idfnetwork.LineType;
import fr.u_paris.gla.project.idfnetwork.Network;
import fr.u_paris.gla.project.idfnetwork.Stop;
import fr.u_paris.gla.project.idfnetwork.TravelPath;
import fr.u_paris.gla.project.idfnetwork.view.RoutePainter;
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
    public static final int MAX_ZOOM = 50;

    private transient Set<StopWaypoint> stopWaypoints = new HashSet<>();
    WaypointPainter<StopWaypoint> wp ;
    RoutePainter routePainter;

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
        
        for (StopWaypoint stopWaypoint : stopWaypoints) {
            this.add(stopWaypoint.getButton());
        }
        //Ligne du RER B
        Line line = Network.getInstance().findLine("B",LineType.RER);
        this.drawLine(line);

        //Create a compound painter that uses both the route-painter and the waypoint-painter
        List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
        painters.add(routePainter);
        painters.add(wp);
        CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
        this.setOverlayPainter(painter);
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

    void drawLine(Line line){
        List<TravelPath> paths = line.getPaths();
        if (line.getColor().length() != 6) {
            routePainter = new RoutePainter(paths);
        }
        else{

        Color couleur = Color.decode("#" + line.getColor());

        routePainter = new RoutePainter(paths,couleur);
        }
    }


}
