package fr.u_paris.gla.crazytrip.gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.u_paris.gla.crazytrip.algorithm.ItineraryResult;
import fr.u_paris.gla.crazytrip.gui.button.CloseButton;
import fr.u_paris.gla.crazytrip.gui.label.StyleLabel;
import fr.u_paris.gla.crazytrip.gui.observer.PanelObserver;
import fr.u_paris.gla.crazytrip.gui.observer.PathResultObserver;
import fr.u_paris.gla.crazytrip.gui.scrollpane.ResultItinerary;

/**
 * Panel used to show the user the itinerary found by the algorithm
 * 
 * @see JPanel
 * @see PathResultObserver
 * @see PanelObserver
 * 
 * @see StyleLabel
 * @see ResultItinerary
 * @see CloseButton
 */
public class ItineraryResultPanel extends JPanel implements PathResultObserver, PanelObserver {
    /** Background color of the panel */
    private static final Color BACKGROUND_COLOR = new Color(104, 157, 113);
    /** Width of the panel */
    private static final int WIDTH = 600;
    /** Text title of the panel */
    private static final String TITLE = "Itinéraire";

    /** Label of the panel */
    private StyleLabel label;
    /** Component where the itinerary will be printed */
    private ResultItinerary result;
    /** Button to close the panel */
    private CloseButton closeButton;

    /**
     * Constructor
     */
    public ItineraryResultPanel() {
        super();

        setBackground(BACKGROUND_COLOR);
        setPreferredSize(new Dimension(WIDTH, getHeight()));
        setVisible(false);

        initComponents();
        addComponents();
    }

    /** Initialize the components */
    private void initComponents() {
        label = new StyleLabel(TITLE);
        result = new ResultItinerary();
        closeButton = new CloseButton();

        closeButton.addObserver(this);
    }

    /** Add the components */
    private void addComponents() {
        add(label, BorderLayout.NORTH);
        add(result, BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);
    }

    @Override
    public void updateVisibility() {
        setVisible(!isVisible());
    }

    @Override
    public void showResult(ItineraryResult result) {
        this.result.setResult(result);
        updateVisibility();
    }

    /**
     * Getter for the title
     * 
     * @return The title
     */
    public JLabel getTitle() {
        return label;
    }
}
