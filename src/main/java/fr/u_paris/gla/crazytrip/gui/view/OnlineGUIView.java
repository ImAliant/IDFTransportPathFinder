package fr.u_paris.gla.crazytrip.gui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.u_paris.gla.crazytrip.gui.maps.Maps;
import fr.u_paris.gla.crazytrip.gui.observer.ClearLineObserver;
import fr.u_paris.gla.crazytrip.gui.observer.DisplayLineObserver;
import fr.u_paris.gla.crazytrip.gui.observer.ErrorOnResearchObserver;
import fr.u_paris.gla.crazytrip.gui.observer.LinePainterObserver;
import fr.u_paris.gla.crazytrip.gui.observer.PanelObserver;
import fr.u_paris.gla.crazytrip.gui.observer.PathResultObserver;
import fr.u_paris.gla.crazytrip.gui.observer.SelectPositionObserver;
import fr.u_paris.gla.crazytrip.gui.observer.ZoomInObserver;
import fr.u_paris.gla.crazytrip.gui.observer.ZoomOutObserver;
import fr.u_paris.gla.crazytrip.gui.panel.InteractiveButtonPanel;
import fr.u_paris.gla.crazytrip.gui.panel.ItineraryResultPanel;
import fr.u_paris.gla.crazytrip.gui.panel.LineSelectionPanel;
import fr.u_paris.gla.crazytrip.gui.panel.ResearchPanel;

/**
 * A GUI view for the online mode.
 * 
 * This class is a graphical implementation of the View interface that uses a
 * JFrame to display the different components of the application.
 * 
 * This class is used when the application is running in online mode.
 * 
 * @see View
 * @see JFrame
 * @see JPanel
 * @see InteractiveButtonPanel
 * @see ResearchPanel
 * @see LineSelectionPanel
 * @see ItineraryResultPanel
 * @see Maps
 */
public class OnlineGUIView extends JFrame implements View {
    /** The width of the window. */
    private static final int WIDTH = 800;
    /** The height of the window. */
    private static final int HEIGHT = 600;
    /** The container of the components. */
    private JPanel container;
    
    /** The interactive button panel. */
    private InteractiveButtonPanel interactiveButtonPanel;
    /** The research panel. */
    private ResearchPanel researchPanel;
    /** The line selection panel. */
    private LineSelectionPanel lineSelectionPanel;
    /** The itinerary result panel. */
    private ItineraryResultPanel itineraryResultPanel;
    /** The map. */
    private Maps map;

    /**
     * Create a new OnlineGUIView.
     * 
     * @param title the title of the window
     */
    public OnlineGUIView(String title) {
        super(title);

        this.container = new JPanel();
        this.interactiveButtonPanel = new InteractiveButtonPanel();
        this.researchPanel = new ResearchPanel();
        this.lineSelectionPanel = new LineSelectionPanel();
        this.itineraryResultPanel = new ItineraryResultPanel();
        this.map = new Maps();
    }

    @Override
    public void start() {
        init();

        pack();
    }
    
    /**
     * Initialize the window.
     */
    private void init() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);

        container.setLayout(new BorderLayout());

        addComponentToContainer();
    }

    /**
     * Add the components to the container.
     */
    private void addComponentToContainer() {
        container.add(interactiveButtonPanel, BorderLayout.SOUTH);
        container.add(researchPanel, BorderLayout.WEST);
        container.add(lineSelectionPanel, BorderLayout.NORTH);
        container.add(itineraryResultPanel, BorderLayout.EAST);
        container.add(map, BorderLayout.CENTER);

        add(container);
    }

    /**
     * Add an observer to the zoom in button.
     * @param observer the observer to add
     * 
     * @see ZoomInObserver
     */
    public void addZoomInObserver(ZoomInObserver observer) {
        interactiveButtonPanel.getZoomIn().addObserver(observer);
    }

    /**
     * Add an observer to the zoom out button.
     * @param observer the observer to add
     * 
     * @see ZoomOutObserver
     */
    public void addZoomOutObserver(ZoomOutObserver observer) {
        interactiveButtonPanel.getZoomOut().addObserver(observer);
    }

    /**
     * Add an observer to the open line button.
     * @param observer the observer to add
     * 
     * @see PanelObserver
     */
    public void addOpenLineButtonObserver(PanelObserver observer) {
        interactiveButtonPanel.getOpenLineButton().addObserver(observer);
    }

    /**
     * Add an observer to the open research button.
     * @param observer the observer to add
     * 
     * @see PanelObserver
     */
    public void addOpenResearchButtonObserver(PanelObserver observer) {
        interactiveButtonPanel.getOpenResearchButton().addObserver(observer);
    }

    /**
     * Add an observer to the open itinerary button.
     * @param observer the observer to add
     * 
     * @see PanelObserver
     */
    public void addTransportButtonsObservers(DisplayLineObserver observer) {
        lineSelectionPanel.addTransportButtonsObservers(observer);
    }

    /**
     * Add an observer to the clear button.
     * @param observer the observer to add
     * 
     * @see ClearLineObserver
     */
    public void addClearButtonObserver(ClearLineObserver observer) {
        lineSelectionPanel.addClearButtonObserver(observer);
    }

    /**
     * Add an observer to the line painter.
     * @param observer the observer to add
     * 
     * @see LinePainterObserver
     */
    public void addLinePainterObserver(LinePainterObserver observer) {
        lineSelectionPanel.addLinePainterObserver(observer);
    }
    
    /**
     * Add an observer to the start position.
     * @param observer the observer to add
     * 
     * @see SelectPositionObserver
     */
    public void addPopupStartPositionObserver(SelectPositionObserver observer) {
        map.getPopupMenu().addObserverForStartPosition(observer);
    }

    /**
     * Add an observer to the end position.
     * @param observer the observer to add
     * 
     * @see SelectPositionObserver
     */
    public void addPopupEndPositionObserver(SelectPositionObserver observer) {
        map.getPopupMenu().addObserverForEndPosition(observer);
    }

    /**
     * Add an observer to the research button.
     * @param observer the observer to add
     * 
     * @see PathResultObserver
     */
    public void addPathDrawerObserver(PathResultObserver observer) {
        researchPanel.getResearchButton().addObserver(observer);
    }

    /**
     * Add an observer to the error on research button.
     * @param observer the observer to add
     * 
     * @see ErrorOnResearchObserver
     */
    public void addErrorObserver(ErrorOnResearchObserver observer) {
        researchPanel.getResearchButton().addErrorObserver(observer);
    }

    /**
     * Getter for the map.
     * @return the map
     */
    public Maps getMap() {
        return map;
    }

    /**
     * Getter for the line selection panel.
     * @return the line selection panel
     * 
     * @see LineSelectionPanel
     */
    public LineSelectionPanel getLineSelectionPanel() {
        return lineSelectionPanel;
    }

    /**
     * Getter for the research panel.
     * @return the research panel
     * 
     * @see ResearchPanel
     */
    public ResearchPanel getResearchPanel() {
        return researchPanel;
    }

    /**
     * Getter for the itinerary result panel.
     * @return the itinerary result panel
     * 
     * @see ItineraryResultPanel
     */
    public ItineraryResultPanel getItineraryResultPanel() {
        return itineraryResultPanel;
    }
}
