package fr.u_paris.gla.project.view.panel;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import fr.u_paris.gla.project.view.button.interactive_panel_button.OpenLineDisplayPanelButton;
import fr.u_paris.gla.project.view.button.interactive_panel_button.OpenResearchPanelButton;
import fr.u_paris.gla.project.view.button.interactive_panel_button.ZoomInButton;
import fr.u_paris.gla.project.view.button.interactive_panel_button.ZoomOutButton;

public class InteractiveButtonPanel extends JPanel {
    private static final Color BACKGROUND_COLOR = new Color(1, 121, 111);

    private OpenResearchPanelButton openResearchButton;
    private OpenLineDisplayPanelButton openLineButton;
    private ZoomInButton zoomIn;
    private ZoomOutButton zoomOut;

    public InteractiveButtonPanel() {
        super();

        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(BACKGROUND_COLOR);

        openResearchButton = new OpenResearchPanelButton();
        openLineButton = new OpenLineDisplayPanelButton();
        zoomIn = new ZoomInButton();
        zoomOut = new ZoomOutButton();

        init();
    }

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
