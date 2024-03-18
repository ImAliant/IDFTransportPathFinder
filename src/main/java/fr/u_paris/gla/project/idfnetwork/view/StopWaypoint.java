package fr.u_paris.gla.project.idfnetwork.view;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import fr.u_paris.gla.project.idfnetwork.Stop;

import java.io.Serializable;

import javax.swing.JLabel;

public class StopWaypoint extends DefaultWaypoint implements Serializable {
    private static final long serialVersionUID = 1L;

    private transient Stop stop;
    private JLabel button;

    public StopWaypoint(Stop stop) {
        super(new GeoPosition(stop.getLatitude(), stop.getLongitude()));
        this.stop = stop;

        initButton();
    }

    private void initButton() {
        button = new ButtonWaypoint();
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Click on " + stop);
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    public Stop getStop() {
        return stop;
    }

    public JLabel getButton() {
        return button;
    }
}
