package fr.u_paris.gla.project.algo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.u_paris.gla.project.idfnetwork.Stop;
import fr.u_paris.gla.project.idfnetwork.TravelPath;
import fr.u_paris.gla.project.idfnetwork.line.Line;
import fr.u_paris.gla.project.idfnetwork.line.MetroLine;
import fr.u_paris.gla.project.idfnetwork.network.Network;

class ItineraryCalculatorTest {
    private static Network network = Network.getInstance();

    @BeforeAll
    static void setUp() {
        network.clear();
    }

    @BeforeEach
    void init() {
        network.clear();
    }

    @AfterAll
    static void tearDown() {
        network.clear();
    }

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
    void isStopNullTest() {
        Stop s1 = new Stop("s1", 0, 0);

        assertFalse(ItineraryCalculator.isStopNull(s1), "isStopNull should return false for a stop");

        assertTrue(ItineraryCalculator.isStopNull(null), "isStopNull should return true for a null stop");
    }

    @Test
    void initializeStopDistancesTest() {
        Map<Stop, Double> duration = new HashMap<>();
        Map<Stop, Stop> previousStops = new HashMap<>();
        PriorityQueue<Stop> queue = new PriorityQueue<>(Comparator.comparingDouble(duration::get));

        Stop start = new Stop("start", 0, 0);
        Stop stop1 = new Stop("stop1", 1, 1);
        Stop stop2 = new Stop("stop2", 2, 2);
        Stop stop3 = new Stop("stop3", 3, 3);

        network.addStop(start);
        network.addStop(stop1);
        network.addStop(stop2);
        network.addStop(stop3);

        ItineraryCalculator.initializeStopDistances(start, duration, previousStops, queue);

        assertEquals(0.0, duration.get(start), "The start stop should have a distance of 0");
        assertNull(previousStops.get(start), "The start stop should have no previous stop");
        
        for (Stop stop : network.getStops()) {
            if (stop != start) {
                assertEquals(Double.MAX_VALUE, duration.get(stop), 0.001);
            }
        }

        assertEquals(1, queue.size(), "The queue should contain the start stop");
    }

    @Test
    void calculateTotalDistanceAndDurationTest() {
        Stop s1 = new Stop("s1", 0, 0);
        Stop s2 = new Stop("s2", 1, 1);
        Stop s3 = new Stop("s3", 2, 2);

        Line l1 = new MetroLine("l1", "red");

        TravelPath p1 = new TravelPath(s1, s2, 10, 1, l1);
        TravelPath p2 = new TravelPath(s1, s3, 20, 2, l1);
        s1.addPath(s2, p1);
        s1.addPath(s3, p2);

        ItineraryCalculator.calculateTotalDistanceAndDuration(s1, s2);

        assertEquals(10, ItineraryCalculator.getTotalDistance(), 0.001);
        assertEquals(1, ItineraryCalculator.getTotalDuration(), 0.001);
    }
}
