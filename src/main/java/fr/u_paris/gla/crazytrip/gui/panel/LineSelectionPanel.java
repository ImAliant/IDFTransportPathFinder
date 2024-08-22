package fr.u_paris.gla.crazytrip.gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import fr.u_paris.gla.crazytrip.gui.observer.ClearLineObserver;
import fr.u_paris.gla.crazytrip.gui.observer.DisplayLineObserver;
import fr.u_paris.gla.crazytrip.gui.observer.LineSelectionPanelObserver;

public class LineSelectionPanel extends JPanel implements LineSelectionPanelObserver {
    /** Background color of the panel. */
    private static final Color BACKGROUND_COLOR = new Color(1, 121, 121);

    private TransportSelectionPanel transportSelectionPanel;
    private SelectionPanel selectionPanel;
    private ClearPanel clearPanel;

    public LineSelectionPanel() {
        super();

        setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOR);
        setVisible(false);

        initComponents();
        addComponents();
    }

    private void initComponents() {
        transportSelectionPanel = new TransportSelectionPanel();
        selectionPanel = new SelectionPanel();
        clearPanel = new ClearPanel();
    }

    private void addComponents() {
        add(transportSelectionPanel, BorderLayout.WEST);
        add(selectionPanel, BorderLayout.CENTER);
        add(clearPanel, BorderLayout.EAST);
    }

    @Override
    public void updateVisibility() {
        setVisible(!isVisible());
    }

    public void addTransportButtonsObservers(DisplayLineObserver observer) {
        transportSelectionPanel.addObservers(observer);
    }

    public void addClearButtonObserver(ClearLineObserver observer) {
        clearPanel.addObserver(observer);
    }

    public SelectionPanel getSelectionPanel() {
        return selectionPanel;
    }

    public TransportSelectionPanel getTransportSelectionPanel() {
        return transportSelectionPanel;
    }

    public ClearPanel getClearPanel() {
        return clearPanel;
    }
}
