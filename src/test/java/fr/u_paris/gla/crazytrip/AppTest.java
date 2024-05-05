package fr.u_paris.gla.crazytrip;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.ExecutionException;

import java.io.IOException;
import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.u_paris.gla.crazytrip.idfnetwork.network.Network;

class AppTest {
    private static final String TEST_PATH_TO_OUTPUT = "src/test/resources/fr/u_paris/gla/crazytrip/idfnetwork/test_output_app.csv";
    private static final String TO_BE_DELETED_PATH_TO_OUTPUT = "src/test/resources/fr/u_paris/gla/crazytrip/idfnetwork/to_be_deleted.csv";
    private File file;

    private static Network network;

    @BeforeAll
    static void setUp() {
        network = Network.getInstance();
    }

    @BeforeEach
    void setUpEach() throws IOException {
        file = new File(TEST_PATH_TO_OUTPUT);

        if (!file.exists())
            file.createNewFile();

        network.clear();
    }

    @AfterEach
    void reset() {
        App.reset();
    }

    @AfterAll
    static void delete() {
        File file = new File(TO_BE_DELETED_PATH_TO_OUTPUT);
        File file2 = new File(TEST_PATH_TO_OUTPUT);

        if (file.exists())
            file.delete();

        if (file2.exists())
            file2.delete();

        network.clear();
    }

    @Test
    void extractionTest() throws IOException {
        App.extraction(TEST_PATH_TO_OUTPUT);

        assertTrue(App.extractionCalled,
                "extraction() should have been called");
    }

    @Test
    void loadWithExitingFileTest() throws IOException {
        App.initNetwork(TEST_PATH_TO_OUTPUT);

        assertTrue(App.loadCalled, "load() should have been called");
    }

    @Test
    void loadWithNonExitingFileTest() throws IOException {
        App.initNetwork(TO_BE_DELETED_PATH_TO_OUTPUT);

        assertTrue(App.extractionCalled, "extraction() should have been called");
        assertTrue(App.loadCalled, "load() should have been called");
    }

    @Test
    void testLaunch() throws InterruptedException, ExecutionException {
        App.launch();
        App.getLatch().await();

        assertNotNull(App.getWindow(), "The window should not be null");

        App.getWindow().dispose();
    }

    @Test
    void mainEmptyTest() {
        App.main(new String[] {});

        assertNull(App.getWindow(), "The window should be null");
    }

    @Test
    void mainInfoTest() {
        App.main(new String[] { "--info" });

        assertNull(App.getWindow(), "The window should be null");

        assertTrue(App.infoCalled, "info() should have been called");
    }

    @Test
    void mainGuiTest() throws InterruptedException {
        App.main(new String[] { "--gui" });
        App.getLatch().await();

        assertNotNull(App.getWindow(), "The window shouldn't be null");

        assertTrue(App.guiCalled, "gui() should have been called");

        App.getWindow().dispose();
    }
}
