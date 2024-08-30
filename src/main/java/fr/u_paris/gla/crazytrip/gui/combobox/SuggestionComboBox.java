package fr.u_paris.gla.crazytrip.gui.combobox;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import fr.u_paris.gla.crazytrip.dao.StationDAO;
import fr.u_paris.gla.crazytrip.gui.listener.SuggestionComboBoxListener;
import fr.u_paris.gla.crazytrip.gui.observer.SelectPositionObserver;
import fr.u_paris.gla.crazytrip.model.Station;

public class SuggestionComboBox extends JComboBox<String> {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 50;

    private transient List<SelectPositionObserver> observers = new ArrayList<>();
    private transient List<Station> stations;

    public SuggestionComboBox() {
        super();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setVisible(false);

        addMouseListener(new SuggestionComboBoxListener(this));
    }

    private void update(List<Station> stations) {
        removeAllItems();

        sortStations(stations);

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

    public void addObserver(SelectPositionObserver observer) {
        observers.add(observer);
    }

    public void notifyStation(Station station) {
        for (SelectPositionObserver observer: observers) {
            observer.update(station);
        }
    }

    private void sortStations(List<Station> stationsToSort) {
        // tri par le nom, puis par le type de transport, puis par le numéro
        stationsToSort.sort((station1, station2) -> {
            int nameComparison = station1.getName().compareTo(station2.getName());
            if (nameComparison != 0) return nameComparison;

            int typeComparison = station1.getLineKey().getRouteType().compareTo(station2.getLineKey().getRouteType());
            if (typeComparison != 0) return typeComparison;

            return station1.getLineKey().getName().compareTo(station2.getLineKey().getName());
        });
    }
}
