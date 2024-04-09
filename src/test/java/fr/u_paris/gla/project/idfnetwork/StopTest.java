package fr.u_paris.gla.project.idfnetwork;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class StopTest {

    @Test
    void createStop(){
        Stop s= new Stop("Gare du Nord",0.0,0.0);
        assertEquals("Gare du Nord",s.getStopName());
    }

    private static Stop s1 = null,s2 = null;
    @BeforeEach
    void initialiseStops(){
        s1= new Stop("Gare du Nord",0.0,0.0);
        s2= new Stop("Chatelet les Halles", 200.0,-200.0);
    }

    @Test
    void compare2IdenticalStop(){
        Stop s2= new Stop("Gare du Nord",0.0,0.0);
        assertEquals(s2,s1);
    }

    /* @Test
    void testAddPath(){
        assertTrue(s1.addPath(s2,100,100));
    }

    @Test
    void testAddPaths(){
        Stop s3= new Stop("Stade De France",100.0,100.0);
        assertTrue(s1.addPath(s2,100,100));
        assertTrue(s1.addPath(s3,100,100));
    }

    @Test
    void addPath2TimeSameStop(){
        assertTrue(s1.addPath(s2,100,100),"1er addPath() avec Chatelet à 100 100");
        assertFalse(s1.addPath(s2,100,100),"2nd addPath() avec Chatelet à 100 100");
        assertFalse(s1.addPath(s2,120,500), "3eme addPath() avec Chatelet à 150 500");
    }

    @Test
    void addPathHimself(){
        assertTrue(s1.addPath(s1,0,0));
    } */

    @Test
    void testAddLine(){
        Line m5 = new MetroLine("Ligne 5","Orange");
        assertTrue(s1.addLine(m5));
    }

    @Test
    void testAddLines(){
        Line m5 = new MetroLine("Ligne 5","Orange");
        assertTrue(s1.addLine(m5));
        Line m4 = new MetroLine("Ligne 4","Violet");
        assertTrue(s1.addLine(m4));
        Line rd = new RERLine("RER D","Vert");
        assertTrue(s1.addLine(rd));
        Line b71 = new BusLine("71", "Bleu");
        assertTrue(s1.addLine(b71));
    }

    @Test
    void add2TimeSameLine(){
        Line m5 = new MetroLine("Ligne 5","Orange");
        assertTrue(s1.addLine(m5));
        assertFalse(s1.addLine(m5));
    }


}
