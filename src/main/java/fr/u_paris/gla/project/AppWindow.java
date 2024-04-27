package fr.u_paris.gla.project;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import fr.u_paris.gla.project.idfnetwork.view.button.OpenLineDisplayPanelButton;
import fr.u_paris.gla.project.idfnetwork.view.button.OpenResearchPanelButton;

public class AppWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    private static final int X_OFFSET = 0;
    private static final int Y_OFFSET = 40;

    private static final int X_COL1_BUTTON = 10;
    private static final int Y_COL1_BUTTON = 10;

    private static final int MAX_WIDTH = 1920;
    private static final int MAX_HEIGHT = 1080;

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JPanel container;

    private Maps map;
    private ResearchPanel researchPanel;

    private JButton zoomIn;
    private JButton zoomOut;

    public AppWindow(String title) {
        super();

        this.container = new JPanel();
        this.map = new Maps();
        this.researchPanel = new ResearchPanel();

        init(title);

        pack();
    }

    private void init(String title) {
        initFrame(title);

        addPanelAndButtons(container);
    }

    private void addPanelAndButtons(JPanel container) {
        /**
         * Container of the map and the buttons.
         */
        container.setLayout(new BorderLayout());

        JLayeredPane lPane = new JLayeredPane();
        lPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        OpenResearchPanelButton searchButton = new OpenResearchPanelButton(
                X_COL1_BUTTON, Y_COL1_BUTTON,
                researchPanel);
        OpenLineDisplayPanelButton lineButton = new OpenLineDisplayPanelButton(
                X_COL1_BUTTON, Y_COL1_BUTTON + 40/*,
                researchPanel */);

        /* JButton test2 = new JButton();
        test2.setBounds(10, 50, 25, 25); */

        map.setBounds(0, 0, MAX_WIDTH, MAX_HEIGHT);

        lPane.add(map, 0, 0);
        lPane.add(searchButton, 1, 0);
        lPane.add(lineButton, 1, 0);

        // Add the map to the center of the container
        container.add(lPane, BorderLayout.CENTER);
        container.add(researchPanel, BorderLayout.WEST);

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
        super.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
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
}
