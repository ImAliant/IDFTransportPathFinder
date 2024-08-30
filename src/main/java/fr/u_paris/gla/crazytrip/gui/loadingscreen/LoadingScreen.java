package fr.u_paris.gla.crazytrip.gui.loadingscreen;

import java.awt.BorderLayout;
import javax.swing.JDialog;

public class LoadingScreen extends JDialog {
    private static final int WIDTH = 225;
    private static final int HEIGHT = 50;

    private LoadingAnimation loadingAnimation;

    private static LoadingScreen instance;

    public static LoadingScreen getInstance() {
        if (instance == null) {
            instance = new LoadingScreen();
        }
        return instance;
    }

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

    public void start() {
        loadingAnimation.start();
        setVisible(true);
    }

    public void stop() {
        loadingAnimation.stop();
        setVisible(false);
    }

    private void init() {
        loadingAnimation = new LoadingAnimation();
    }

    private void addComponents() {
        add(loadingAnimation, BorderLayout.CENTER);
    }
}
