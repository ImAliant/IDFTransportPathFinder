package fr.u_paris.gla.crazytrip.gui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import fr.u_paris.gla.crazytrip.gui.combobox.SuggestionComboBox;
import fr.u_paris.gla.crazytrip.model.Station;

public class SuggestionComboBoxListener extends MouseAdapter {
    private SuggestionComboBox suggestionComboBox;

    public SuggestionComboBoxListener(SuggestionComboBox suggestionComboBox) {
        this.suggestionComboBox = suggestionComboBox;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Station station = suggestionComboBox.getSelectedStation();
        if (station == null)
            return;

        suggestionComboBox.notifyStation(station);
        suggestionComboBox.clearSuggestions();
    }
}
