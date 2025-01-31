package fr.u_paris.gla.crazytrip.gui.loadingscreen;

import java.awt.BorderLayout;
import javax.swing.JDialog;

/**
 * Class representing the loading screen.
 * 
 * @see LoadingAnimation
 * @see JDialog
 */
public class LoadingScreen extends JDialog {
    /** Width of the panel */
    private static final int WIDTH = 225;
    /** Height of the panel */
    private static final int HEIGHT = 50;

    /** The loading animation */
    private LoadingAnimation loadingAnimation;
    /** The unique instance of the loading screen */
    private static LoadingScreen instance;

    /**
     * Get the unique instance of the loading screen.
     * 
     * @return the unique instance of the loading screen
     */
    public static LoadingScreen getInstance() {
        if (instance == null) {
            instance = new LoadingScreen();
        }
        return instance;
    }

    /**
     * Constructor.
     */
    private LoadingScreen() {
        super();
        
        setTitle("Chargement...");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(false);
        setLayout(new BorderLayout());

        init();
        addComponents();
    }

    /**
     * Start the loading screen.
     */
    public void start() {
        loadingAnimation.start();
        setVisible(true);
    }

    /**
     * Stop the loading screen.
     */
    public void stop() {
        loadingAnimation.stop();
        setVisible(false);
    }

    /**
     * Initialize the loading screen.
     */
    private void init() {
        loadingAnimation = new LoadingAnimation();
    }

    /**
     * Add the components to the loading screen.
     */
    private void addComponents() {
        add(loadingAnimation, BorderLayout.CENTER);
    }
}
