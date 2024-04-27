package fr.u_paris.gla.project;

import java.awt.Color;

import javax.swing.JPanel;

import fr.u_paris.gla.project.observer.LineDisplayPanelObserver;

public class LineDisplayPanel extends JPanel implements LineDisplayPanelObserver {
    public LineDisplayPanel() {
        super();

        setBackground(Color.WHITE);

        setVisible(false);
    }

    @Override
    public void updateVisibility() {
        setVisible(!isVisible());
    }
    
}
