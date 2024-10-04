package fr.u_paris.gla.crazytrip.gui.label;

import java.awt.Color;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import fr.u_paris.gla.crazytrip.gui.observer.ErrorOnResearchObserver;

public class ErrorLabel extends StyleLabel implements ErrorOnResearchObserver {
    private static final String TEXT = "Erreur lors de la recherche";
    private static final Color RED_COLOR = new Color(255, 0, 0);

    public ErrorLabel() {
        super(TEXT);

        setForeground(RED_COLOR);
        setVisible(false);
    }

    @Override
    public void errorOnResearch() {
        SwingUtilities.invokeLater(this::showAndHide);
    }
    
    private void showAndHide() {
        setVisible(true);
        repaint();

        Timer timer = new Timer(5000, e -> setVisible(false));
        timer.setRepeats(false);
        timer.start();

        repaint();
    }
}
