package fr.u_paris.gla.project;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Unit test for simple App. */
class AppTest {
    @BeforeEach
    void setUp() {
        // Delete the output.csv file before each test
        File file = new File("target/output.csv");
        if (file.exists()) {
            file.delete();
        }
    }

    @AfterEach
    void reset() {
        App.reset();
    }

    @Test
    void testLaunch() throws InterruptedException, ExecutionException {
        App.launch();
        App.getLatch().await();
        assertNotNull(App.getWindow(), "The window should not be null");

        App.getWindow().dispose();
        System.out.println("testLaunch OK");
    }

    @Test
    void testInitNetworkFileExists() {
        File file = new File("target/output.csv");
        try {
            assertTrue(file.createNewFile());
        } catch (Exception e) {
            fail("Failed to create output.csv the file");
        }

        App.initNetwork();

        // verify that extraction() was not called
        assertTrue(!App.extractionCalled, "extraction() should not have been called");

        // verify that load() was called
        assertTrue(App.loadCalled, "load() should have been called");

        System.out.println("testInitNetworkFileExists OK");
    }

    @Test
    void testInitNetworkFileDoesNotExist() {
        App.initNetwork();

        // verify that extraction() was called
        assertTrue(App.extractionCalled, "extraction() should have been called");

        // verify that load() was called
        assertTrue(App.loadCalled, "load() should have been called");

        System.out.println("testInitNetworkFileDoesNotExist OK");
    }

    @Test
    void testExtractionCalled() {
        try {
            App.extraction();
        } catch (IOException e) {
            fail("extraction() should not throw an exception");
        }

        // verify that extraction() was called
        assertTrue(App.extractionCalled, "extraction() should have been called");

        System.out.println("testExtractionCalled OK");
    }
}
