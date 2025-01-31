package fr.u_paris.gla.crazytrip.gui.panel;

import javax.swing.JPanel;

import fr.u_paris.gla.crazytrip.gui.button.transport_selection_button.BusButton;
import fr.u_paris.gla.crazytrip.gui.button.transport_selection_button.MetroButton;
import fr.u_paris.gla.crazytrip.gui.button.transport_selection_button.RailButton;
import fr.u_paris.gla.crazytrip.gui.button.transport_selection_button.TramButton;
import fr.u_paris.gla.crazytrip.gui.observer.DisplayLineObserver;

/**
 * Panel to select the transport type.
 * <p>Composed of four buttons to select the transport type: metro, bus, rail and tram.</p>
 * 
 * @see MetroButton
 * @see BusButton
 * @see RailButton
 * @see TramButton
 */
public class TransportSelectionPanel extends JPanel {
    /** Metro lines button */
    private MetroButton metroButton;
    /** Bus lines button */
    private BusButton busButton;
    /** Rail lines button */
    private RailButton railButton;
    /** Tram lines button */
    private TramButton tramButton;

    /**
     * Constructor
     */
    public TransportSelectionPanel() {
        initComponents();
        addComponents();

        setOpaque(false);
    }

    /**
     * Initialize the components
     */
    private void initComponents() {
        metroButton = new MetroButton();
        busButton = new BusButton();
        railButton = new RailButton();
        tramButton = new TramButton();
    }

    /**
     * Add the components to the panel
     */
    private void addComponents() {
        add(metroButton);
        add(busButton);
        add(railButton);
        add(tramButton);
    }

    /**
     * Add the observers to the buttons
     * 
     * @param observer the observer to add
     */
    public void addObservers(DisplayLineObserver observer) {
        metroButton.addObserver(observer);
        busButton.addObserver(observer);
        railButton.addObserver(observer);
        tramButton.addObserver(observer);
    }
}
