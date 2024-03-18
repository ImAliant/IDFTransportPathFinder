package fr.u_paris.gla.project;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.BiPredicate;

import javax.swing.JButton;

import org.junit.jupiter.api.Test;

/** Unit test for AppWindow class. */
class WindowTest {
    private AppWindow window = new AppWindow("Test");

    @Test
    void testMapIsNotNull() {
        assertNotNull(window.getMap(), "The map should not be null");

        window.dispose();
        System.out.println("testMapIsNotNull OK");
    }    

     private void testZoomValue(JButton button, BiPredicate<Integer, Integer> comparaison, String func, String errorMessage) {
        int zoomValue = window.getMap().getZoom();
        button.doClick();
        int zoomValueAfter = window.getMap().getZoom();
        assertTrue(comparaison.test(zoomValue, zoomValueAfter), errorMessage);

        window.dispose();
        System.out.printf("%s OK | %d -> %d\n", func, zoomValue, zoomValueAfter);
    }

    @Test
    void testZoomInValue() {
        testZoomValue(
            window.getZoomInButton(), 
            (before, after) -> before > after, 
            "testZoomInValue", 
            "The zoom value should be less than the previous one"
        );
    }

    @Test
    void testZoomOutValue() {
        testZoomValue(
            window.getZoomOutButton(), 
            (before, after) -> before < after, 
            "testZoomOutValue", 
            "The zoom value should be greater than the previous one"
        );
    }
}
