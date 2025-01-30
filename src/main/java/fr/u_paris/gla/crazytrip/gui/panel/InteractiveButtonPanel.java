package fr.u_paris.gla.crazytrip.gui.panel;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import fr.u_paris.gla.crazytrip.gui.button.OpenLineSelectionPanelButton;
import fr.u_paris.gla.crazytrip.gui.button.OpenResearchPanelButton;
import fr.u_paris.gla.crazytrip.gui.button.ZoomInButton;
import fr.u_paris.gla.crazytrip.gui.button.ZoomOutButton;

/**
 * Panel containing the interactive buttons.
 * 
 * The user will be able to open the research panel, the line selection panel and zoom in or out on the map.
 * 
 * @see OpenResearchPanelButton
 * @see OpenLineSelectionPanelButton
 * @see ZoomInButton
 * @see ZoomOutButton
 */
public class InteractiveButtonPanel extends JPanel {
    /** Background color of the panel. */
    private static final Color BACKGROUND_COLOR = new Color(1, 121, 111);
    /** Button to open the research panel. */
    private OpenResearchPanelButton openResearchButton;
    /** Button to open the line display panel. */
    private OpenLineSelectionPanelButton openLineSelectionButton;
    /** Zoom in button. */
    private ZoomInButton zoomIn;
    /** Zoom out button. */
    private ZoomOutButton zoomOut;

    /*
     * Constructor
     */
    public InteractiveButtonPanel() {
        super();

        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(BACKGROUND_COLOR);

        initComponents();
        addComponents();
    }

    /** Initialize the components of the panel */
    private void initComponents() {
        openResearchButton = new OpenResearchPanelButton();
        openLineSelectionButton = new OpenLineSelectionPanelButton();
        zoomIn = new ZoomInButton();
        zoomOut = new ZoomOutButton();
    }

    /** Add components in the panel */
    private void addComponents() {
        add(openResearchButton);
        add(openLineSelectionButton);
        add(zoomIn);
        add(zoomOut);
    }

    /**
     * Getter research button
     * 
     * @return The research button
     * 
     * @see OpenResearchPanelButton
     */
    public OpenResearchPanelButton getOpenResearchButton() {
        return openResearchButton;
    }

    /**
     * Getter line selection button
     * 
     * @return The line selection button
     * 
     * @see OpenLineSelectionPanelButton
     */
    public OpenLineSelectionPanelButton getOpenLineButton() {
        return openLineSelectionButton;
    }

    /**
     * Getter zoom in button
     * 
     * @return The zoom in button
     * 
     * @see ZoomInButton
     */
    public ZoomInButton getZoomIn() {
        return zoomIn;
    }

    /**
     * Getter zoom out button
     * 
     * @return The zoom out button
     * 
     * @see ZoomOutButton
     */
    public ZoomOutButton getZoomOut() {
        return zoomOut;
    }
}
