package fr.u_paris.gla.project.idfnetwork.line;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import fr.u_paris.gla.project.idfnetwork.Stop;

class LineTest {
    @Test
    void addStopTest() {
        Stop stop = new Stop("stop1", 0.0, 0.0);
        Line line = new MetroLine("test", "red");
        line.addStop(stop);
        
        assertEquals(1, line.stops.size(), "The stop should be added to the line");

        line.addStop(stop);
        assertEquals(1, line.stops.size(), "The stop should not be added twice to the line");
    }

    @Test
    void addPathTest() {
        Stop stop1 = new Stop("stop1", 0.0, 0.0);
        Stop stop2 = new Stop("stop2", 0.0, 0.0);
        Line line = new MetroLine("test", "red");
        line.addStop(stop1);
        line.addStop(stop2);
        line.addPath(stop1, stop2, 10.0, 1);

        assertEquals(1, line.paths.size(), "The path should be added to the line");

        line.addPath(stop1, stop2, 10.0, 1);
        assertEquals(1, line.paths.size(), "The path should not be added twice to the line");
    }

    @Test
    void removePathsTest() {
        Stop stop1 = new Stop("stop1", 0.0, 0.0);
        Stop stop2 = new Stop("stop2", 0.0, 0.0);
        Line line = new MetroLine("test", "red");
        line.addStop(stop1);
        line.addStop(stop2);
        line.addPath(stop1, stop2, 10.0, 1);

        assertEquals(1, line.paths.size(), "The path should be added to the line");

        line.removePaths();
        assertEquals(0, line.paths.size(), "The path should be removed from the line");
    }

    @Test
    void findStopTest() {
        Stop stop1 = new Stop("stop1", 0.0, 0.0);
        Stop stop2 = new Stop("stop2", 0.0, 0.0);
        Line line = new MetroLine("test", "red");
        line.addStop(stop1);
        line.addStop(stop2);

        assertEquals(stop1, line.findStop("stop1"), "The stop should be found");
        assertEquals(stop2, line.findStop("stop2"), "The stop should be found");
        assertEquals(null, line.findStop("stop3"), "The stop should not be found");
    }

    @Test
    void pathsToStringTest() {
        Stop stop1 = new Stop("stop1", 0.0, 0.0);
        Stop stop2 = new Stop("stop2", 0.0, 0.0);
        Line line = new MetroLine("test", "red");
        line.addStop(stop1);
        line.addStop(stop2);
        line.addPath(stop1, stop2, 10.0, 1);

        assertEquals("stop1 -> stop2\n", line.pathsToString(), "The paths should be correctly formatted");
    } 

    @Test
    void getStopsTest() {
        Stop stop1 = new Stop("stop1", 0.0, 0.0);
        Stop stop2 = new Stop("stop2", 0.0, 0.0);
        Line line = new MetroLine("test", "red");
        line.addStop(stop1);
        line.addStop(stop2);

        assertEquals(2, line.getStops().size(), "The stops should be returned");


        boolean exceptionThrown = false;
        try {
            line.getStops().clear();
        } catch (UnsupportedOperationException e) {
            exceptionThrown = true;
        }

        if (!exceptionThrown) {
            fail("The list of stops should be unmodifiable");
        }
    }

    @Test
    void getPathsTest() {
        Stop stop1 = new Stop("stop1", 0.0, 0.0);
        Stop stop2 = new Stop("stop2", 0.0, 0.0);
        Line line = new MetroLine("test", "red");
        line.addStop(stop1);
        line.addStop(stop2);
        line.addPath(stop1, stop2, 10.0, 1);

        assertEquals(1, line.getPaths().size(), "The paths should be returned");

        boolean exceptionThrown = false;
        try {
            line.getPaths().clear();
        } catch (UnsupportedOperationException e) {
            exceptionThrown = true;
        }

        if (!exceptionThrown) {
            fail("The list of paths should be unmodifiable");
        }
    }

    @Test
    void correspondances_of_lineTest() {
        Line test = new MetroLine("test", "red");
        Stop stop1 = new Stop("stop1", 0.0, 0.0);
        stop1.addLine(test);

        Line line = new MetroLine("line", "blue");
        line.addStop(stop1);

        line.correspondances_of_line();

        assertEquals(1, line.correspondances.size(), 
        "The correspondance should be added to the line");
    }
}
