package fr.u_paris.gla.project.idfnetwork;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TravelPathTest {

    private static Stop a,b;
    @BeforeAll
    static void setup(){
        a = new Stop("a",0.0,0.0);
        b= new Stop("b",100.0,0.0);
    }

    @Test
    void createTravelPath(){
        TravelPath tp =new TravelPath(a,b,100,2);
        assertEquals(a,tp.getStart());
        assertEquals(b,tp.getEnd());

    }
}
