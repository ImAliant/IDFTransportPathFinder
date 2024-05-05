package fr.u_paris.gla.crazytrip.view.listener;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import fr.u_paris.gla.crazytrip.algo.AutoComplete;
import fr.u_paris.gla.crazytrip.view.combobox.SuggestionStationsComboBox;
import fr.u_paris.gla.crazytrip.view.textfield.CustomTextField;
/**
 * A document listener to handle text input changes and trigger autocomplete suggestions.
 * @author Aziz Ghizlane
 */
public class SuggestionDocumentListener implements DocumentListener {
    private CustomTextField t;
    private SuggestionStationsComboBox suggestions;
     /**
     * Constructs a new SuggestionDocumentListener.
     * @param t the CustomTextField to listen for changes on.
     * @param suggestions the SuggestionStationsComboBox where autocomplete suggestions are displayed.
     */
    public SuggestionDocumentListener(CustomTextField t, SuggestionStationsComboBox suggestions) {
        this.t = t;
        this.suggestions = suggestions;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        if (!suggestions.isVisible()) {
            suggestions.setVisible(true);
        }

        updateSuggestion(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateSuggestion(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateSuggestion(e);
    }
    /**
     * Method to request suggestions update based on the current document state.
     * @param e the document event that triggered the update.
     */
    public void updateSuggestion(DocumentEvent e) {
        SwingUtilities.invokeLater(() -> AutoComplete.showSuggestions(t, suggestions));

        SwingUtilities.invokeLater(() -> {
            if (t.getText().isEmpty()) {
                suggestions.clearSuggestion();
                suggestions.setVisible(false);
            }
        });
    }

}