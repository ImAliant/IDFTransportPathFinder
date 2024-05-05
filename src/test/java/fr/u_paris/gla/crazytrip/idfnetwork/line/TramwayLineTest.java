package fr.u_paris.gla.crazytrip.idfnetwork.line;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import fr.u_paris.gla.crazytrip.idfnetwork.Stop;

class TramwayLineTest {
    @Test
    void hashCodeTest() {
        TramwayLine TramwayLine = new TramwayLine("test", "red");
        TramwayLine TramwayLine2 = new TramwayLine("test", "red");
        assertEquals(
            TramwayLine.hashCode(), TramwayLine2.hashCode(), 
            "Hash code should be the same for two identical TramwayLine objects"
            );
    }

    @Test
    void equalsDifferentLineTest() {
        TramwayLine TramwayLine = new TramwayLine("test", "red");
        TramwayLine TramwayLine2 = new TramwayLine("test", "red");
        assertEquals(
            TramwayLine, TramwayLine2, 
            "Two identical TramwayLine objects should be equal"
        );
        assertEquals(TramwayLine2, TramwayLine, "equals should be symmetric");
    }

    @Test
    void equalsReflexiveTest() {
        TramwayLine TramwayLine = new TramwayLine("test", "red");
        assertEquals(
            TramwayLine, TramwayLine, 
            "A TramwayLine object should be equal to itself"
        );
    }

    @Test
    void equalsNullTest() {
        TramwayLine TramwayLine = new TramwayLine("test", "red");
        assertNotEquals(null, TramwayLine, 
        "A TramwayLine object should not be equal to null");
    }

    @Test
    void equalsDifferentTypeTest() {
        TramwayLine TramwayLine = new TramwayLine("test", "red");
        Object obj = new Object();
        assertNotEquals(
            TramwayLine, obj, 
            "A TramwayLine object should not be equal to an object of another type"
        );
    }

    @Test
    void equalsDifferentLineNameTest() {
        TramwayLine TramwayLine = new TramwayLine("test", "red");
        TramwayLine TramwayLine2 = new TramwayLine("test2", "red");
        assertNotEquals(
            TramwayLine, TramwayLine2, 
            "Two TramwayLine objects with different names should not be equal"
        );
    }

    @Test
    void equalsDifferentLineColorTest() {
        TramwayLine TramwayLine = new TramwayLine("test", "red");
        TramwayLine TramwayLine2 = new TramwayLine("test", "blue");
        assertNotEquals(
            TramwayLine, TramwayLine2, 
            "Two TramwayLine objects with different colors should not be equal"
        );
    }

    @Test
    void equalsDifferentLineStopsTest() {
        TramwayLine TramwayLine = new TramwayLine("test", "red");
        TramwayLine TramwayLine2 = new TramwayLine("test", "red");
        TramwayLine.addStop(new Stop("stop1", 0, 0));
        assertNotEquals(
            TramwayLine, TramwayLine2, 
            "Two TramwayLine objects with different stops should not be equal"
        );
    }

    @Test
    void equalsDifferentLinePathsTest() {
        TramwayLine TramwayLine = new TramwayLine("test", "red");
        TramwayLine TramwayLine2 = new TramwayLine("test", "red");
        TramwayLine.addPath(new Stop("stop1", 0, 0), new Stop("stop2", 0, 0), 0, 0);
        assertNotEquals(
            TramwayLine, TramwayLine2, 
            "Two TramwayLine objects with different paths should not be equal"
        );
    }

    @Test
    void toStringTest() {
        TramwayLine TramwayLine = new TramwayLine("test", "red");
        assertEquals(
            "T test", TramwayLine.toString(), 
            "toString should return the correct string representation of a TramwayLine object"
        );
    }

    @Test
    void getTypeTest() {
        TramwayLine TramwayLine = new TramwayLine("test", "red");
        assertEquals(
            LineType.TRAMWAY, TramwayLine.getType(), 
            "getType should return LineType.TRAMWAY for a TramwayLine object"
        );
    }
}
