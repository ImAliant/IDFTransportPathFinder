package fr.u_paris.gla.crazytrip.gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import fr.u_paris.gla.crazytrip.gui.observer.ClearLineObserver;
import fr.u_paris.gla.crazytrip.gui.observer.DisplayLineObserver;
import fr.u_paris.gla.crazytrip.gui.observer.LinePainterObserver;
import fr.u_paris.gla.crazytrip.gui.observer.PanelObserver;

/**
 * Panel where the user can choose to show all lines of the idf network on the map.
 * 
 * There are three components on this panel:
 * <ol>
 *  <li>Panel to select a type of transportation</li>
 *  <li>Panel to select the line to show</li>
 *  <li>Panel to clear the map</li>
 * </ol>
 * 
 * @see JPanel
 * @see PanelObserver
 */
public class LineSelectionPanel extends JPanel implements PanelObserver {
    /** Background color of the panel. */
    private static final Color BACKGROUND_COLOR = new Color(1, 121, 121);
    /** Panel with all the transport modes */
    private TransportSelectionPanel transportSelectionPanel;
    /** Panel to select the line */
    private SelectionPanel selectionPanel;
    /** Panel to clear the map */
    private ClearPanel clearPanel;

    /**
     * Constructor
     */
    public LineSelectionPanel() {
        super();

        setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOR);
        setVisible(false);

        initComponents();
        addComponents();
    }

    /** Initialize the components */
    private void initComponents() {
        transportSelectionPanel = new TransportSelectionPanel();
        selectionPanel = new SelectionPanel();
        clearPanel = new ClearPanel();
    }

    /** Add the components */
    private void addComponents() {
        add(transportSelectionPanel, BorderLayout.WEST);
        add(selectionPanel, BorderLayout.CENTER);
        add(clearPanel, BorderLayout.EAST);
    }

    @Override
    public void updateVisibility() {
        setVisible(!isVisible());
    }

    /** 
     * Add observer to the transport selection panel 
     * 
     * @param observer the observer to add. The observer will know the current transportation mode selected.
     * 
     * @see DisplayLineObserver 
     */
    public void addTransportButtonsObservers(DisplayLineObserver observer) {
        transportSelectionPanel.addObservers(observer);
    }

    /**
     * Add observer to the clear panel
     * 
     * @param observer the observer to add. The observer will know if the map need to be cleared.
     * 
     * @see ClearLineObserver
     */
    public void addClearButtonObserver(ClearLineObserver observer) {
        clearPanel.addObserver(observer);
    }

    /**
     * Add observer to the selection panel
     * 
     * @param observer the observer to add. The observer will know the selected line to paint.
     * 
     * @see LinePainterObserver
     */
    public void addLinePainterObserver(LinePainterObserver observer) {
        selectionPanel.addLinePainterObserver(observer);
    }

    /**
     * Getter for the selection panel
     * 
     * @return the selection panel
     * 
     * @see SelectionPanel
     */
    public SelectionPanel getSelectionPanel() {
        return selectionPanel;
    }

    /**
     * Getter for the transport selection panel
     * 
     * @return the transport selection panel
     * 
     * @see TransportSelectionPanel
     */
    public TransportSelectionPanel getTransportSelectionPanel() {
        return transportSelectionPanel;
    }

    /**
     * Getter for the clear panel
     * 
     * @return the clear panel
     * 
     * @see ClearPanel
     */
    public ClearPanel getClearPanel() {
        return clearPanel;
    }
}
