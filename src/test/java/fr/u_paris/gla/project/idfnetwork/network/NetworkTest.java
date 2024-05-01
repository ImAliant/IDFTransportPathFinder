package fr.u_paris.gla.project.idfnetwork.network;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.u_paris.gla.project.idfnetwork.Stop;
import fr.u_paris.gla.project.idfnetwork.line.BusLine;
import fr.u_paris.gla.project.idfnetwork.line.Line;
import fr.u_paris.gla.project.idfnetwork.line.LineType;
import fr.u_paris.gla.project.idfnetwork.line.MetroLine;

class NetworkTest {
    private static Network instance = Network.getInstance();

    @BeforeEach
    void setUp() {
        instance.clear();
    }

    @Test
    void getInstanceTest() {
        Network network2 = Network.getInstance();
        assertEquals(instance, network2, "The two instances should be the same");
    }

    @Test
    void findLineNotExistingTest() {
        Line line = instance.findLine("test", LineType.METRO);
        assertEquals(null, line, "The line should not be found");
    }

    @Test
    void findLineExistingTest() {
        Line line = new MetroLine("test", "red");
        instance.addLine(line);

        Line test = instance.findLine("test", LineType.METRO);
        assertEquals(line, test, "The line should be found");
    }

    @Test
    void getLinesByTypeSameTypeTest() {
        Line line1 = new MetroLine("test1", "red");
        Line line2 = new MetroLine("test2", "blue");
        Line line3 = new MetroLine("test3", "green");
        instance.addLine(line1);
        instance.addLine(line2);
        instance.addLine(line3);

        assertEquals(3, instance.getLinesByType(LineType.METRO).size(), 
        "The number of lines should be 3");
    }

    @Test
    void getLinesByTypeDifferentTypeTest() {
        Line line1 = new MetroLine("test1", "red");
        Line line2 = new BusLine("test2", "blue");
        Line line3 = new MetroLine("test3", "green");
        instance.addLine(line1);
        instance.addLine(line2);
        instance.addLine(line3);

        assertEquals(1, instance.getLinesByType(LineType.BUS).size(), 
        "The number of lines should be 1");
    }

    @Test
    void findStopNotExistingTest() {
        assertEquals(null, instance.findStop("test", 0.0, 0.0), 
        "The stop should not be found");
    }

    @Test
    void findStopExistingTest() {
        Stop stop = new Stop("test", 0.0, 0.0);
        instance.addStop(stop);

        assertEquals(stop, instance.findStop("test", 0.0, 0.0), 
        "The stop should be found");
    }

    @Test
    void findSameStopNotExistingTest() {
        assertEquals(null, instance.findSameStop("test", 0.0, 0.0), 
        "The stop should not be found");
    }

    @Test
    void findSameStopExistingTest() {
        Stop stop = new Stop("test", 0.0, 0.0);
        instance.addStop(stop);

        assertEquals(stop, instance.findSameStop("test", 0.0, 0.0), 
        "The stop should be found");
    }

    @Test
    void findStopByNameNotExistingTest() {
        assertEquals(null, instance.findStopByName("test"), 
        "The stop should not be found");
    }

    @Test
    void findStopByNameExistingTest() {
        Stop stop = new Stop("test", 0.0, 0.0);
        instance.addStop(stop);

        assertEquals(stop, instance.findStopByName("test"), 
        "The stop should be found");
    }

    @Test
    void findStopFromGeoPosition() {
        Stop stop1 = new Stop("test1", 2.38705, 48.86326);
        Stop stop2 = new Stop("test2", 2.38701, 48.86327);
        Stop stop3 = new Stop("test3", 2.38700, 48.86339);
        instance.addStop(stop1);
        instance.addStop(stop2);
        instance.addStop(stop3);

        System.out.println(instance.getStops().size());

        assertEquals(3, instance.findStopFromGeoPosition(48.86300,2.38728, 0.2).size(), 
        "The number of stops should be 3");
    }

    @Test
    void findClosestStopByGeoPosition() {
        Stop stop1 = new Stop("test1", 2.38705, 48.86326);
        Stop stop2 = new Stop("test2", 2.38701, 48.86327);
        Stop stop3 = new Stop("test3", 2.38603, 48.86309);
        instance.addStop(stop1);
        instance.addStop(stop2);
        instance.addStop(stop3);

        assertEquals(stop3, instance.findClosestStopByGeoPosition(48.86320,2.38579), 
        "The closest stop should be stop3");
    }

    @Test
    void removeStop() {
        Stop stop = new Stop("test", 0.0, 0.0);
        instance.addStop(stop);

        instance.removeStop(stop);
        assertEquals(null, instance.findStop("test", 0.0, 0.0), 
        "The stop should not be found");
    }

    @Test
    void removeStopNull() {
        Stop stop = new Stop("test", 0.0, 0.0);
        instance.addStop(stop);

        instance.removeStop(null);
        assertEquals(1, instance.getStops().size(), 
        "The number of stops should be 1");
    }

    @Test
    void toStringTest() {
        assertEquals("Network [lines={}, stops={}]", instance.toString(), 
        "The string should be the same");
    }

    @Test
    void getLinesTest() {
        assertEquals(0, instance.getLines().size(), 
        "The number of lines should be 0");

        Line line1 = new MetroLine("test1", "red");
        Line line2 = new MetroLine("test2", "blue");
        instance.addLine(line1);
        instance.addLine(line2);

        assertEquals(2, instance.getLines().size(),
        "The number of lines should be 2");

        boolean exceptionThrown = false;
        try {
            instance.getLines().clear();
        } catch (UnsupportedOperationException e) {
            exceptionThrown = true;
        }

        if (!exceptionThrown) {
            fail("The list of paths should be unmodifiable");
        }
    }
}
