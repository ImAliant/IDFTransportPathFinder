package fr.u_paris.gla.project.view.listener;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import fr.u_paris.gla.project.algo.AutoComplete;
import fr.u_paris.gla.project.view.combobox.SuggestionStationsComboBox;
import fr.u_paris.gla.project.view.textfield.CustomTextField;

public class SuggestionDocumentListener implements DocumentListener {
    private CustomTextField t;
    private SuggestionStationsComboBox suggestions;

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