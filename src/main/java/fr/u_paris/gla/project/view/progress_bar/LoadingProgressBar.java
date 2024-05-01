package fr.u_paris.gla.project.view.progress_bar;

import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class LoadingProgressBar extends JProgressBar {
    private static LoadingProgressBar instance;

    private static final int MINIMUM = 0;
    private static final int MAXIMUM = 100;

    private int currentProgress = 0;

    public static LoadingProgressBar getInstance() {
        if (instance == null) {
            instance = new LoadingProgressBar();
        }
        return instance;
    }

    private LoadingProgressBar() {
        super();
        setStringPainted(true);

        setMinimum(MINIMUM);
        setMaximum(MAXIMUM);

        setValue(currentProgress);
    }

    public void incrementProgress(int value) {
        SwingUtilities.invokeLater(() -> {
            currentProgress += value;
            setValue(currentProgress);
        });
    }
}
