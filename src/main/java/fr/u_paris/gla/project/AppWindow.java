package fr.u_paris.gla.project;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;

public class AppWindow extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Width of the window.
     */
    private static final int WIDTH = 1600;
    /**
     * Height of the window.
     */
    private static final int HEIGHT = 900;

    private final String title;
    /**
     * This show a map from OpenStreetMap.
     */
    private Maps map = new Maps(WIDTH, HEIGHT);
    /**
     * Button to zoom in the map.
     */
    private JButton zoomIn;
    /**
     * Button to zoom out the map.
     */
    private JButton zoomOut;
    /**
     * Container of the map and the buttons.
     */
    private JPanel container;
    /**
     * Panel to put the buttons.
     */
    private JPanel buttonsPanel;

    public AppWindow(String title) {
        super();

        this.title = title;
        init();
    }

    /**
     * Initialize all the components of the window.
     */
    private void init() {
        initFrame();

        configureMapMouseListeners();

        container = new JPanel();
        container.setLayout(new BorderLayout());

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2));

        zoomIn = new JButton("Zoom In");
        zoomIn.addActionListener(e -> map.zoomIn());
        zoomOut = new JButton("Zoom Out");
        zoomOut.addActionListener(e -> map.zoomOut());

        buttonsPanel.add(zoomOut);
        buttonsPanel.add(zoomIn);

        container.add(map, BorderLayout.CENTER);
        container.add(buttonsPanel, BorderLayout.SOUTH);

        add(container, BorderLayout.CENTER);
    }

    /**
    * Initialize the frame
    */
    private void initFrame() {
        setTitle(title);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    /**
     * Configure the mouse listeners of the map.
     */
    private void configureMapMouseListeners() {
        MouseInputListener listener = new PanMouseInputListener(map);
        map.addMouseListener(listener);
        map.addMouseMotionListener(listener);
        map.addMouseWheelListener(new ZoomMouseWheelListenerCursor(map));
    }
}
