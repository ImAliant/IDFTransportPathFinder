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

    public AppWindow(String title) {
        super();

        init(title);
    }

    /**
     * Initialize all the components of the window.
     */
    private void init(String title) {
        initFrame(title);

        configureMapMouseListeners();

        /**
        * Container of the map and the buttons.
        */
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        /**
        * Panel to put the buttons.
        */
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2));

        /**
        * Button to zoom in the map.
        */
        zoomIn = new JButton("Zoom In");
        zoomIn.addActionListener(e -> map.zoomIn());
        /**
        * Button to zoom out the map.
        */
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
    private void initFrame(String title) {
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

    // TESTABILITY
    /**
     * @return the map
     */
    public Maps getMap() {
        return map;
    }
    /**
     * @return the zoomInButton
     */
    public JButton getZoomInButton() {
        return zoomIn;
    }
    /**
     * @return the zoomOutButton
     */
    public JButton getZoomOutButton() {
        return zoomOut;
    }
}
