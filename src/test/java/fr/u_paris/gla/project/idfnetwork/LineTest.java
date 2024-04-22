package fr.u_paris.gla.project.idfnetwork;

import fr.u_paris.gla.project.App;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LineTest {
    @Test
    void createMetroLine(){
        Line l = new MetroLine("Ligne 14","Violet");
        assertEquals("Ligne 14",l.getLineName());
        assertEquals("Violet",l.getColor());
        assertEquals(LineType.METRO,l.getType());
    }

    @Test
    void createBusLine(){
        Line l = new BusLine("237","Violet");
        assertEquals("237",l.getLineName());
        assertEquals("Violet",l.getColor());
        assertEquals(LineType.BUS,l.getType());
    }

    @Test
    void createRERLine(){
        Line l = new RERLine("RER A","Rouge");
        assertEquals("RER A",l.getLineName());
        assertEquals("Rouge",l.getColor());
        assertEquals(LineType.RER,l.getType());
    }

    @Test
    void createFunicularLine(){
        Line l = new FunicularLine("Test","Vert");
        assertEquals("Test",l.getLineName());
        assertEquals("Vert",l.getColor());
        assertEquals(LineType.FUNICULAIRE,l.getType());
    }

    private static MetroLine m14 = null;

    @BeforeEach
    void initialiseLine(){
        m14 = new MetroLine("Ligne 14","Violet");

    }
    @Test
    void compare2IdenticalMetroLines(){
        Line l2 = new MetroLine("Ligne 14","Violet");
        assertEquals(l2,m14);
    }

    @Test
    void compare2DifferentMetroLines(){
        Line l2 = new MetroLine("Ligne 13","Bleu");
        assertNotEquals(l2,m14);
    }

    @Test
    void compare2DifferentTypeOfLines(){
        Line l2 = new BusLine("237","Violet");
        assertNotEquals(l2,m14);
    }

    @Test
    void testAddStop(){
        Stop s = new Stop("Madeleine",0.0,0.0);
        m14.addStop(s);
        m14.addStop(s);
        assertEquals(s,m14.findStop("Madeleine"));
        assertEquals(1,m14.getStops().size());
    }


    @Test
    void testAddPath(){
        Stop s1 = new Stop("Chatelet",0.0,0.0);
        Stop s2 = new Stop("Gare de Lyon",0.0,100.0);
        m14.addPath(s1,s2,100,5);
        assertEquals(1,m14.getPaths().size());
    }

    @BeforeAll
    static void setup(){
        App.initNetwork();
    }

    @Test
    void getTravelPath(){
        List<Line> lines = Network.getInstance().getLines();
        Line line = lines.get(0);

        List<TravelPath> path =line.paths;
        for(TravelPath p : path){
            System.out.println("Start :"+p.getStart().getStopName()+
                    " End : " + p.getEnd().getStopName()+
                    " Duration : " + p.getDuration());
        }
    }

//    @Test
//    public void testPrintStops(){
//        List<Line> lines = Network.getInstance().getLines();
//        Line line = lines.get(0);
//
//        Set<String> stations=line.stops.keySet();
//        for(String station: stations){
//            System.out.println(station);
//        }
//    }

    @Test
    void getScheduleOfLine(){
        //Return une liste de Horaire + Stop, faisant tous parti de la même ligne
        List<Line> lines = Network.getInstance().getLines();
        Line line = lines.get(0);

        assertTrue(line.getSchedule().isEmpty());
        line.schedulesGenerator();
        assertFalse(line.getSchedule().isEmpty());
        String stationName = line.getStops().get(0).getStopName();
        ArrayList<LocalTime> schedule = line.getScheduleForAStop(stationName);
        System.out.println("Schedule for \"" +stationName+"\":");

        for (LocalTime horaire : schedule) {
            System.out.println(horaire);
        }
    }


}
