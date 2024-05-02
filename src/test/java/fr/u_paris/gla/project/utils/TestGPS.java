package fr.u_paris.gla.project.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestGPS {

    @Test 
    public void testDistance() {
        assertEquals(0.0, GPS.distance(0, 0, 0, 0));
        //golfe de guinnee altitude 0
        //golfe de guinnee longitude 0
        //Latitude : 48.826985 | Longitude : 2.380995 Grands Moulin de Paris Cite Universite
        // erreur autorisee 

        double reelValue = 5337.356;
        double erreurAutorisee = 0.001;
        assertTrue(Math.abs(reelValue - GPS.distance(48, 2, 0,0))/reelValue<=erreurAutorisee);
    }
    
}
