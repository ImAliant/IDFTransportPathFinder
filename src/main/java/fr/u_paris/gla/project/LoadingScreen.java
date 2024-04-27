package fr.u_paris.gla.project;

import javax.swing.JDialog;

import fr.u_paris.gla.project.observer.LoadingObserver;

public class LoadingScreen extends JDialog implements LoadingObserver {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 100;

    public LoadingScreen() {
        super();
        this.setTitle("Loading...");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModal(true);
    }

    @Override
    public void onLoadingDone() {
        this.setVisible(false);
        this.dispose();
    }
}
