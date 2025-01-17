package fr.u_paris.gla.crazytrip.idfnetwork.line.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import fr.u_paris.gla.crazytrip.idfnetwork.line.BusLine;
import fr.u_paris.gla.crazytrip.idfnetwork.line.FunicularLine;
import fr.u_paris.gla.crazytrip.idfnetwork.line.Line;
import fr.u_paris.gla.crazytrip.idfnetwork.line.LineType;
import fr.u_paris.gla.crazytrip.idfnetwork.line.MetroLine;
import fr.u_paris.gla.crazytrip.idfnetwork.line.RERLine;
import fr.u_paris.gla.crazytrip.idfnetwork.line.TramwayLine;

class LineFactoryTest {
    @Test
    void createBusLineTest() {
        Line line = LineFactory.createLine(LineType.BUS, "test", "red");
        assertNotNull(line, "Line should not be null");
        assertEquals("test", line.getLineName(), "Line name should be 'test'");
        assertEquals("red", line.getColor(), "Line color should be 'red'");
        if (!(line instanceof BusLine)) {
            throw new AssertionError("Line should be an instance of BusLine");
        }
    }

    @Test
    void createTramwayLineTest() {
        Line line = LineFactory.createLine(LineType.TRAMWAY, "test", "blue");
        assertNotNull(line, "Line should not be null");
        assertEquals("test", line.getLineName(), "Line name should be 'test'");
        assertEquals("blue", line.getColor(), "Line color should be 'red'");
        if (!(line instanceof TramwayLine)) {
            throw new AssertionError("Line should be an instance of BusLine");
        }
    }

    @Test
    void createRERLineTest() {
        Line line = LineFactory.createLine(LineType.RER, "test", "green");
        assertNotNull(line, "Line should not be null");
        assertEquals("test", line.getLineName(), "Line name should be 'test'");
        assertEquals("green", line.getColor(), "Line color should be 'red'");
        if (!(line instanceof RERLine)) {
            throw new AssertionError("Line should be an instance of BusLine");
        }
    }

    @Test
    void createMetroLineTest() {
        Line line = LineFactory.createLine(LineType.METRO, "test", "yellow");
        assertNotNull(line, "Line should not be null");
        assertEquals("test", line.getLineName(), "Line name should be 'test'");
        assertEquals("yellow", line.getColor(), "Line color should be 'red'");
        if (!(line instanceof MetroLine)) {
            throw new AssertionError("Line should be an instance of BusLine");
        }
    }

    @Test
    void createFunicularLineTest() {
        Line line = LineFactory.createLine(LineType.FUNICULAIRE, "test", "purple");
        assertNotNull(line, "Line should not be null");
        assertEquals("test", line.getLineName(), "Line name should be 'test'");
        assertEquals("purple", line.getColor(), "Line color should be 'red'");
        if (!(line instanceof FunicularLine)) {
            throw new AssertionError("Line should be an instance of BusLine");
        }
    }
}
