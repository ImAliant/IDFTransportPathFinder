package fr.u_paris.gla.crazytrip.idfnetwork.line;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import fr.u_paris.gla.crazytrip.idfnetwork.Stop;

class MetroLineTest {
    @Test
    void hashCodeTest() {
        MetroLine MetroLine = new MetroLine("test", "red");
        MetroLine MetroLine2 = new MetroLine("test", "red");
        assertEquals(
            MetroLine.hashCode(), MetroLine2.hashCode(), 
            "Hash code should be the same for two identical MetroLine objects"
            );
    }

    @Test
    void equalsDifferentLineTest() {
        MetroLine MetroLine = new MetroLine("test", "red");
        MetroLine MetroLine2 = new MetroLine("test", "red");
        assertEquals(
            MetroLine, MetroLine2, 
            "Two identical MetroLine objects should be equal"
        );
        assertEquals(MetroLine2, MetroLine, "equals should be symmetric");
    }

    @Test
    void equalsReflexiveTest() {
        MetroLine MetroLine = new MetroLine("test", "red");
        assertEquals(
            MetroLine, MetroLine, 
            "A MetroLine object should be equal to itself"
        );
    }

    @Test
    void equalsNullTest() {
        MetroLine MetroLine = new MetroLine("test", "red");
        assertNotEquals(null, MetroLine, 
        "A MetroLine object should not be equal to null");
    }

    @Test
    void equalsDifferentTypeTest() {
        MetroLine MetroLine = new MetroLine("test", "red");
        Object obj = new Object();
        assertNotEquals(
            MetroLine, obj, 
            "A MetroLine object should not be equal to an object of another type"
        );
    }

    @Test
    void equalsDifferentLineNameTest() {
        MetroLine MetroLine = new MetroLine("test", "red");
        MetroLine MetroLine2 = new MetroLine("test2", "red");
        assertNotEquals(
            MetroLine, MetroLine2, 
            "Two MetroLine objects with different names should not be equal"
        );
    }

    @Test
    void equalsDifferentLineColorTest() {
        MetroLine MetroLine = new MetroLine("test", "red");
        MetroLine MetroLine2 = new MetroLine("test", "blue");
        assertNotEquals(
            MetroLine, MetroLine2, 
            "Two MetroLine objects with different colors should not be equal"
        );
    }

    @Test
    void equalsDifferentLineStopsTest() {
        MetroLine MetroLine = new MetroLine("test", "red");
        MetroLine MetroLine2 = new MetroLine("test", "red");
        MetroLine.addStop(new Stop("stop1", 0, 0));
        assertNotEquals(
            MetroLine, MetroLine2, 
            "Two MetroLine objects with different stops should not be equal"
        );
    }

    @Test
    void equalsDifferentLinePathsTest() {
        MetroLine MetroLine = new MetroLine("test", "red");
        MetroLine MetroLine2 = new MetroLine("test", "red");
        MetroLine.addPath(new Stop("stop1", 0, 0), new Stop("stop2", 0, 0), 0, 0);
        assertNotEquals(
            MetroLine, MetroLine2, 
            "Two MetroLine objects with different paths should not be equal"
        );
    }

    @Test
    void toStringTest() {
        MetroLine MetroLine = new MetroLine("test", "red");
        assertEquals(
            "M test", MetroLine.toString(), 
            "toString should return the correct string representation of a MetroLine object"
        );
    }

    @Test
    void getTypeTest() {
        MetroLine MetroLine = new MetroLine("test", "red");
        assertEquals(
            LineType.METRO, MetroLine.getType(), 
            "getType should return LineType.METRO for a MetroLine object"
        );
    }
}
