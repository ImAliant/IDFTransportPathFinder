package fr.u_paris.gla.project.idfnetwork.line;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import fr.u_paris.gla.project.idfnetwork.Stop;

class WalkingLineTest {
    @Test
    void hashCodeTest() {
        WalkingLine WalkingLine = new WalkingLine("test", "red");
        WalkingLine WalkingLine2 = new WalkingLine("test", "red");
        assertEquals(
            WalkingLine.hashCode(), WalkingLine2.hashCode(), 
            "Hash code should be the same for two identical WalkingLine objects"
            );
    }

    /* @Test
    void equalsDifferentLineTest() {
        WalkingLine WalkingLine = new WalkingLine("test", "red");
        WalkingLine WalkingLine2 = new WalkingLine("test", "red");
        assertEquals(
            WalkingLine, WalkingLine2, 
            "Two identical WalkingLine objects should be equal"
        );
        assertEquals(WalkingLine2, WalkingLine, "equals should be symmetric");
    } */

    @Test
    void equalsReflexiveTest() {
        WalkingLine WalkingLine = new WalkingLine("test", "red");
        assertEquals(
            WalkingLine, WalkingLine, 
            "A WalkingLine object should be equal to itself"
        );
    }

    @Test
    void equalsNullTest() {
        WalkingLine WalkingLine = new WalkingLine("test", "red");
        assertNotEquals(null, WalkingLine, 
        "A WalkingLine object should not be equal to null");
    }

    @Test
    void equalsDifferentTypeTest() {
        WalkingLine WalkingLine = new WalkingLine("test", "red");
        Object obj = new Object();
        assertNotEquals(
            WalkingLine, obj, 
            "A WalkingLine object should not be equal to an object of another type"
        );
    }

    @Test
    void equalsDifferentLineNameTest() {
        WalkingLine WalkingLine = new WalkingLine("test", "red");
        WalkingLine WalkingLine2 = new WalkingLine("test2", "red");
        assertNotEquals(
            WalkingLine, WalkingLine2, 
            "Two WalkingLine objects with different names should not be equal"
        );
    }

    @Test
    void equalsDifferentLineColorTest() {
        WalkingLine WalkingLine = new WalkingLine("test", "red");
        WalkingLine WalkingLine2 = new WalkingLine("test", "blue");
        assertNotEquals(
            WalkingLine, WalkingLine2, 
            "Two WalkingLine objects with different colors should not be equal"
        );
    }

    @Test
    void equalsDifferentLineStopsTest() {
        WalkingLine WalkingLine = new WalkingLine("test", "red");
        WalkingLine WalkingLine2 = new WalkingLine("test", "red");
        WalkingLine.addStop(new Stop("stop1", 0, 0));
        assertNotEquals(
            WalkingLine, WalkingLine2, 
            "Two WalkingLine objects with different stops should not be equal"
        );
    }

    @Test
    void equalsDifferentLinePathsTest() {
        WalkingLine WalkingLine = new WalkingLine("test", "red");
        WalkingLine WalkingLine2 = new WalkingLine("test", "red");
        WalkingLine.addPath(new Stop("stop1", 0, 0), new Stop("stop2", 0, 0), 0, 0);
        assertNotEquals(
            WalkingLine, WalkingLine2, 
            "Two WalkingLine objects with different paths should not be equal"
        );
    }

    @Test
    void toStringTest() {
        WalkingLine WalkingLine = new WalkingLine("test", "red");
        assertEquals(
            "Marcher jusqu'à", WalkingLine.toString(), 
            "toString should return the correct string representation of a WalkingLine object"
        );
    }

    @Test
    void getTypeTest() {
        WalkingLine WalkingLine = new WalkingLine("test", "red");
        assertEquals(
            LineType.WALKING, WalkingLine.getType(), 
            "getType should return LineType.WALKING for a WalkingLine object"
        );
    }


}
