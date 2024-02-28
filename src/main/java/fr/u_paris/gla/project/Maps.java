package fr.u_paris.gla.project;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;

public class Maps extends JFrame {
    private static final long serialVersionUID = 1L;

    private static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final double DEFAULT_LATITUDE = 48.85656189753147; 
    private static final double DEFAULT_LONGITUDE = 2.4002576758282745;
    private static final int DEFAULT_ZOOM = 2;
    private static final int MAX_ZOOM = 5;

    private MapViewer mapViewer = new MapViewer(MAX_ZOOM);
    private JPanel buttonsPanel = new JPanel();
    private JButton button1 = new JButton();
    private JButton button2 = new JButton();

    public Maps() {
        initComponents();
        init();
    }

    private void initComponents() {
        setTitle("Maps");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        buttonsPanel.setLayout(new GridLayout(1, 2));

        addToFrame();
    }

    private void addToFrame() {
        add(mapViewer, BorderLayout.CENTER);
        buttonsPanel.add(button1);
        buttonsPanel.add(button2);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void init() {
        // Create a TileFactoryInfo for OpenStreetMap
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        mapViewer.setTileFactory(tileFactory);

        // Default location
        GeoPosition idf = new GeoPosition(DEFAULT_LATITUDE, DEFAULT_LONGITUDE);
        mapViewer.setZoom(DEFAULT_ZOOM);
        mapViewer.setCenterPosition(idf);

        // Mouse listener to update the current position
        MouseInputListener ml = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(ml);
        mapViewer.addMouseMotionListener(ml);
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(mapViewer));

        // Buttons
        button1.setText("Click me");
        button1.addActionListener(e -> System.out.println("Button clicked"));
        button2.setText("Click me too");
        button2.addActionListener(e -> System.out.println("Button clicked too"));
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new Maps().setVisible(true));
    }
}
