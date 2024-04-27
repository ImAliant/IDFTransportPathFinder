package fr.u_paris.gla.project;

import javax.swing.JLabel;
import javax.swing.JDialog;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import fr.u_paris.gla.project.observer.LoadingObserver;
import fr.u_paris.gla.project.utils.IconUtils;

public class LoadingScreen extends JDialog implements LoadingObserver {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 100;

    private JLabel loadingLabel;
    private int dotCount = 0;

    public LoadingScreen(String title) {
        super();
        this.setTitle(title);
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModal(true);

        loadingLabel = new JLabel("Loading...", SwingConstants.CENTER);

        add(loadingLabel);

        init();
    }

    private void init() {
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
