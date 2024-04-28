package fr.u_paris.gla.project.idfnetwork.view;

import java.awt.Dimension;

import javax.swing.JTextField;

import org.jxmapviewer.viewer.GeoPosition;

import fr.u_paris.gla.project.observer.GeoPositionObserver;

public class CustomTextField extends JTextField implements GeoPositionObserver {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 25;

    public CustomTextField(SuggestionStationsComboBox suggestions) {
        super();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        this.getDocument().addDocumentListener(
            new SuggestionDocumentListener(this, suggestions)
        );
    }

    @Override
    public void getGeoPosition(GeoPosition position) {
        double latitude = position.getLatitude();
        double longitude = position.getLongitude();

        String pos = String.format("%.14f,%.14f", latitude, longitude);

        setText(pos);
    }
}
