package fr.u_paris.gla.project.idfnetwork.view;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import fr.u_paris.gla.project.AutoComplete;

public class SuggestionDocumentListener implements DocumentListener {
    private CustomTextField t;
    private SuggestionStationsScrollPane suggestions;

    public SuggestionDocumentListener(CustomTextField t, SuggestionStationsScrollPane suggestions) {
        this.t = t;
        this.suggestions = suggestions;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
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
            }
        });
    }

}