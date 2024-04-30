package fr.u_paris.gla.project;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AppWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JPanel container;

    private InteractiveButtonPanel interactiveButtonPanel;
    private Maps map;
    private ResearchPanel researchPanel;
    private LineDisplayPanel lineDisplayPanel;
    private ShowResultPanel showResultPanel;

    public AppWindow(String title) {
        super();

        this.container = new JPanel();
        this.map = new Maps();
        this.researchPanel = new ResearchPanel();
        this.lineDisplayPanel = new LineDisplayPanel();
<<<<<<< src/main/java/fr/u_paris/gla/project/AppWindow.java
        this.showResultPanel = new ShowResultPanel();
        this.interactiveButtonPanel = new InteractiveButtonPanel();

>>>>>>> src/main/java/fr/u_paris/gla/project/AppWindow.java
        init(title);

        pack();
    }

    private void init(String title) {
        initFrame(title);

        setLayoutContainer();

        initializeComponents();

        initializeObservers();

        addComponentsToContainer();

        add(container);
    }

    private void setLayoutContainer() {
        container.setLayout(new BorderLayout());
    }

    private void initializeComponents() {
        setGlassPane(interactiveButtonPanel);
        interactiveButtonPanel.setVisible(true);
    }

    private void initializeObservers() {
<<<<<<< src/main/java/fr/u_paris/gla/project/AppWindow.java
        researchPanel.getSearchButton().addObserver(showResultPanel);
        interactiveButtonPanel.getZoomIn().addObserver(map);
        interactiveButtonPanel.getZoomOut().addObserver(map);
        interactiveButtonPanel.getOpenResearchButton().addObserver(researchPanel);
        interactiveButtonPanel.getOpenLineButton().addObserver(lineDisplayPanel);
>>>>>>> src/main/java/fr/u_paris/gla/project/AppWindow.java
    }

    private void addComponentsToContainer() {
        container.add(map, BorderLayout.CENTER);
        container.add(researchPanel, BorderLayout.WEST);
        container.add(lineDisplayPanel, BorderLayout.NORTH);
        container.add(showResultPanel, BorderLayout.EAST);
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
    }

    // TESTABILITY
    /**
     * @return the map
     */
    public Maps getMap() {
        return map;
    }
}
