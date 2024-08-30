package fr.u_paris.gla.crazytrip.gui.loadingscreen;

import javax.swing.Timer;
import javax.swing.JLabel;

public class LoadingAnimation extends JLabel {
    private static final int DOT_MAX = 4;
    private static final int DELAY = 500;
    private static final String TEXT = "Chargement";

    private Timer timer;
    private int dot;

    public LoadingAnimation() {
        super();
        
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
        
        setFont(getFont().deriveFont(20.0f));

        init();

        setVisible(true);
    }

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

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    private void appendDot(StringBuilder sb, int dot) {
        for (int i = 0; i < dot; i++) {
            sb.append(".");
        }
    }

    private void incrementDot() {
        dot = (dot + 1) % DOT_MAX;
    }
}
