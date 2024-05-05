package fr.u_paris.gla.crazytrip.idfnetwork.line;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import fr.u_paris.gla.crazytrip.idfnetwork.Stop;

class BusLineTest {
    @Test
    void hashCodeTest() {
        BusLine busLine = new BusLine("test", "red");
        BusLine busLine2 = new BusLine("test", "red");
        assertEquals(
            busLine.hashCode(), busLine2.hashCode(), 
            "Hash code should be the same for two identical BusLine objects"
            );
    }

    @Test
    void equalsDifferentLineTest() {
        BusLine busLine = new BusLine("test", "red");
        BusLine busLine2 = new BusLine("test", "red");
        assertEquals(
            busLine, busLine2, 
            "Two identical BusLine objects should be equal"
        );
        assertEquals(busLine2, busLine, "equals should be symmetric");
    }

    @Test
    void equalsReflexiveTest() {
        BusLine busLine = new BusLine("test", "red");
        assertEquals(
            busLine, busLine, 
            "A BusLine object should be equal to itself"
        );
    }

    @Test
    void equalsNullTest() {
        BusLine busLine = new BusLine("test", "red");
        assertNotEquals(null, busLine, 
        "A BusLine object should not be equal to null");
    }

    @Test
    void equalsDifferentTypeTest() {
        BusLine busLine = new BusLine("test", "red");
        Object obj = new Object();
        assertNotEquals(
            busLine, obj, 
            "A BusLine object should not be equal to an object of another type"
        );
    }

    @Test
    void equalsDifferentLineNameTest() {
        BusLine busLine = new BusLine("test", "red");
        BusLine busLine2 = new BusLine("test2", "red");
        assertNotEquals(
            busLine, busLine2, 
            "Two BusLine objects with different names should not be equal"
        );
    }

    @Test
    void equalsDifferentLineColorTest() {
        BusLine busLine = new BusLine("test", "red");
        BusLine busLine2 = new BusLine("test", "blue");
        assertNotEquals(
            busLine, busLine2, 
            "Two BusLine objects with different colors should not be equal"
        );
    }

    @Test
    void equalsDifferentLineStopsTest() {
        BusLine busLine = new BusLine("test", "red");
        BusLine busLine2 = new BusLine("test", "red");
        busLine.addStop(new Stop("stop1", 0, 0));
        assertNotEquals(
            busLine, busLine2, 
            "Two BusLine objects with different stops should not be equal"
        );
    }

    @Test
    void equalsDifferentLinePathsTest() {
        BusLine busLine = new BusLine("test", "red");
        BusLine busLine2 = new BusLine("test", "red");
        busLine.addPath(new Stop("stop1", 0, 0), new Stop("stop2", 0, 0), 0, 0);
        assertNotEquals(
            busLine, busLine2, 
            "Two BusLine objects with different paths should not be equal"
        );
    }

    @Test
    void toStringTest() {
        BusLine busLine = new BusLine("test", "red");
        assertEquals(
            "B test", busLine.toString(), 
            "toString should return the correct string representation of a BusLine object"
        );
    }

    @Test
    void getTypeTest() {
        BusLine busLine = new BusLine("test", "red");
        assertEquals(
            LineType.BUS, busLine.getType(), 
            "getType should return LineType.BUS for a BusLine object"
        );
    }
}
