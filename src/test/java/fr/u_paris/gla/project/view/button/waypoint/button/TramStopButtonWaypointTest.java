package fr.u_paris.gla.project.view.button.waypoint.button;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import fr.u_paris.gla.project.idfnetwork.Stop;

class TramStopButtonWaypointTest {
    private static Stop stop;
    private static TramStopButtonWaypoint tramStopButtonWaypoint;

    @BeforeAll
    static void setUp() {
        stop = new Stop("stop", 0, 0);
        tramStopButtonWaypoint = new TramStopButtonWaypoint(stop);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7})
    void updateVisibilityTest(int value) {
        tramStopButtonWaypoint.updateVisibility(value);
        if (value <= TramStopButtonWaypoint.ZOOM_THRESHOLD) {
            assertTrue(tramStopButtonWaypoint.isVisible(),
                    "The tram stop button should be visible when the zoom level is less than or equal to the threshold.");
        } else {
            assertFalse(tramStopButtonWaypoint.isVisible(),
                    "The tram stop button should not be visible when the zoom level is greater than the threshold.");
        }
    }
}
