package fr.u_paris.gla.project;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jxmapviewer.viewer.GeoPosition;

import fr.u_paris.gla.project.idfnetwork.view.RoutingData;
import fr.u_paris.gla.project.idfnetwork.view.RoutingService;
import fr.u_paris.gla.project.idfnetwork.view.StopWaypoint;

public class AppWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    private Maps map;

    private JButton zoomIn;

    private JButton zoomOut;
    private Set<StopWaypoint> stopWaypoints;

    public AppWindow(String title) {
        super();
        this.map = new Maps(WIDTH, HEIGHT);
        init(title);
    }

    private void init(String title) {
        initFrame(title);

        JPanel container = new JPanel();
        addMapAndButtons(container);

        ResearchPanel researchPanel = new ResearchPanel();
        container.add(researchPanel, BorderLayout.WEST);

    }

    private void addMapAndButtons(JPanel container) {
        /**
         * Container of the map and the buttons.
         */
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
    public void createRoutingdata(){ //  Routing Data
        if (stopWaypoints.size() == 2) {
            GeoPosition start = null;
            GeoPosition end = null;
            for (StopWaypoint w : stopWaypoints) {
                if (w.getPointType() == StopWaypoint.PointType.START) {
                    start = w.getPosition();
                } else if (w.getPointType() == StopWaypoint.PointType.END) {
                    end = w.getPosition();
                }
            }
            List <RoutingData> routingData =  new LinkedList<>();
            if (start != null && end != null) {
             routingData = RoutingService.getInstance().routing(start.getLatitude(), start.getLongitude(), end.getLatitude(), end.getLongitude());

            } else {
                routingData.clear();
            }
            jXMapViewer.setRoutingData(routingData);
        }
}

}
