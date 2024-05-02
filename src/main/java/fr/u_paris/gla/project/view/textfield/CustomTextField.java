package fr.u_paris.gla.project.view.textfield;

import java.awt.Dimension;

import javax.swing.JTextField;

import org.jxmapviewer.viewer.GeoPosition;

import fr.u_paris.gla.project.observer.GeoPositionObserver;
import fr.u_paris.gla.project.view.combobox.SuggestionStationsComboBox;
import fr.u_paris.gla.project.view.listener.SuggestionDocumentListener;
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

        String pos = String.format("%.14f, %.14f", latitude, longitude);

        setText(pos);
    }
}
