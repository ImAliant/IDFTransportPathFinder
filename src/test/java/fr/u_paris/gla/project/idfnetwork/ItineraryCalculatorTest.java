package fr.u_paris.gla.project.idfnetwork;

import fr.u_paris.gla.project.App;
import fr.u_paris.gla.project.idfnetwork.stop.Stop;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ItineraryCalculatorTest {
    private Network network = Network.getInstance();

    /*
    * Ancien tests
    *
    * Prendre deux stops du network et applique ItineraryCalculator.CalculateRoad sur les stops
    *
    * A REFAIRE
    *
    * Se baser sur le code dans SimpleGraphTest pour créer un Network fictif
    *
    *
    * */

    @Test
    public void getStopsFromAdressTest(){
        List<Stop> s= ItineraryCalculator.getStopsFromAdress(48.8483463,2.3573528);
        assertFalse(s.isEmpty());
        /*System.out.println(s.size());
        for (Stop stop : s) {
            System.out.println(stop);
        }*/
    }
}
