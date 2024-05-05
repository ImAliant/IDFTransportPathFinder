package fr.u_paris.gla.crazytrip.algo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import fr.u_paris.gla.crazytrip.idfnetwork.Stop;
import fr.u_paris.gla.crazytrip.idfnetwork.line.Line;
import fr.u_paris.gla.crazytrip.idfnetwork.line.MetroLine;

class ItineraryTest {
    private static Itinerary itinerary;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        Stop s1 = new Stop("s1", 0, 0);
        Stop s2 = new Stop("s2", 1, 1);
        Stop s3 = new Stop("s3", 2, 2);

        Line l1 = new MetroLine("l1", "red");
        Line l2 = new MetroLine("l2", "blue");
        Line l3 = new MetroLine("l3", "green");

        double distance = 1.0;
        double duration = 1.0;

        List<Stop> stops = List.of(s1, s2, s3);
        List<Line> lines = List.of(l1, l2, l3);

        itinerary = new Itinerary(stops, lines, distance, duration);
    }

    @Test
    void toStringTest() {
        StringBuilder expected = new StringBuilder();
        expected.append("Route:\n");
        expected.append("Prendre ")
            .append("M l1")
            .append(" à l'arrêt ")
            .append("s1")
            .append("\n")
            .append("Prendre ")
            .append("M l2")
            .append(" à l'arrêt ")
            .append("s2")
            .append("\n")
            .append("Arrivé à destination: ")
            .append("s3")
            .append("\n");
        expected.append("Distance totale: ").append(1.0).append(" km\n");
        expected.append("Durée totale: ").append(1.0).append(" minutes\n");

        assertEquals(expected.toString(), itinerary.toString(),
            "Itinerary toString method does not return the expected string");
    }

    @Test
    void equalsSameObjectTest() {
        assertEquals(itinerary, itinerary, "Itinerary equals method does not return true for the same object");
    }

    @Test
    void equalsDifferentObjectTest() {
        Itinerary other = new Itinerary(itinerary.getStops(), itinerary.getLines(), itinerary.getTotalDistance(), itinerary.getTotalDuration());
        assertEquals(itinerary, other, "Itinerary equals method does not return true for equivalent objects");
    }

    @Test
    void equalsDifferentTypeTest() {
        Object other = new Object();
        assertEquals(false, itinerary.equals(other), "Itinerary equals method does not return false for different types");
    }

    @Test
    void hashCodeTest() {
        Itinerary other = new Itinerary(itinerary.getStops(), itinerary.getLines(), itinerary.getTotalDistance(), itinerary.getTotalDuration());
        assertEquals(itinerary.hashCode(), other.hashCode(), "Itinerary hashCode method does not return the same value for equivalent objects");
    }
}
