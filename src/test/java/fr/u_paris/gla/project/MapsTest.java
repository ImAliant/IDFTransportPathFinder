package fr.u_paris.gla.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.BiFunction;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MapsTest {
    private Maps map = new Maps();

    private void testZoomValue(Runnable action, BiFunction<Integer, Integer, Integer> comparison, String func, String errorMessage) {
        int zoomValue = map.getZoom();
        action.run();
        int zoomValueAfter = map.getZoom();
        assertEquals(comparison.apply(zoomValue, 1), zoomValueAfter, errorMessage);

        System.out.printf("%s OK | %d -> %d\n", func, zoomValue, zoomValueAfter);
    }

    @Test
    void testZoomIn() {
        testZoomValue(
            map::zoomIn, 
            (before, after) -> before - after, 
            "testZoomIn", 
            "The zoom value should be greater than the previous one"
        );
    }

    @Test
    void testZoomOut() {
        testZoomValue(
            map::zoomOut, 
            (before, after) -> before + after, 
            "testZoomOut", 
            "The zoom value should be less than the previous one"
        );
    }

    @ParameterizedTest
    @ValueSource (ints = {-1, 0, 1, 2, 6})
    void testSetZoom(int numbers) {
        int zoomValue = map.getZoom();
        map.setZoom(numbers);
        int zoomValueAfter = map.getZoom();

        if (numbers < Maps.MIN_ZOOM) {
            assertEquals(zoomValue, zoomValueAfter, "The zoom value should not be less than 0");
        }
        else if (numbers == Maps.MIN_ZOOM) {
            assertEquals(Maps.MIN_ZOOM, zoomValueAfter, "The zoom value should be equal to 0");
        }
        else if (numbers > Maps.MAX_ZOOM) {
            assertEquals(zoomValueAfter, zoomValue, "The zoom value should not be greater than 5");
        } else {
            assertEquals(zoomValueAfter, numbers, "The zoom value should be equal to the input value");
        }

        System.out.println("testSetZoom OK");
    }
}
