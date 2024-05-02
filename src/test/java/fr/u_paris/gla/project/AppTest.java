package fr.u_paris.gla.project;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.ExecutionException;

import java.io.IOException;
import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.u_paris.gla.project.idfnetwork.network.Network;

class AppTest {
    private static final String TEST_PATH_TO_OUTPUT = "src/test/resources/fr/u_paris/gla/project/idfnetwork/test_output.csv";
    private static final String TO_BE_DELETED_PATH_TO_OUTPUT = "src/test/resources/fr/u_paris/gla/project/idfnetwork/to_be_deleted.csv";
    private File file;

    private static Network network;

    @BeforeAll
    static void setUp() {
        network = Network.getInstance();
    }

    @BeforeEach
    void setUpEach() {
        file = new File(TEST_PATH_TO_OUTPUT);

        if (!file.exists())
            fail("The file " + TEST_PATH_TO_OUTPUT + " does not exist");

        network.clear();
    }

    @AfterEach
    void reset() {
        App.reset();
    }

    @AfterAll
    static void delete() {
        File file = new File(TO_BE_DELETED_PATH_TO_OUTPUT);

        if (file.exists())
            file.delete();
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
