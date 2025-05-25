package fr.u_paris.gla.crazytrip.gui.loadingscreen;

import javax.swing.Timer;
import javax.swing.JLabel;

/**
 * Class representing the loading animation for the loading screen.
 * 
 * @see JLabel
 */
public class LoadingAnimation extends JLabel {
    /** Maximum number of dots. */
    private static final int DOT_MAX = 4;
    /** Delay between each dot. */
    private static final int DELAY = 500;
    /** Text displayed by the label. */
    private static final String TEXT = "Chargement";

    /** Timer to animate the dots. */
    private Timer timer;
    /** Number of dots displayed. */
    private int dot;

    /**
     * Constructor.
     */
    public LoadingAnimation() {
        super();
        
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
        
        setFont(getFont().deriveFont(20.0f));

        init();

        setVisible(true);
    }

    /**
     * Initialize the loading animation.
     */
    private void init() {
        dot = 0;

        timer = new Timer(DELAY, e -> {
            StringBuilder sb = new StringBuilder(TEXT);
            appendDot(sb, dot);
            setText(sb.toString());
            incrementDot();
        });
        timer.setRepeats(true);
    }

    /**
     * Start the loading animation.
     */
    public void start() {
        timer.start();
    }

    /**
     * Stop the loading animation.
     */
    public void stop() {
        timer.stop();
    }

    /**
     * Append dots to the text.
     * 
     * @param sb the string builder
     * @param dot the number of dots to append
     */
    private void appendDot(StringBuilder sb, int dot) {
        for (int i = 0; i < dot; i++) {
            sb.append(".");
        }
    }

    /**
     * Increment the number of dots.
     */
    private void incrementDot() {
        dot = (dot + 1) % DOT_MAX;
    }
}
