package fr.u_paris.gla.crazytrip.gui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import fr.u_paris.gla.crazytrip.gui.combobox.SuggestionComboBox;
import fr.u_paris.gla.crazytrip.model.Station;

/**
 * Listener for a suggestion combo box.
 * 
 * @see SuggestionComboBox
 */
public class SuggestionComboBoxListener extends MouseAdapter {
    /** The suggestion combo box. */
    private SuggestionComboBox suggestionComboBox;

    /**
     * Constructor.
     * 
     * @param suggestionComboBox the suggestion combo box
     * 
     * @see SuggestionComboBox
     */
    public SuggestionComboBoxListener(SuggestionComboBox suggestionComboBox) {
        this.suggestionComboBox = suggestionComboBox;
    }

    /**
     * Notify the station when the user clicks on it, and clear the suggestions.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        Station station = suggestionComboBox.getSelectedStation();
        if (station == null)
            return;

        suggestionComboBox.notifyStation(station);
        suggestionComboBox.clearSuggestions();
    }
}
