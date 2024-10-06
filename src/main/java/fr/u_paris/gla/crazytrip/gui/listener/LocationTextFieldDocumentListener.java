package fr.u_paris.gla.crazytrip.gui.listener;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import fr.u_paris.gla.crazytrip.gui.combobox.SuggestionComboBox;
import fr.u_paris.gla.crazytrip.gui.textfield.LocationTextField;

/**
 * Class representing a listener for the location text field.
 * 
 * @see DocumentListener
 * @see LocationTextField
 */
public class LocationTextFieldDocumentListener implements DocumentListener {
    /** The location text field */
    private LocationTextField textField;
    /** The suggestion combo box */
    private SuggestionComboBox suggestionComboBox;

    /**
     * Constructor.
     * 
     * @param textField the location text field
     * @param suggestionComboBox the suggestion combo box
     * 
     * @see LocationTextField
     * @see SuggestionComboBox
     */
    public LocationTextFieldDocumentListener(LocationTextField textField, SuggestionComboBox suggestionComboBox) {
        this.textField = textField;
        this.suggestionComboBox = suggestionComboBox;
    }

    /**
     * <p>Inserts an update.</p>
     * 
     * <p>When the text field is updated, the suggestion combo box is displayed and the suggestions are updated.</p>
     * 
     * @param e the document event
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        if (!suggestionComboBox.isVisible()) {
            suggestionComboBox.setVisible(true);
        }
        
        checkAndUpdateSuggestions();
    }

    /**
     * <p>Changes an update.</p>
     * 
     * <p>When the text field is updated, the suggestion combo box is displayed and the suggestions are updated.</p>
     * 
     * @param e the document event
     */
    @Override
    public void changedUpdate(DocumentEvent arg0) {
        checkAndUpdateSuggestions();
    }

    /**
     * <p>Removes an update.</p>
     * 
     * <p>When the text field is updated, the suggestion combo box is displayed and the suggestions are updated.</p>
     * 
     * @param e the document event
     */
    @Override
    public void removeUpdate(DocumentEvent arg0) {
        checkAndUpdateSuggestions();
    }
    
    /**
     * Check if the text field is empty and update the suggestions.
     */
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
