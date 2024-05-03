package fr.u_paris.gla.project.algo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.u_paris.gla.project.idfnetwork.Stop;
import fr.u_paris.gla.project.idfnetwork.TravelPath;
import fr.u_paris.gla.project.idfnetwork.line.Line;
import fr.u_paris.gla.project.idfnetwork.line.MetroLine;


class ItineraryCalculatorTest {
    @Test
    void isSameStopTest() {
        Stop s1 = new Stop("s1", 0, 0);
        Stop s2 = new Stop("s2", 1, 1);
        Stop s3 = new Stop("s3", 2, 2);

        assertTrue(ItineraryCalculator.isSameStop(s1, s1), "isSameStop should return true for the same stop");
        assertFalse(ItineraryCalculator.isSameStop(s1, s2), "isSameStop should return false for different stops");
        assertFalse(ItineraryCalculator.isSameStop(s1, s3), "isSameStop should return false for different stops");
    }

    @Test
    void isSameLineTest() {
        Stop s1 = new Stop("s1", 0, 0);
        Stop s2 = new Stop("s2", 1, 1);
        Stop s3 = new Stop("s3", 2, 2);

        Line l1 = new MetroLine("l1", "red");
        Line l2 = new MetroLine("l2", "blue");
 
        TravelPath p1 = new TravelPath(s1, s2, 0, 0, l1);
        TravelPath p2 = new TravelPath(s2, s3, 0, 0, l1);
        TravelPath p3 = new TravelPath(s3, s1, 0, 0, l2);

        assertTrue(ItineraryCalculator.isSameLine(p1, p2), "isSameLine should return true for the same line");
        assertFalse(ItineraryCalculator.isSameLine(p3, p2), "isSameLine should return false for different lines");
    }

    @Test
    void isStopNullTest() {
        Stop s1 = new Stop("s1", 0, 0);

        assertFalse(ItineraryCalculator.isStopNull(s1), "isStopNull should return false for a stop");

        assertTrue(ItineraryCalculator.isStopNull(null), "isStopNull should return true for a null stop");
    }
}
