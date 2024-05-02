package fr.u_paris.gla.project.view.button.line_display_panel_button;

import java.util.List;
import java.util.ArrayList;

import javax.swing.JButton;

import fr.u_paris.gla.project.observer.CleanDisplayObserver;

public class CleanDisplayButton extends JButton {
    private transient List<CleanDisplayObserver> observers = new ArrayList<>();

    private static final String TEXT = "Effacer";

    public CleanDisplayButton() {
        super(TEXT);

        addActionListener(e -> onClick());
    }

    public void addObserver(CleanDisplayObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (CleanDisplayObserver observer : observers) {
            observer.cleanDisplay();
        }
    }

    private void onClick() {
        notifyObservers();
    }
}
