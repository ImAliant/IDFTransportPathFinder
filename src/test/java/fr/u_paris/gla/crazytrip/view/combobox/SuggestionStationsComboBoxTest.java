package fr.u_paris.gla.crazytrip.view.combobox;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SuggestionStationsComboBoxTest {
    @Test
    void clearSuggestionTest() {
        SuggestionStationsComboBox cb = new SuggestionStationsComboBox();
        cb.addItem("Station 1");
        cb.addItem("Station 2");
        cb.addItem("Station 3");

        assertEquals(3, cb.getItemCount(), 
        "The number of items in the combobox should be 3 before clearing it");

        cb.clearSuggestion();

        assertEquals(0, cb.getItemCount(),
        "The number of items in the combobox should be 0 after clearing it");
    }
}
