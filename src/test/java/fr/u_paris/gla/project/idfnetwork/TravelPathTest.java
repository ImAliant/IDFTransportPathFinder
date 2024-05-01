package fr.u_paris.gla.project.idfnetwork;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.u_paris.gla.project.idfnetwork.line.Line;
import fr.u_paris.gla.project.idfnetwork.line.MetroLine;

class TravelPathTest {
    @Test
    void constructorTest() {
        Stop stop1 = new Stop("stop1", 1.0, 1.0);
        Stop stop2 = new Stop("stop2", 2.0, 2.0);
        Line line = new MetroLine("line1", "red");

        TravelPath travelPath = new TravelPath(stop1, stop2, 0, 0, line);
        assertEquals(stop1, travelPath.getStart(), 
            "The first stop should be stop1");

        assertEquals(stop2, travelPath.getEnd(),
            "The last stop should be stop2");
        
        assertEquals(0, travelPath.getDuration(),
            "The duration should be 0");

        assertEquals(0, travelPath.getDistance(),
            "The distance should be 0");

        assertEquals(line, travelPath.getLine(),
            "The line should be line1");
    }
}
