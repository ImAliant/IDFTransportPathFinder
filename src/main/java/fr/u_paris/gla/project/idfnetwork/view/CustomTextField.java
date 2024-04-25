package fr.u_paris.gla.project.idfnetwork.view;

import javax.swing.JTextField;

public class CustomTextField extends JTextField {
    public CustomTextField(SuggestionStationsScrollPane suggestions) {
        super();

        this.getDocument().addDocumentListener(
            new SuggestionDocumentListener(this, suggestions)
        );
    }
}
