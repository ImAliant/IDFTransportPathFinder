package fr.u_paris.gla.crazytrip.view.textfield;

import java.awt.Dimension;

import javax.swing.JTextField;

import org.jxmapviewer.viewer.GeoPosition;

import fr.u_paris.gla.crazytrip.observer.GeoPositionObserver;
import fr.u_paris.gla.crazytrip.view.combobox.SuggestionStationsComboBox;
import fr.u_paris.gla.crazytrip.view.listener.SuggestionDocumentListener;
/**
 * It extends {@code JTextField} to integrate geo-position functionality
 * and interact with a suggestions combo box to display location-based suggestions. This class implements
 * {@code GeoPositionObserver} to update its text content based on the geographic coordinates provided.
 *
 * @see JTextField
 * @see GeoPositionObserver
 * @author Aziz Ghizlane
 * @author Diamant Alexandre
 */
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
    /**
     * This method is called when the geo-position is updated.
     *
     * @param position an object containing the new geographic coordinates.
     */
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
