package fr.u_paris.gla.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RestrictedZoomJXMapViewerTest {
    private RestrictedZoomJXMapViewer map = new RestrictedZoomJXMapViewer();

    @ParameterizedTest
    @ValueSource (ints = {-1, 0, 1, 2, 6})
    void testSetZoom(int numbers) {
        int zoomValue = map.getZoom();
        map.setZoom(numbers);
        int zoomValueAfter = map.getZoom();

        if (numbers < 0) {
            assertEquals(zoomValue, zoomValueAfter, "The zoom value should not be less than 0");
        }
        else if (numbers == 0) {
            assertEquals(1, zoomValueAfter, "The zoom value should be equal to 0");
        }
        else if (numbers > 5) {
            assertEquals(zoomValueAfter, zoomValue, "The zoom value should not be greater than 5");
        } else {
            assertEquals(zoomValueAfter, numbers, "The zoom value should be equal to the input value");
        }

        System.out.println("testSetZoom OK");
    }
}
