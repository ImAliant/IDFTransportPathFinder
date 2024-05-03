package fr.u_paris.gla.crazytrip.view.panel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
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

import fr.u_paris.gla.crazytrip.algo.Itinerary;
import fr.u_paris.gla.crazytrip.idfnetwork.Stop;
import fr.u_paris.gla.crazytrip.idfnetwork.TravelPath;
import fr.u_paris.gla.crazytrip.idfnetwork.line.Line;
import fr.u_paris.gla.crazytrip.idfnetwork.network.Network;
import fr.u_paris.gla.crazytrip.observer.ArrivalMapButtonObserver;
import fr.u_paris.gla.crazytrip.observer.DepartureMapButtonObserver;
import fr.u_paris.gla.crazytrip.observer.GeoPositionObserver;
import fr.u_paris.gla.crazytrip.observer.ItineraryObserver;
import fr.u_paris.gla.crazytrip.observer.LinePaintObserver;
import fr.u_paris.gla.crazytrip.observer.ZoomInObserver;
import fr.u_paris.gla.crazytrip.observer.ZoomOutObserver;
import fr.u_paris.gla.crazytrip.view.button.waypoint.StopWaypoint;
import fr.u_paris.gla.crazytrip.view.painter.ItineraryPainter;
import fr.u_paris.gla.crazytrip.view.painter.RoutePainter;
import fr.u_paris.gla.crazytrip.view.painter.StopRender;
import fr.u_paris.gla.crazytrip.view.progress_bar.LoadingProgressBar;

import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;

public class Maps extends JXMapViewer implements ZoomInObserver, ZoomOutObserver, DepartureMapButtonObserver, ArrivalMapButtonObserver , ItineraryObserver, LinePaintObserver {
    /**
     * List of observers for the departure.
     */
    private transient List<GeoPositionObserver> departureObservers = new ArrayList<>();
    /**
     * List of observers for the arrival.
     */
    private transient List<GeoPositionObserver> arrivalObservers = new ArrayList<>();

    private static final long serialVersionUID = 1L;
    /**
     * Default latitude of the map. Latitude of Paris.
     */
    private static final double IDF_LATITUDE = 48.82961436445113;
    /**
     * Default longitude of the map. Longitude of Paris.
     */
    private static final double IDF_LONGITUDE = 2.38175332546234;
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

    /**
     * Boolean to know if the departure button is clicked.
     */
    private boolean isDepGeoPositionClickEnabled = false;
    /**
     * Boolean to know if the arrival button is clicked.
     */
    private boolean isArrGeoPositionClickEnabled = false;
    /**
     * GeoPosition clicked by the user.
     */
    private GeoPosition geoPositionClicked;

    /**
     * Set of stop waypoints.
     */
    private transient Set<StopWaypoint> stopWaypoints = new HashSet<>();
    /**
     * Painter for the route.
     */
    private transient Painter<JXMapViewer> routePainter;
    /**
     * Painter for the waypoints.
     */
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

    /**
     * Display the network on the map.
     */
    private void displayNetwork() {
        Network network = Network.getInstance();

        List<Stop> stops = network.getStops();
        stops.parallelStream().forEach(this::addStopWaypoint);

        initWaypoint();

    }

    /**
     * Add a stop waypoint to the map.
     * @param stop Stop to add.
     */
    private void addStopWaypoint(Stop stop) {
        stopWaypoints.add(
                new StopWaypoint(stop)
        );
    }

    /**
     * Initialize the waypoints.
     */
    private void initWaypoint() {
        this.wayPointPainter = new StopRender();
        wayPointPainter.setWaypoints(stopWaypoints);

        // Add the button of the waypoints to the map
        for (StopWaypoint stopWaypoint : stopWaypoints) {
            this.add(stopWaypoint.getButton());
        }

        this.setOverlayPainter(wayPointPainter);
    }

    /**
     * Configure the mouse listeners of the map.
     */
    private void configureMapMouseListeners() {
        MouseInputListener listener = new PanMouseInputListener(this);
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);

        // Add a mouse listener to get the position of the click
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean isLeftButtonClicked = e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1;
                if (isLeftButtonClicked) {
                    geoPositionClicked = getGeoPosition(e);
                    if (isDepGeoPositionClickEnabled) {
                        notifyDepObservers();
                    } else if (isArrGeoPositionClickEnabled){
                        notifyArrObservers();
                    }
                }
            }
        });
    }

    /**
     * Convert a point to a GeoPosition.
     * @param p Point to convert.
     * @return GeoPosition of the point.
     */
    private GeoPosition getGeoPosition(MouseEvent e) {
        Point p = e.getPoint();

        return convertPointToGeoPosition(p);
    }

    /**
     * Set the default location of the map.
     */
    private void setDefaultLocation() {
        GeoPosition idf = new GeoPosition(IDF_LATITUDE, IDF_LONGITUDE);
        setZoom(DEFAULT_ZOOM);
        setCenterPosition(idf);
    }

    /**
     * Create the tiles of the map.
     */
    private void createTiles() {
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        setTileFactory(tileFactory);
    }

    @Override
    public void zoomIn() {
        adjustZoom(-1);
    }

    /**
     * Update the visibility of the stops.
     */
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

    /**
     * Adjust the zoom of the map.
     * @param factor Factor to adjust the zoom.
     */
    private void adjustZoom(int factor) {
        setZoom(getZoom() + factor);

        updateVisibleStop();
    }

    @Override
    public void onChangeDeparture(boolean value) {
        isDepGeoPositionClickEnabled = value;
    }

    @Override
    public void onChangeArrival(boolean value) {
        isArrGeoPositionClickEnabled = value;
    }

    @Override
    public void setZoom(int zoom) {
        if (zoom > MAX_ZOOM) {
            return;
        }
        super.setZoom(zoom);
    }

    /**
     * Set the painter of the itinerary.
     * @param itinerary Itinerary to display.
     */
    public void setItineraryPainter(Itinerary itinerary){
        this.routePainter = new ItineraryPainter(itinerary);
    }

    /**
     * Get the waypoints of the map.
     * @return Set of waypoints.
     */
    public Set<StopWaypoint> getWaypoints() {
        return stopWaypoints;
    }

    /**
     * Draw a line on the map.
     * @param line Line to draw.
     */
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

    /**
     * Add an observer for the departure.
     * @param observer Observer to add.
     */
    public void addDepartureObserver(GeoPositionObserver observer){
        departureObservers.add(observer);
    }

    /**
     * Add an observer for the arrival.
     * @param observer Observer to add.
     */
    public void addArrivalObserver(GeoPositionObserver observer) {
        arrivalObservers.add(observer);
    }

    /**
     * Notify the observers for the departure.
     */
    public void notifyDepObservers() {
        for (GeoPositionObserver observer : departureObservers) {
            observer.getGeoPosition(geoPositionClicked);
        }

        isDepGeoPositionClickEnabled = false;
    }

    /**
     * Notify the observers for the arrival.
     */
    public void notifyArrObservers(){
        for (GeoPositionObserver observer : arrivalObservers){
            observer.getGeoPosition(geoPositionClicked);
        }

        isArrGeoPositionClickEnabled = false;
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

    /**
     * Delete the line displayed on the map.
     */
    public void deleteLineDisplayed(){
        this.setOverlayPainter(wayPointPainter);
        this.repaint();
    }
}
