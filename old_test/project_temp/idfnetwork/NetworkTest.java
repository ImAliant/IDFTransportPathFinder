package fr.u_paris.gla.project_temp.idfnetwork;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import fr.u_paris.gla.project.idfnetwork.line.BusLine;
import fr.u_paris.gla.project.idfnetwork.line.FunicularLine;
import fr.u_paris.gla.project.idfnetwork.line.Line;
import fr.u_paris.gla.project.idfnetwork.line.LineType;
import fr.u_paris.gla.project.idfnetwork.line.MetroLine;
import fr.u_paris.gla.project.idfnetwork.line.RERLine;
import fr.u_paris.gla.project.idfnetwork.network.Network;
import fr.u_paris.gla.project_temp.idfnetwork.stop.Stop;

import static org.junit.jupiter.api.Assertions.*;
class NetworkTest {

    static Network networkTest = Network.getInstance();

    @BeforeAll
    static void clearNetwork(){
        networkTest.clear();
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