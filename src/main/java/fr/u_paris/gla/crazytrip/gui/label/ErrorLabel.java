package fr.u_paris.gla.crazytrip.gui.label;

import java.awt.Color;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import fr.u_paris.gla.crazytrip.gui.observer.ErrorOnResearchObserver;

/**
 * Label to display an error message.
 * 
 * @see ErrorOnResearchObserver
 * @see StyleLabel
 */
public class ErrorLabel extends StyleLabel implements ErrorOnResearchObserver {
    /** Default text of the label. */
    private static final String DEFAULT_TEXT = "";
    /** Color of the text when an error occurs. */
    private static final Color RED_COLOR = new Color(255, 0, 0);
    /** Time to wait before hiding the label. */
    private static final int TIME_TO_HIDE = 5000;

    /**
     * Constructor.
     */
    public ErrorLabel() {
        super(DEFAULT_TEXT);

        setForeground(RED_COLOR);
        setVisible(false);
    }

    /**
     * Display an error message.
     * 
     * @param message the error message
     */
    @Override
    public void errorOnResearch(String message) {
        SwingUtilities.invokeLater(() -> showAndHide(message, TIME_TO_HIDE));
    }
    
    /**
     * Show the error message and hide it after a certain time.
     * 
     * @param message the error message
     */
    private void showAndHide(String message, int timeToHide) {
        setText(message);
        setVisible(true);
        repaint();

        Timer timer = new Timer(timeToHide, e -> {
            setVisible(false);
            setText(DEFAULT_TEXT);
        });
        
        timer.setRepeats(false);
        timer.start();
    }
}
