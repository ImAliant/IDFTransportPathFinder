package fr.u_paris.gla.crazytrip.utils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

public class TestCSVTools {

    // IDF mobilite API URLs
    private static final String TRACE_FILE_URL = "https://data.iledefrance-mobilites.fr/api/explore/v2.1/catalog/datasets/traces-des-lignes-de-transport-en-commun-idfm/exports/csv?lang=fr&timezone=Europe%2FBerlin&use_labels=true&delimiter=%3B";
    private static final String TEST_CSV = "src/test/resources/test.csv";
    private static ArrayList<String[]> lines = new ArrayList<>();

    @BeforeEach
    void testReadCSV() {
        assertDoesNotThrow(
                () -> {
                    CSVTools.readCSVFromURL(TRACE_FILE_URL, (String[] line) -> {
                        TestCSVTools.lines.add(line);
                    });
                }, "CSV reading throwed an exception");

    }

    @AfterAll
    static void stop() {
        File file = new File(TEST_CSV);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testReadCSVException() {
        assertThrows(Exception.class,
                () -> {
                    CSVTools.readCSVFromURL("TRACE_FILE_URL", (String[] line) -> {
                        TestCSVTools.lines.add(line);
                    });
                }, "CSV reading did not throw an exception as excpected");

    }

    @Test
    void testLinesFromCSV() {
        // assert that there is something readed from the CSV
        assertNotNull(TestCSVTools.lines, "CSV does not read lines correctly");
        for (String[] line : TestCSVTools.lines) {

            // assert que la ligne n'est pas vide et bien lue
            assertTrue(line.length > 0,
                    "CSV does not read the line correctly :\n" + line);
            // assert that the line has the right number of columns == 13 Especially for the
            // Traces file
            assertTrue(line.length == 13,
                    "CSV does not read the All line colomns :\n" + line);
        }
    }

    @Test
    void testWriteCSV() {
        assertDoesNotThrow(() -> {
            CSVTools.writeCSVToFile(TEST_CSV, TestCSVTools.lines.stream());
        }, "CSV writing throwed an exception");

    }

}
