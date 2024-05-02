package fr.u_paris.gla.project.idfnetwork;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.u_paris.gla.project.idfnetwork.line.Line;
import fr.u_paris.gla.project.idfnetwork.line.LineType;
import fr.u_paris.gla.project.idfnetwork.line.MetroLine;
import fr.u_paris.gla.project.idfnetwork.line.RERLine;

class StopTest {
    @Test
    void addPathTest() {
        Stop stop1 = new Stop("stop1", 0, 0);
        Stop stop2 = new Stop("stop2", 0, 0);

        Line line = new MetroLine("line1", "red");
        TravelPath path = new TravelPath(stop1, stop2, 0, 0, line);

        boolean test1 = stop1.addPath(stop2, path);

        assertEquals(1, stop1.getPaths().size(), 
            "The number of paths of stop1 should be 1");
        assertEquals(true, test1, 
            "The path should be added to stop1");

        boolean test2 = stop1.addPath(stop2, path);

        assertEquals(1, stop1.getPaths().size(), 
            "The number of paths of stop1 should be 1");
        assertEquals(false, test2,
            "The path should not be added to stop1");
    }

    @Test
    void removeLineTest() {
        Stop stop1 = new Stop("stop1", 0, 0);
        Line line = new MetroLine("line1", "red");
        Line line2 = new MetroLine("line2", "blue");

        stop1.addLine(line);

        boolean test1 = stop1.removeLine(line);
        assertEquals(true, test1,
            "The line should be removed from stop1");
        boolean test2 = stop1.removeLine(line2);
        assertEquals(false, test2,
            "The line should not be removed from stop1");
    }

    @Test
    void hashcodeTest() {
        Stop stop1 = new Stop("stop1", 0, 0);
       
        assertEquals(stop1.hashCode(), stop1.hashCode(),
            "The hashcode of stop1 should be the same");
    }

    @Test
    void getLineTypeTest() {
        Stop stop1 = new Stop("stop1", 0, 0);
        Line line = new MetroLine("line1", "red");

        stop1.addLine(line);

        assertEquals(LineType.METRO, stop1.getLineType(),
            "The line type of stop1 should be red");

        RERLine line2 = new RERLine("line2", "blue");
        stop1.addLine(line2);

        assertEquals(LineType.RER, stop1.getLineType(),
            "The line type of stop1 should be blue");
    }

    @Test
    void toStringTest() {
        Stop stop1 = new Stop("stop1", 0, 0);
        Line line = new MetroLine("line1", "red");

        stop1.addLine(line);

        assertEquals("<html>stop1<br>Ligne(s): <br>M line1<br></html>", stop1.toString(),
            "The string representation of stop1 should be 'stop1 [0.0, 0.0]\nLigne(s): \nM line1\n'");
    }
}
