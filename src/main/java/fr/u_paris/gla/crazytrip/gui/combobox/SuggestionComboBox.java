package fr.u_paris.gla.crazytrip.gui.combobox;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import fr.u_paris.gla.crazytrip.dao.StationDAO;
import fr.u_paris.gla.crazytrip.gui.listener.SuggestionComboBoxListener;
import fr.u_paris.gla.crazytrip.gui.observer.SelectPositionObserver;
import fr.u_paris.gla.crazytrip.model.Station;

/**
 * Class representing a combo box to suggest stations.
 * 
 * @see JComboBox
 * @see SuggestionComboBoxListener
 */
public class SuggestionComboBox extends JComboBox<String> {
    /** Width of the combo box */
    private static final int WIDTH = 200;
    /** Height of the combo box */
    private static final int HEIGHT = 50;

    /** List of observers to notify when a station is selected */
    private transient List<SelectPositionObserver> observers = new ArrayList<>();
    /** List of the stations shown in the combo box */
    private transient List<Station> stations;

    /**
     * Constructor.
     */
    public SuggestionComboBox() {
        super();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setVisible(false);

        addMouseListener(new SuggestionComboBoxListener(this));
    }

    /**
     * <p>Update the combo box with the list of stations.</p>
     * 
     * <p>Remove all the items from the combo box, sort the stations and add them to the combo box.</p>
     * 
     * @param stations the list of stations
     */
    private void update(List<Station> stations) {
        removeAllItems();

        sortStations(stations);

        for (Station station : stations) {
            addItem(station.toString());
        }

        this.stations = stations;
    }

    /**
     * Get the selected station in the combo box.
     * 
     * @return the selected station
     */
    public Station getSelectedStation() {
        if (getSelectedIndex() == -1) return null;

        return stations.get(getSelectedIndex());
    }

    /**
     * Update the suggestions in the combo box.
     * 
     * @param text the text to search for
     */
    public void updateSuggestions(String text) {
        List<Station> foundStations = StationDAO.findStation(text);
        if (foundStations == null) return;

        update(foundStations);
    }

    /**
     * Clear the suggestions in the combo box.
     */
    public void clearSuggestions() {
        removeAllItems();
        stations.clear();
    }

    /**
     * Update the visibility of the combo box.
     */
    public void updateVisibility() {
        setVisible(!isVisible());
    }

    /**
     * Add an observer to the list of observers.
     * 
     * @param observer the observer to add
     */
    public void addObserver(SelectPositionObserver observer) {
        observers.add(observer);
    }

    /**
     * Notify all observers that a station has been selected.
     * 
     * @param station the selected station
     */
    public void notifyStation(Station station) {
        for (SelectPositionObserver observer: observers) {
            observer.update(station);
        }
    }

    /**
     * Sort the stations by their name, then by their transport type, then by their number.
     * 
     * @param stationsToSort the list of stations to sort
     */
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
