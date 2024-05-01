package fr.u_paris.gla.project_temp.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import fr.u_paris.gla.project.io.TimeFormat;

class TimeFormatTest {
    @ParameterizedTest
    @ValueSource (strings = {"00:00", "00:10", "02:00", "test"})
    void testConvertToSeconds(String time) {    
        if (time.equals("test")) {
            assertThrows(IllegalArgumentException.class, () -> TimeFormat.convertToSeconds(time));
        } else {
            String[] parts = time.split(":");
            int expected = Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
            int actual = TimeFormat.convertToSeconds(time);
            assertEquals(expected, actual, "The time " + actual + " should be converted to " + expected + " seconds");

            System.out.println("testConvertToSeconds OK");
        }
    }
}
