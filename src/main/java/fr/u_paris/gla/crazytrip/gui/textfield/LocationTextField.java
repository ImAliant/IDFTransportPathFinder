package fr.u_paris.gla.crazytrip.gui.textfield;

import java.awt.Dimension;

import javax.swing.JTextField;

import org.jxmapviewer.viewer.GeoPosition;

import fr.u_paris.gla.crazytrip.gui.combobox.SuggestionComboBox;
import fr.u_paris.gla.crazytrip.gui.listener.LocationTextFieldDocumentListener;
import fr.u_paris.gla.crazytrip.gui.observer.SelectPositionObserver;
import fr.u_paris.gla.crazytrip.model.Node;

/**
 * A text field that allows the user to enter a location.
 * 
 * This text field is used to enter the start and end locations of a trip.
 * 
 * @see SuggestionComboBox
 * @see LocationTextFieldDocumentListener
 * @see SelectPositionObserver
 * @see Node
 */
public class LocationTextField extends JTextField implements SelectPositionObserver {
    /** Width of the text field */
    private static final int WIDTH = 200;
    /** Height of the text field */
    private static final int HEIGHT = 25;

    /**
     * Create a new location text field.
     * 
     * @param suggestionComboBox The suggestion combo box that will be used to suggest
     *                           locations to the user.
     */
    public LocationTextField(SuggestionComboBox suggestionComboBox) {
        super();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        this.getDocument().addDocumentListener(new LocationTextFieldDocumentListener(this, suggestionComboBox));
    }

    @Override
    public void update(Node station) {
        setText(station.toString());
    }

    @Override
    public void update(GeoPosition position) {
        setText(position.getLatitude() + ", " + position.getLongitude());
    }
}
