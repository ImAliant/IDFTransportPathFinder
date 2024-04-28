package fr.u_paris.gla.project.idfnetwork.view;

import java.awt.Dimension;

import javax.swing.JTextField;

public class CustomTextField extends JTextField {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 25;

    public CustomTextField(SuggestionStationsComboBox suggestions) {
        super();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        this.getDocument().addDocumentListener(
            new SuggestionDocumentListener(this, suggestions)
        );
    }
}
