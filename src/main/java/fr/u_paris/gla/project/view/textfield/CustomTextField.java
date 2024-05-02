package fr.u_paris.gla.project.view.textfield;

import java.awt.Dimension;

import javax.swing.JTextField;

import org.jxmapviewer.viewer.GeoPosition;

import fr.u_paris.gla.project.observer.GeoPositionObserver;
import fr.u_paris.gla.project.view.combobox.SuggestionStationsComboBox;
import fr.u_paris.gla.project.view.listener.SuggestionDocumentListener;

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

        /* System.out.println("Latitude: " + latitude + ", Longitude: " + longitude);
        String latString = Double.toString(latitude);
        String lonString = Double.toString(longitude);
        System.out.println("Latitude: " + latString + ", Longitude: " + lonString); */

        String pos = String.format("%s, %s", latitude, longitude);

        /* System.out.println("Position: " + pos); */

        setText(pos);
    }
}
