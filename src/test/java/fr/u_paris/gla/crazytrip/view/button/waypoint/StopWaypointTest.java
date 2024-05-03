package fr.u_paris.gla.crazytrip.view.button.waypoint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import fr.u_paris.gla.crazytrip.idfnetwork.Stop;

class StopWaypointTest {
    @Test
    void constructorTest() {
        Stop s1 = new Stop("stop", 0, 0);
        StopWaypoint sw = new StopWaypoint(s1);

        assertEquals(s1, sw.getStop(),
        "StopWaypoint constructor does not set the stop correctly");

        assertEquals(s1.getStopName(), sw.toString(),
        "StopWaypoint constructor does not set the name correctly");

        assertNotNull(sw.getButton(),
        "StopWaypoint constructor does not set the button correctly");
    }
}
