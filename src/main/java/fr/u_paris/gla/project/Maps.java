package fr.u_paris.gla.project;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;

public class Maps extends JFrame{
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JXMapViewer mapViewer = new JXMapViewer();

    public Maps() {
        initComponents();
        init();
    }

    private void initComponents() {
        setTitle("Maps");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addToFrame();
    }

    private void addToFrame() {
        add(mapViewer);
    }

    private void init() {
        // Create a TileFactoryInfo for OpenStreetMap
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        mapViewer.setTileFactory(tileFactory);

        // Default location
        double defaultLatitude = 48.856158;
        double defaultLongitude = 2.4008184;
        GeoPosition geo = new GeoPosition(defaultLatitude, defaultLongitude);
        mapViewer.setAddressLocation(geo);
        int defaultZoom = 12;
        mapViewer.setZoom(defaultZoom);

        // Mouse listener to update the current position
        MouseInputListener ml = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(ml);
        mapViewer.addMouseMotionListener(ml);
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new Maps().setVisible(true));
    }
}
