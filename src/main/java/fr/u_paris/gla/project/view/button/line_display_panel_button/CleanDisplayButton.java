package fr.u_paris.gla.project.view.button.line_display_panel_button;

import java.util.List;
import java.util.ArrayList;

import javax.swing.JButton;

import fr.u_paris.gla.project.observer.CleanDisplayObserver;
import fr.u_paris.gla.project.utils.StyleButton;

public class CleanDisplayButton extends JButton {
    private transient List<CleanDisplayObserver> observers = new ArrayList<>();

    private static final String TEXT = "Effacer";

    public CleanDisplayButton() {
        super(TEXT);
        StyleButton.styleButton(this);

        addActionListener(e -> onClick());
    }
    /**
     * Add an observer to the button.
     *
     * @param observer The observer to add.
     */
    public void addObserver(CleanDisplayObserver observer) {
        observers.add(observer);
    }
    /**
     * Notify all observers.
     */
    private void notifyObservers() {
        for (CleanDisplayObserver observer : observers) {
            observer.cleanDisplay();
        }
    }
    /**
     * Notify all observers when button clicked.
     */
    private void onClick() {
        notifyObservers();
    }
}
