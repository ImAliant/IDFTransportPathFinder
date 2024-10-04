package fr.u_paris.gla.crazytrip.gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.u_paris.gla.crazytrip.algorithm.ItineraryResult;
import fr.u_paris.gla.crazytrip.gui.button.CloseButton;
import fr.u_paris.gla.crazytrip.gui.label.StyleLabel;
import fr.u_paris.gla.crazytrip.gui.observer.PathResultObserver;
import fr.u_paris.gla.crazytrip.gui.scrollpane.ResultItinerary;

public class ItineraryResultPanel extends JPanel implements PathResultObserver {
    private static final Color BACKGROUND_COLOR = new Color(104, 157, 113);
    private static final int WIDTH = 250;
    private static final String TITLE = "Itinéraire";

    private StyleLabel label;
    private ResultItinerary result;
    private CloseButton closeButton;

    public ItineraryResultPanel() {
        super();

        setBackground(BACKGROUND_COLOR);
        setPreferredSize(new Dimension(WIDTH, getHeight()));
        setVisible(false);

        initComponents();
        addComponents();
    }

    private void initComponents() {
        label = new StyleLabel(TITLE);
        result = new ResultItinerary();
        closeButton = new CloseButton(this);
    }

    private void addComponents() {
        add(label, BorderLayout.NORTH);
        add(result, BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);
    }

    @Override
    public void showResult(ItineraryResult result) {
        this.result.setResult(result);
        setVisible(true);
    }

    public JLabel getTitle() {
        return label;
    }
}
