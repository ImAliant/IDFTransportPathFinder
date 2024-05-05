package fr.u_paris.gla.crazytrip.view.window;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.u_paris.gla.crazytrip.view.panel.InteractiveButtonPanel;
import fr.u_paris.gla.crazytrip.view.panel.LineDisplayPanel;
import fr.u_paris.gla.crazytrip.view.panel.Maps;
import fr.u_paris.gla.crazytrip.view.panel.ResearchPanel;
import fr.u_paris.gla.crazytrip.view.panel.ShowResultPanel;
/**
 * This class provides the main application window for a GUI application.
 * 
 */

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

    /**
     * Constructs a new application window with the specified title.
     * @param title the title to be displayed in the application window's title bar
     */
    public AppWindow(String title) {
        super();

        this.container = new JPanel();
        this.map = new Maps();
        this.researchPanel = new ResearchPanel();
        this.lineDisplayPanel = new LineDisplayPanel();
        this.showResultPanel = new ShowResultPanel();
        this.interactiveButtonPanel = new InteractiveButtonPanel();

        init(title);

        pack();
    }
    /**
     * Initializes the frame with a specific title, sets the layout container, registers observers and listeners, 
     * and adds components to the container.
     */
    private void init(String title) {
        initFrame(title);

        setLayoutContainer();
        
        initializeObservers();
        initializeListeners();

        addComponentsToContainer();

        add(container);
    }

    private void setLayoutContainer() {
        container.setLayout(new BorderLayout());
    }

    private void initializeObservers() {
        researchPanel.getSearchButton().addObserver(showResultPanel);
        researchPanel.getSearchButton().addObserver(map);
        researchPanel.getDepartureEnablingMapButton().addObserver(map);
        researchPanel.getArrivalEnablingMapButton().addObserver(map);

        interactiveButtonPanel.getZoomIn().addObserver(map);
        interactiveButtonPanel.getZoomOut().addObserver(map);
        interactiveButtonPanel.getOpenResearchButton().addObserver(researchPanel);
        interactiveButtonPanel.getOpenLineButton().addObserver(lineDisplayPanel);

        map.addDepartureObserver(researchPanel.getDepartureTextField());
        map.addArrivalObserver(researchPanel.getArrivalTextField());

        lineDisplayPanel.getComboBoxAndValidate().getComboBox().addObserver(map);
    }

    private void initializeListeners(){
        showResultPanel.getCloseButton().addActionListener(e -> {
            this.map.deleteLineDisplayed();
        });
        lineDisplayPanel.getClearButton().addActionListener(e -> {
            this.map.deleteLineDisplayed();
        });
        interactiveButtonPanel.getOpenLineButton().addActionListener(e -> {
            this.map.deleteLineDisplayed();
        });

    }

    private void addComponentsToContainer() {
        container.add(map, BorderLayout.CENTER);
        container.add(researchPanel, BorderLayout.WEST);
        container.add(lineDisplayPanel, BorderLayout.NORTH);
        container.add(showResultPanel, BorderLayout.EAST);
        container.add(interactiveButtonPanel, BorderLayout.SOUTH);
    }

    /**
     * Sets up the main frame's properties including title, size, and default close operation.
     * @param title the title for the frame
     */
    private void initFrame(String title) {
        setTitle(title);
        super.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * @return the map
     */
    public Maps getMap() {
        return map;
    }
}
