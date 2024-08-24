package fr.u_paris.gla.crazytrip.gui.panel;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import fr.u_paris.gla.crazytrip.gui.button.OpenLineSelectionPanelButton;
import fr.u_paris.gla.crazytrip.gui.button.OpenResearchPanelButton;
import fr.u_paris.gla.crazytrip.gui.button.ZoomInButton;
import fr.u_paris.gla.crazytrip.gui.button.ZoomOutButton;

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

    public InteractiveButtonPanel() {
        super();

        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(BACKGROUND_COLOR);

        openResearchButton = new OpenResearchPanelButton();
        openLineSelectionButton = new OpenLineSelectionPanelButton();
        zoomIn = new ZoomInButton();
        zoomOut = new ZoomOutButton();

        initComponents();
    }

    private void initComponents() {
        add(openResearchButton);
        add(openLineSelectionButton);
        add(zoomIn);
        add(zoomOut);
    }

    public OpenResearchPanelButton getOpenResearchButton() {
        return openResearchButton;
    }

    public OpenLineSelectionPanelButton getOpenLineButton() {
        return openLineSelectionButton;
    }

    public ZoomInButton getZoomIn() {
        return zoomIn;
    }

    public ZoomOutButton getZoomOut() {
        return zoomOut;
    }
}
