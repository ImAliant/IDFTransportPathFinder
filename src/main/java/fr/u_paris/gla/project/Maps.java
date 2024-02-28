package fr.u_paris.gla.project;

import java.awt.BorderLayout;
import java.awt.GridLayout;
// import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Maps extends JFrame {
    private static final long serialVersionUID = 1L;

    private static final String TITLE = "Maps";
    private static final int WIDTH = 1600;//Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int HEIGHT = 900;//Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final double IDF_LATITUDE = 48.85656189753147; 
    private static final double IDF_LONGITUDE = 2.4002576758282745;
    private static final int DEFAULT_ZOOM = 2;
    private static final int MAX_ZOOM = 5;

    private MapViewer mapViewer = new MapViewer(MAX_ZOOM);
    private JPanel buttonsPanel = new JPanel();
    private JButton zoomIn = new JButton();
    private JButton zoomOut = new JButton();

    public Maps() {
        initComponents();
        init();
    }

    private void initComponents() {
        // Frame parameters
        configureFrame();

        // buttonsPanel parameters
        buttonsPanel.setLayout(new GridLayout(1, 2));

        // add components to the frame
        addToFrame();
    }

    private void init() {
        // Create a tilefactory for OpenStreetMap
        createTileFactory();

        // Default location
        setDefaultLocation();

        // Mouse listener to update the current position
        configureMapViewerMouseListeners();

        // Get the GPS coordinates of the clicked point
        registerMapClickForGPSCoordinates();

        // Waypoints
        Set<Waypoint> waypoints = createMapWaypoints();

        // Create a waypoint painter that takes all the waypoints
        paintWaypoints(waypoints);

        // Buttons
        configureZoomButton(zoomIn, "Zoom in", -1);
        configureZoomButton(zoomOut, "Zoom out", 1);
    }

    private void configureFrame() {
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void addToFrame() {
        add(mapViewer, BorderLayout.CENTER);
        buttonsPanel.add(zoomOut);
        buttonsPanel.add(zoomIn);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void createTileFactory() {
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        mapViewer.setTileFactory(tileFactory);
    }

    private void setDefaultLocation() {
        GeoPosition idf = new GeoPosition(IDF_LATITUDE, IDF_LONGITUDE);
        mapViewer.setZoom(DEFAULT_ZOOM);
        mapViewer.setCenterPosition(idf);
    }

    private void configureMapViewerMouseListeners() {
        MouseInputListener ml = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(ml);
        mapViewer.addMouseMotionListener(ml);
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(mapViewer));
    }

    private void registerMapClickForGPSCoordinates() {
        mapViewer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GeoPosition pos = mapViewer.convertPointToGeoPosition(e.getPoint());
                System.out.println("Latitude: " + pos.getLatitude() + " Longitude: " + pos.getLongitude());
            }
        });
    }

    private Set<Waypoint> createMapWaypoints() {
        GeoPosition maison = new GeoPosition(48.85656189753147, 2.4002576758282745);
        GeoPosition place = new GeoPosition(48.85577450348078, 2.4010621837844637);

        List<GeoPosition> list = Arrays.asList(maison, place);
        mapViewer.zoomToBestFit(new HashSet<>(list), 0.7);

        return new HashSet<>(Arrays.asList(
            new DefaultWaypoint(maison),
            new DefaultWaypoint(place)
        ));
    }

    private void paintWaypoints(Set<Waypoint> waypoints) {
        WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
        waypointPainter.setWaypoints(waypoints);
        mapViewer.setOverlayPainter(waypointPainter);
    }

    private void configureZoomButton(JButton button, String label, int zoomChange) {
        button.setText(label);
        button.addActionListener(e -> mapViewer.setZoom(mapViewer.getZoom() + zoomChange));
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new Maps().setVisible(true));
    }
}
