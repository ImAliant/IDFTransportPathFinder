package fr.u_paris.gla.project.idfnetwork.line;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import fr.u_paris.gla.project.idfnetwork.Stop;

class RERLineTest {
    @Test
    void hashCodeTest() {
        RERLine RERLine = new RERLine("test", "red");
        RERLine RERLine2 = new RERLine("test", "red");
        assertEquals(
            RERLine.hashCode(), RERLine2.hashCode(), 
            "Hash code should be the same for two identical RERLine objects"
            );
    }

    @Test
    void equalsDifferentLineTest() {
        RERLine RERLine = new RERLine("test", "red");
        RERLine RERLine2 = new RERLine("test", "red");
        assertEquals(
            RERLine, RERLine2, 
            "Two identical RERLine objects should be equal"
        );
        assertEquals(RERLine2, RERLine, "equals should be symmetric");
    }

    @Test
    void equalsReflexiveTest() {
        RERLine RERLine = new RERLine("test", "red");
        assertEquals(
            RERLine, RERLine, 
            "A RERLine object should be equal to itself"
        );
    }

    @Test
    void equalsNullTest() {
        RERLine RERLine = new RERLine("test", "red");
        assertNotEquals(null, RERLine, 
        "A RERLine object should not be equal to null");
    }

    @Test
    void equalsDifferentTypeTest() {
        RERLine RERLine = new RERLine("test", "red");
        Object obj = new Object();
        assertNotEquals(
            RERLine, obj, 
            "A RERLine object should not be equal to an object of another type"
        );
    }

    @Test
    void equalsDifferentLineNameTest() {
        RERLine RERLine = new RERLine("test", "red");
        RERLine RERLine2 = new RERLine("test2", "red");
        assertNotEquals(
            RERLine, RERLine2, 
            "Two RERLine objects with different names should not be equal"
        );
    }

    @Test
    void equalsDifferentLineColorTest() {
        RERLine RERLine = new RERLine("test", "red");
        RERLine RERLine2 = new RERLine("test", "blue");
        assertNotEquals(
            RERLine, RERLine2, 
            "Two RERLine objects with different colors should not be equal"
        );
    }

    @Test
    void equalsDifferentLineStopsTest() {
        RERLine RERLine = new RERLine("test", "red");
        RERLine RERLine2 = new RERLine("test", "red");
        RERLine.addStop(new Stop("stop1", 0, 0));
        assertNotEquals(
            RERLine, RERLine2, 
            "Two RERLine objects with different stops should not be equal"
        );
    }

    @Test
    void equalsDifferentLinePathsTest() {
        RERLine RERLine = new RERLine("test", "red");
        RERLine RERLine2 = new RERLine("test", "red");
        RERLine.addPath(new Stop("stop1", 0, 0), new Stop("stop2", 0, 0), 0, 0);
        assertNotEquals(
            RERLine, RERLine2, 
            "Two RERLine objects with different paths should not be equal"
        );
    }

    @Test
    void toStringTest() {
        RERLine RERLine = new RERLine("test", "red");
        assertEquals(
            "R test", RERLine.toString(), 
            "toString should return the correct string representation of a RERLine object"
        );
    }

    @Test
    void getTypeTest() {
        RERLine RERLine = new RERLine("test", "red");
        assertEquals(
            LineType.RER, RERLine.getType(), 
            "getType should return LineType.RER for a RERLine object"
        );
    }
}
