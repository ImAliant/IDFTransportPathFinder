package fr.u_paris.gla.project.idfnetwork;

import fr.u_paris.gla.project.App;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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


    void testAddPaths(){

    }

    @BeforeAll
    static void setup(){
        App.initNetwork();
    }

    @Test
    void getScheduleOfLine(){
        //Return une liste de Horaire + Stop, faisant tous parti de la même ligne
        //m14.schedulesGenerator();
        List<Line> lines = Network.getInstance().getLines();
        Line line = lines.get(0);
//        for(Stop s: line.getStops()){
//            System.out.println(s);
//        }


        line.schedulesGenerator();
        //Map<String, ArrayList<Integer>> stop= line.horairesParStation;
        ArrayList<Integer> schedule = line.getHorairesPourStation(line.getStops().get(0).getStopName());
        System.out.println(schedule);
        /*for(int i = 0; i < stop.get(0).size();i++){
            System.out.println(stop.get(0).get(i));
        }*/
    }

}
