package fr.u_paris.gla.project.view.progress_bar;

import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/**
 * This class encapsulates functionality for a loading progress bar in a GUI application.
 * 
 * @author Diamant Alexandre
 */

public class LoadingProgressBar extends JProgressBar {
    private static LoadingProgressBar instance;

    private static final int MINIMUM = 0;
    private static final int MAXIMUM = 100;

    private int currentProgress = 0;
    /**
     * Returns the single instance of this class, creating it if it does not yet exist.
     * @return the single instance of LoadingProgressBar
     */
    public static LoadingProgressBar getInstance() {
        if (instance == null) {
            instance = new LoadingProgressBar();
        }
        return instance;
    }
    /**
     * Private constructor to prevent external instantiation.
     * Initializes the progress bar with minimum and maximum values set,
     * and sets the display to show the progress percentage.
     */
    private LoadingProgressBar() {
        super();
        setStringPainted(true);

        setMinimum(MINIMUM);
        setMaximum(MAXIMUM);

        setValue(currentProgress);
    }
     /**
     * Increments the current progress of the progress bar by the specified value.
     * 
     * @param value the value to add to the current progress
     */
    public void incrementProgress(int value) {
        SwingUtilities.invokeLater(() -> {
            currentProgress += value;
            setValue(currentProgress);
        });
    }
}
