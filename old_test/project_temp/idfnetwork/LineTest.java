package fr.u_paris.gla.project_temp.idfnetwork;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.u_paris.gla.project.idfnetwork.line.BusLine;
import fr.u_paris.gla.project.idfnetwork.line.FunicularLine;
import fr.u_paris.gla.project.idfnetwork.line.Line;
import fr.u_paris.gla.project.idfnetwork.line.LineType;
import fr.u_paris.gla.project.idfnetwork.line.MetroLine;
import fr.u_paris.gla.project.idfnetwork.line.RERLine;
import fr.u_paris.gla.project_temp.idfnetwork.stop.Stop;

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


}
