package fr.u_paris.gla.project;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import fr.u_paris.gla.project.idfnetwork.view.button.transportButton.BusButton;
import fr.u_paris.gla.project.idfnetwork.view.button.transportButton.MetroButton;
import fr.u_paris.gla.project.idfnetwork.view.button.transportButton.PlansButton;
import fr.u_paris.gla.project.idfnetwork.view.button.transportButton.RERButton;
import fr.u_paris.gla.project.idfnetwork.view.button.transportButton.TramButton;
import fr.u_paris.gla.project.observer.LineDisplayPanelObserver;
import javax.swing.BoxLayout;

public class LineDisplayPanel extends JPanel implements LineDisplayPanelObserver {
    public LineDisplayPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS)); // Set layout to horizantal
        setBackground(new Color(100, 181, 246));
        setVisible(false);
        initializeButtons();
        setPreferredSize(new Dimension(100, 100));
    }

    private void initializeButtons() {
        add(new MetroButton());
        add(new RERButton());
        add(new BusButton());
        add(new TramButton());
        add(new PlansButton());

        this.revalidate();

    }

    @Override
    public void updateVisibility() {
        setVisible(!isVisible());
    }

    public void setPanelSize(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        revalidate(); // This ensures the panel updates its layout after the size change
        repaint(); // This ensures the panel repaints after the size change
    }

}
