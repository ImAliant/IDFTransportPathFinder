package fr.u_paris.gla.project.idfnetwork;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class NetworkTest {

    static Network networkTest = null;

    @BeforeAll
    static void setup(){
        networkTest = Network.getInstance();
    }

    @Test
    void testAddLine(){
        Line m14 = new MetroLine("Ligne 14","Violet");
        networkTest.addLine(m14);
        assertEquals(m14,networkTest.findLine("Ligne 14",LineType.METRO));
    }

    @Test
    void testAddLines(){
        Line b237 = new BusLine("237","Violet");
        Line m14 = new MetroLine("Ligne 14","Violet");
        Line rerA = new RERLine("RER A","Rouge");
        Line funi = new FunicularLine("Test","Vert");
        networkTest.addLine(b237);
        networkTest.addLine(m14);
        networkTest.addLine(rerA);
        networkTest.addLine(funi);
        assertEquals(4,networkTest.getLines().size());
    }

    @Test
    void testAddStop(){
        Stop s1= new Stop("Gare du Nord",0.0,0.0);
        networkTest.addStop(s1);
        assertEquals(s1,networkTest.findStop("Gare du Nord",0.0,0.0));
        assertNull(networkTest.findStop("Gare du Nord",10.0,0.0));
    }

    @Test
    void testAddStops(){
        Stop s1= new Stop("Gare du Nord",0.0,0.0);
        Stop s2= new Stop("Chatelet les Halles", 200.0,-200.0);
        Stop s3= new Stop("Stade De France",100.0,100.0);
        networkTest.addStop(s1);
        networkTest.addStop(s2);
        networkTest.addStop(s3);
        assertEquals(3,networkTest.getStops().size());
    }


}
