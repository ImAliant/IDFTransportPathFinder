package fr.u_paris.gla.crazytrip.view.button.waypoint.button;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import fr.u_paris.gla.crazytrip.idfnetwork.Stop;
import fr.u_paris.gla.crazytrip.idfnetwork.line.LineType;

class StopButtonWaypointTest {
    private static Stop s1;

    @BeforeAll
    static void setUp() {
        s1 = new Stop("stop", 0, 0);
    }

    @Test
    void createButtonNullLineTypeTest() {
        StopButtonWaypoint sbw = StopButtonWaypoint.createButton(s1, null);

        assertEquals(DefaultStopButtonWaypoint.class, sbw.getClass());
    }

    @Test
    void createButtonMetroLineTypeTest() {
        StopButtonWaypoint sbw = StopButtonWaypoint.createButton(s1, LineType.METRO);

        assertEquals(MetroStopButtonWaypoint.class, sbw.getClass());
    }

    @Test
    void createButtonRERLineTypeTest() {
        StopButtonWaypoint sbw = StopButtonWaypoint.createButton(s1, LineType.RER);

        assertEquals(RERStopButtonWaypoint.class, sbw.getClass());
    }

    @Test
    void createButtonTramwayLineTypeTest() {
        StopButtonWaypoint sbw = StopButtonWaypoint.createButton(s1, LineType.TRAMWAY);

        assertEquals(TramStopButtonWaypoint.class, sbw.getClass());
    }

    @Test
    void createButtonBusLineTypeTest() {
        StopButtonWaypoint sbw = StopButtonWaypoint.createButton(s1, LineType.BUS);

        assertEquals(BusStopButtonWaypoint.class, sbw.getClass());
    }

    @Test
    void createButtonFunicularLineTypeTest() {
        StopButtonWaypoint sbw = StopButtonWaypoint.createButton(s1, LineType.FUNICULAIRE);

        assertEquals(FunicularStopButtonWaypoint.class, sbw.getClass());
    }

    @Test
    void createButtonOtherLineTypeTest() {
        StopButtonWaypoint sbw = StopButtonWaypoint.createButton(s1, LineType.WALKING);

        assertEquals(DefaultStopButtonWaypoint.class, sbw.getClass());
    }
}
