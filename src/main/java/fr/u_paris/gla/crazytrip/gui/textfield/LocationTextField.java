package fr.u_paris.gla.crazytrip.gui.textfield;

import java.awt.Dimension;

import javax.swing.JTextField;

import fr.u_paris.gla.crazytrip.gui.combobox.SuggestionComboBox;
import fr.u_paris.gla.crazytrip.gui.listener.LocationTextFieldDocumentListener;
import fr.u_paris.gla.crazytrip.gui.observer.SuggestionSelectionObserver;
import fr.u_paris.gla.crazytrip.model.Station;

public class LocationTextField extends JTextField implements SuggestionSelectionObserver {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 25;

    public LocationTextField(SuggestionComboBox suggestionComboBox) {
        super();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        this.getDocument().addDocumentListener(new LocationTextFieldDocumentListener(this, suggestionComboBox));
    }

    @Override
    public void update(Station station) {
        setText(station.toString());
    }
}
