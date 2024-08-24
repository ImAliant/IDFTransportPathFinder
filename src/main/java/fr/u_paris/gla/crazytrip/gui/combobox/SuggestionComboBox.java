package fr.u_paris.gla.crazytrip.gui.combobox;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import fr.u_paris.gla.crazytrip.dao.StationDAO;
import fr.u_paris.gla.crazytrip.gui.listener.SuggestionComboBoxListener;
import fr.u_paris.gla.crazytrip.gui.observer.SuggestionSelectionObserver;
import fr.u_paris.gla.crazytrip.model.Station;

public class SuggestionComboBox extends JComboBox<String> {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 50;

    private transient List<SuggestionSelectionObserver> observers = new ArrayList<>();
    private transient List<Station> stations;

    public SuggestionComboBox() {
        super();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setVisible(false);

        addMouseListener(new SuggestionComboBoxListener(this));
    }

    private void update(List<Station> stations) {
        removeAllItems();

        for (Station station : stations) {
            addItem(station.toString());
        }

        this.stations = stations;
    }

    public Station getSelectedStation() {
        if (getSelectedIndex() == -1) return null;

        return stations.get(getSelectedIndex());
    }

    public void updateSuggestions(String text) {
        List<Station> foundStations = StationDAO.findStation(text);
        if (foundStations == null) return;

        update(foundStations);
    }

    public void clearSuggestions() {
        removeAllItems();
        stations.clear();
    }

    public void updateVisibility() {
        setVisible(!isVisible());
    }

    public void addObserver(SuggestionSelectionObserver observer) {
        observers.add(observer);
    }

    public void notifyStation(Station station) {
        for (SuggestionSelectionObserver observer: observers) {
            observer.update(station);
        }
    }
}
