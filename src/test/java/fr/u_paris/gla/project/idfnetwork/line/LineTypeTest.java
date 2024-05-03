package fr.u_paris.gla.project.idfnetwork.line;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class LineTypeTest {
    @Test
    void fromStringTest() {
        assertEquals(LineType.BUS, LineType.fromString("Bus"),
                "LineType.fromString should return BUS for the string \"Bus\"");
        assertEquals(LineType.TRAMWAY, LineType.fromString("Tram"),
                "LineType.fromString should return TRAMWAY for the string \"Tram\"");
        assertEquals(LineType.RER, LineType.fromString("Rail"),
                "LineType.fromString should return RER for the string \"Rail\"");
        assertEquals(LineType.METRO, LineType.fromString("Subway"),
                "LineType.fromString should return METRO for the string \"Subway\"");
        assertEquals(LineType.FUNICULAIRE, LineType.fromString("Funicular"),
                "LineType.fromString should return FUNICULAIRE for the string \"Funicular\"");

        try {
            LineType.fromString("Unknown");
        } catch (IllegalArgumentException e) {
            assertEquals("Unknown line type: Unknown", e.getMessage());
        }
    }

    @Test
    void fromLineTest() {
        Line line = new Line("test", "red") {
            @Override
            public LineType getType() {
                return null;
            }
        };

        BusLine busLine = new BusLine("test", "red");
        TramwayLine tramwayLine = new TramwayLine("test", "red");
        RERLine rerLine = new RERLine("test", "red");
        MetroLine metroLine = new MetroLine("test", "red");
        FunicularLine funicularLine = new FunicularLine("test", "red");
        
        assertEquals(LineType.BUS, LineType.fromLine(busLine),
                "LineType.fromLine should return BUS for a BusLine object");
        assertEquals(LineType.TRAMWAY, LineType.fromLine(tramwayLine),
                "LineType.fromLine should return TRAMWAY for a TramwayLine object");
        assertEquals(LineType.RER, LineType.fromLine(rerLine),
                "LineType.fromLine should return RER for a RERLine object");
        assertEquals(LineType.METRO, LineType.fromLine(metroLine),
                "LineType.fromLine should return METRO for a MetroLine object");
        assertEquals(LineType.FUNICULAIRE, LineType.fromLine(funicularLine),
                "LineType.fromLine should return FUNICULAIRE for a FunicularLine object");

        try {
            LineType.fromLine(line);

            fail("LineType.fromLine should throw an IllegalArgumentException for an unknown line type");
        } catch (IllegalArgumentException e) {
            assertEquals("Unknown line type: fr.u_paris.gla.project.idfnetwork.line.LineTypeTest$1", e.getMessage());
        }
    }
}
