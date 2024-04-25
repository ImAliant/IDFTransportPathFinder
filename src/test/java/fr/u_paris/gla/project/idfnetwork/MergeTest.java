package fr.u_paris.gla.project.idfnetwork;

import fr.u_paris.gla.project.App;
import fr.u_paris.gla.project.utils.GPS;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MergeTest {
    @Test
    public void findSameStopTest(){

        /* La méthode findSameStop cherche un arrêt ayant le même nom que celui entré en argument
        et renvoie cet arrêt s'il se trouve à moins de 0,5 km sinon elle renvoie null.*/

        Stop a = new Stop("a", 1, 0);
        Network.addStop(a);

        // Math.sqrt(Math.pow(2 - 0, 2) + Math.pow(2 - 1, 2)) == 2.23;
        assertNull(Network.findSameStop("a", 2, 2));
        double latitude = 0.34;
        double longitude = 1.34;
        // Math.sqrt(Math.pow(0.34 - 0, 2) + Math.pow(1.34 - 1, 2)) == 0,48;
        assertNotNull(Network.findSameStop("a",longitude , latitude));
        assertNull(Network.findSameStop("b",longitude , latitude));

//        Stop stop = Network.findSameStop("a",longitude , latitude);
//        double distance = Math.sqrt(Math.pow(latitude - stop.getLatitude(), 2) + Math.pow(longitude- stop.getLongitude(), 2));
//        double dist = GPS.distance(latitude,longitude,stop.getLatitude(),stop.getLongitude());
//        System.out.println(stop);
//        System.out.println("Distance gps:" + dist);
//        System.out.println("Distance math:" + distance);
    }
}
