package fr.u_paris.gla.project.view.combobox;

import java.awt.Dimension;

import javax.swing.JComboBox;

public class SuggestionStationsComboBox extends JComboBox<String>{
    private static final int WIDTH = 200;
    private static final int HEIGHT = 50;

    public SuggestionStationsComboBox() {
        super();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public void clearSuggestion() {
        removeAllItems();
    }
}


    

