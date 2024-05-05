package fr.u_paris.gla.crazytrip.idfnetwork.line;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import fr.u_paris.gla.crazytrip.idfnetwork.Stop;

class FunicularLineTest {
    @Test
    void hashCodeTest() {
        FunicularLine FunicularLine = new FunicularLine("test", "red");
        FunicularLine FunicularLine2 = new FunicularLine("test", "red");
        assertEquals(
            FunicularLine.hashCode(), FunicularLine2.hashCode(), 
            "Hash code should be the same for two identical FunicularLine objects"
            );
    }

    @Test
    void equalsDifferentLineTest() {
        FunicularLine FunicularLine = new FunicularLine("test", "red");
        FunicularLine FunicularLine2 = new FunicularLine("test", "red");
        assertEquals(
            FunicularLine, FunicularLine2, 
            "Two identical FunicularLine objects should be equal"
        );
        assertEquals(FunicularLine2, FunicularLine, "equals should be symmetric");
    }

    @Test
    void equalsReflexiveTest() {
        FunicularLine FunicularLine = new FunicularLine("test", "red");
        assertEquals(
            FunicularLine, FunicularLine, 
            "A FunicularLine object should be equal to itself"
        );
    }

    @Test
    void equalsNullTest() {
        FunicularLine FunicularLine = new FunicularLine("test", "red");
        assertNotEquals(null, FunicularLine, 
        "A FunicularLine object should not be equal to null");
    }

    @Test
    void equalsDifferentTypeTest() {
        FunicularLine FunicularLine = new FunicularLine("test", "red");
        Object obj = new Object();
        assertNotEquals(
            FunicularLine, obj, 
            "A FunicularLine object should not be equal to an object of another type"
        );
    }

    @Test
    void equalsDifferentLineNameTest() {
        FunicularLine FunicularLine = new FunicularLine("test", "red");
        FunicularLine FunicularLine2 = new FunicularLine("test2", "red");
        assertNotEquals(
            FunicularLine, FunicularLine2, 
            "Two FunicularLine objects with different names should not be equal"
        );
    }

    @Test
    void equalsDifferentLineColorTest() {
        FunicularLine FunicularLine = new FunicularLine("test", "red");
        FunicularLine FunicularLine2 = new FunicularLine("test", "blue");
        assertNotEquals(
            FunicularLine, FunicularLine2, 
            "Two FunicularLine objects with different colors should not be equal"
        );
    }

    @Test
    void equalsDifferentLineStopsTest() {
        FunicularLine FunicularLine = new FunicularLine("test", "red");
        FunicularLine FunicularLine2 = new FunicularLine("test", "red");
        FunicularLine.addStop(new Stop("stop1", 0, 0));
        assertNotEquals(
            FunicularLine, FunicularLine2, 
            "Two FunicularLine objects with different stops should not be equal"
        );
    }

    @Test
    void equalsDifferentLinePathsTest() {
        FunicularLine FunicularLine = new FunicularLine("test", "red");
        FunicularLine FunicularLine2 = new FunicularLine("test", "red");
        FunicularLine.addPath(new Stop("stop1", 0, 0), new Stop("stop2", 0, 0), 0, 0);
        assertNotEquals(
            FunicularLine, FunicularLine2, 
            "Two FunicularLine objects with different paths should not be equal"
        );
    }

    @Test
    void toStringTest() {
        FunicularLine FunicularLine = new FunicularLine("test", "red");
        assertEquals(
            "F test", FunicularLine.toString(), 
            "toString should return the correct string representation of a FunicularLine object"
        );
    }

    @Test
    void getTypeTest() {
        FunicularLine FunicularLine = new FunicularLine("test", "red");
        assertEquals(
            LineType.FUNICULAIRE, FunicularLine.getType(), 
            "getType should return LineType.FUNICULAIRE for a FunicularLine object"
        );
    }
}
