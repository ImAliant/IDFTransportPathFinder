package fr.u_paris.gla.project;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/** Unit test for simple App. */
class AppTest {
    @Test
    void testLaunch() throws InterruptedException {
        App.launch();
        App.getLatch().await();
        assertNotNull(App.getWindow(), "The window should not be null");

        App.getWindow().dispose();
        System.out.println("testLaunch OK");
    }

    
}
