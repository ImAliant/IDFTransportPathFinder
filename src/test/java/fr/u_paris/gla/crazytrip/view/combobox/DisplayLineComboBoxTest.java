package fr.u_paris.gla.crazytrip.view.combobox;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.u_paris.gla.crazytrip.idfnetwork.line.Line;
import fr.u_paris.gla.crazytrip.idfnetwork.line.MetroLine;

class DisplayLineComboBoxTest {
    private static DisplayLineComboBox cb;

    @BeforeAll
    static void setUp() {
        cb = new DisplayLineComboBox();
    }

    @BeforeEach
    void reset() {
        if (cb != null) {
            cb.clean();
        }
    }

    @Test
    void addLineTest() {
        Line line = new MetroLine("1", "yellow");
        cb.addLine(line);

        assertEquals(1, cb.getItemCount(),
                "The number of items in the combobox should be 1 after adding a line");
    }

    @Test
    void cleanTest() {
        Line line = new MetroLine("1", "yellow");
        cb.addLine(line);
        cb.clean();

        assertEquals(0, cb.getItemCount(), "The combobox should be empty after cleaning it");
    }

    @Test
    void getSelectedLineNotNullTest() {
        Line line = new MetroLine("1", "yellow");
        cb.addLine(line);
        cb.setSelectedIndex(0);

        assertEquals(line, cb.getSelectedLine(), "The selected line should be the one added");
    }

    @Test
    void getSelectedLineNullTest() {
        assertEquals(null, cb.getSelectedLine(), "The selected line should be null if no line is added");
    }

    @Test
    void updateTest() {
        Line line = new MetroLine("1", "yellow");
        cb.addLine(line);

        assertEquals(1, cb.getItemCount(),
                "The number of items in the combobox should be 1 after adding a line");

        Line line2 = new MetroLine("2", "red");
        Line line3 = new MetroLine("3", "blue");
        Line line4 = new MetroLine("4", "green");
        List<Line> lines = List.of(line2, line3, line4);
        cb.update(lines);

        assertEquals(3, cb.getItemCount(),
                "The number of items in the combobox should be 3 after updating it");
    }

    @Test
    void updateEmptyTest() {
        Line line = new MetroLine("1", "yellow");
        cb.addLine(line);

        assertEquals(1, cb.getItemCount(),
                "The number of items in the combobox should be 1 after adding a line");

        cb.update(List.of());

        assertEquals(0, cb.getItemCount(),
                "The number of items in the combobox should be 0 after updating it with an empty list");
    }
}
