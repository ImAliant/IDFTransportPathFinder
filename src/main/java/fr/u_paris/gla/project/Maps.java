package fr.u_paris.gla.project;


import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import javax.swing.event.MouseInputListener;

import fr.u_paris.gla.project.idfnetwork.*;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;

import fr.u_paris.gla.project.idfnetwork.stop.Stop;

import fr.u_paris.gla.project.idfnetwork.view.progress_bar.LoadingProgressBar;
import fr.u_paris.gla.project.idfnetwork.view.ItineraryPainter;
import fr.u_paris.gla.project.idfnetwork.view.RoutePainter;
import fr.u_paris.gla.project.idfnetwork.view.waypoint.StopRender;
import fr.u_paris.gla.project.idfnetwork.view.waypoint.StopWaypoint;
import fr.u_paris.gla.project.observer.ItineraryObserver;
import fr.u_paris.gla.project.observer.LinePaintObserver;
import fr.u_paris.gla.project.observer.ZoomInObserver;
import fr.u_paris.gla.project.observer.ZoomOutObserver;

public class Maps extends JXMapViewer implements ZoomInObserver, ZoomOutObserver , ItineraryObserver, LinePaintObserver {
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
    private transient Painter<JXMapViewer> routePainter;
    private transient WaypointPainter<StopWaypoint> wayPointPainter;

    /**
     * Constructor of the maps.
     */
    public Maps() {
        super();

        init();

        LoadingProgressBar.getInstance().incrementProgress(25);
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
        stops.parallelStream().forEach(this::addStopWaypoint);

        initWaypoint();

    }


    private void addStopWaypoint(Stop stop) {
        stopWaypoints.add(
                new StopWaypoint(stop)
        );
    }

    private void initWaypoint() {
        this.wayPointPainter = new StopRender();
        wayPointPainter.setWaypoints(stopWaypoints);

        for (StopWaypoint stopWaypoint : stopWaypoints) {
            this.add(stopWaypoint.getButton());
        }

        this.setOverlayPainter(wayPointPainter);
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

    public void setItineraryPainter(Itinerary itinerary){
        this.routePainter = new ItineraryPainter(itinerary);
    }

    public Set<StopWaypoint> getWaypoints() {
        return stopWaypoints;
    }


    public void drawLine(Line line){
        List<TravelPath> paths = line.getPaths();
        if (line.getColor().length() != 6) {
            routePainter = new RoutePainter(paths);
        }
        else{
            Color couleur = Color.decode("#" + line.getColor());
            routePainter = new RoutePainter(paths,couleur);
        }

        List <Painter<JXMapViewer>> painters = new java.util.ArrayList<>();
        painters.add(routePainter);
        painters.add(wayPointPainter);
        this.setOverlayPainter(new CompoundPainter<>(painters));
        this.repaint();
    }

    @Override
    public void showLine(Line line) {
        drawLine(line);
    }

    @Override
    public void showItinerary(Itinerary itinerary) {
        if (itinerary == null || itinerary.getStops().isEmpty() || itinerary.getLines().isEmpty() ) {
            this.setOverlayPainter(wayPointPainter);
            this.repaint();
            return ;
        }

        this.setItineraryPainter(itinerary);
        List <Painter<JXMapViewer>> painters = new java.util.ArrayList<>();
        painters.add(routePainter);
        painters.add(wayPointPainter);
        this.setOverlayPainter(new CompoundPainter<>(painters));
        this.repaint();
    }

    // method to delete the line or itinerary displayed
    public void deleteLineDisplayed(){
        this.setOverlayPainter(wayPointPainter);
        this.repaint();
    }
}
