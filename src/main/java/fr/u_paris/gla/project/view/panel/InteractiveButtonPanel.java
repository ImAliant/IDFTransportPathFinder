package fr.u_paris.gla.project.view.panel;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import fr.u_paris.gla.project.view.button.interactive_panel_button.OpenLineDisplayPanelButton;
import fr.u_paris.gla.project.view.button.interactive_panel_button.OpenResearchPanelButton;
import fr.u_paris.gla.project.view.button.interactive_panel_button.ZoomInButton;
import fr.u_paris.gla.project.view.button.interactive_panel_button.ZoomOutButton;

/**
 * Panel containing the interactive buttons.
 * 
 * @see JPanel
 * @see OpenResearchPanelButton
 * @see OpenLineDisplayPanelButton
 * @see ZoomInButton
 * @see ZoomOutButton
 * 
 * @author Diamant Alexandre
 */
public class InteractiveButtonPanel extends JPanel {
    /** Background color of the panel. */
    private static final Color BACKGROUND_COLOR = new Color(1, 121, 111);

    /** Research panel */
    private OpenResearchPanelButton openResearchButton;
    /** Line display panel */
    private OpenLineDisplayPanelButton openLineButton;
    /** Zoom in button */
    private ZoomInButton zoomIn;
    /** Zoom out button */
    private ZoomOutButton zoomOut;

    /**
     * Constructor of the interactive panel.    
     */
    public InteractiveButtonPanel() {
        super();

        // Set the flow layout to left
        setLayout(new FlowLayout(FlowLayout.LEFT));
        // Set the background color
        setBackground(BACKGROUND_COLOR);

        openResearchButton = new OpenResearchPanelButton();
        openLineButton = new OpenLineDisplayPanelButton();
        zoomIn = new ZoomInButton();
        zoomOut = new ZoomOutButton();

        init();
    }

    /**
     * Add components to the panel.
     */
    private void init() {
        add(openResearchButton);
        add(openLineButton);
        add(zoomIn);
        add(zoomOut);
    }
    

    public OpenResearchPanelButton getOpenResearchButton() {
        return openResearchButton;
    }

    public OpenLineDisplayPanelButton getOpenLineButton() {
        return openLineButton;
    }

    public ZoomInButton getZoomIn() {
        return zoomIn;
    }

    public ZoomOutButton getZoomOut() {
        return zoomOut;
    }
}
