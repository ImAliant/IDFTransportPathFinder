package fr.u_paris.gla.crazytrip.gui.panel;

import javax.swing.JPanel;

import fr.u_paris.gla.crazytrip.gui.button.transport_selection_button.BusButton;
import fr.u_paris.gla.crazytrip.gui.button.transport_selection_button.MetroButton;
import fr.u_paris.gla.crazytrip.gui.button.transport_selection_button.RailButton;
import fr.u_paris.gla.crazytrip.gui.button.transport_selection_button.TramButton;
import fr.u_paris.gla.crazytrip.gui.observer.DisplayLineObserver;

public class TransportSelectionPanel extends JPanel {
    private MetroButton metroButton;
    private BusButton busButton;
    private RailButton railButton;
    private TramButton tramButton;

    public TransportSelectionPanel() {
        initComponents();
        addComponents();

        setOpaque(false);
    }

    private void initComponents() {
        metroButton = new MetroButton();
        busButton = new BusButton();
        railButton = new RailButton();
        tramButton = new TramButton();
    }

    private void addComponents() {
        add(metroButton);
        add(busButton);
        add(railButton);
        add(tramButton);
    }

    public void addObservers(DisplayLineObserver observer) {
        metroButton.addObserver(observer);
        busButton.addObserver(observer);
        railButton.addObserver(observer);
        tramButton.addObserver(observer);
    }
}
