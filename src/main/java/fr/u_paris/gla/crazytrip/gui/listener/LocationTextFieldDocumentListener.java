package fr.u_paris.gla.crazytrip.gui.listener;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import fr.u_paris.gla.crazytrip.gui.combobox.SuggestionComboBox;
import fr.u_paris.gla.crazytrip.gui.textfield.LocationTextField;

public class LocationTextFieldDocumentListener implements DocumentListener {
    private LocationTextField textField;
    private SuggestionComboBox suggestionComboBox;

    public LocationTextFieldDocumentListener(LocationTextField textField, SuggestionComboBox suggestionComboBox) {
        this.textField = textField;
        this.suggestionComboBox = suggestionComboBox;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        if (!suggestionComboBox.isVisible()) {
            suggestionComboBox.setVisible(true);
        }
        
        checkAndUpdateSuggestions();
    }

    @Override
    public void changedUpdate(DocumentEvent arg0) {
        checkAndUpdateSuggestions();
    }

    @Override
    public void removeUpdate(DocumentEvent arg0) {
        checkAndUpdateSuggestions();
    }
    
    private void checkAndUpdateSuggestions() {
        SwingUtilities.invokeLater(() -> {
            if (textField.getText().isEmpty()) {
                suggestionComboBox.setVisible(false);
                suggestionComboBox.clearSuggestions();
            } else {
                suggestionComboBox.updateSuggestions(textField.getText());
            }
        });
    }
}
