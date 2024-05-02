package fr.u_paris.gla.project.view.label;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CustomLabelTest {
    @Test
    void constructorTest() {
        CustomLabel customLabel = new CustomLabel("text");
        
        assertEquals("text", customLabel.getText(), 
        "The text of the label should be the one given in the constructor.");

        assertEquals(CustomLabel.BACKGROUND_COLOR, customLabel.getBackground(),
        "The background color of the label should be the one defined in the class.");

        assertEquals(CustomLabel.LABEL_FOREGROUND, customLabel.getForeground(),
        "The foreground color of the label should be the one defined in the class.");

        assertTrue(customLabel.isOpaque(),
        "The opaque property of the label should be true.");
    }
}
