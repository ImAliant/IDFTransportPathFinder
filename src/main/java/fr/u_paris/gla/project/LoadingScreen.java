package fr.u_paris.gla.project;

import javax.swing.JLabel;
import javax.swing.JDialog;
import javax.swing.Timer;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;

import fr.u_paris.gla.project.idfnetwork.view.progress_bar.LoadingProgressBar;
import fr.u_paris.gla.project.observer.LoadingObserver;

/**
 * Class to represent a loading screen.
 * 
 * @see JDialog
 * @see LoadingObserver
 */
public class LoadingScreen extends JDialog implements LoadingObserver {
    /** Width of the loading screen */
    private static final int WIDTH = 300;
    /** Height of the loading screen */
    private static final int HEIGHT = 100;

    /** Label to display the loading text */
    private JLabel loadingLabel;

    private LoadingProgressBar progressBar;

    /** Number of dots to display */
    private int dotCount = 0;

    /**
     * Create a new loading screen with the given title.
     * 
     * @param title The title of the application.
     */
    public LoadingScreen(String title) {
        super();
        this.setTitle(title);
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModal(true);

        this.setLayout(new BorderLayout());

        loadingLabel = new JLabel("Loading...", SwingConstants.CENTER);
        progressBar = LoadingProgressBar.getInstance();

        add(loadingLabel, BorderLayout.NORTH);
        add(progressBar, BorderLayout.CENTER);

        init();
    }

    /**
     * Initialize the loading screen.
     */
    private void init() {
        // Create a timer to animate the loading text
        Timer timer = new Timer(500, e -> {
            dotCount = (dotCount + 1) % 4;
            StringBuilder text = new StringBuilder("Loading");
            for (int i = 0; i < dotCount; i++) {
                text.append(".");
            }
            loadingLabel.setText(text.toString());
        });
        timer.setRepeats(true);
        timer.start();
    }

    @Override
    public void onLoadingDone() {
        this.setVisible(false);
        this.dispose();
    }
}
