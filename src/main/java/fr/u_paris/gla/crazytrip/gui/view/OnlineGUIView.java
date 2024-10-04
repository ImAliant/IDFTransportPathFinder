package fr.u_paris.gla.crazytrip.gui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.u_paris.gla.crazytrip.gui.maps.Maps;
import fr.u_paris.gla.crazytrip.gui.observer.ClearLineObserver;
import fr.u_paris.gla.crazytrip.gui.observer.DisplayLineObserver;
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

public class OnlineGUIView extends JFrame implements View {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JPanel container;
    
    private InteractiveButtonPanel interactiveButtonPanel;
    private ResearchPanel researchPanel;
    private LineSelectionPanel lineSelectionPanel;
    private ItineraryResultPanel itineraryResultPanel;
    private Maps map;

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
    
    private void init() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);

        container.setLayout(new BorderLayout());

        addComponentToContainer();
    }

    private void addComponentToContainer() {
        container.add(interactiveButtonPanel, BorderLayout.SOUTH);
        container.add(researchPanel, BorderLayout.WEST);
        container.add(lineSelectionPanel, BorderLayout.NORTH);
        container.add(itineraryResultPanel, BorderLayout.EAST);
        container.add(map, BorderLayout.CENTER);

        add(container);
    }

    public void addZoomInObserver(ZoomInObserver observer) {
        interactiveButtonPanel.getZoomIn().addObserver(observer);
    }

    public void addZoomOutObserver(ZoomOutObserver observer) {
        interactiveButtonPanel.getZoomOut().addObserver(observer);
    }

    public void addOpenLineButtonObserver(PanelObserver observer) {
        interactiveButtonPanel.getOpenLineButton().addObserver(observer);
    }

    public void addOpenResearchButtonObserver(PanelObserver observer) {
        interactiveButtonPanel.getOpenResearchButton().addObserver(observer);
    }

    public void addTransportButtonsObservers(DisplayLineObserver observer) {
        lineSelectionPanel.addTransportButtonsObservers(observer);
    }

    public void addClearButtonObserver(ClearLineObserver observer) {
        lineSelectionPanel.addClearButtonObserver(observer);
    }

    public void addLinePainterObserver(LinePainterObserver observer) {
        lineSelectionPanel.addLinePainterObserver(observer);
    }
    
    public void addPopupStartPositionObserver(SelectPositionObserver observer) {
        map.getPopupMenu().addObserverForStartPosition(observer);
    }

    public void addPopupEndPositionObserver(SelectPositionObserver observer) {
        map.getPopupMenu().addObserverForEndPosition(observer);
    }

    public void addPathDrawerObserver(PathResultObserver observer) {
        researchPanel.getResearchButton().addObserver(observer);
    }

    public Maps getMap() {
        return map;
    }

    public LineSelectionPanel getLineSelectionPanel() {
        return lineSelectionPanel;
    }

    public ResearchPanel getResearchPanel() {
        return researchPanel;
    }

    public ItineraryResultPanel getItineraryResultPanel() {
        return itineraryResultPanel;
    }
}
