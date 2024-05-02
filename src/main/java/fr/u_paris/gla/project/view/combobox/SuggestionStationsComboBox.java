package fr.u_paris.gla.project.view.combobox;

import java.awt.Dimension;

import javax.swing.JComboBox;
/**
* Custom JComboBox specifically designed for showing station suggestions in a GUI application.
* It is tailored to have fixed dimensions suitable for display.
* @author Aziz Ghizlane
*/
public class SuggestionStationsComboBox extends JComboBox<String>{
    private static final int WIDTH = 200;
    private static final int HEIGHT = 50;
    /**
     * Constructs a new SuggestionStationsComboBox with predefined dimensions.
     */
    public SuggestionStationsComboBox() {
        super();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public void clearSuggestion() {
        removeAllItems();

    }

    @Override
    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
}


    

