package fr.u_paris.gla.project;

import javax.swing.*;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.event.MouseInputListener;

public class AppWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    private Maps map;

    private JButton zoomIn;

    private JButton zoomOut;

    private JTextField departureField;
    private JTextField destinationField;
    private JTextField departureTimeField;

    public AppWindow(String title) {
        super();
        this.map = new Maps(WIDTH, HEIGHT);
        init(title);
    }

    private void init(String title) {
        initFrame(title);

        configureMapMouseListeners();

        /**
         * Container of the map and the buttons.
         */
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());

        // Add the map to the center of the container
        container.add(map, BorderLayout.CENTER);

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

        // Add the buttons to the bottom of the container
        container.add(buttonsPanel, BorderLayout.SOUTH);

        // Add the container to the frame
        add(container);

        // Create the form components
        JPanel formPanel = new JPanel(new GridLayout(3, 2));
        JLabel departureLabel = new JLabel("Departure:");
        departureField = new JTextField();
        JLabel destinationLabel = new JLabel("Destination:");
        destinationField = new JTextField();
        JLabel departureTimeLabel = new JLabel("Departure Time:");
        departureTimeField = new JTextField();
        JButton searchButton = new JButton("Search");

        formPanel.add(departureLabel);
        formPanel.add(departureField);
        formPanel.add(destinationLabel);
        formPanel.add(destinationField);
        formPanel.add(departureTimeLabel);
        formPanel.add(departureTimeField);
        formPanel.add(searchButton);

        // Add the form panel to the top of the container
        container.add(formPanel, BorderLayout.NORTH);
    }

    /**
     * Initialize the frame
     */
    private void initFrame(String title) {
        setTitle(title);
        // setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
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
