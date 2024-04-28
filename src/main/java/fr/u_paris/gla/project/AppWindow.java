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

    // need to be reworked
    private static final int MAX_WIDTH = 3500;
    private static final int MAX_HEIGHT = 3500;

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JPanel container;

    private JLayeredPane lPane;
    private Maps map;
    private ResearchPanel researchPanel;
    private LineDisplayPanel lineDisplayPanel;

    private ZoomInButton zoomIn;
    private ZoomOutButton zoomOut;
    private OpenResearchPanelButton openResearchButton;
    private OpenLineDisplayPanelButton openLineButton;

    public AppWindow(String title) {
        super();

        this.container = new JPanel();
        this.map = new Maps();
        this.researchPanel = new ResearchPanel();
        this.lineDisplayPanel = new LineDisplayPanel();
        
        init(title);
        
        pack();
    }

    private void init(String title) {
        initFrame(title);
        
        setLayoutContainer();
        
        initializeComponentOfLayeredPane();
        
        initializeObservers();
        
        addComponentsToContainer();
        
        add(container);
    }

    private void setLayoutContainer() {
        container.setLayout(new BorderLayout());
    }

    private void initializeComponentOfLayeredPane() {
        lPane = new JLayeredPane();
        lPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        map.setBounds(0, 0, MAX_WIDTH, MAX_HEIGHT);

        openResearchButton = new OpenResearchPanelButton(X_COL1_BUTTON, Y_COL1_BUTTON);
        openLineButton = new OpenLineDisplayPanelButton(X_COL1_BUTTON, Y_COL1_BUTTON + Y_OFFSET);

        zoomIn = new ZoomInButton(X_COL1_BUTTON, Y_COL1_BUTTON + 2 * Y_OFFSET);
        zoomOut = new ZoomOutButton(X_COL1_BUTTON, Y_COL1_BUTTON + 3 * Y_OFFSET);

        lPane.add(map, 0, 0);
        lPane.add(openResearchButton, 1, 0);
        lPane.add(openLineButton, 1, 0);
        lPane.add(zoomIn, 1, 0);
        lPane.add(zoomOut, 1, 0);
    }

    private void initializeObservers() {
        zoomIn.addObserver(map);
        zoomOut.addObserver(map);
        openResearchButton.addObserver(researchPanel);
        openLineButton.addObserver(lineDisplayPanel);
    }

    private void addComponentsToContainer() {
        container.add(lPane, BorderLayout.CENTER);
        container.add(researchPanel, BorderLayout.WEST);
        container.add(lineDisplayPanel, BorderLayout.NORTH);
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
