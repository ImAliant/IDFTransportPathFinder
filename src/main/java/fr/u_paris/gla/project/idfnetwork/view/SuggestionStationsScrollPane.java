package fr.u_paris.gla.project.idfnetwork.view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SuggestionStationsScrollPane extends JScrollPane {
    private static final int WIDTH = 150;
    private static final int HEIGHT = 50;

    /** Panel containing the suggestions */
    private JPanel suggestions; 

    public SuggestionStationsScrollPane() {
        super();

        suggestions = new JPanel(new GridLayout(0,1));
        setViewportView(suggestions);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public void addSuggestion(String suggestion) {
        suggestions.add(new CustomLabel(suggestion));
    }

    public void clearSuggestion() {
        suggestions.removeAll();
        suggestions.revalidate();
        suggestions.repaint();
    }

    @Override
    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public JPanel getSuggestions() {
        return suggestions;
    }
}
