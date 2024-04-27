package fr.u_paris.gla.project;

import java.awt.BorderLayout;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import fr.u_paris.gla.project.idfnetwork.view.button.OpenLineDisplayPanelButton;
import fr.u_paris.gla.project.idfnetwork.view.button.OpenResearchPanelButton;
import fr.u_paris.gla.project.idfnetwork.view.button.ZoomInButton;
import fr.u_paris.gla.project.idfnetwork.view.button.ZoomOutButton;

public class AppWindow extends JFrame {
    private static final long serialVersionUID = 1L;

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

        OpenResearchPanelButton openResearchButton = new OpenResearchPanelButton(
                X_COL1_BUTTON, Y_COL1_BUTTON);
        OpenLineDisplayPanelButton lineButton = new OpenLineDisplayPanelButton(
                X_COL1_BUTTON, Y_COL1_BUTTON + Y_OFFSET/*
                                                        * ,
                                                        * researchPanel
                                                        */);

        map.setBounds(0, 0, MAX_WIDTH, MAX_HEIGHT);

        ZoomInButton zoomInButton = new ZoomInButton(
                X_COL1_BUTTON, Y_COL1_BUTTON + 2 * Y_OFFSET);
        ZoomOutButton zoomOutButton = new ZoomOutButton(
                X_COL1_BUTTON, Y_COL1_BUTTON + 3 * Y_OFFSET);

        zoomInButton.addObserver(map);
        zoomOutButton.addObserver(map);
        openResearchButton.addObserver(researchPanel);

        lPane.add(map, 0, 0);
        lPane.add(openResearchButton, 1, 0);
        lPane.add(lineButton, 1, 0);
        lPane.add(zoomInButton, 1, 0);
        lPane.add(zoomOutButton, 1, 0);

        // Add the map to the center of the container
        container.add(lPane, BorderLayout.CENTER);
        container.add(researchPanel, BorderLayout.WEST);

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
