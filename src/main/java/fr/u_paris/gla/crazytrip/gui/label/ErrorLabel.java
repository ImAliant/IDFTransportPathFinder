package fr.u_paris.gla.crazytrip.gui.label;

import java.awt.Color;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import fr.u_paris.gla.crazytrip.gui.observer.ErrorOnResearchObserver;

public class ErrorLabel extends StyleLabel implements ErrorOnResearchObserver {
    private static final String DEFAULT_TEXT = "";
    private static final Color RED_COLOR = new Color(255, 0, 0);
    private static final int TIME_TO_HIDE = 5000;

    public ErrorLabel() {
        super(DEFAULT_TEXT);

        setForeground(RED_COLOR);
        setVisible(false);
    }

    @Override
    public void errorOnResearch(String message) {
        SwingUtilities.invokeLater(() -> showAndHide(message));
    }
    
    private void showAndHide(String message) {
        setText(message);
        setVisible(true);
        repaint();

        Timer timer = new Timer(TIME_TO_HIDE, e -> {
            setVisible(false);
            setText(DEFAULT_TEXT);
        });
        
        timer.setRepeats(false);
        timer.start();
    }
}
